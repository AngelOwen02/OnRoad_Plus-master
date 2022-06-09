package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.maps.android.SphericalUtil;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.CommandsContainerActivity;
import com.pnla.onroadplus.z_version2.Containers.LoginContainer.view.LoginContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.ExternalGPSApp.view.ExternalGPSDialog;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.adapter.TripAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.models.TripsByDay;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.presenter.UnitMapPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.presenter.UnitMapPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;
import com.pnla.onroadplus.z_version2.fragments.mapV2.adapters.AdapterDatesV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.components.ComponentVehicleCustomFields;
import com.pnla.onroadplus.z_version2.fragments.mapV2.components.ComponentVehicleHeader;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.PositionV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.widget.TimePicker;

import static android.content.Context.LOCATION_SERVICE;

public class UnitMapViewImpl extends Fragment implements UnitMapView, GoogleMap.OnInfoWindowClickListener,GoogleMap.OnMarkerClickListener ,GoogleMap.InfoWindowAdapter,OnMapReadyCallback, View.OnClickListener, AdapterDatesV2.OnDateClickListener, TripAdapter.OnClickTripListener {

    //Map//map
    private UnitMapPresenter presenter;
    private GoogleMap mMap;
    private ProgressBar progressBar;
    private Polyline polylineTrip;
    private Marker startMaker;
    private Marker endMarker;
    private Marker mainIconMarker;
    //Vehicle Data//
    private String vehicleName;
    private String valudatebundle;
    private String dateToday;
    private String hourdefault1;
    private String hourdefault2;
    private String vehicleSendTime;
    private String vehicleSendTime1;
    private String vehicleSendTime2;
    private String vehicleImageURL;
    private int vehicleCve;
    private double vehicleLat;
    private double vehicleLng;
    private int vehicleSwitch;
    //Dates//
    private List<DateV2> dates;
    private RecyclerView rvDates;
    //Trips//
    private List<TripV2> trips;
    private List<TripsByDay> daytrips;
    private List<String> tripsBDx;
    private List<String> tripsBDy;
    private List<String> tripsBDx2;
    private List<String> tripsBDy2;
    private List<String> calles;
    private List<String> sendtimes;
    private RecyclerView rvTripsV2;
    private TripAdapter tripAdapter;
    // Components //
    private ComponentVehicleCustomFields componentVehicleCustomFields;
    private View separator;
    private LinearLayout btnTripContainer;
    private TextView btnTripTitle,mytextimer1,mytextimer2;
    private LinearLayout btnInfoContainer;
    private TextView btnInfoTitle;
    private BottomSheetBehavior bottomSheetBehavior;
    private MapView mapView;
    // VehicleV2Map //
    private int cve_vehicle;
    private int vehicle_switch;
    private String vehicle_name;
    private String vehicle_image;
    private String send_time;
    private String desc_brand;
    private String desc_model;
    private String vehicle_year;
    private String vehicle_vin;
    private String vehicle_plate;
    private String georeference;
    private String time_travel;
    private String time_elapsed;
    private double latitude;
    private double longitude;
    private double mileage;
    private double km_travel;
    private double current_speed;
    private double max_speed;
    private ProgressDialog progressDialog;
    private boolean isClickedDrawTrip,swapstate=false;
    private ConstraintLayout linearLayoutBSheet,timers;
    private ConstraintLayout btnTrips;
    private ConstraintLayout btnInfo;
    private ImageView itemBack, itemRefresh;
    private ImageView itemNavigation,itemShowtrips,checkBytime,timer1,timer2,itemhd,itemselflocation;
    private ImageView itemTerminal;
    private ComponentVehicleHeader componentVehicleHeader;

    private List<String> positionListofday= new ArrayList<>();

    final Handler handler = new Handler();
    private Runnable runnable;

    private MarkerOptions mylasmarkerpos;
    private int actualpositionImagemap=0;

    private TimePickerDialog.OnTimeSetListener mDateSetListenr,mDateSetListenr2;

    List<Unit> vehicles;
    int position;
    private List <String> positionsdatetime=new ArrayList<>();
    private int positionfordateontime=0;
    private String timeStart="";
    private String timeEnd="";
    private String dateofField="x";
    private  String datealternative;
    private double HeadingRotation;
    private List<String> listangles;
    private List<List<Double>> HDdoublelist=new ArrayList<>();
    private List<Double> HDlatlongHDRoute=new ArrayList<>();

    private List<List<Float>> resumeDotsfromInteractor=new ArrayList<>();
    private boolean statusHD=false;

    private int locationvar=0;

