package com.pnla.onroadplus.z_version2.fragments.mapV2.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Interactor;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Services;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2MapRequest;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2MapResponse;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehiclesV2MapData;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.markersV2.VehicleBitmapDescriptorV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Data;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Request;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Response;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionRequest;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionResponse;
import com.pnla.onroadplus.z_version2.fragments.mapV2.utils.MapV2Utils;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.view.FragmentVehiclesV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmVehicle;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentMapV2InteractorImpl implements MapV2Interactor {

    public List<VehicleBitmapDescriptorV2> markerList;
    /**
     * Marcadores de los vehiculos solicitados
     */
    public int vehiclesSizeList;

    private MapV2Presenter presenter;
    private MapV2Services services;
    private Retrofit retrofitClient;
    private Retrofit retrofitVehicles;

    /**
     * Para el web service de los vehiculos
     */
    private VehiclesUpdate vehiclesUpdateClass;

    public FragmentMapV2InteractorImpl(MapV2Presenter presenter) {
        this.presenter = presenter;
        retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(MapV2Services.class);
    }

    @Override
    public void getDates() {
        List<DateV2> dates = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < 30; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i * (-1));
            String reportDate = df.format(cal.getTime());
            dates.add(new DateV2(reportDate));
        }
        presenter.setDatesToView(dates);
    }

    @Override
    public void getVehiclesFromBundleOrRealm(Bundle bundle, Context context) {
        /**
         *   Para enviar los vehículos al mapa, validamos de la siguiente manera:
         *   Si es la 1ra vez que el usuario entra a la app despues de instalarla enviamos las 30 unidades obtenidas desde el WS
         *   En caso contrario
         *      si el bundle NO esta vacio, obtenemos las unidades desde el bundle
         *      si el bundle esta vacio entonces obtenemos las unidades desde realm
         */
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        if (RealmUserData.isFirstTimeThatUserUsedApp(userName)) {
            RealmUserData.updateUserRegistered(userName, GeneralConstantsV2.IS_NOT_FIRST_TIME);      /**Actualizamos el status des usuario*/
            /**Solicitamos las primeras 30 unidades*/
            List<Integer> noCves = new ArrayList<>();
            noCves.add(0);
            startVehiclesV2MapRequest(GeneralConstantsV2.REQUEST_SPECIFIC_VEHICLES, noCves, context);
        } else {
            List<VehicleV2> vehicles;
            if (bundle != null) {
                /**Obtenemos los vehiculos del bundle*/
                vehicles = MapV2Utils.getVehiclesFromBundle(bundle);
                if (vehicles != null && vehicles.size() > 0) {
                    /**Empezamos el request con la claves de los vehiculos*/
                    startVehiclesV2MapRequest(GeneralConstantsV2.REQUEST_SPECIFIC_VEHICLES, MapV2Utils.getVehiclesCves(vehicles), context);
                } else {
                    Fragment fragment = new FragmentVehiclesV2();
                    presenter.setFragmentVehiclesToView(fragment);
                }
            } else {
                /**Obtenemos los vehiculos de realm y si no hay vehiculos tenemos que mandar a "FragmentVehiclesV2"*/
                vehicles = RealmVehicle.getAllVehiclesByUserName(userName);
                if (vehicles != null && vehicles.size() > 0) {
                    startVehiclesV2MapRequest(GeneralConstantsV2.REQUEST_SPECIFIC_VEHICLES, MapV2Utils.getVehiclesCves(vehicles), context);
                } else {
                    Fragment fragment = new FragmentVehiclesV2();
                    presenter.setFragmentVehiclesToView(fragment);
                }
            }
        }

    }

    private void startVehiclesV2MapRequest(int typeRequest, List<Integer> vehiclesCves, final Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        VehicleV2MapRequest request = new VehicleV2MapRequest(token, typeRequest, vehiclesCves);
        presenter.showLoaderFromInteractor();
        services.getFullVehicles(request).enqueue(new Callback<VehicleV2MapResponse>() {
            @Override
            public void onResponse(Call<VehicleV2MapResponse> call, Response<VehicleV2MapResponse> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<VehicleV2MapResponse> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateCode(Response<VehicleV2MapResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehiclesData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getVehiclesData(Response<VehicleV2MapResponse> response, Context context) {
        VehicleV2MapResponse vehicleV2MapResponse = response.body();
        if (vehicleV2MapResponse != null) {
            int responseCode = vehicleV2MapResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                VehiclesV2MapData data = vehicleV2MapResponse.getData();
                if (data != null) {
                    List<VehicleV2Map> vehicles = data.getVehicles();
                    if (vehicles != null && vehicles.size() > 0) {
                        vehicles = MapV2Utils.setDefaultImage(vehicles);    /**Colocamos "NO_IMAGE" en caso de que no contenga imagen el vehiculo*/
                        presenter.setVehiclesListToView(vehicles, GeneralConstantsV2.FIRST_VEHICLE_REQUEST);
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView(vehicleV2MapResponse.getMessage());
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyResponse));
        }
    }

    /**
     * VehiscleDescriptionWebService
     *
     * @param vehicleCve
     */
    @Override
    public void getVehicleDescription(int vehicleCve, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String validation = MapV2Utils.validateVehicleDescriptionDataToRequest(token, vehicleCve);
        if (validation.equals(GeneralConstantsV2.SUCCESS)) {
            startVehicleDescriptionRequest(token, vehicleCve, context);
        } else {
            presenter.setMessageToView(validation);
        }
    }

    private void startVehicleDescriptionRequest(String token, int vehicleCve, final Context context) {
        VehicleDescriptionRequest request = new VehicleDescriptionRequest(vehicleCve, token);
        services.getVehicleDescription(request).enqueue(new Callback<VehicleDescriptionResponse>() {
            @Override
            public void onResponse(Call<VehicleDescriptionResponse> call, Response<VehicleDescriptionResponse> response) {
                validateVehicleDescriptionCode(response, context);
            }

            @Override
            public void onFailure(Call<VehicleDescriptionResponse> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateVehicleDescriptionCode(Response<VehicleDescriptionResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehicleDescriptionData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getVehicleDescriptionData(Response<VehicleDescriptionResponse> response, Context context) {
        VehicleDescriptionResponse vehicleDescriptionResponse = response.body();
        if (vehicleDescriptionResponse != null) {
            int responseCode = vehicleDescriptionResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                VehicleDescriptionData descriptionData = vehicleDescriptionResponse.getData();
                if (descriptionData != null) {
                    presenter.setVehicleDescriptionToView(descriptionData);
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView(vehicleDescriptionResponse.getMessage());
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
        }
    }

    @Override
    public void getTripsByDate(int cveVehicle, String date, Context context) {
        String startUtcDate = MapV2Utils.getUtcStartDate(date);
        String endUtcDate = MapV2Utils.getUtcEndDate(date);
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);

        String validation = MapV2Utils.validateTripsData(token, cveVehicle, startUtcDate, endUtcDate);
        if (validation.equals(GeneralConstantsV2.SUCCESS)) {
            startTripsRequest(token, cveVehicle, startUtcDate, endUtcDate, context);
        } else {
            presenter.setMessageToView(validation);
        }
    }

    private void startTripsRequest(String token, int cveVehicle, String startDate, String endDate, final Context context) {
        TripsV2Request request = new TripsV2Request(cveVehicle, startDate, endDate, token);
        services.getTrips(request).enqueue(new Callback<TripsV2Response>() {
            @Override
            public void onResponse(Call<TripsV2Response> call, Response<TripsV2Response> response) {
                validateTripsCode(response, context);
            }

            @Override
            public void onFailure(Call<TripsV2Response> call, Throwable t) {
                presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateTripsCode(Response<TripsV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getTripsData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getTripsData(Response<TripsV2Response> response, Context context) {
        TripsV2Response tripsV2Response = response.body();
        if (tripsV2Response != null) {
            int responseCode = tripsV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                TripsV2Data data = tripsV2Response.getData();
                if (data != null) {
                    List<TripV2> trips = data.getTrips();
                    if (trips != null && trips.size() > 0) {
                        trips = MapV2Utils.orderPositions(trips);                   /**     Acomodamos las posiciones ya que llegan desordenadas    */
                        trips = MapV2Utils.buildGoogleUrlImage(trips, context);     /**     Construimos la url de la imagen estatica de google*/
                        presenter.setTripsToView(trips);
                    } else {
                        presenter.setMessageToView(context.getString(R.string.textEmptyTrips));
                    }
                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyTrips));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView(tripsV2Response.getMessage());
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textErrorDataEmptyMap));
        }
    }

    @Override
    public void getVehiclesMarkers(List<VehicleV2Map> vehicles) {
        vehiclesSizeList = vehicles.size();
        markerList = new ArrayList<>();
        for (int i = 0; i < vehicles.size(); i++) {
            GetBitmapFromURLAsync getBitmapFromURLAsync = new GetBitmapFromURLAsync(vehicles.get(i));
            getBitmapFromURLAsync.execute(i);
        }
    }

    private class GetBitmapFromURLAsync extends AsyncTask<Integer, Void, Integer> {
        private VehicleV2Map vehicle;
        private Bitmap bitmapMarker;

        public GetBitmapFromURLAsync(VehicleV2Map vehicle) {
            this.vehicle = vehicle;
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            Integer finalPosition = params[0];
            bitmapMarker = getBitmapFromURL(vehicle.getVehicleImage());
            return finalPosition;
        }

        @Override
        protected void onPostExecute(Integer finalPosition) {
            Bitmap bitmap;
            BitmapDescriptor icon;

            if (bitmapMarker != null) {
                bitmap = Bitmap.createScaledBitmap(bitmapMarker, 90, 90, false);
                icon = BitmapDescriptorFactory.fromBitmap(bitmap);
            } else {
                icon = BitmapDescriptorFactory.fromResource(R.drawable.sedan);
            }

            markerList.add(new VehicleBitmapDescriptorV2(vehicle.getCveVehicle(), icon, vehicle));

            // verificamos para cerrar el loadere
            if (finalPosition == vehiclesSizeList - 1) {
                presenter.hideLoaderFromInteractor();
                presenter.setVehicleBitmapDescriptorToView(markerList);
            }
        }

        public Bitmap getBitmapFromURL(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public void getVehiclesToUpdate(List<VehicleV2Map> vehicles, Context context) {
        if (vehicles != null && vehicles.size() > 0) {
            List<Integer> vehicleCves = MapV2Utils.getVehiclesV2MapCves(vehicles);
            SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
            String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
            vehiclesUpdateClass = new VehiclesUpdate(vehicleCves, token);
            vehiclesUpdateClass.execute();
        }
    }

    @Override
    public void cancelRequest() {
        //para ya no realizar request de vehiculos son los siguientes 2 if
        if (vehiclesUpdateClass != null) {
            vehiclesUpdateClass.calcenRequest();
            vehiclesUpdateClass.cancel(true);
            vehiclesUpdateClass = null;
        }
    }

    @Override
    public void getLocationUpdatedByVehicleSeledted(VehicleV2Map vehicleSelected, List<VehicleV2Map> vehicles) {
        VehicleV2Map vehicleV2Map = MapV2Utils.findVehicle(vehicles, vehicleSelected);
        presenter.setLocationUpdatedByVehicleSelected(vehicleV2Map.getLatitude(), vehicleV2Map.getLongitude());
    }

    public class VehiclesUpdate extends AsyncTask<Void, Void, Void> {

        public Call<VehicleV2MapResponse> call;
        private List<Integer> vehiclesCves;
        private String token;

        /**
         * Esta variable controla el consumo del webService "getVehicles", si aun no llega la respuesta del servicio
         * debemos de esperar a que llegue, y posteriormente hacer de nuevo el request
         */
        private boolean readyToCharge = true;

        public VehiclesUpdate(List<Integer> vehiclesCves, String token) {
            this.vehiclesCves = vehiclesCves;
            this.token = token;
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
                        getFullVehiclesInfinity(vehiclesCves, token);
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

        private void getFullVehiclesInfinity(List<Integer> cvesSelected, String token) {
            if (!isCancelled()) {
                VehicleV2MapRequest request = new VehicleV2MapRequest(token, GeneralConstantsV2.REQUEST_SPECIFIC_VEHICLES, cvesSelected);
                call = services.getFullVehicles(request);
                call.enqueue(new Callback<VehicleV2MapResponse>() {
                    @Override
                    public void onResponse(Call<VehicleV2MapResponse> call, Response<VehicleV2MapResponse> response) {
                        readyToCharge = true;
                        VehicleV2MapResponse vehicleV2MapResponse = response.body();
                        if (vehicleV2MapResponse != null) {
                            int responseCode = vehicleV2MapResponse.getResponseCode();
                            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                                VehiclesV2MapData data = vehicleV2MapResponse.getData();
                                if (data != null) {
                                    List<VehicleV2Map> vehicles = data.getVehicles();
                                    if (vehicles != null && vehicles.size() > 0) {
                                        Log.e("TAG_ASYNC", "Se obtuvieron -> " + vehicles.size() + " vehiculos del servicio");
                                        vehicles = MapV2Utils.setDefaultImage(vehicles);    /**Colocamos "NO_IMAGE" en caso de que no contenga imagen el vehiculo*/
                                        presenter.setVehiclesListToView(vehicles, !GeneralConstantsV2.FIRST_VEHICLE_REQUEST);
                                        presenter.updateVehiclesMarkers(vehicles);
                                    } else {
                                        Log.e("TAG_ASYNC", "La respuesta no trajo vehiculos!!!");
                                    }
                                } else {
                                    Log.e("TAG_ASYNC", "La respuesta de vehiculos fue null (data)");
                                }
                            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                                Log.e("TAG_ASYNC", "La sesion expiro");
                            } else {
                                Log.e("TAG_ASYNC", "error: " + vehicleV2MapResponse.getMessage());
                            }
                        } else {
                            Log.e("TAG_ASYNC", "La respuesta de vehiculos fue null");
                        }
                    }

                    @Override
                    public void onFailure(Call<VehicleV2MapResponse> call, Throwable t) {
                        Log.e("TAG_ASYNC", "ON_FAILTURE: " + t.getMessage());
                    }
                });
            }
        }
    }

}