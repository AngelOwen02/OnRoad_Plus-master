package com.pnla.onroadplus.z_version2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.ClusterTracking;
import com.pnla.onroadplus.z_version2.MenuFragments.TrackingV2.model.Datum;
import com.pnla.onroadplus.z_version2.MenuFragments.TrackingV2.model.gecercasRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.TrackingV2.model.geoCercasResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.FinalUnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitResponse;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.UnitService;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.realmOnRoad.RealmUserData;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitClientV2;
import com.pnla.onroadplus.z_version2.retrofit.RetrofitValidationsV2;
import com.pranavpandey.android.dynamic.utils.DynamicUnitUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TrackingMapInteractorImpl implements TrackingMapInteractor {
    private TrackingMapPresenter presenter;
    private Context context;
    private BitmapDescriptor markerIcon;
    private List<Unit> nullImages = new ArrayList<>();
    private List<Unit> fullImages = new ArrayList<>();
    private UnitService unitService;
    private Handler handler = new Handler();
    private Runnable runnable;
    private List<Unit> allUnitsLista;
    private   ClusterTracking clusterTracking;
    private List<Unit>  allUnitsList=new ArrayList<>();

    private int allUnits = 1;
    private int activeUnits = 2;
    private int activeGroups = 3;
    public TrackingMapInteractorImpl(TrackingMapPresenter presenter, Context context) {
        this.context = context;
        this.presenter = presenter;
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofitClient = RetrofitClientV2.getRetrofitInstance();
        unitService = retrofitClient.create(UnitService.class);
    }
    @Override
    public void getMarkers() {
        allUnitsList = UnitDB.getUnitList();
        allUnitsLista=UnitDB.getUnitList();
        List<Unit> activeUnitsList = UnitDB.getUnitListActive();
        List<Group> activeGroupslist = GroupDB.getActiveGroupList();

        Log.e("unitsfirstscreen","uDB   "+UnitDB.getUnitListActive());
        Log.e("unitsfirstscreen","iL    "+UnitTrackingAdapter.integerList);

      /*  if(UnitDB.getUnitListActive().isEmpty())
        {
            UnitTrackingAdapter.integerList.clear();
        }*/
        SharedPreferences prefs = context.getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
        boolean isFirstLogin = prefs.getBoolean("isFirst", true);
        Log.e("First Login?", String.valueOf(isFirstLogin));

        if (activeUnitsList.isEmpty() && activeGroupslist.isEmpty()) {
            clusterTrackingBuilder(allUnits);
            Log.e("groupsitems"," request units ");

       } else {
            Log.e("groupsitems"," request units/groups ");
            clusterTrackingBuilder(activeUnits);
            clusterTrackingBuilder(activeGroups);
        }
    }


    @Override
    public void eraseeverything() {
        UnitDB.deleteDB();
        createUnitListDB(allUnitsLista);
        Log.e("requestdeunitsagain","erase:"+ UnitDB.getUnitList() );
    }


    private void clusterTrackingBuilder(int requestType) {
        if(UnitDB.getUnitList().isEmpty()==false)
            {
           allUnitsList=UnitDB.getUnitList();
                Log.e("requestdeunitsagain","dab 1 if"+allUnitsLista.toString());
            }
        else {
        Log.e("requestdeunitsagain","dab 1 else"+allUnitsList.toString());
        }
        List<Unit> activeUnitsList = UnitDB.getUnitListActive();
        List<Group> activeGroupslist = GroupDB.getActiveGroupList();
       // Log.e("requestdeunitsagain","cluster:"+ UnitDB.getUnitList()+ allUnitsLista );
        switch (requestType) {
            case 1: // All Units
                if (!allUnitsList.isEmpty()) {
                    for (Unit unit : allUnitsList) {
                        if (unit.getVehicleImage() == null||unit.getVehicleImage()=="null") {
                            nullImages.add(unit);
                        } else {
                            fullImages.add(unit);
                        }
                    }

                    for (final Unit unit : fullImages) {
                        Glide.with(context)
                                .asBitmap()
                                .load(unit.getVehicleImage())
                                .error(R.drawable.sedan)
                                .into(new CustomTarget<Bitmap>(160, 160) {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        View customView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.unit_marker, null);
                                        markerIcon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(customView, 160, 160, resource, unit.getVehicleName(), unit.getVehicleSwitch()));
                                        clusterTracking = new ClusterTracking(new LatLng(unit.getLatitude(), unit.getLongitude()), unit.getVehicleName(), markerIcon);
                                        presenter.putMarkersInMap(clusterTracking);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                    }
                                });
                    }

                    for (final Unit unit : nullImages) {
                        Glide.with(context)
                                .asBitmap()
                                .load(R.drawable.sedan)
                                .error(R.drawable.sedan)
                                .into(new CustomTarget<Bitmap>(160, 160) {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        View customView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.unit_marker, null);
                                        markerIcon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(customView, 160, 160, resource, unit.getVehicleName(), unit.getVehicleSwitch()));
                                        clusterTracking = new ClusterTracking(new LatLng(unit.getLatitude(), unit.getLongitude()), unit.getVehicleName(), markerIcon);
                                        presenter.putMarkersInMap(clusterTracking);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                    }
                                });
                    }
                } else {
                    presenter.hideProgressDialog();
                    presenter.setMessageToView("No cuentas con unidades disponibles para visualizar");
                }
                break;
            case 2: // Active Units
                if (!activeUnitsList.isEmpty()) {
                    for (Unit unit : activeUnitsList) {
                        if (unit.getVehicleImage() == null||unit.getVehicleImage()=="null") {
                            nullImages.add(unit);
                        } else {
                            fullImages.add(unit);
                        }
                    }

                    for (final Unit unit : fullImages) {
                        Glide.with(context)
                                .asBitmap()
                                .load(unit.getVehicleImage())
                                .error(R.drawable.sedan)
                                .into(new CustomTarget<Bitmap>(160, 160) {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        View customView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.unit_marker, null);
                                        markerIcon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(customView, 160, 160, resource, unit.getVehicleName(), unit.getVehicleSwitch()));
                                        ClusterTracking clusterTracking = new ClusterTracking(new LatLng(unit.getLatitude(), unit.getLongitude()), unit.getVehicleName(), markerIcon);
                                        presenter.putMarkersInMap(clusterTracking);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                    }
                                });
                    }

                    for (final Unit unit : nullImages) {
                        Glide.with(context)
                                .asBitmap()
                                .load(R.drawable.sedan)
                                .error(R.drawable.sedan)
                                .into(new CustomTarget<Bitmap>(160, 160) {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        View customView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.unit_marker, null);
                                        markerIcon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(customView, 160, 160, resource, unit.getVehicleName(), unit.getVehicleSwitch()));
                                        ClusterTracking clusterTracking = new ClusterTracking(new LatLng(unit.getLatitude(), unit.getLongitude()), unit.getVehicleName(), markerIcon);
                                        presenter.putMarkersInMap(clusterTracking);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                    }
                                });
                    }
                } else {
                    presenter.hideProgressDialog();
                    presenter.setMessageToView("No hay unidades seleccionadas");
//                    List<Integer> noCves = new ArrayList<>();
//                    noCves.add(0);
//                    startVehiclesRequest(1,noCves,context);
                }
                break;
        /*    case 3: // Active Groups
                List<UnitGroup> groupImages = new ArrayList<>();
                List<UnitGroup> nullGroupImages = new ArrayList<>();
                List<UnitGroup> fullGroupImages = new ArrayList<>();

                for (Group group : activeGroupslist) {
                    for (final UnitGroup unit : group.getVehicles()) {
                        groupImages.add(unit);
                    }
                }

                for (UnitGroup unitGroup : groupImages) {
                    if (unitGroup.getVehicle_image() == null) {
                        nullGroupImages.add(unitGroup);
                    } else {
                        fullGroupImages.add(unitGroup);
                    }
                }

                for (final UnitGroup unit : fullGroupImages) {
                    Glide.with(context)
                            .asBitmap()
                            .load(unit.getVehicle_image())
                            .error(R.drawable.sedan)
                            .into(new CustomTarget<Bitmap>(160, 160) {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    View customView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.unit_marker, null);
                                    markerIcon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(customView, 160, 160, resource, unit.getVehicle_name(), unit.getVehicle_switch()));
                                    ClusterTracking clusterTracking = new ClusterTracking(new LatLng(unit.getLatitude(), unit.getLongitude()), unit.getVehicle_name(), markerIcon);
                                    presenter.putMarkersInMap(clusterTracking);
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {
                                }
                            });
                }

                for (final UnitGroup unit : nullGroupImages) {
                    Glide.with(context)
                            .asBitmap()
                            .load(R.drawable.sedan)
                            .error(R.drawable.sedan)
                            .into(new CustomTarget<Bitmap>(160, 160) {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    View customView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.unit_marker, null);
                                    markerIcon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(customView, 160, 160, resource, unit.getVehicle_name(), unit.getVehicle_switch()));
                                    ClusterTracking clusterTracking = new ClusterTracking(new LatLng(unit.getLatitude(), unit.getLongitude()), unit.getVehicle_name(), markerIcon);
                                    presenter.putMarkersInMap(clusterTracking);
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {
                                }
                            });
                }
                break;*/
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
        }
        else if (vehicleSwitch == 4) {
            circleImageView.setBorderColor(context.getResources().getColor(R.color.colorBlack));
        }else {
            circleImageView.setBorderColor(context.getResources().getColor(R.color.colorBorderCarGray));
        }
    }


    @Override
    public void hidegeoCercas() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            hidegeoCercasCallRequest(1146,token);
        }
    }

    private void hidegeoCercasCallRequest(int cve_object, String token) {
     gecercasRequest request=new gecercasRequest(cve_object,token);
     presenter.showProgressDialog();
     unitService.hideShowgeoCercas(request).enqueue(new Callback<geoCercasResponse>() {
         @Override
         public void onResponse(Call<geoCercasResponse> call, Response<geoCercasResponse> response) {
             validateCodeGeo(response, context);
         }

         @Override
         public void onFailure(Call<geoCercasResponse> call, Throwable t) {

             Toast.makeText(context,  t.getMessage(), Toast.LENGTH_LONG).show();
         }
     });

    }

       private void validateCodeGeo(Response<geoCercasResponse> response, Context context) {
              if(response!=null){
             // Log.e("LAPRINCESS", String.valueOf(response.body().getResponseCode()));
              if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
                  hideShowgeCercasMenu(response, context);
              } else {
                  presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
                  //Toast.makeText(context,  "sesion expirada", Toast.LENGTH_LONG).show();
              }
              }
         }

    private void hideShowgeCercasMenu(Response<geoCercasResponse> response, Context context) {

        geoCercasResponse responsedata= response.body();
        if(responsedata!=null)
        {
            int responseCode=responsedata.getResponseCode();
            if(responseCode==GeneralConstantsV2.RESPONSE_CODE_OK)
            {
                List<Datum> databool=responsedata.getData();
                if(databool!=null)
                {
                    if(databool.get(0).getAccessFlag()==true)
                    {
                        presenter.hideShowIconGeoCercas(databool.get(0).getAccessFlag());
                    }else
                    {
                        presenter.hideShowIconGeoCercas(databool.get(0).getAccessFlag());

                    }
                    presenter.hideProgressDialog();
                }

            }
        }


        /**
         *  UnitResponse unitResponse = response.body();
         *         //Log.e("sasas",String.valueOf(unitResponse));
         *         if (unitResponse != null) {
         *             int responseCode = unitResponse.getResponseCode();
         *             if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
         *                 UnitData data = unitResponse.getData();
         *                 if (data != null) {*/

    }


    @Override
    public void getAllVehiclesFromAPI() {
        final List<Integer> noCves = new ArrayList<>();
        noCves.add(0);

        startVehiclesRequest(GeneralConstantsV2.REQUEST_ALL_VEHICLES, noCves, context);

        Log.e("UnitDB", UnitDB.getUnitList().toString());
        Log.e("doitonce", "" + UnitTrackingAdapter.integerList);
    }

    private void startVehiclesRequest(int typeRequest, List<Integer> vehiclesCves, final Context context) {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        Log.e("requestdeunitsagain","token: "+token);
        // int requesttipe=1;

        UnitRequest request = new UnitRequest(token, 1, vehiclesCves);
        presenter.showProgressDialog();
        unitService.getFullVehicles(request).enqueue(new Callback<UnitResponse>() {
            @Override
            public void onResponse(Call<UnitResponse> call, Response<UnitResponse> response) {
                validateCode(response, context);

            }

            @Override
            public void onFailure(Call<UnitResponse> call, Throwable t) {
                // Log.e("onFailure",t.getLocalizedMessage());
                Toast.makeText(context,  t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    private void validateCode(Response<UnitResponse> response, Context context) {
        if(response!=null){
        Log.e("LAPRINCESS", String.valueOf(response.body().getResponseCode()));
        if (RetrofitValidationsV2.checkSuccessCode(response.code())) {
            getVehiclesData(response, context);
        } else {
            presenter.setMessageToView(RetrofitValidationsV2.getErrorByStatus(response.code(), context));
          //  Toast.makeText(context,  "sesion expirada", Toast.LENGTH_LONG).show();
        }
        }
    }
    private void getVehiclesData(Response<UnitResponse> response, Context context) {
        UnitResponse unitResponse = response.body();
        //Log.e("sasas",String.valueOf(unitResponse));
        if (unitResponse != null) {
            int responseCode = unitResponse.getResponseCode();
            if (responseCode == GeneralConstantsV2.RESPONSE_CODE_OK) {
                UnitData data = unitResponse.getData();
                if (data != null) {

                    List<Unit> unitList = data.getUnitList();
                    //  Log.e("sasas",String.valueOf(unitList.get(0).getVehicleName()));
                    if (unitList.isEmpty()){
                        Toast.makeText(context, "No cuentas con unidades disponibles para visualizar", Toast.LENGTH_SHORT).show();
                    }
                    List<Unit> filteredList = new ArrayList<>();

                    RealmList unitRealmList = new RealmList();

                    for (Unit unit: unitList){
                        if (unit.getLatitude() == 0 && unit.getLongitude() == 0){

                        }else {

                            filteredList.add(unit);


                            unitRealmList.add(unit);
                        }
                    }

                    Log.e("HHHHH", unitRealmList.toString());

                    presenter.setVehiclesListToView(unitRealmList);
                    //  presenter.putMarkersInMap(clusterTracking);

                    presenter.hideProgressDialog();



                    if (unitDBIsEmpty()) {
                        unitSelectedFalse();
                        groupSelectedFalse();
                        firstLoginTrue();
                        changeUnitStatusToFalse(unitList);

                        createUnitListDB(filteredList);

                        SharedPreferences unitPrefs = context.getSharedPreferences("TrackingUnit:Selected", Context.MODE_PRIVATE);
                        boolean isUnitSelected = unitPrefs.getBoolean("isSelected", true);
                        Log.e("Unit Selected?", String.valueOf(isUnitSelected));

                        SharedPreferences groupPrefs = context.getSharedPreferences("TrackingGroup:Selected", Context.MODE_PRIVATE);
                        boolean isGroupSelected = groupPrefs.getBoolean("isSelected", true);
                        Log.e("Group Selected?", String.valueOf(isGroupSelected));


                        if (isUnitSelected){
                            firstLoginFalse();

                        } else if (isGroupSelected){
                            firstLoginFalse();

                        }else {
                            firstLoginTrue();
                        }
                       // allUnitsLista= UnitDB.getUnitList();
                        allUnitsLista= UnitDB.getUnitList();
                        Log.e("requestdeunitsagain","ifemty:"+UnitDB.getUnitList() );
                        //doitAgain();
                    }else {
                        //    updateUnits(unitRealmList);
                        // createUnitListDB(unitRealmList);
                        if(TrackingMapFragment.readytorefreh==true) {
                           UnitDB.deleteDB();
                            createUnitListDB(filteredList);
                            allUnitsLista = UnitDB.getUnitList();

                        }
//                        Log.e("requestdeunitsagain","else size:"+ allUnitsLista.size() );
                         Log.e("requestdeunitsagain","else:"+ UnitDB.getUnitList() );
/*
                        //  Log.e("requestdeunitsagain","aasasas   :"+UnitDB.getUnitList() );
                        SharedPreferences unitPrefs = context.getSharedPreferences("TrackingUnit:Selected", Context.MODE_PRIVATE);
                        boolean isUnitSelected = unitPrefs.getBoolean("isSelected", true);
                        Log.e("Unit Selected?", String.valueOf(isUnitSelected));

                        SharedPreferences groupPrefs = context.getSharedPreferences("TrackingGroup:Selected", Context.MODE_PRIVATE);
                        boolean isGroupSelected = groupPrefs.getBoolean("isSelected", true);
                        Log.e("Group Selected?", String.valueOf(isGroupSelected));


                        if (isUnitSelected){
                            firstLoginFalse();

                        } else if (isGroupSelected){
                            firstLoginFalse();

                        }else {
                            firstLoginTrue();
                        }
                        //*/

                    }
//                   getGroups(context);

                } else {
                    presenter.setMessageToView(context.getString(R.string.textEmptyCarsResponse));
                }
            } else if (responseCode == GeneralConstantsV2.RESPONSE_CODE_SESSION_EXPIRED) {

                UnitDB.deleteDB();
                GroupDB.deleteDB();
                RealmUserData.deleteDB();

                Bundle bndl = new Bundle();
                bndl.putBoolean("HelpStatus", true);

                Intent intent = new Intent(context, LoginContainerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtras(bndl);
             // UnitTrackingAdapter.integerList=null;
                context.startActivity(intent);



                presenter.setMessageToView(context.getString(R.string.textSessionExpired));
            } else {
                presenter.setMessageToView("Error");
            }
        } else {
            presenter.setMessageToView(context.getString(R.string.textEmptyResponse));
        }
    }




    private boolean unitDBIsEmpty() {
        return UnitDB.getUnitList().isEmpty();
    }

    private void createUnitListDB(List<Unit> unitList) {
       // Realm realm = Realm.getDefaultInstance();
        //realm.beginTransaction();
        for (Unit unit : unitList) {

            UnitDB.createNewUnit(unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(), unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(), unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed());
        }
       // realm.commitTransaction();
    }

    private void createFinalUnitListDB(List<Unit> unitList) {
        for (Unit unit : unitList) {
            FinalUnitDB.createNewUnit(unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(), unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(), unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed());
        }
    }

    private boolean groupDBIsEmpty() {
        return GroupDB.getGroupList().isEmpty();
    }

    private void createGroupListDB(List<Group> groupList) {
        for (Group group : groupList) {
            GroupDB.createNewGroup(group.getCve_vehicle_group(), group.getUserGroup(), group.getVehicle_group(), group.getDesc_vehicle_group(), group.isSelected(), group.getPositionItem(), group.getVehicles());
        }
    }

    private void changeUnitStatusToFalse(List<Unit> vehicleList) {
        for (Unit vehicles : vehicleList) {
            vehicles.setVehicleStatus(false);
        }
    }

    private void changeGroupStatusToFalse(List<Group> groupList) {
        for (Group group : groupList) {
            group.setSelected(false);
        }
    }

    private void firstLoginTrue() {
        SharedPreferences prefs = context.getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirst", true);
        editor.commit();
    }

    private void firstLoginFalse() {
        SharedPreferences prefs = context.getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirst", false);
        editor.commit();
    }

    private void unitSelectedFalse() {
        SharedPreferences prefs = context.getSharedPreferences("TrackingUnit:Selected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isSelected", false);
        editor.commit();
    }

    private void groupSelectedFalse() {
        SharedPreferences prefs = context.getSharedPreferences("TrackingGroup:Selected", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isSelected", false);
        editor.commit();
    }

    private void updateUnits(List<Unit> unitList){
        UnitDB.deleteDB();
        createUnitListDB(unitList);
    }

    private void doitAgain()
    {


    }
}