    private double mylat,mylong;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_view_impl, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }
    private void initPresenter() {
        presenter = new UnitMapPresenterImpl(this, getContext());
    }

    private void initPresenterInMap() {
        presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);
        presenter.zoomVehicleInMap(vehicleLat, vehicleLng);
        presenter.getDates();
        //  presenter.getTripsByDay(vehicleCve,vehicleSendTime,getContext());
        presenter.getTripsByTime(vehicleCve,datealternative+" "+timeStart,datealternative+" "+timeEnd,getContext());
        presenter.getCurrentDateTrip();
        presenter.getVehicleDescription(vehicleCve, getContext());
        fillDatesInMap();
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

    private void initView(View view) {
        initViewID(view);
        initPresenter();
        initOnClickListeners();
        fillVehicleDataHeader();
        bottomSheetSettings();
        buttonRefresh();
    }
    private void makeNewposition()
    {
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {

                // presenter.getTripsByDay(vehicleCve,vehicleSendTime,getContext());
                if (getContext() != null){


                presenter.getTripsByTime(vehicleCve,datealternative+" "+timeStart,datealternative+" "+timeEnd,getContext());
                updateVehicleDataHeader();
                Log.e("findthenull","vehiclesentime pulling:"+vehicleCve+datealternative+" "+timeStart+datealternative+" "+timeEnd);

                //Log.e("mydaytrips1",""+tripsBDy);
                if (tripsBDx!=null && tripsBDy!=null){
                    String x = tripsBDy.get(tripsBDy.size()-1);
                    Double dx = Double.parseDouble(x);
                    String y = tripsBDx.get(tripsBDx.size()-1);
                    Double dy = Double.parseDouble(y);
                    //Log.e("mydaytrips","cada 5"+" "+dx+" "+dy+ " "+tripsBDy.size());
                    //Log.e("mydaytrips",""+tripsBDy.size()+" "+tripsBDy.get(tripsBDy.size()-1));
                    //   mylasmarkerpos.position(new LatLng(dx,dy));
                    // mMap.clear();
                    mainIconMarker.setPosition(new LatLng(dx,dy));
                    //   mylasmarkerpos.position(new LatLng(dx, dy));

                    //  presenter.vehicleMarkerSetup(mylasmarkerpos);
                    // presenter.hideProgressDialog();

                    // presenter.putVehicleMarkerInMap(dx, dy, vehicleName, vehicleImageURL, vehicleSwitch);
                    //   presenter.zoomVehicleInMap(dx, dy);

                    //   Toast.makeText(getContext(), "cada 5", Toast.LENGTH_SHORT).show();
                    //tripsBDx!=null && tripsBDy!=null
                    //!tripsBDx.isEmpty() && !tripsBDy.isEmpty()

                }
                handler.postDelayed(this,7000);
            }
            }
        },7000);
    }
    private void initViewID(View view) {
        mapView = view.findViewById(R.id.map);
        componentVehicleHeader = view.findViewById(R.id.vehicleDataHeader);
        itemRefresh = view.findViewById(R.id.map_toolbar_item_refresh);
        itemBack = view.findViewById(R.id.map_toolbar_item_back);
        itemNavigation = view.findViewById(R.id.map_toolbar_item_navigation);
        itemShowtrips= view.findViewById(R.id.map_toolbar_item_tripsbyday);
        itemTerminal = view.findViewById(R.id.map_toolbar_item_terminal);

        itemselflocation=view.findViewById(R.id.selflocationgps);

        timers= view.findViewById(R.id.constraintLayout4);
        progressBar = view.findViewById(R.id.unit_map_view_progress_bar);
        rvDates = view.findViewById(R.id.rvDates);
        rvTripsV2 = view.findViewById(R.id.rvEvents);
        btnTrips = view.findViewById(R.id.btn_viajes);
        btnInfo = view.findViewById(R.id.btn_informacion);
        btnTripContainer = view.findViewById(R.id.btn_trip_container);
        btnTripTitle = view.findViewById(R.id.btn_trip_title);
        btnInfoContainer = view.findViewById(R.id.btn_info_container);
        btnInfoTitle = view.findViewById(R.id.btn_info_title);
        componentVehicleCustomFields = view.findViewById(R.id.componentVehicleCustomFields);
        separator = view.findViewById(R.id.view123);
        linearLayoutBSheet = view.findViewById(R.id.bottomSheet);
        checkBytime= view.findViewById(R.id.consultarButton);
        timer1= view.findViewById(R.id. clockone);
        timer2= view.findViewById(R.id. clocktwo);
        mytextimer1=view.findViewById(R.id.textimer1);
        mytextimer2=view.findViewById(R.id.textimer2);
        progressDialog = new ProgressDialog(getContext());

        itemhd=view.findViewById(R.id.hdicon);
        itemhd.setImageResource(R.drawable.hd_1);
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

    private void updateVehicleDataHeader(){

        presenter.getVehicleDescription(vehicleCve, getContext());

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
        componentVehicleHeader.setVehicleData(vehicleSwitch, vehicleName, vehicleGeoreference, vehicleTimeTravel, decimalUnitKMTravel, sVehicleCurrentSpeed, vehicleImage, vehicleSendTime);
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


    @Override
    public void setMarkerOptions(MarkerOptions markerOptions) {

        this.mylasmarkerpos=markerOptions;

        mainIconMarker= mMap.addMarker(markerOptions);
        makeNewposition();
    }

    @Override
    public void setZoomOptions(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    @Override
    public void setCurrentDate(String currentDate) {
        presenter.getTripsByDate(vehicleCve, currentDate, getContext());
        Log.e("dataclocks","date:"+currentDate);
        dateofField=currentDate;

    }

    @Override
    public void fillTripsToView(List<TripV2> trips) {
        this.trips = trips;
        fillTripsInMap();

    }

    @Override
    public void fillTripsbydayToView(List<TripsByDay> daytriips) {
        this.daytrips=daytriips;

    }

    @Override
    public void fillStringTipsbyDaylat(List<String> TbDx) {
        this.tripsBDx=TbDx;
        createListfromTripslong();

    }
    @Override
    public void fillStringTipsbyDaylong(List<String> TbDy) {
        this.tripsBDy=TbDy;
        createListfromTripslat();

    }

    @Override
    public void drawtripdbxbdy(List<String> xdots, List<String> ydots) {
        this.tripsBDx2=ydots;
        this.tripsBDy2=xdots;
        snapedExternalapidots();
    }

    @Override
    public void fillStringcalles(List<String> calles) {
        this.calles=calles;
    }

    private void fillTripsInMap() {
        rvTripsV2.setNestedScrollingEnabled(false);
        tripAdapter = new TripAdapter(vehicleName, trips, getContext());
        tripAdapter.setOnClickTripListener(UnitMapViewImpl.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTripsV2.setLayoutManager(layoutManager);
        rvTripsV2.setAdapter(tripAdapter);
       // Toast.makeText(getContext(),""+(trips.size()),Toast.LENGTH_SHORT);
        //Log.e("tripsdimens",""+trips.size());
    }

    @Override
    public void fillDatesList(List<DateV2> dates) {
        this.dates = dates;
        fillDatesInMap();

    }

    public void buttonRefresh(){

        //Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();

        //presenter.getTripsByDay(vehicleCve,vehicleSendTime,getContext());
        presenter.getTripsByTime(vehicleCve,datealternative+" "+timeStart,datealternative+" "+timeEnd,getContext());
        updateVehicleDataHeader();
        Log.e("dataclocks","vehiclesentime update:"+vehicleSendTime);

        if (tripsBDx!=null && tripsBDy!=null){
            String x = tripsBDy.get(tripsBDy.size()-1);
            Double dx = Double.parseDouble(x);
            String y = tripsBDx.get(tripsBDx.size()-1);
            Double dy = Double.parseDouble(y);
            mainIconMarker.setPosition(new LatLng(dx,dy));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },1500);

    }

    public void showDialog(){
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Actualizando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    public void hideDialog(){
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.dismiss();
    }

    @Override
    public void fillVehicleDescription(VehicleDescriptionData data)  {
        componentVehicleCustomFields.setVehicleDescription(data);
        vehicle_switch = getArguments().getInt("vehicleSwitch");
        double vehicleKmTravel = getArguments().getDouble("vehicleKmTravel");
        double vehicleCurrentSpeed = getArguments().getDouble("vehicleCurrentSpeed");
        String sVehicleCurrentSpeed = String.valueOf(vehicleCurrentSpeed);

        vehicleName = getArguments().getString("vehicleName");
        DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
        decimalFormat.format(vehicleKmTravel);
        String decimalUnitKMTravel = decimalFormat.format(vehicleKmTravel);
        int lastSwitch = data.getVehicle_switch();
        //Log.e("switch",""+vehicle_switch+"  "+lastSwitch);
        //Log.e("lastswitch",""+lastSwitch);
        String lastKm = data.getVehicle_km();
        String lastSpeed = data.getVehicle_speed();
        double d = Double.parseDouble(lastKm);
        double d1 = Double.parseDouble(lastSpeed);
        String lastkm = decimalFormat.format(d);
        String lastspeed = decimalFormat.format(d1);
        //String lastName = data.getVehicleName();
        String lastSendTime = data.getLastMessage();
        String[] string = lastSendTime.split(" ");
        String date1 = string[0];
        String time1 = string[1];
        //Log.e("time1",""+time1);
        String lastAddress = data.getAddress();
        componentVehicleHeader.setVehicleData(lastSwitch,vehicleName,lastAddress,time1,lastkm,lastspeed,vehicleImageURL,lastSendTime);
    }

    private void fillDatesInMap() {
        rvDates.setNestedScrollingEnabled(false);
        AdapterDatesV2 adapterDatesV2 = new AdapterDatesV2(dates, getContext());
        adapterDatesV2.setOnClickDateListener(UnitMapViewImpl.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDates.setLayoutManager(layoutManager);
        rvDates.setAdapter(adapterDatesV2);
    }

    @Override
    public void showRouteWithExternalApps(double lat, double lng) {
        ExternalGPSDialog externalGPSDialog = new ExternalGPSDialog();
        externalGPSDialog.setLocationVehicle(lat, lng);
        externalGPSDialog.show(getActivity().getSupportFragmentManager(), ExternalGPSDialog.TAG);
    }

    @Override
    public void showCommandScreen() {
        Bundle bndlCommands;
        bndlCommands = new Bundle();
        bndlCommands.putInt("vehicleCve", vehicleCve);
        bndlCommands.putString("vehicleName", vehicleName);
        if (vehicleSwitch == 0){
            Toast.makeText(getContext(), "La unidad se encuentra desconectada.", Toast.LENGTH_SHORT).show();
            itemTerminal.setActivated(false);
        } else {
            Intent intent = new Intent(getContext(), CommandsContainerActivity.class);
            intent.putExtras(bndlCommands);
            startActivity(intent);
        }

    }

    @Override
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopallhandlers() {
        handler.removeCallbacks(runnable);
        Intent intent = new Intent(getContext(), LoginContainerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(getActivity().getIntent());
        getContext().startActivity(intent);

    }

    @Override
    public void hideProgressDialog() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setErrorMessage(String message) {
        if(message!=null) {
            Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void drawResumeDots(List<List<Float>> resumeDots) {
        resumeDotsfromInteractor=resumeDots;
        HDdots();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map_toolbar_item_refresh:
                showDialog();
                buttonRefresh();
                break;
            case R.id.map_toolbar_item_back:
                goToMainMenuContainer();
                //handler.removeCallbacks(runnable);
                timers.setVisibility(View.GONE);
                break;
            case R.id.map_toolbar_item_navigation:
                showRouteWithExternalApps(vehicleLat, vehicleLng);
                timers.setVisibility(View.GONE);
                break;
            case R.id.map_toolbar_item_terminal:
                showCommandScreen();
                timers.setVisibility(View.GONE);
                break;
            case R.id.map_toolbar_item_tripsbyday:

                showtripsByday();
                if(swapstate==false) {
                    timers.setVisibility(View.VISIBLE);
                    swapstate=true;
              //      itemhd.setImageResource(R.drawable.ic_hd);
                }else
                {
                    deletePolylineAndTripMarkers();
                    mMap.clear();

                    //  presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);
                    timers.setVisibility(View.GONE);
                    swapstate=false;
                }
                break;

            case R.id.btn_viajes:
                tripButtonInit();
                timers.setVisibility(View.GONE);

                break;
            case R.id.btn_informacion:
                infoButtonInit();
                timers.setVisibility(View.GONE);
                break;
            case R.id.consultarButton:/// esto pertenece al boton de consultar que mueve el rango de tiempo

                deletePolylineAndTripMarkers();
                mMap.clear();

                //  Log.e("dataclocks4",""+datealternative);
                // Log.e("dataclocks4","start "+timeStart);
                //Log.e("dataclocks4","end "+timeEnd);
                showtripsByday2();
                //presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);
                /*if(chekbyahour==false){
                    chekbyahour=true;
                }
                else
                {
                    chekbyahour=false;
                }*/

                //  Toast.makeText(getContext(), "changeState"+chekbyahour, Toast.LENGTH_SHORT).show();
                break;
            case R.id.clockone:
                shotimer1();
                break;
            case R.id.clocktwo:
                shotimer2();
                //Toast.makeText(getContext(), "timer2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.selflocationgps:
                locationvar=locationvar+1;
               // Log.e("BOTONLOCATION","Location lat: "+ vehicleLat+"  long:  "+ vehicleLng);
                if(locationvar==2)
                {

                    locationvar=0;
                    Log.e("BOTONLOCATION","0    "+locationvar);
                    itemselflocation.setImageResource(R.drawable.ic_miubicacionoff);
                    deletePolylineAndTripMarkers();
                    mMap.clear();
                    presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(vehicleLat, vehicleLng), 16.5f));

                }else if(locationvar==1)
                {
                    Log.e("BOTONLOCATION","1    "+locationvar);
                    itemselflocation.setImageResource(R.drawable.ic_miubicacionon);
                    checkmylocation();
                }
                else
                {
                    Log.e("BOTONLOCATION","else case");
                }



                break;
            case R.id.hdicon:
                Log.e("externalApimaps","data full list: "+ HDdoublelist);
               // itemhd.getDrawable().
                if(!HDdoublelist.isEmpty()) {/**si la lista esta vacia*/


                    if(statusHD==false)
                    {
                        Log.e("buttonHD","enter here true mode");
                        itemhd.setImageResource(R.drawable.ic_hd);
                        presenter.getexternalApi(HDdoublelist);
                        statusHD=true;
                    }else
                    {
                        itemhd.setImageResource(R.drawable.hd_1);
                        deletePolylineAndTripMarkers();
                        mMap.clear();
                        presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);
                        statusHD=false;

                        timers.setVisibility(View.GONE);
                        swapstate=false;
                        HDdoublelist.clear();
                    }

                }
                else
                {

//                    statusHD=false;
//                    if(statusHD==true)
//                    {
//                        Log.e("buttonHD","enter here false mode ");
//
//                    }
                }
                // Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();
                break;


        }
    }

    LocationListener locationListenerGPS=new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            double latitude=location.getLatitude();
            double longitude=location.getLongitude();
            String msg="New Latitude: "+latitude + "New Longitude: "+longitude;
            // Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    private void checkmylocation()
    {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);

        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSEnabled) {
            Location location = null;
            if (location == null) {
                //check the network permission
                if (ActivityCompat.checkSelfPermission(this.getContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((this.getActivity()), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
                }

                // LocationListener locationListenerGPS = null;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,5,locationListenerGPS );

                Log.d("GPS Enabled", "GPS Enabled");
                if (locationManager != null) {
                    location = locationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        Log.e("findlocation","  Lat:"+latitude+"   Long: "+ longitude);
                        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.5f));
                        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bluedot);
                        Bitmap b = bitmapdraw.getBitmap();
                        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 60, 60, false);
                        LatLng point = new LatLng(latitude, longitude);
                        mMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                        //startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet(String.valueOf(calles.get(0))));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.5f));
                        mylat=latitude;
                        mylong=longitude;
                               LatLngBounds.Builder builder = new LatLngBounds.Builder();
                                        LatLng dot = new LatLng(latitude, longitude);
                                        builder.include(dot);
                                        LatLng dot2=new LatLng(vehicleLat,vehicleLng);
                                        builder.include(dot2);

                                    LatLngBounds bounds = builder.build();
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));


                    }
                }
            }
        }

    }
    private void HDdots()
    {
      Log.e("externalApimaps","drawdata "+ resumeDotsfromInteractor);
      deletePolylineAndTripMarkers();
      mMap.clear();
      presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        PolylineOptions options = new PolylineOptions().width(8).color(Color.BLACK).geodesic(true);

        for (int z = 0; z < resumeDotsfromInteractor.size(); z++) {

           // String[] parts=resumeDotsfromInteractor.get(0).contains(",");
            String[] parts=String .valueOf(resumeDotsfromInteractor.get(z)).split(",");
            String[] partlongitude=parts[0].split("\\[");
            String[] partlatitude=parts[1].split("\\]");
            Double dx = Double.parseDouble(partlatitude[0]);
            Double dy = Double.parseDouble(partlongitude[1]);
            LatLng point = new LatLng(dx, dy);
            builder.include(point);
            options.add(point);
          //  Log.e("externalApimaps","drawdata sublist0  "+partlatitude[0]+"   "+ partlongitude[1]);
          //  Log.e("externalApimaps","drawdata sublist "+resumeDotsfromInteractor.get(z));
        }
            LatLngBounds bounds = builder.build();
            int padding = 50;
            final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);
            polylineTrip = mMap.addPolyline(options);
        Log.e("externalApimaps","normal dots size "+tripsBDx.size()+ "   "+tripsBDx.get(0));

        snapedExternalapidots();

    }

    private void snapedExternalapidots()
    {


        int height = 20;
        int width = 20;
        String initialposx = tripsBDy2.get(0);
        String initialposy = tripsBDx2.get(0);
        Double iPx = Double.parseDouble(initialposx);
        Double iPy = Double.parseDouble(initialposy);
        LatLng notificationPosition = new LatLng(iPx, iPy);
        //Log.e("mydaytrips",""+positions.get(0).getLatitude()+" "+positions.get(0).getLongitude());
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.green_dot);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet(String.valueOf(calles.get(0))));
        listangles=new ArrayList<>();
        for(int j=0;j<tripsBDx2.size();j++)

        {

            if(j==0){

            }
            else if(j==tripsBDx2.size())
            {

            }
            else
            {
                height = 15;
                width = 15;
                String x = tripsBDy2.get(j);
                Double dx = Double.parseDouble(x);
                String y = tripsBDx2.get(j);
                Double dy = Double.parseDouble(y);
                notificationPosition= new LatLng(dx, dy);
                HeadingRotation= SphericalUtil.computeHeading(new LatLng(Double.parseDouble(tripsBDy2.get(j-1)),Double.parseDouble(tripsBDx2.get(j-1))),new LatLng(Double.parseDouble(tripsBDy2.get(j)),Double.parseDouble(tripsBDx2.get(j))) );
                listangles.add(String.valueOf(HeadingRotation));
                bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arrowe);//.start_marker);
                b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title(vehicleName).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).rotation(Float.valueOf(listangles.get(j-1))-90f)
                        .anchor(.5f,.5f).snippet(String.valueOf(calles.get(j))));//+","

            }

        }
        Log.e("datafroangles" +"",""+listangles);
        height = 30;
        width = 30;
        String initialposxf = tripsBDy2.get(tripsBDy2.size() - 1);
        String initialposyf = tripsBDx2.get(tripsBDx2.size() - 1);
        Double iPxf = Double.parseDouble(initialposxf);
        Double iPyf = Double.parseDouble(initialposyf);
        notificationPosition = new LatLng(iPxf, iPyf);
        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.end_marker);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        endMarker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
    }

    private void goToMainMenuContainer() {
        Bundle bndle = new Bundle();
        bndle.putString("nav", "UNITS");
        Intent intent = new Intent(getContext(), menuViewImpl.class);// MainMenuContainerActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bndle);
        startActivity(intent);
    }

    @Override
    public void onDate(String date, int position) {
        if (tripAdapter != null && tripAdapter.getItemCount() > 0) {
            tripAdapter.clearRecyclerView();
        }
        presenter.getTripsByDate(vehicleCve, date, getContext());

    }

    private void tripButtonInit() {
        btnInfoContainer.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        btnInfoTitle.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
        btnTripContainer.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        btnTripTitle.setTextColor(getResources().getColor(R.color.colorWhite));

        separator.setVisibility(View.VISIBLE);
        componentVehicleCustomFields.setVisibility(View.GONE);
        rvDates.setVisibility(View.VISIBLE);
        rvTripsV2.setVisibility(View.VISIBLE);


    }

    private void infoButtonInit() {
        btnTripContainer.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        btnTripTitle.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
        btnInfoContainer.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        btnInfoTitle.setTextColor(getResources().getColor(R.color.colorWhite));

        separator.setVisibility(View.GONE);
        componentVehicleCustomFields.setVisibility(View.VISIBLE);
        rvDates.setVisibility(View.GONE);
        rvTripsV2.setVisibility(View.GONE);
    }

    private void drawTrip(List<PositionV2> positions) {
        //Log.e("postrips",""+positions.size());
        deletePolylineAndTripMarkers();
        mMap.clear();
        presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        PolylineOptions options = new PolylineOptions().width(8).color(Color.BLACK).geodesic(true);
        Log.e("externalApimaps",""+positions.size());
        for (int z = 0; z < positions.size(); z++) {
            LatLng point = new LatLng(positions.get(z).getLatitude(), positions.get(z).getLongitude());
            builder.include(point);
            options.add(point);
        }

        LatLngBounds bounds = builder.build();
        int padding = 50;
        final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);
        polylineTrip = mMap.addPolyline(options);

        int height = 20;
        int width = 20;
        LatLng notificationPosition = new LatLng(positions.get(0).getLatitude(), positions.get(0).getLongitude());
        //Log.e("mydaytrips",""+positions.get(0).getLatitude()+" "+positions.get(0).getLongitude());
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.start_marker);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

      /* for(int i=0;i<positions.size();i++)
        {
            if(i==0){

            }
            else if(i==positions.size())
            {

            }
            else
            {
                height = 18;
                width = 18;
                notificationPosition = new LatLng(positions.get(i).getLatitude(), positions.get(i).getLongitude());
             //   trips.get(i).getDescriptionTrip();
                //Log.e("mydaytrips",""+positions.get(0).getLatitude()+" "+positions.get(0).getLongitude());
               bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.start_marker);
                 b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition)//.title(vehicleName)//.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
             //   .anchor(.5f,.5f).snippet(String.valueOf(positions.get(i).getLatitude()+"\t"+","+positions.get(i).getLongitude()+","
              //  +positions.get(i).getSend_time()/*+trips.get(actualpositionImagemap).getHourTrip())).infoWindowAnchor(.5f,.5f));
              //  startMaker.showInfoWindow();

               // positionfordateontime=i;
            }
            positionsdatetime.clear();
            positionsdatetime.add(positions.get(i).getSend_time());
            Log.e("datapointspositions",""+positions.get(i).getSend_time());
        }/*/

        height = 30;
        width = 30;
        notificationPosition = new LatLng(positions.get(positions.size() - 1).getLatitude(), positions.get(positions.size() - 1).getLongitude());
        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.end_marker);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        endMarker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        // Toast.makeText(getContext(),""+(trips.size()),Toast.LENGTH_SHORT);
        //Log.e("poliline","ahita la raya");
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onClickGoogleImage(View view, int position) {//trips te da el numero de viajes en imagenes de minimapa
        List<PositionV2> positionsToDraw = trips.get(position).getPositions();
        actualpositionImagemap=position;
        //Toast.makeText(getContext(), "clicka="+actualpositionImagemap, Toast.LENGTH_LONG).show();
        isClickedDrawTrip = true;
        if (positionsToDraw != null) {
            deletePolylineAndTripMarkers();
            drawTrip(positionsToDraw);
        } else {
            Toast.makeText(getContext(), getString(R.string.textNotFoundTrip), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClickFinalPosition(View view, int position) {//esto es un campo de las vistas de lso trips
        int height = 40;
        int width = 40;

        //   Log.e("tripsroutes","click");

        deletePolylineAndTripMarkers();
        mMap.clear();
        double latitude = trips.get(position).getPositions().get(trips.get(position).getPositions().size() - 1).getLatitude();
        double longitude = trips.get(position).getPositions().get(trips.get(position).getPositions().size() - 1).getLongitude();
        LatLng notificationPosition = new LatLng(latitude, longitude);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.end_marker);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        zoomToVehicle(latitude, longitude, GeneralConstantsV2.VEHICLE_FINAL_POSITION);

        presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void zoomToVehicle(double latitude, double longitude, float zoom) {
        if (latitude != 0.0 && longitude != 0.0) {
            LatLng notificationPosition = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(notificationPosition, zoom));
        }
    }

    private void deletePolylineAndTripMarkers() {
        if (polylineTrip != null) {
            polylineTrip.remove();
        }
        if (startMaker != null) {
            startMaker.remove();
        }
        if (endMarker != null) {
            endMarker.remove();
        }
    }
    private void readtripsonView()
    {
        //  Log.e("mydaytrips",""+tripsBDx);
        //Log.e("mydaytrips1",""+tripsBDy);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        PolylineOptions options = new PolylineOptions().width(8).color(Color.BLACK).geodesic(true);
        HDdoublelist.clear();
        if ( !tripsBDx.isEmpty() && !tripsBDy.isEmpty()) {
            deletePolylineAndTripMarkers();
            //Log.e("tripsbdx",String.valueOf(tripsBDx));
            //Log.e("tripbdy",String.valueOf(tripsBDy));
            for (int z = 0; z < tripsBDx.size(); z++) {
                String x = tripsBDy.get(z);
                Double dx = Double.parseDouble(x);
                String y = tripsBDx.get(z);
                Double dy = Double.parseDouble(y);
                HDlatlongHDRoute =new ArrayList<>();
                HDlatlongHDRoute.add(dy);
                HDlatlongHDRoute.add(dx);

                HDdoublelist.add(HDlatlongHDRoute);

                LatLng point = new LatLng(dx, dy);
                builder.include(point);
                options.add(point);
            }
           Log.e(" externalApimaps",""+tripsBDx.size()+ "  "+tripsBDx);

            LatLngBounds bounds = builder.build();
            int padding = 50;
            final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.animateCamera(cu);
            polylineTrip = mMap.addPolyline(options);
            int height = 20;
            int width = 20;
            String initialposx = tripsBDy.get(0);
            String initialposy = tripsBDx.get(0);
            Double iPx = Double.parseDouble(initialposx);
            Double iPy = Double.parseDouble(initialposy);
            LatLng notificationPosition = new LatLng(iPx, iPy);
            //Log.e("mydaytrips",""+positions.get(0).getLatitude()+" "+positions.get(0).getLongitude());
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.green_dot);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).snippet(String.valueOf(calles.get(0))));
            listangles=new ArrayList<>();
            for(int j=0;j<tripsBDx.size();j++)

            {

                if(j==0){

                }
                else if(j==tripsBDx.size())
                {

                }
                else
                {
                    height = 15;
                    width = 15;
                    String x = tripsBDy.get(j);
                    Double dx = Double.parseDouble(x);
                    String y = tripsBDx.get(j);
                    Double dy = Double.parseDouble(y);
                    notificationPosition= new LatLng(dx, dy);
                    HeadingRotation= SphericalUtil.computeHeading(new LatLng(Double.parseDouble(tripsBDy.get(j-1)),Double.parseDouble(tripsBDx.get(j-1))),new LatLng(Double.parseDouble(tripsBDy.get(j)),Double.parseDouble(tripsBDx.get(j))) );

                    listangles.add(String.valueOf(HeadingRotation));

                    // notificationPosition = new LatLng(tripsBDx.get(j).getLatitude(), positions.get(i).getLongitude());
                    //   trips.get(i).getDescriptionTrip();
                    //Log.e("mydaytrips",""+positions.get(0).getLatitude()+" "+positions.get(0).getLongitude());

                 /*   if ((listangles.get(j - 1) >= 0 && listangles.get(j - 1) < 22.5) || (listangles.get(j - 1) >= 337.5 && listangles.get(j - 1) <= 359)) {
                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arrowe);

                    } else if ( listangles.get(j - 1) >= 22.5 && listangles.get(j - 1) < 67.5) {
                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arrowne);
                    } else if (listangles.get(j - 1) >= 67.5 && listangles.get(j - 1) < 112.5) {
                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arrown);
                    } else if (listangles.get(j - 1) >= 112.5 && listangles.get(j - 1) < 157.5) {
                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arrownw);
                    } else if (listangles.get(j - 1) >= 157.5 && listangles.get(j - 1) < 202.5) {
                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arroww);
                    } else if (listangles.get(j - 1) >= 202.5 && listangles.get(j - 1) < 247.5) {
                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arrowsw);
                    } else if (listangles.get(j - 1) >= 247.5 && listangles.get(j - 1) < 292.5) {
                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arrows);
                    } else if (listangles.get(j - 1) >= 292.5 && listangles.get(j - 1) < 337.5) {
                        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arrowse);
                    }
                    else
                    {
                       // height = 18;
                      //  width = 18;
                       // bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.start_marker);
                    }*/

                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.arrowe);//.start_marker);
                    b = bitmapdraw.getBitmap();
                    smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                    startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title(vehicleName).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).rotation(Float.valueOf(listangles.get(j-1))-90f)
                        .anchor(.5f,.5f).snippet(String.valueOf(calles.get(j))));//+","
                    //   +positions.get(i).getSend_time()/+trips.get(actualpositionImagemap).getHourTrip()/)).infoWindowAnchor(.5f,.5f));
                    //  startMaker.showInfoWindow();

                    // positionfordateontime=i;
                }


                // positionsdatetime.clear();
                // positionsdatetime.add(positions.get(i).getSend_time());
                // Log.e("datapointspositions",""+positions.get(i).getSend_time());

            }
            Log.e("datafroangles" +"",""+listangles);
            height = 30;
            width = 30;
            String initialposxf = tripsBDy.get(tripsBDy.size() - 1);
            String initialposyf = tripsBDx.get(tripsBDx.size() - 1);
            Double iPxf = Double.parseDouble(initialposxf);
            Double iPyf = Double.parseDouble(initialposyf);
            notificationPosition = new LatLng(iPxf, iPyf);
            bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.end_marker);
            b = bitmapdraw.getBitmap();
            smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            endMarker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));


        }else {
            Toast.makeText(getContext(), "No cuentas con viajes del día.", Toast.LENGTH_SHORT).show();
        }
    }
    private  void createListfromTripslong() {//metodos longs




    }
    private  void createListfromTripslat() {//lats




    }
    private void showtripsByday()//boton de viajes por dia
    {
        deletePolylineAndTripMarkers();
        mMap.clear();
        presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);

        if (tripsBDx!=null && tripsBDy!=null){
            readtripsonView();
            //  Log.e("mydaytrips",""+tripsBDy.size()+" "+tripsBDy.get(tripsBDy.size()-1));
            //   presenter.getTripsByDay(vehicleCve,vehicleSendTime,getContext());
            presenter.getTripsByTime(vehicleCve,datealternative+" "+timeStart,datealternative+" "+timeEnd,getContext());
            Log.e("dataclocks","vehiclesentime getfrominteractor:"+vehicleSendTime);

        } else {
            Toast.makeText(getContext(), "La unidad no cuenta con viajes del día.", Toast.LENGTH_SHORT).show();
        }
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        if(trips!=null) {
            // Toast.makeText(getContext(), "" + (trips.size()), Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getContext(), ""+positionList, Toast.LENGTH_SHORT).show();
    }

    private void showtripsByday2()//boton de viajes por dia
    {
        deletePolylineAndTripMarkers();
        mMap.clear();
        presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);

        if (tripsBDx!=null && tripsBDy!=null){
            readtripsonView();
            presenter.getTripsByTime(vehicleCve,datealternative+" "+timeStart,datealternative+" "+timeEnd,getContext());
            //  Log.e("mydaytrips",""+tripsBDy.size()+" "+tripsBDy.get(tripsBDy.size()-1));
            //   presenter.getTripsByDay(vehicleCve,vehicleSendTime,getContext());

            Log.e("dataclocks","vehiclesentime getfrominteractor:"+vehicleSendTime);

        } else {
            Toast.makeText(getContext(), "La unidad no cuenta con viajes del día.", Toast.LENGTH_SHORT).show();
        }
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        if(trips!=null) {
            // Toast.makeText(getContext(), "" + (trips.size()), Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getContext(), ""+positionList, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,12000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
    private void shotimer1() {
        /*Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
      //  boolean day = calendar.get(Calendar.AM_PM);
        TimePickerDialog datePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth ,mDateSetListenr, hour, minute, android.text.format.DateFormat.is24HourFormat(getContext()));
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
        mytextimer1.setText(hour+":"+minute);*/
        TimePickerDialog mTimePicker;

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);

        mTimePicker = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {


                String time = selectedHour + ":" + selectedMinute;

                SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                Date date = null;
                try {
                    date = fmt.parse(time);
                } catch (ParseException e) {

                    e.printStackTrace();
                }

                SimpleDateFormat fmtOut = new SimpleDateFormat("HH:mm:ss");

                String formattedTime = fmtOut.format(date);
                timeStart=formattedTime;
                setTimers();
                mytextimer1.setText(formattedTime);
            }
        }, hour, minute,true);//No 24 hour time
        mTimePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mTimePicker.setTitle("Selecciona un horario");
        mTimePicker.show();

    }
    private void shotimer2() {
        /*Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        //  boolean day = calendar.get(Calendar.AM_PM);
        TimePickerDialog datePickerDialog = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth ,mDateSetListenr2, hour, minute, android.text.format.DateFormat.is24HourFormat(getContext()));
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
        mytextimer2.setText(hour+":"+minute);*/
        TimePickerDialog mTimePicker;

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        mTimePicker = new TimePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth , new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {


                String time = selectedHour + ":" + selectedMinute;

                SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                Date date = null;
                try {
                    date = fmt.parse(time);
                } catch (ParseException e) {

                    e.printStackTrace();
                }

                SimpleDateFormat fmtOut = new SimpleDateFormat("HH:mm:ss");

                String formattedTime = fmtOut.format(date);
                timeEnd=formattedTime;
                setTimers();
                mytextimer2.setText(formattedTime);
            }
        }, hour, minute,true);//No 24 hour time
        mTimePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mTimePicker.setTitle("Selecciona un horario");
        mTimePicker.show();  //  mytextimer2.setText(hour+":"+minute);
    }

    private void setTimers() {

/*
         deletePolylineAndTripMarkers();
        mMap.clear();
        Log.e("dataclocks4",""+datealternative);
        Log.e("dataclocks4","start "+timeStart);
        Log.e("dataclocks4","end "+timeEnd);
        showtripsByday2();
         presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);*/
    }


    @Override
    public void onInfoWindowClick(Marker marker) {

        // Toast.makeText(getContext(), "Info window clicked",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // Toast.makeText(getContext(), "marker clicked",Toast.LENGTH_SHORT).show();

        Log.e("datapointspositions",""+marker.getPosition().latitude+ "   "+  String.valueOf( marker.getSnippet()));
        // getInfoWindow(marker);
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View getInfoWindow(Marker marker) {
        return prepareInfoView( marker);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View getInfoContents(Marker marker) {
        return prepareInfoView( marker);
    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint({"ResourceAsColor", "WrongConstant"})
    private View prepareInfoView(Marker marker){

        //prepare InfoView programmatically
        LatLng mypointlatlong = new LatLng(mylat, mylong);

        Log.e("infoviewmylocation","markerlat"+marker.getPosition().latitude+"  markerlong"+ marker.getPosition().longitude+"  reallat: "+mylat+"  realong: "+mylong);

        if(marker.getPosition().latitude!=mylat) {

            LinearLayout infoView = new LinearLayout(getContext());
            LinearLayout.LayoutParams infoViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            infoViewParams.leftMargin = 10;
            infoViewParams.rightMargin = 10;
            infoView.setOrientation(LinearLayout.HORIZONTAL);
            infoView.setBackground(getResources().getDrawable(R.drawable.ic_ubicacionl));
            infoView.setLayoutParams(infoViewParams);

       /* ImageView infoImageView = new ImageView(getContext());
        //Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        Drawable drawable = getResources().getDrawable(android.R.drawable.ic_dialog_map);
        infoImageView.setImageDrawable(drawable);
        infoView.addView(infoImageView);*/

            LinearLayout subInfoView = new LinearLayout(getContext());
            LinearLayout.LayoutParams subInfoViewParams = new LinearLayout.LayoutParams(

                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            subInfoView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.clearBackgroundDialog));
            subInfoView.setOrientation(LinearLayout.VERTICAL);
            subInfoView.setGravity(Gravity.CENTER_VERTICAL);
            subInfoView.setLayoutParams(subInfoViewParams);

            TextView nameVehicle = new TextView(getContext());
            nameVehicle.setText(" Unidad: " + vehicleName + " ");
            TextView subInfoLat = new TextView(getContext());
            subInfoLat.setText(" Lat: " + marker.getPosition().latitude + "  " + " Long: " + marker.getPosition().longitude + " ");
            TextView subInfoLnt = new TextView(getContext());
            subInfoLnt.setText("                                    ");
            TextView subInfodate = new TextView(getContext());
            subInfodate.setText(" Localización: " + marker.getSnippet() + " ");//positionsdatetime.get(0) );
            subInfoView.addView(nameVehicle);
            subInfoView.addView(subInfoLat);
            subInfoView.addView(subInfodate);
            subInfoView.addView(subInfoLnt);

            infoView.addView(subInfoView);

            return infoView;
        }else
        {
            return null;
        }
    }
}