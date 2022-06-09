package com.pnla.onroadplus.z_version2.fragments.notifications_v2.interactors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.interfaces.NotificationsV2Services;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationV2OnRoad;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationsV2AlertsList;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationsV2Data;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationsV2Request;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationsV2Response;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.utils.NotificationsV2Utils;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2Request;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2Response;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehiclesV2Data;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.utils.VehiclesV2Utils;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserNotifications;
import com.pnla.onroadplus.z_version2.realmOnRoad.UserNotificationRealmSave;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationsV2InteractorImpl implements NotificationsV2Interactor {

    private NotificationsV2Presenter presenter;
    private NotificationsV2Services services;
    private Retrofit retrofitClient;
private int listiterator;
private int notifications;
    private NotificationsUpdate notificationsUpdate;

    public NotificationsV2InteractorImpl(NotificationsV2Presenter presenter) {
        this.presenter = presenter;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(NotificationsV2Services.class);
    }

    @Override
    public void getMainDate() {
        presenter.setMainDate(NotificationsV2Utils.getCurrentDate());
    }

    @Override
    public void getAllVehicles(Context context) {
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
                Toast.makeText(context,  "La session ha expirado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCode(Response<VehicleV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehiclesData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
            Toast.makeText(context,  "sesion expirada", Toast.LENGTH_LONG).show();
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
                        presenter.setVehiclesToView(vehicleV2List);
                        listiterator=vehicleV2List.size();
                        //  Toast.makeText(context,  Integer.toString(vehicleV2List.size()), Toast.LENGTH_SHORT).show();
                    } else {
                        presenter.setMessageToView("No se encontraron vehículos");
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
               // presenter.sessionExpired(context.getString(R.string.textSessionExpired));
                UnitDB.deleteDB();
                GroupDB.deleteDB();
                RealmUserData.deleteDB();

                Bundle bndl = new Bundle();
                bndl.putBoolean("HelpStatus", true);

                Intent intent = new Intent(context, LoginContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bndl);
                context.startActivity(intent);

                Toast.makeText(context, "La session ha expirado", Toast.LENGTH_SHORT).show();
            } else {
                presenter.setMessageToView(vehicleV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyResponse));
        }
    }

    @Override
    public void cancelRequest() {
        //para ya no realizar request de vehiculos son los siguientes 2 if
        if (notificationsUpdate != null) {
            notificationsUpdate.calcenRequest();
            notificationsUpdate.cancel(true);
            notificationsUpdate = null;
        }
    }

    @Override
    public void getAllNotification(List<VehicleV2> vehicles, String date, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        List<Integer> vehicleCves = NotificationsV2Utils.getCveVehicle(vehicles);
        startNotificationRequest(token, date, vehicleCves, context);
    }

    private void startNotificationRequest(String token, final String date, final List<Integer> vehicleCves, final Context context) {
        NotificationsV2Request request = new NotificationsV2Request(vehicleCves, token, date);
        services.getAllNotifications(request).enqueue(new Callback<NotificationsV2Response>() {
            @Override
            public void onResponse(Call<NotificationsV2Response> call, Response<NotificationsV2Response> response) {
                presenter.hideLoaderFromInteractor();
                validateNotificationsCode(response, context, vehicleCves, date);
            }

            @Override
            public void onFailure(Call<NotificationsV2Response> call, Throwable t) {
                presenter.hideLoaderFromInteractor();
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
                Toast.makeText(context,  "La session ha expirado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateNotificationsCode(Response<NotificationsV2Response> response, Context context, List<Integer> vehicleCves, String date) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getNotificationData(response, context, vehicleCves, date);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getNotificationData(Response<NotificationsV2Response> response, Context context, List<Integer> vehicleCves, String date) {
        NotificationsV2Response notificationsV2Response = response.body();
        if (notificationsV2Response != null) {
            int responseCode = notificationsV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                NotificationsV2Data data = notificationsV2Response.getData();
                if (data != null) {
                    List<NotificationsV2AlertsList> alerts = data.getAlerts();
                    if (alerts != null && alerts.size() >=0) {
                        List<NotificationV2OnRoad> notifications = NotificationsV2Utils.getOnRoadNotifications(alerts);
                        presenter.setAllNotificationsToView(notifications);
                       // Toast.makeText(context,  Integer.toString(NotificationsV2Utils.getOnRoadNotifications(alerts).size()), Toast.LENGTH_SHORT).show();
                   /*    for(int i=0; i<listiterator; i++){

                       }*/
                        /***
                         *      Si el usuario tiene activadas las notificaciones, empezamos a consumir el web service,
                         *      solo si es la fecha de hoy
                         */
                        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
                        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
                        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
                        UserNotificationRealmSave userNotificationRealmSave = RealmUserNotifications.existUserNotification(user);
                        if (userNotificationRealmSave != null) {
                            if (userNotificationRealmSave.isActiveNotifications()) {
                                /**SE EMPIEZAN A CONSUMIR LAS NOTIFICACIONES, solo si es la fecha actual*/
                                String currentDate = NotificationsV2Utils.getCurrentDate();
                                if (date.equals(currentDate)) {
                                    notificationsUpdate = new NotificationsUpdate(vehicleCves, token, date);
                                    notificationsUpdate.execute();
                                }
                            } else {
                                Log.e("TAG", "NO ESTAN ACTIVADAS NO SE HACE NADA");
                            }
                        } else {
                            Log.e("TAG", "NO ESTAN ACTIVADAS NO SE HACE NADA");
                        }
                    } else {
                        //ghj
                        //presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                        presenter.setMessageToView("No se encontraron notificaciones el día: " + date);
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
              //  presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            //    Toast.makeText(context,  "La session ha expirado", Toast.LENGTH_SHORT).show();

                UnitDB.deleteDB();
                GroupDB.deleteDB();
                RealmUserData.deleteDB();

                Bundle bndl = new Bundle();
                bndl.putBoolean("HelpStatus", true);

                Intent intent = new Intent(context, LoginContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bndl);
                context.startActivity(intent);

                Toast.makeText(context, "La session ha expirado", Toast.LENGTH_SHORT).show();
            } else {
                presenter.setMessageToView(notificationsV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
        }
    }

    @Override
    public void rechargeNotifications(List<VehicleV2> vehicles, String date, Context context) {
        List<Integer> vehicleCves = NotificationsV2Utils.getCveVehicle(vehicles);
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        UserNotificationRealmSave userNotificationRealmSave = RealmUserNotifications.existUserNotification(user);
        if (userNotificationRealmSave != null) {
            if (userNotificationRealmSave.isActiveNotifications()) {
                /**SE EMPIEZAN A CONSUMIR LAS NOTIFICACIONES, solo si es el diaq de hoy*/
                String currentDate = NotificationsV2Utils.getCurrentDate();
                if (date.equals(currentDate)) {
                    notificationsUpdate = new NotificationsUpdate(vehicleCves, token, date);
                    notificationsUpdate.execute();
                }
            } else {
                Log.e("TAG", "NO ESTAN ACTIVADAS NO SE HACE NADA");
            }
        }
    }

    public class NotificationsUpdate extends AsyncTask<Void, Void, Void> {

        public Call<NotificationsV2Response> call;
        private List<Integer> vehiclesCves;
        private String token, date;

        /**
         * Esta variable controla el consumo del webService "getVehicles", si aun no llega la respuesta del servicio
         * debemos de esperar a que llegue, y posteriormente hacer de nuevo el request
         */
        private boolean readyToCharge = true;

        public NotificationsUpdate(List<Integer> vehiclesCves, String token, String date) {
            this.vehiclesCves = vehiclesCves;
            this.token = token;
            this.date = date;
        }

        public void calcenRequest() {
            if (call != null) {
                call.cancel();
                call = null;
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            while (true) {
                Log.e("TAG_ASYNC", "Entro al WHILE");
                if (isCancelled()) {
                    Log.e("TAG_ASYNC", "Se cancelo el request");
                    readyToCharge = false;
                    vehiclesCves.clear();
                    call.cancel();
                    break;
                } else {
                    if (readyToCharge) {
                        readyToCharge = false;
                        try {
                            Log.e("TAG_ASYNC", "Se duerme 10 segundos");
                            Thread.sleep(15000);
                        } catch (InterruptedException e) {
                            Log.e("TAG_ASYNC", "Error al dormir el hilo");
                            e.printStackTrace();
                        }
                        Log.e("TAG_ASYNC", "Se va a consumir el WEBSERVICE de los vehiculos");
                        //getFullVehiclesInfinity(vehiclesCves, token);
                        getNotifications(vehiclesCves, token, date);
                    } else {
                        Log.e("TAG_ASYNC", "Aun NOOOO se va a consumir el SERVICIOS DE LOS VEHICULOS");
                        try {
                            Log.e("TAG_ASYNC", "Se duerme 10 segundos");
                            Thread.sleep(15000);
                        } catch (InterruptedException e) {
                            Log.e("TAG_ASYNC", "Error al dormir el hilo");
                            e.printStackTrace();
                        }
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        private void getNotifications(List<Integer> cvesSelected, String token, String date) {
            if (!isCancelled()) {
                NotificationsV2Request request = new NotificationsV2Request(cvesSelected, token, date);

                call = services.getAllNotifications(request);
                call.enqueue(new Callback<NotificationsV2Response>() {
                    @Override
                    public void onResponse(Call<NotificationsV2Response> call, Response<NotificationsV2Response> response) {
                        readyToCharge = true;
                        validateNotificationResponse(response);
                    }

                    @Override
                    public void onFailure(Call<NotificationsV2Response> call, Throwable t) {
                        Log.e("TAG", "ERROR::: " + t.getMessage());
                    }
                });
            }
        }

        private void validateNotificationResponse(Response<NotificationsV2Response> response) {
            NotificationsV2Response notificationsV2Response = response.body();
            if (notificationsV2Response != null) {
                int responseCode = notificationsV2Response.getResponseCode();
                if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                    NotificationsV2Data data = notificationsV2Response.getData();
                    if (data != null) {
                        List<NotificationsV2AlertsList> alerts = data.getAlerts();
                        if (alerts != null && alerts.size() > 0) {
                            List<NotificationV2OnRoad> notifications = NotificationsV2Utils.getOnRoadNotifications(alerts);
                            Log.e("TAG_ASYNC", "tamañooooooooo:::: " + notifications.size());
                            presenter.setAllNotificationsToView(notifications);
                        } else {
                            //presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                        }
                    } else {
                        //presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                    }
                } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {

                   presenter.setMessageToView("sesion expirada");
                } else {
                    //presenter.setMessageToView(notificationsV2Response.getMessage());
                }
            } else {
                //presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
            }
        }

    }


}
