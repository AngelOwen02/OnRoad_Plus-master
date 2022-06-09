package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.interactor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsAlertList;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsOnroad;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.Vehicles;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.VehiclesRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.VehiclesResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.presenter.NotificationsPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.utils.clases.NotificationsUtils;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.utils.clases.VehiclesData;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.utils.clases.VehiclesUtils;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.utils.interfaces.NotificationsServices;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
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

public class NotificationsInteractorImpl implements NotificationsInteractor {
    private NotificationsPresenter presenter;
    private NotificationsServices services;
    private Retrofit retrofitClient;
    private int listiterator;
    private int notifications;
    private NotificationsUpdate notificationsUpdate;
    final Handler handler = new Handler();
    private Runnable runnable;
    public NotificationsInteractorImpl(NotificationsPresenter presenter) {
        this.presenter = presenter;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(NotificationsServices.class);
    }

    @Override
    public void getMainDate() {
        presenter.setMainDate(NotificationsUtils.getCurrentDate());
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
        VehiclesRequest request = new VehiclesRequest(token, GeneralConstantsV2.REQUEST_ALL_VEHICLES, vehiclesCves);
        services.getFullVehicles(request).enqueue(new Callback<VehiclesResponse>() {
            @Override
            public void onResponse(Call<VehiclesResponse> call, Response<VehiclesResponse> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<VehiclesResponse> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
                Toast.makeText(context,  "La session ha expirado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateCode(Response<VehiclesResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehiclesData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
            Toast.makeText(context,  "sesion expirada", Toast.LENGTH_LONG).show();
        }
    }

    private void getVehiclesData(Response<VehiclesResponse> response, Context context) {
        VehiclesResponse vehicleV2Response = response.body();
        if (vehicleV2Response != null) {
            int responseCode = vehicleV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                VehiclesData data = vehicleV2Response.getData();
                if (data != null) {
                    List<Vehicles> vehicleV2List = data.getVehicles();
                    if (vehicleV2List != null && vehicleV2List.size() > 0) {
                        /**
                         *  Seteamos la cadena "NO_IMAGE" en caso de que no tenga la imagen algun objeto vehiculo
                         *  asi como tambien un valor en el atributo "selected" y una posición por default
                         *  para trabajar posteriormente con el filter.
                         */
                        vehicleV2List = VehiclesUtils.setUserSelectedPositionAndImageToEveryVehicle(vehicleV2List, context);
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
    public void getAllNotification(List<Vehicles> vehicles, String date, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        List<Integer> vehicleCves = NotificationsUtils.getCveVehicle(vehicles);
        startNotificationRequest(token, date, vehicleCves, context);
    }

    private void startNotificationRequest(String token, final String date, final List<Integer> vehicleCves, final Context context) {
        NotificationsRequest request = new NotificationsRequest(vehicleCves, token, date);
        Log.e("notificationsper", ""+"cves:   "+vehicleCves +" date:  "+date+" token: " +token);
        services.getAllNotifications(request).enqueue(new Callback<NotificationsResponse>() {
            @Override
            public void onResponse(Call<NotificationsResponse> call, Response<NotificationsResponse> response) {
                presenter.hideLoaderFromInteractor();
                validateNotificationsCode(response, context, vehicleCves, date);
            }

            @Override
            public void onFailure(Call<NotificationsResponse> call, Throwable t) {
                presenter.hideLoaderFromInteractor();
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
                Toast.makeText(context,  "La session ha expirado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateNotificationsCode(Response<NotificationsResponse> response, Context context, List<Integer> vehicleCves, String date) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getNotificationData(response, context, vehicleCves, date);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getNotificationData(Response<NotificationsResponse> response, Context context, List<Integer> vehicleCves, String date) {
        NotificationsResponse notificationsV2Response = response.body();
        if (notificationsV2Response != null) {
            int responseCode = notificationsV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                NotificationsData data = notificationsV2Response.getData();
                if (data != null) {
                    List<NotificationsAlertList> alerts = data.getAlerts();
                    if (alerts != null && alerts.size() >=0) {
                        List<NotificationsOnroad> notifications = NotificationsUtils.getOnRoadNotifications(alerts);
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
                                String currentDate = NotificationsUtils.getCurrentDate();
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
    public void rechargeNotifications(List<Vehicles> vehicles, String date, Context context) {
        List<Integer> vehicleCves = NotificationsUtils.getCveVehicle(vehicles);
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String user = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        UserNotificationRealmSave userNotificationRealmSave = RealmUserNotifications.existUserNotification(user);
      /*  if (userNotificationRealmSave != null) {
            if (userNotificationRealmSave.isActiveNotifications()) {
                /**SE EMPIEZAN A CONSUMIR LAS NOTIFICACIONES, solo si es el diaq de hoy
                String currentDate = NotificationsUtils.getCurrentDate();
                if (date.equals(currentDate)) {


                }
            } else {
                Log.e("TAGBOONTONOTI", "NO ESTAN ACTIVADAS NO SE HACE NADA");
            }
        }*/

        notificationsUpdate = new NotificationsUpdate(vehicleCves, token, date);
        notificationsUpdate.execute();
    }

    public class NotificationsUpdate extends AsyncTask<Void, Void, Void> {

        public Call<NotificationsResponse> call;
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
                NotificationsRequest request = new NotificationsRequest(cvesSelected, token, date);

                call = services.getAllNotifications(request);
                call.enqueue(new Callback<NotificationsResponse>() {
                    @Override
                    public void onResponse(Call<NotificationsResponse> call, Response<NotificationsResponse> response) {
                        readyToCharge = true;
                        validateNotificationResponse(response);
                    }

                    @Override
                    public void onFailure(Call<NotificationsResponse> call, Throwable t) {
                        Log.e("TAG", "ERROR::: " + t.getMessage());
                    }
                });
            }
        }

        private void validateNotificationResponse(Response<NotificationsResponse> response) {
            NotificationsResponse notificationsV2Response = response.body();
            if (notificationsV2Response != null) {
                int responseCode = notificationsV2Response.getResponseCode();
                if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                    NotificationsData data = notificationsV2Response.getData();
                    if (data != null) {
                        List<NotificationsAlertList> alerts = data.getAlerts();
                        if (alerts != null && alerts.size() > 0) {
                            List<NotificationsOnroad> notifications = NotificationsUtils.getOnRoadNotifications(alerts);
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
