package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.TrackingData;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.TrackingRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.TrackingResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.TrackingServices;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.VehicleTracking;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.presenter.TrackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.TrackingDBHelper;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;
import com.pranavpandey.android.dynamic.utils.DynamicUnitUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TrackingInteractorImpl implements TrackingInteractor {

    private TrackingPresenter presenter;
    private TrackingServices services;
    private Context context;
    private static BitmapDescriptor vehicleIcon;
    public int vehiclesSizeList;

    public TrackingInteractorImpl(TrackingPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(TrackingServices.class);
    }

    @Override
    public void getAllVehiclesFromAPI() {
        List<Integer> noCves = new ArrayList<>();
        noCves.add(0);
        startVehiclesRequest(GeneralConstantsV2.REQUEST_ALL_VEHICLES, noCves, context);
    }

    private void startVehiclesRequest(int typeRequest, List<Integer> vehiclesCves, final Context context) {

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);

        presenter.showProgressBar();
        TrackingRequest request = new TrackingRequest(token, typeRequest, vehiclesCves);
        services.getFullVehicles(request).enqueue(new Callback<TrackingResponse>() {
            @Override
            public void onResponse(Call<TrackingResponse> call, Response<TrackingResponse> response) {
                validateCode(response, context);
            }

            @Override
            public void onFailure(Call<TrackingResponse> call, Throwable t) {
                //  presenter.setMessageToView(RetrofitValidationsV2.getOnFailureResponse(t, context));
                Log.e("dsd", "dsdsd");
            }
        });
    }

    private void validateCode(Response<TrackingResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehiclesData(response, context);
        } else {
            //presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getVehiclesData(Response<TrackingResponse> response, Context context) {
        TrackingResponse vehicleV2Response = response.body();
        if (vehicleV2Response != null) {
            int responseCode = vehicleV2Response.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                TrackingData data = vehicleV2Response.getData();
                if (data != null) {
                    List<Unit> vehicleV2List = data.getVehicles();
                    if (vehicleV2List != null && vehicleV2List.size() > 0) {

                        List<VehicleTracking> vehicleListDB = TrackingDBHelper.readVehicleList();



                       //    getGroups(context);
                    } else {
                        //     presenter.setMessageToView("No se encontraron vehículos");
                    }
                } else {
                    //   presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                // presenter.sessionExpired(context.getString(R.string.textSessionExpired));
            } else {
                //   presenter.setMessageToView(vehicleV2Response.getMessage());
            }
        } else {
            // presenter.setMessageToView(context.getString(R.string.textEmptyResponse));
        }
    }

    //private void saveVehicleList(List<VehicleTracking> vehicleList){
    //    TrackingRealmHelper.createVehicleList(vehicleList);
    // }

    //         //
    // Markers //
    //         //


    @Override
    public void vehicleMarkerSetup(double lat, double lng, String title, String image, int vehicleSwitch) {
        presenter.showProgressBar();
            TrackingInteractorImpl.GetVehicleMarkerAsync getVehicleMarkerAsync = new TrackingInteractorImpl.GetVehicleMarkerAsync(lat, lng, title, image, vehicleSwitch);
            getVehicleMarkerAsync.execute();
    }

    @Override
    public void zoomToVehicle(double lat, double lng) {
        if (lat != 0.0 && lng != 0.0) {
            LatLng latLng = new LatLng(lat, lng);
            float zoom = 6;
            presenter.zoomVehicleSetup(latLng, zoom);
        }
    }



    private class GetVehicleMarkerAsync extends AsyncTask<Void, Void, Void> {

        private double lat;
        private double lng;
        private String title;
        private String urlImage;
        private int vehicleSwitch;

        private GetVehicleMarkerAsync(double lat, double lng, String title, String urlImage, int vehicleSwitch) {
            this.lat = lat;
            this.lng = lng;
            this.title = title;
            this.urlImage = urlImage;
            this.vehicleSwitch = vehicleSwitch;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bitmap bitmapMarker = getBitmapFromURL(urlImage);
            if (bitmapMarker != null) {
                Bitmap bitmap = Bitmap.createScaledBitmap(bitmapMarker, 160, 160, false);
                View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.unit_marker, null);
                vehicleIcon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(marker, 160, 160, bitmap, title, vehicleSwitch));
            } else {
                vehicleIcon = BitmapDescriptorFactory.fromResource(R.drawable.sedan);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            MarkerOptions markerOptions = new MarkerOptions();

            markerOptions.position(new LatLng(lat, lng));
            markerOptions.title(title);
            markerOptions.infoWindowAnchor(2, 2);
            markerOptions.anchor(0.5f, 0.5f);
            markerOptions.icon(vehicleIcon);

            presenter.hideProgressBar();
           presenter.vehicleMarkerSetup(markerOptions);
        }
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return  BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap createBitmapFromView(@NonNull View view, int width, int height, Bitmap resource, String name, int vehicleSwitch) {

        CircleImageView circleImageView = view.findViewById(R.id.unit_marker_img);
        TextView vehicleName = view.findViewById(R.id.unit_marker_title);

        circleImageView.setImageBitmap(resource);
        vehicleName.setText(name);

        setImageBorderColor(vehicleSwitch, circleImageView);

        if (width > 0 && height > 0) {
            view.measure(View.MeasureSpec.makeMeasureSpec(DynamicUnitUtils.convertDpToPixels(width), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(DynamicUnitUtils.convertDpToPixels(height), View.MeasureSpec.EXACTLY));
        }
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable background = view.getBackground();

        if (background != null) {
            background.draw(canvas);
        }
        view.draw(canvas);
        return bitmap;
    }

    private void setImageBorderColor(int vehicleSwitch, CircleImageView circleImageView) {
        if (vehicleSwitch == 1) {
            circleImageView.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGreen));
        } else if (vehicleSwitch == 2) {
            circleImageView.setBorderColor(context.getResources().getColor(R.color.colorBorderCarOrange));
        } else if (vehicleSwitch == 3) {
            circleImageView.setBorderColor(context.getResources().getColor(R.color.colorBorderCarRed));
        } else {
            circleImageView.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGray));
        }
    }
}
