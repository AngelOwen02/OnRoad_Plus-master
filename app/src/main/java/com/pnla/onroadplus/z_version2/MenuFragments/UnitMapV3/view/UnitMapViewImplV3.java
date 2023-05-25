package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.TripsByDay;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.view.UnitMapView;
import com.pnla.onroadplus.z_version2.fragments.mapV2.components.ComponentVehicleHeader;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

public class UnitMapViewImplV3 extends Fragment {/*implements unitMapViewV3, OnMapReadyCallback ,
                                                           GoogleMap.InfoWindowAdapter,GoogleMap.OnInfoWindowClickListener,
                                                           GoogleMap.OnMarkerClickListener,View.OnClickListener{

    //Map
    private MapView mapView;
    private GoogleMap mMap;

    //Header
    private ComponentVehicleHeader componentVehicleHeader;
    private ImageView itemBack, itemRefresh,itemNavigation,itemShowtrips,checkBytime,timer1,timer2,itemhd,itemselflocation,itemTerminal;
    private ConstraintLayout linearLayoutBSheet,timers,btnTrips,btnInfo;

    //progres
    private ProgressBar progressBar;
    //rv fechas
    private RecyclerView rvDates,rvTripsV2;


    private LinearLayout btnTripContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_view_impl, container, false);
        initView(view);
        return view;
    }
    private void initView(View view) {
        initViewID(view);
        initPresenter();
        initOnClickListeners();
        fillVehicleDataHeader();
        bottomSheetSettings();
        buttonRefresh();
    }


    private void initViewID(View view) {
        mapView = view.findViewById(R.id.map);
       //menus de modificaciones
        componentVehicleHeader = view.findViewById(R.id.vehicleDataHeader);
        itemRefresh = view.findViewById(R.id.map_toolbar_item_refresh);
        itemBack = view.findViewById(R.id.map_toolbar_item_back);
        itemNavigation = view.findViewById(R.id.map_toolbar_item_navigation);
        itemShowtrips= view.findViewById(R.id.map_toolbar_item_tripsbyday);
        itemhd=view.findViewById(R.id.hdicon);
        itemhd.setImageResource(R.drawable.hd_1);
        itemselflocation=view.findViewById(R.id.selflocationgps);
        checkBytime= view.findViewById(R.id.consultarButton);
        timer1= view.findViewById(R.id. clockone);
        timer2= view.findViewById(R.id. clocktwo);
        itemTerminal = view.findViewById(R.id.map_toolbar_item_terminal);
        timers= view.findViewById(R.id.constraintLayout4);
        btnTrips = view.findViewById(R.id.btn_viajes);
        btnInfo = view.findViewById(R.id.btn_informacion);

        progressBar = view.findViewById(R.id.unit_map_view_progress_bar);
        rvDates = view.findViewById(R.id.rvDates);
        rvTripsV2 = view.findViewById(R.id.rvEvents);

        btnTripContainer = view.findViewById(R.id.btn_trip_container);
        btnTripTitle = view.findViewById(R.id.btn_trip_title);
        btnInfoContainer = view.findViewById(R.id.btn_info_container);
        btnInfoTitle = view.findViewById(R.id.btn_info_title);
        componentVehicleCustomFields = view.findViewById(R.id.componentVehicleCustomFields);
        separator = view.findViewById(R.id.view123);
        linearLayoutBSheet = view.findViewById(R.id.bottomSheet);
        mytextimer1=view.findViewById(R.id.textimer1);
        mytextimer2=view.findViewById(R.id.textimer2);
        progressDialog = new ProgressDialog(getContext());

    }
    private void initOnClickListeners() {
        btnTrips.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        itemRefresh.setOnClickListener(this);
        itemBack.setOnClickListener(this);
        itemNavigation.setOnClickListener(this);
        itemShowtrips.setOnClickListener(this);
        itemTerminal.setOnClickListener(this);
        itemselflocation.setOnClickListener(this);
        checkBytime.setOnClickListener(this);
        timer1.setOnClickListener(this);
        timer2.setOnClickListener(this);
        itemhd.setOnClickListener(this);
    }
    private void fillVehicleDataHeader() {
        vehicleName = getArguments().getString("vehicleName");
        vehicleImageURL = getArguments().getString("vehicleImage");
        vehicleLat = getArguments().getDouble("vehicleLatitude");
        vehicleLng = getArguments().getDouble("vehicleLongitude");
        vehicleCve = getArguments().getInt("vehicleCve");
        vehicleSwitch = getArguments().getInt("vehicleSwitch");
        String vehicleName = getArguments().getString("vehicleName");
        String vehicleImage = getArguments().getString("vehicleImage");
        valudatebundle =getArguments().getString("vehicleSendTime");

        String[] datemerge=valudatebundle.split(" ");
        dateToday=datemerge[0];//"2020/09/25"; //Component vehicleHeader
        datealternative=dateToday;
        hourdefault1=datemerge[1];//"00:00:00";
        hourdefault2="23:59:00";
        vehicleSendTime =dateToday+" "+hourdefault1;
        vehicleSendTime1=dateToday+" "+"00:00:00";
        vehicleSendTime2=dateToday+" "+"23:59:00";
        timeStart="00:00:00";
        timeEnd="23:59:00";
        Log.e("vehicleSendTime","vehiclesentime startview:"+vehicleSendTime+"  "+vehicleSendTime2);

        String vehicleGeoreference="---- ---- ---- ----";
        if(getArguments().getString("vehicleGeoreference")!=null) {
            vehicleGeoreference = getArguments().getString("vehicleGeoreference");
        }else
        {
            vehicleGeoreference="---- ---- ---- ----";
        }

        String vehicleTimeTravel = getArguments().getString("vehicleTimeTravel");
        double vehicleKmTravel = getArguments().getDouble("vehicleKmTravel");
        double vehicleCurrentSpeed = getArguments().getDouble("vehicleCurrentSpeed");
        String sVehicleCurrentSpeed = String.valueOf(vehicleCurrentSpeed);

        DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
        decimalFormat.format(vehicleKmTravel);
        String decimalUnitKMTravel = decimalFormat.format(vehicleKmTravel);
        Log.e("mvehicleSendTime",""+sVehicleCurrentSpeed);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        initOnMapReady(googleMap);

    }

    private void initOnMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        initPresenterInMap();
    }
    public void buttonRefresh(){
//        presenter.getTripsByTime(vehicleCve,datealternative+" "+timeStart,datealternative+" "+timeEnd,getContext());
//        updateVehicleDataHeader();
//        Log.e("dataclocks","vehiclesentime update:"+vehicleSendTime);
//
//        if (tripsBDx!=null && tripsBDy!=null){
//            String x = tripsBDy.get(tripsBDy.size()-1);
//            Double dx = Double.parseDouble(x);
//            String y = tripsBDx.get(tripsBDx.size()-1);
//            Double dy = Double.parseDouble(y);
//            mainIconMarker.setPosition(new LatLng(dx,dy));
//        }
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                progressDialog.dismiss();
//            }
//        },1500);

    }
    private void bottomSheetSettings() {

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        if (!isClickedDrawTrip) {
                            mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, -350));
                        } else {
                        }
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        isClickedDrawTrip = false;
                        if (!isClickedDrawTrip) {
                            mMap.animateCamera(CameraUpdateFactory.scrollBy(0.0f, 350));
                        } else {
                        }
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void initPresenter() {
        //presenter = new UnitMapPresenterImpl(this, getContext());
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }*/
}
