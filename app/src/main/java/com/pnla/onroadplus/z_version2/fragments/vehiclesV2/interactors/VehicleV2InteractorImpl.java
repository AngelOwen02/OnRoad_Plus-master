package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.activities.online.utils.ActivityOnLineUtils;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.VehiclesAndGroupsServices;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.VehiclesV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.VehiclesV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2Request;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2Response;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupsV2Data;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2Request;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2Response;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehiclesV2Data;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.utils.VehiclesV2Utils;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmGroup;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmVehicle;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VehicleV2InteractorImpl implements VehiclesV2Interactor {

    private VehiclesV2Presenter presenter;
    private VehiclesAndGroupsServices services;
    private Retrofit retrofitClient;

    public VehicleV2InteractorImpl(VehiclesV2Presenter presenter) {
        this.presenter = presenter;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(VehiclesAndGroupsServices.class);
    }

    @Override
    public void getVehiclesAnGroups(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        startVehiclesRequest(token, context);
    }

    private void startVehiclesRequest(String token, final Context context) {
        List<Integer> vehiclesCves = new ArrayList<>();
        vehiclesCves.add(0);
        VehicleV2Request request = new VehicleV2Request(token, GeneralConstantsV2.REQUEST_ALL_VEHICLES, vehiclesCves);
        services.getFullVehicles(request).enqueue(new Callback<VehicleV2Response>() {
            @Override
            public void onResponse(Call<VehicleV2Response> call, Response<VehicleV2Response> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<VehicleV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateCode(Response<VehicleV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehiclesData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getVehiclesData(Response<VehicleV2Response> response, Context context) {
        VehicleV2Response vehicleV2Response = response.body();
        if (vehicleV2Response != null) {
            int responseCode = vehicleV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                VehiclesV2Data data = vehicleV2Response.getData();
                if (data != null) {
                    List<VehicleV2> vehicleV2List = data.getVehicles();
                    if (vehicleV2List != null && vehicleV2List.size() > 0) {
                        /**
                         *  Seteamos la cadena "NO_IMAGE" en caso de que no tenga la imagen algun objeto vehiculo
                         *  asi como tambien un valor en el atributo "selected" y una posición por default
                         *  para trabajar posteriormente con el filter.
                         */
                        vehicleV2List = VehiclesV2Utils.setUserSelectedPositionAndImageToEveryVehicle(vehicleV2List, context);

                        /**
                         *  Traemos unidades de la base de datos para saber cuales estan seleccionadas, si no tiene
                         *  unidades almacenadas en la db entonces mandamos la lista anterior, en caso contrario
                         *  buscamos las unidades seleccionadas para que se pinten en el recyclerView
                         */
                        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                        String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
                        List<VehicleV2> realmVehicles = RealmVehicle.getAllVehiclesByUserName(userName);
                        Log.e("REALM_VAL", "EN LA BASE DE DATOS --> " + realmVehicles.size());
                        if (realmVehicles != null && realmVehicles.size() > 0) {
                            /**
                             *  Encontramos las unidades que estan seleccionadas en la db con la lista
                             *  que llego en el webService, para que se muestren los vehiculos seleccionados
                             */
                            vehicleV2List = VehiclesV2Utils.getSelectedVehicles(vehicleV2List, realmVehicles);
                            presenter.setVehicleList(vehicleV2List);
                        } else {
                            presenter.setVehicleList(vehicleV2List);
                        }
                        getGroups(context);
                    } else {
                        presenter.setMessageToView("No se encontraron vehículos");
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView(vehicleV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyResponse));
        }
    }

    private void getGroups(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        startGroupsRequest(token, context);
    }

    private void startGroupsRequest(String token, final Context context) {
        GroupV2Request request = new GroupV2Request(token);
        presenter.showLoaderFromInteractor();
        services.getGroups(request).enqueue(new Callback<GroupV2Response>() {
            @Override
            public void onResponse(Call<GroupV2Response> call, Response<GroupV2Response> response) {
                validateGroupsCode(response, context);
            }

            @Override
            public void onFailure(Call<GroupV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateGroupsCode(Response<GroupV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getGroupsData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getGroupsData(Response<GroupV2Response> response, Context context) {
        GroupV2Response groupV2Response = response.body();
        if (groupV2Response != null) {
            int responseCode = groupV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                GroupsV2Data data = groupV2Response.getData();
                if (data != null) {
                    RealmList<GroupV2> groups = data.getGroups();
                    if (groups != null && groups.size() > 0) {
                        List<GroupV2> myGroups=new ArrayList<>();
                        myGroups=ActivityOnLineUtils.getGroupsList(groups);
                        /**
                         *  Seteamos false en el atributo "selected" y una posición por default
                         *  para trabajar posteriormente con el filter.
                         */
                        myGroups = VehiclesV2Utils.setUserSelectedPositionToEveryGroup(myGroups, context);

                        /**
                         *  Traemos los grupos de la base de datos para saber cuales estan seleccionados, si no tiene
                         *  grupos almacenados en la db entonces mandamos la lista anterior, en caso contrario
                         *  buscamos los grupos seleccionados para que se pinten en el recyclerView
                         */
                        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                        String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
                        List<GroupV2> realmGroups = RealmGroup.getAllGroupsByUser(userName);
                        if (realmGroups != null && realmGroups.size() > 0) {
                            /**
                             *  Encontramos las unidades que estan seleccionadas en la db con la lista
                             *  que llego en el webService, para que se muestren los vehiculos seleccionados
                             */
                            myGroups = VehiclesV2Utils.getSelectedGroups(myGroups, realmGroups);
                            presenter.setGroupsList(myGroups);
                        } else {
                            presenter.setGroupsList(myGroups);
                        }
                    } else {
                        presenter.setMessageToView("No se encotraron grupos");
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView(groupV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyResponse));
        }
    }


}