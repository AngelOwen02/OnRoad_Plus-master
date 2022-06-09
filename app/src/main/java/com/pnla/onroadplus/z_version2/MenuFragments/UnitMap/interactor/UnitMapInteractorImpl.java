package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.interactor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.Path;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.RequestAPIMAP;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.ResponseAPIMAP;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.TripByDayResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.TripsbyDayData;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsBytimeData;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbyTimeResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.tripsbydayRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.presenter.UnitMapPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.utils.ExternalApiSerice;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Services;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Data;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Request;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripsV2Response;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionRequest;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionResponse;
import com.pnla.onroadplus.z_version2.fragments.mapV2.utils.MapV2Utils;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitExternalApi;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;
import com.pranavpandey.android.dynamic.utils.DynamicUnitUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UnitMapInteractorImpl implements UnitMapInteractor {

    private static BitmapDescriptor vehicleIcon;
    private Context context;
    private UnitMapPresenter presenter;
    private MapV2Services services;
    private ExternalApiSerice externalservice;


    public UnitMapInteractorImpl(UnitMapPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        services = retrofitClient.create(MapV2Services.class);

        Retrofit retrofitExternalapi= RetrofitExternalApi.getApiMap2();
        externalservice=retrofitExternalapi.create(ExternalApiSerice.class);

    }

    // Markers //

    @Override
    public void vehicleMarkerSetup(double lat, double lng, String title, String image, int vehicleSwitch) {
        presenter.showProgressDialog();
        GetVehicleMarkerAsync getVehicleMarkerAsync = new GetVehicleMarkerAsync(lat, lng, title, image, vehicleSwitch);
        getVehicleMarkerAsync.execute();

    }


    @Override
    public void zoomToVehicle(double lat, double lng) {
        if (lat != 0.0 && lng != 0.0) {
            LatLng latLng = new LatLng(lat, lng);
            float zoom = 14;
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
                Bitmap bitmap = Bitmap.createScaledBitmap(bitmapMarker, 80, 80, false);
                View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.unit_marker, null);
                vehicleIcon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(marker, 160, 160, bitmap, title, vehicleSwitch));
            } else {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sedan);
                Bitmap resized = Bitmap.createScaledBitmap(bitmap, 160, 160, true);
                vehicleIcon = BitmapDescriptorFactory.fromBitmap(resized);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            MarkerOptions markerOptions = new MarkerOptions();

            markerOptions.position(new LatLng(lat, lng));
            markerOptions.infoWindowAnchor(2, 2);
            markerOptions.anchor(0.5f, 0.5f);
            markerOptions.icon(vehicleIcon);

            presenter.vehicleMarkerSetup(markerOptions);
            presenter.hideProgressDialog();

        }

        private Bitmap getBitmapFromURL(String src) {
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

    private Bitmap createBitmapFromView(@NonNull View view, int width, int height, Bitmap resource, String name, int vehicleSwitch) {

        CircleImageView circleImageView = view.findViewById(R.id.unit_marker_img);
        TextView vehicleName = view.findViewById(R.id.unit_marker_title);

        circleImageView.setImageBitmap(resource);
        vehicleName.setText(name);

        setImageBorderColor(vehicleSwitch,circleImageView);

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
        }else if (vehicleSwitch == 4) {
            circleImageView.setBorderColor(context.getResources().getColor(R.color.colorBlack));
        }
        else if (vehicleSwitch == 0) {
            circleImageView.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGray));
        }
        else {
            circleImageView.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGray));
        }
    }

    // Dates //

    @Override
    public void getCurrentDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String currentDate = df.format(cal.getTime());

        presenter.setCurrentDate(currentDate);
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

    // Vehicle Description //
    @Override
    public void getTripbyDay(int vehicleCve,String sendime , Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String validation = MapV2Utils.validategettripbydatetoRequest(vehicleCve,sendime,token);
        presenter.showProgressDialog();
        if (validation.equals(GeneralConstantsV2.SUCCESS)) {
            startvehicleTripbyDayRequest(vehicleCve,sendime,token,context);
        }
        else{
            presenter.setErrorMessage(validation);
        }
    }

    public void getTripsbyTime(int vehicleCve,String startTimer,String endtime,Context context)
    {
        //Log.e("dataclocks3",""+startTimer+ "  "+ endtime+"   dssdacw");

        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if (token != null){
            String validation = MapV2Utils.validategettripbytinetoRequest(vehicleCve,endtime,startTimer,token);
            presenter.showProgressDialog();
            if (validation.equals(GeneralConstantsV2.SUCCESS)) {
                startvehicleTripbytimeRequest(vehicleCve,endtime,startTimer,token,context);
            }
            else{
                presenter.setErrorMessage(validation);
            }
        }

    }

    @Override
    public void dataExteralAPI(List<List<Double>> correctedDots) {
        List<List<Double>> points=new ArrayList<>();
        List<Double> dots=new ArrayList<>();
        dots.add(-103.348356);//,20.629786]");
        dots.add(20.629786);
        List<Double> dots2=new ArrayList<>();
        dots2.add(-103.349047);//,20.629786]");
        dots2.add(20.629536);
       // dots.add("[-103.349047,20.629536]");
        points.add(0,dots);
        points.add(1,dots2);
       // Log.e("externalApimaps",""+points);
        RequestAPIMAP requestAPIMAP =new RequestAPIMAP(correctedDots,false,"car",false,false);
        externalservice.closerPointsAPI(requestAPIMAP).enqueue(new Callback<ResponseAPIMAP>() {
            @Override
            public void onResponse(Call<ResponseAPIMAP> call, Response<ResponseAPIMAP> response) {
                Log.e("externalApimaps","respuetaA : "+ response.code());
                externalApivoid(response,context);
            }

            @Override
            public void onFailure(Call<ResponseAPIMAP> call, Throwable t) {
                Toast.makeText(context,  ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("externalApimaps","respueta: "+t.getMessage());
            }
        });
    }
    private void externalApivoid(Response<ResponseAPIMAP> response, Context context)
    {
            if(RetrofitValidationsV2.checkSuccessCode(response.code())){
              getExternalApiDataData(response,context);
             }
             else{
                   presenter.setErrorMessage(RetrofitValidationsV2.getErrorByStatus(response.code(),context));
                Log.e("externalApimaps","respueta2 : "+RetrofitValidationsV2.getErrorByStatus(response.code(),context));
             }
    }
    private void getExternalApiDataData( Response<ResponseAPIMAP> response, Context context)
    {
        ResponseAPIMAP myresponse=response.body();
        List<Path> paths = new ArrayList<>();
        paths=myresponse.getPaths();
        if(paths.size()!=0)
        {
            List<List<Float>> resumeDots=new ArrayList<>();
            resumeDots.clear();

            List<List<Float>> snapedDots=new ArrayList<>();
            snapedDots.clear();
            List<String> xdots=new ArrayList<>();
            List<String> ydots=new ArrayList<>();

            xdots.clear();
            ydots.clear();
            if(paths.get(0).getPoints().getCoordinates().size()!=0)
            {

                for(int i=0; i<paths.get(0).getPoints().getCoordinates().size();i++)
                {
                    resumeDots.add(paths.get(0).getPoints().getCoordinates().get(i));
                }
                for(int j=0; j<paths.get(0).getSnappedWaypoints().getCoordinates().size();j++)
                {
                    snapedDots.add(paths.get(0).getSnappedWaypoints().getCoordinates().get(j));

                     String[] parts=String .valueOf(paths.get(0).getSnappedWaypoints().getCoordinates().get(j)).split(",");
                                String[] partlongitude=parts[0].split("\\[");
                                String[] partlatitude=parts[1].split("\\]");

                    xdots.add(partlatitude[0]);
                    ydots.add(partlongitude[1]);

                }

                Log.e("1externalApimaps"," these are the new dots"+resumeDots);
                Log.e("1externalApimaps"," snapped x dots "+xdots.size()+"   "+ xdots.get(0));
                presenter.tripsBDx2tripsBDy2(xdots,ydots);
                presenter.drawHDdots(resumeDots);
            }
        }

    }

    //region testdata
    private void  startvehicleTripbytimeRequest(int vehicleCve,String sendtime,String sendtime1,String token,final Context context){
        tripsbyTimeRequest request=new tripsbyTimeRequest(vehicleCve,sendtime,sendtime1,token);
        services.gettripsbytime(request).enqueue(new Callback<tripsbyTimeResponse>() {


            @Override
            public void onResponse(Call<tripsbyTimeResponse> call, Response<tripsbyTimeResponse> response) {
                validateTripsbyTimeCode(response,context);
            }

            @Override
            public void onFailure(Call<tripsbyTimeResponse> call, Throwable t) {

            }

        });

    }

    private void startvehicleTripbyDayRequest(int vehicleCve,String sendtime,String token,final Context context) {
        tripsbydayRequest request=new tripsbydayRequest(vehicleCve,sendtime,token);
        services.gettripsbyday(request).enqueue(new Callback<TripByDayResponse>() {
            @Override
            public void onResponse(Call<TripByDayResponse> call, Response<TripByDayResponse> response) {
                validateTripsbyDayCode(response,context);
            }

            @Override
            public void onFailure(Call<TripByDayResponse> call, Throwable t) {

            }
        });
    }
    private  void validateTripsbyTimeCode(Response<tripsbyTimeResponse> response, Context context)
    {
        if(RetrofitValidationsV2.checkSuccessCode(response.code())){
            getTipsByTimeData(response,context);
        }
        else{
            presenter.setErrorMessage(RetrofitValidationsV2.getErrorByStatus(response.code(),context));
        }
    }

    private  void validateTripsbyDayCode(Response<TripByDayResponse> response, Context context)
    {
        if(RetrofitValidationsV2.checkSuccessCode(response.code())){
            getTipsByDayData(response,context);
        }
        else{
            presenter.setErrorMessage(RetrofitValidationsV2.getErrorByStatus(response.code(),context));
        }
    }
// endregion testdata
    private void getTipsByTimeData(Response<tripsbyTimeResponse> response, Context context)
    {
        {
            tripsbyTimeResponse tripbytimeResponse=response.body();

            if(tripbytimeResponse!=null) {
                int responseCode=tripbytimeResponse.getResponseCode();

                if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK){
                    tripsBytimeData data=tripbytimeResponse.getData();

                    if(data!=null)
                    {
                        if (!data.getLatitude().isEmpty()){

                            Log.e("dataclocks5",""+data.getGeoreference());
                            String lislats= data.getLatitude();
                            String listLongs=data.getLongitude();
                            String calles=data.getGeoreference();
                            String sendstimes=data.getSendtime();
                            List<String> latitudeList=new ArrayList<>();
                            List<String> longitudeList=new ArrayList<>();
                            List<String> calles1=new ArrayList<>();
                            ////falta agregar punto inicial y punto final
                            if(lislats.split(",").length>50&&lislats.split(",").length==listLongs.split(",").length) {
                                int idslat= lislats.split(",").length/lislats.split(",").length;
                                int idslong= lislats.split(",").length/50;
                                // Log.e("mydaytrips",""+ids);

                                String[] parts1=lislats.split(",");
                                String[] parts2=listLongs.split(",");
                                String[] parts3=calles.split("::");
                                for(int i=0;i<lislats.split(",").length; i=i+idslat)
                                {
                                    String partlat=parts1[i];
                                    String parlong=parts2[i];
                                    String calless=parts3[i];
                                    //latlong.add("lat/lng: ("+parlong+","+partlat+")");///formato de output de latitudes y longitudes
                                    latitudeList.add(parlong);
                                    longitudeList.add(partlat);
                                    calles1.add(calless);
                                }
                                // latitudeList.add(parts1[ (lislats.split(",").length]);
                                // String[] parts2=listLongs.split(",");
                    /*  for(int j=0;j<listLongs.split(",").length; j=j+idslong)
                      {
                          String parlong=parts2[j];
                          longitudeList.add(parlong);
                      }*/
                                //longitudeList.add(parts2[listLongs.split(",").length]);
                                //Log.e("mydaytrips",""+latitudeList.size()+" "+latitudeList.get(latitudeList.size()-1));
                                presenter.setcalles(calles1);
                                presenter.setdatafromlistDayLats(latitudeList);
                                presenter.setdatafromlistDayLongs(longitudeList);
                            }else
                            {
                                int idslat= lislats.split(",").length/lislats.split(",").length;
                                int idslong= lislats.split(",").length/50;
                                // Log.e("mydaytrips",""+ids);

                                String[] parts1=lislats.split(",");
                                String[] parts2=listLongs.split(",");
                                String[] parts3=calles.split("::");
                                for(int i=0;i<lislats.split(",").length; i=i+idslat)
                                {
                                    String partlat=parts1[i];
                                    String parlong=parts2[i];
                                    String calless=parts3[i];
                                    //latlong.add("lat/lng: ("+parlong+","+partlat+")");///formato de output de latitudes y longitudes
                                    latitudeList.add(parlong);
                                    longitudeList.add(partlat);
                                    calles1.add(calless);
                                }
                                // latitudeList.add(parts1[ (lislats.split(",").length]);
                                // String[] parts2=listLongs.split(",");
                    /*  for(int j=0;j<listLongs.split(",").length; j=j+idslong)
                      {
                          String parlong=parts2[j];
                          longitudeList.add(parlong);
                      }*/
                                //longitudeList.add(parts2[listLongs.split(",").length]);
                                //  Log.e("mydaytrips",""+latitudeList+" "+longitudeList);
                                //  Log.e("mydaytrips",""+latitudeList.size()+" "+latitudeList.get(latitudeList.size()-1));
                                presenter.setcalles(calles1);
                                presenter.setdatafromlistDayLats(latitudeList);
                                presenter.setdatafromlistDayLongs(longitudeList);
                                // presenter.setErrorMessage("la informacion del sevidor es erronea");
                            }


                            // presenter.setdatafromlistDay(lislats);
                    /* if(daytrips!=null)
                       {*/

                            // presenter.setTripsBydayToView(daytrips);
                            //}else{   presenter.setErrorMessage(context.getString(R.string.textEmptyTrips));
                            // }*/
                        }else {
                            //presenter.setErrorMessage(context.getString(R.string.textEmptyTrips));
                        }
                    }else{   presenter.setErrorMessage(context.getString(R.string.textEmptyTrips));
                    }
                }else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                    presenter.setErrorMessage(context.getString(R.string.textSessionExpired));

                    UnitDB.deleteDB();
                    GroupDB.deleteDB();
                    RealmUserData.deleteDB();

                    Bundle bndl = new Bundle();
                    bndl.putBoolean("HelpStatus", true);





                } else {
                    presenter.setErrorMessage(tripbytimeResponse.getMessage());
                }
            }else {
                presenter.setErrorMessage(context.getString(R.string.textErrorDataEmptyMap));
            }
        }
    }
    private void getTipsByDayData(Response<TripByDayResponse> response,Context context)
    {
        TripByDayResponse tripbydayResponse=response.body();

        if(tripbydayResponse!=null) {
            int responseCode=tripbydayResponse.getResponseCode();

            if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK){
                TripsbyDayData data=tripbydayResponse.getData();

                if(data!=null)
                {
                    if (!data.getLatitude().isEmpty()){


                        String lislats= data.getLatitude();
                        String listLongs=data.getLongitude();
                        List<String> latitudeList=new ArrayList<>();
                        List<String> longitudeList=new ArrayList<>();
                        List<String> latlong=new ArrayList<>();
                        ////falta agregar punto inicial y punto final
                        if(lislats.split(",").length>50&&lislats.split(",").length==listLongs.split(",").length) {
                            int idslat= lislats.split(",").length/lislats.split(",").length;
                            int idslong= lislats.split(",").length/50;
                            // Log.e("mydaytrips",""+ids);

                            String[] parts1=lislats.split(",");
                            String[] parts2=listLongs.split(",");
                            for(int i=0;i<lislats.split(",").length; i=i+idslat)
                            {
                                String partlat=parts1[i];
                                String parlong=parts2[i];
                                //latlong.add("lat/lng: ("+parlong+","+partlat+")");///formato de output de latitudes y longitudes
                                latitudeList.add(partlat);
                                longitudeList.add(parlong);
                            }
                            // latitudeList.add(parts1[ (lislats.split(",").length]);
                            // String[] parts2=listLongs.split(",");
                    /*  for(int j=0;j<listLongs.split(",").length; j=j+idslong)
                      {
                          String parlong=parts2[j];
                          longitudeList.add(parlong);
                      }*/
                            //longitudeList.add(parts2[listLongs.split(",").length]);
                            //Log.e("mydaytrips",""+latitudeList.size()+" "+latitudeList.get(latitudeList.size()-1));
                            presenter.setdatafromlistDayLats(latitudeList);
                            presenter.setdatafromlistDayLongs(longitudeList);
                        }else
                        {
                            int idslat= lislats.split(",").length/lislats.split(",").length;
                            int idslong= lislats.split(",").length/50;
                            // Log.e("mydaytrips",""+ids);

                            String[] parts1=lislats.split(",");
                            String[] parts2=listLongs.split(",");
                            for(int i=0;i<lislats.split(",").length; i=i+idslat)
                            {
                                String partlat=parts1[i];
                                String parlong=parts2[i];
                                //latlong.add("lat/lng: ("+parlong+","+partlat+")");///formato de output de latitudes y longitudes
                                latitudeList.add(partlat);
                                longitudeList.add(parlong);
                            }
                            // latitudeList.add(parts1[ (lislats.split(",").length]);
                            // String[] parts2=listLongs.split(",");
                    /*  for(int j=0;j<listLongs.split(",").length; j=j+idslong)
                      {
                          String parlong=parts2[j];
                          longitudeList.add(parlong);
                      }*/
                            //longitudeList.add(parts2[listLongs.split(",").length]);
                            //  Log.e("mydaytrips",""+latitudeList+" "+longitudeList);
                            //  Log.e("mydaytrips",""+latitudeList.size()+" "+latitudeList.get(latitudeList.size()-1));
                            presenter.setdatafromlistDayLats(latitudeList);
                            presenter.setdatafromlistDayLongs(longitudeList);
                            // presenter.setErrorMessage("la informacion del sevidor es erronea");
                        }


                        // presenter.setdatafromlistDay(lislats);
                    /* if(daytrips!=null)
                       {*/

                        // presenter.setTripsBydayToView(daytrips);
                        //}else{   presenter.setErrorMessage(context.getString(R.string.textEmptyTrips));
                        // }*/
                    }else {
                        //presenter.setErrorMessage(context.getString(R.string.textEmptyTrips));
                    }
                }else{   presenter.setErrorMessage(context.getString(R.string.textEmptyTrips));
                }
            }else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {

                presenter.setErrorMessage(context.getString(R.string.textSessionExpired));
                UnitDB.deleteDB();
                GroupDB.deleteDB();
                //RealmUserData.deleteDB();

                Bundle bndl = new Bundle();
                bndl.putBoolean("HelpStatus", true);

                Intent intent = new Intent(context, LoginContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bndl);
                context.startActivity(intent);

            } else {
                presenter.setErrorMessage(tripbydayResponse.getMessage());
            }
        }else {
            presenter.setErrorMessage(context.getString(R.string.textErrorDataEmptyMap));
        }
    }
    @Override
    public void getVehicleDescription(int vehicleCve, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        String validation = MapV2Utils.validateVehicleDescriptionDataToRequest(token, vehicleCve);
        presenter.showProgressDialog();
        if (validation.equals(GeneralConstantsV2.SUCCESS)) {
            startVehicleDescriptionRequest(token, vehicleCve, context);
        } else {
            presenter.setErrorMessage(validation);
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
                presenter.setErrorMessage(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateVehicleDescriptionCode(Response<VehicleDescriptionResponse> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehicleDescriptionData(response, context);
        } else {
            presenter.setErrorMessage(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
        }
    }

    private void getVehicleDescriptionData(Response<VehicleDescriptionResponse> response, Context context) {
        VehicleDescriptionResponse vehicleDescriptionResponse = response.body();
        if (vehicleDescriptionResponse != null) {
            int responseCode = vehicleDescriptionResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                VehicleDescriptionData descriptionData = vehicleDescriptionResponse.getData();
                if (descriptionData != null) {
                    presenter.hideProgressDialog();
                    presenter.setVehicleDescriptionToView(descriptionData);
                } else {
                    presenter.hideProgressDialog();
                    presenter.setErrorMessage(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.hideProgressDialog();
//                presenter.setErrorMessage(context.getString(R.string.textSessionExpired));
                UnitDB.deleteDB();
                GroupDB.deleteDB();
                RealmUserData.deleteDB();

                Bundle bndl = new Bundle();
                bndl.putBoolean("HelpStatus", true);

                Intent intent = new Intent(context, LoginContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bndl);
                context.startActivity(intent);


            } else {
                presenter.hideProgressDialog();
                presenter.setErrorMessage(vehicleDescriptionResponse.getMessage());
            }
        } else {
            presenter.hideProgressDialog();
            presenter.setErrorMessage(context.getString(R.string.textEmptyCarsResponse));
        }
    }

    // Trips //

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
                presenter.setErrorMessage(RetrofitValidationsV2.getOnFailureResponse(t, context));
            }
        });
    }

    private void validateTripsCode(Response<TripsV2Response> response, Context context) {
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getTripsData(response, context);
        } else {
            presenter.setErrorMessage(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
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
                        trips = MapV2Utils.orderPositions(trips);
                        trips = MapV2Utils.buildGoogleUrlImage(trips, context);
                        Log.e("tripsroutes",""+trips);
                        presenter.setTripsToView(trips);
                    } else {

                        presenter.setErrorMessage(context.getString(R.string.textEmptyTrips));
                    }
                } else {
                    presenter.setErrorMessage(context.getString(R.string.textEmptyTrips));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {
                presenter.setErrorMessage(context.getString(R.string.textSessionExpired));
                UnitDB.deleteDB();
                GroupDB.deleteDB();
                RealmUserData.deleteDB();

                Bundle bndl = new Bundle();
                bndl.putBoolean("HelpStatus", true);

                    Intent intent = new Intent(context, LoginContainerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bndl);
                    context.startActivity(intent);


            } else {
                presenter.setErrorMessage(tripsV2Response.getMessage());
            }
        } else {
            presenter.setErrorMessage(context.getString(R.string.textErrorDataEmptyMap));
        }
    }
}
