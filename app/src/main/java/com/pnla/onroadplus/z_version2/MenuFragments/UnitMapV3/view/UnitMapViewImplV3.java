package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view;

import static android.content.Context.LOCATION_SERVICE;
import static com.pranavpandey.android.dynamic.utils.DynamicBitmapUtils.createBitmapFromView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.CommandsContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.ExternalGPSApp.view.ExternalGPSDialog;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.adapter.TripAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.view.UnitMapViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.Adapter.AdapterDatesV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.Adapter.TripAdapterV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.Adapter.adapterHeader;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.dataVehicleDescV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter.unitViewpresenterV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.presenter.unitViewpresenterV3Impl;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;
import com.pnla.onroadplus.z_version2.fragments.mapV2.components.ComponentVehicleCustomFields;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UnitMapViewImplV3 extends Fragment implements unitMapViewV3, OnMapReadyCallback ,
                                                           GoogleMap.InfoWindowAdapter,GoogleMap.OnInfoWindowClickListener,
                                                           GoogleMap.OnMarkerClickListener,View.OnClickListener ,AdapterDatesV3.OnDateClickListener
                                                           ,TripAdapterV3.OnClickTripListener{

    //Map
    private MapView mapView;
    private GoogleMap mMap;

    //Header
    //private ComponentVehicleHeader componentVehicleHeader;
    private ImageView itemBack, itemRefresh,itemNavigation,itemShowtrips,checkBytime,timer1,timer2,itemhd,itemselflocation,itemTerminal;
    private ConstraintLayout linearLayoutBSheet,timers,btnTrips,btnInfo;

    private double mylat,mylong;

    private boolean locationP=false;//boleano para la ubicacion del telefono
    private boolean HDP=false; //boleano para uso del HD
    private boolean tripByDay=false;


    //rv fechas
    private RecyclerView rvDates,rvTripsV2;
    private TripAdapterV3 tripAdapter;

    private LinearLayout btnTripContainer;
    private TextView btnTripTitle,mytextimer1,mytextimer2,btnInfoTitle;
    private LinearLayout btnInfoContainer;

    //info botonsheet
    private View separator;
    private BottomSheetBehavior bottomSheetBehavior;
    private boolean isClickedDrawTrip=false;
    private boolean tripsAndInfo=false;
    private List<TripV2> mtrips;//TODO viajes del dial en bottom sheet

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
    private  String datealternative;
    private String timeStart="";
    private String timeEnd="";
    private String vehicleGeoreference="";
    private String vehicleTimeTravel="";
    private Double vehicleKmTravel,vehicleCurrentSpeed;

    //Modulo de informacion de endpoint /vehicles/getVehicleDescripcion
  //  private ComponentVehicleCustomFields componentVehicleCustomFields;//se remplazo este modulo por el include en bottm_sheet_map_view.xml linea 233
     private ConstraintLayout informacionContrain; //TODO llenar los campos nuevode debajo de esto asignar en init view y asignar datos de modulo
                                                   //TODO  setDataetVehicleDescripcion  LUIS encazo de venir nulos los campos poner ""

        private TextView txvLastMessageResponse,txvVehicleAddressResponse,txvVehicleAddressResponse1,txvVehicleAltitudeResponse,txvOdometerResponse
                        ,txvHorometerResponse,txvVehicleMarkResponse,txvVehicleDescriptionResponse,txvVehicleModelResponse,txvVehiclePlateResponse,txvVehicleSerieResponse,
                        txvVehicleInsuranceResponse,txvVehiclePolicyResponse;
    private dataVehicleDescV3 datadesc;

    private List<DateV2> mdates=new ArrayList<>();

    //componentVehicHeader FIX
    private ImageView  vehicleHeader;
    private TextView   last_send_time,header_name,header_speed,header_hour,header_km,header_adress;
    private RecyclerView rvHeader;
    private adapterHeader adapterH;

    //markers y mapas
    private Marker startMaker;
    private Marker endMarker;
    private Marker mainIconMarker,secondaryIconMarker;
//    private static BitmapDescriptor vehicleIcon;


    //presentador
    private unitViewpresenterV3 presenter;
    private ProgressDialog progressDialog;

    final Handler handler = new Handler();
    private Runnable runnable;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_view_impl, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setInfoWindowAdapter(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        LatLng latLng = new LatLng(vehicleLat, vehicleLng);
      //  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        startMainiconMarker(vehicleLat,vehicleLng);

    }

    private void startMainiconMarker(Double lat, Double longt) {
        MarkerOptions markerOptions = new MarkerOptions();
        setImageBorderColor(vehicleSwitch,lat,  longt);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.car);
        Bitmap vehicleIcon = null;
        Glide.with(this)
                .asBitmap()
                .load(vehicleImageURL)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        //imageView.setImageBitmap(resource);
                        Bitmap b =resource;
                        Bitmap vehicleIcon = Bitmap.createScaledBitmap(b, 110, 110, false);
                        markerOptions.position(new LatLng(lat, longt));
                        markerOptions.infoWindowAnchor(.5f, .4f);
                        markerOptions.anchor(0.5f, 0.5f);
                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(vehicleIcon));
                        mainIconMarker=mMap.addMarker(markerOptions);
                        mainIconMarker.setTag("mainMarker");
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }
    private void setImageBorderColor(int vehicleSwitch, Double lat, Double longt) {
        MarkerOptions markerOptions = new MarkerOptions();
        BitmapDrawable bitmapdraw ;
        if (vehicleSwitch == 1) {
           // circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGreen));
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_green);
        } else if (vehicleSwitch == 2) {
           bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_orange);
            //circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarOrange));
        } else if (vehicleSwitch == 3) {
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_red);
           // circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarRed));
        }else if (vehicleSwitch == 4) {
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_black);
            //circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBlack));
        }
        else if (vehicleSwitch == 0) {
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_gray);
            //circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGray));
        }
        else {
             bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_gray);
            //circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGray));
        }
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap vehicleIcon = Bitmap.createScaledBitmap(b, 120, 120, false);
        markerOptions.position(new LatLng(lat, longt));
        markerOptions.infoWindowAnchor(.5f, .4f);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(vehicleIcon));
        secondaryIconMarker=mMap.addMarker(markerOptions);
        secondaryIconMarker.setTag("border");
    }

    private void initView(View view) {
        initViewID(view);
        getDates();       // obtiene las fechas para solicitar minimapas
        initOnClickListeners(); //inicializa
        fillVehicleDataHeader();//setea el encabezado
        bottomSheetSettings();  //configura el bottomsheet
       // buttonRefresh();       //no deberia soliciar la peticion hasta que se terminen de setear los datos iniciales
        fillDatainHeader(vehicleName,vehicleImageURL,String.valueOf(vehicleCurrentSpeed),vehicleTimeTravel,String.valueOf(vehicleKmTravel),vehicleGeoreference);
        initPresenter();
    }


    private void initViewID(View view) {
        mapView = view.findViewById(R.id.map1);
       //menus de modificaciones
        //componentVehicleHeader = view.findViewById(R.id.vehicleDataHeader);
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
        //estos dos son los botones del boton sheet
        btnTrips = view.findViewById(R.id.btn_viajes);
        btnInfo = view.findViewById(R.id.btn_informacion);

        rvDates = view.findViewById(R.id.rvDates);
        rvTripsV2 = view.findViewById(R.id.rvEvents);
        rvHeader=view.findViewById(R.id.rvheader);

        btnTripContainer = view.findViewById(R.id.btn_trip_container);
        btnTripTitle = view.findViewById(R.id.btn_trip_title);
        linearLayoutBSheet = view.findViewById(R.id.bottomSheet);

        mytextimer1=view.findViewById(R.id.textimer1);
        mytextimer2=view.findViewById(R.id.textimer2);

        btnInfoContainer = view.findViewById(R.id.btn_info_container);
        btnInfoTitle = view.findViewById(R.id.btn_info_title);
        //componentVehicleCustomFields = view.findViewById(R.id.componentVehicleCustomFields);//TODO LUIS
        informacionContrain=view.findViewById(R.id.componentVehicleCustomFields);
        informacionContrain.setVisibility(View.GONE);
        separator = view.findViewById(R.id.view123);

        //componentVehicHeader
        vehicleHeader= view.findViewById(R.id.vheader);

        last_send_time= view.findViewById(R.id.txt_vehicle_header_last_send_time);
        header_name= view.findViewById(R.id.txt_vehicle_header_name);
        header_speed= view.findViewById(R.id.txt_vehicle_header_speed);
        header_hour= view.findViewById(R.id.txt_vehicle_header_hour);
        header_km= view.findViewById(R.id.txt_vehicle_header_km);
        header_adress= view.findViewById(R.id.txt_vehicle_header_adress);


        //todo LUIS colocar aqui los campos del xml component_vehicle_custom_fileds.xml
        progressBar= view.findViewById(R.id.unit_map_view_progress_bar);
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
        vehicleGeoreference=getArguments().getString("vehicleGeoreference");
        vehicleTimeTravel = getArguments().getString("vehicleTimeTravel");
        vehicleKmTravel = getArguments().getDouble("vehicleKmTravel");
        vehicleCurrentSpeed = getArguments().getDouble("vehicleCurrentSpeed");


        valudatebundle =getArguments().getString("vehicleSendTime");//este es el sendtime
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


        String sVehicleCurrentSpeed = String.valueOf(vehicleCurrentSpeed);
        DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
        decimalFormat.format(vehicleKmTravel);
        String decimalUnitKMTravel = decimalFormat.format(vehicleKmTravel);
        Log.e("mvehicleSendTime",""+sVehicleCurrentSpeed);
    }

    private void fillDatainHeader(String vehicleName,String vehicleImage,String vehicleCurrentSpeed,String vehicleTimeTravel,String vehicleKmTravel,String vehicleGeoreference) {
        adapterH = new adapterHeader(vehicleName,vehicleImage,vehicleCurrentSpeed,vehicleTimeTravel,vehicleKmTravel,vehicleGeoreference, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvHeader.setLayoutManager(layoutManager);
        rvHeader.setAdapter(adapterH);
    }



    public void setMainIconMarker(Marker marker){

    }
    public void fillDataDescription(){

    }
    //region ciclo de vida
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    goToMainMenuContainer();
                    return true;
                }
                return false;
            }
        });
    }



    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
        handler.removeCallbacks(runnable);

    }
    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
        handler.removeCallbacks(runnable);

    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
    //endregion
    private void goToMainMenuContainer() {
        Bundle bndle = new Bundle();
        bndle.putString("nav", "UNITS");
        Intent intent = new Intent(getContext(), menuViewImpl.class);// MainMenuContainerActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bndle);
        startActivity(intent);
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
        progressDialog = new ProgressDialog(getActivity());
        presenter = new unitViewpresenterV3Impl(this, getContext());

        tripToday();//aqui deberia consultarse el header una vez
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                presenter.setDataetVehicleDescripcion(vehicleCve);
                handler.postDelayed(this,7000);
            }
        },7000);
    }


    @Override
    public void VehicleDescriptionSucess(dataVehicleDescV3 data) {
        this.datadesc=data;
        LatLng newlat=new LatLng(datadesc.getLatitude(),datadesc.getLongitude());
        secondaryIconMarker.setPosition(newlat);
        mainIconMarker.setPosition(newlat);


    }
    public void tripToday() {//este es para obtener trips by date del dia
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String currentDate = df.format(cal.getTime());
        presenter.getEvents(vehicleCve, currentDate, getContext());// este podria ir en el retorno de as

        // presenter.getTripsByDate(vehicleCve, currentDate, getContext());

    }
    public void getDates() {//

        List<DateV2> dates = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < 30; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i * (-1));
            String reportDate = df.format(cal.getTime());
            dates.add(new DateV2(reportDate));
        }

        fillDatesInMap(dates);

    }
    @Override
    public void onDate(String date, int position) {// este metodo envia peticiones para cada dia selecionado
        if (tripAdapter != null && tripAdapter.getItemCount() > 0) {
            tripAdapter.clearRecyclerView();
        }
      presenter.getEvents(vehicleCve, date, getContext());
    }
    private void fillDatesInMap(List<DateV2> mdates) {
        rvDates.setNestedScrollingEnabled(false);
        AdapterDatesV3 adapterDatesV3 = new AdapterDatesV3(mdates, getContext());
        adapterDatesV3.setOnClickDateListener(UnitMapViewImplV3.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDates.setLayoutManager(layoutManager);
        rvDates.setAdapter(adapterDatesV3);
    }


    //region llenado de minimapas
    @Override
    public void setEvents(List<TripV2> trips) {
        this.mtrips=trips;
        fillTripsInMap();

    }

    private void fillTripsInMap() {
        rvTripsV2.setNestedScrollingEnabled(false);
        tripAdapter = new TripAdapterV3(vehicleName, mtrips, getContext());
        tripAdapter.setOnClickTripListener(UnitMapViewImplV3.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTripsV2.setLayoutManager(layoutManager);
        rvTripsV2.setAdapter(tripAdapter);
    }
    @Override
    public void onClickGoogleImage(View view, int position) {

    }

    @Override
    public void onClickFinalPosition(View view, int position) {

    }
    //endregion




    public void buttonRefresh(){
        presenter.setDataetVehicleDescripcion(vehicleCve);

    }
        //region myLocation
    private void showMyOwnLocation() {//muestra la ubicacion del usuario
        mMap.clear();//limpia el mapa
        startMainiconMarker(vehicleLat,vehicleLng);//pinta el marcador del vehiculo donde sea que este
        checkmylocation();
        //de beria pintar un marcador de la ubicacion del dispositivo y hacer un zoom entre ambas
    }

    private void checkmylocation() {
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
    //endregionmyLocation
    //region metodos del selector del buton sheet
    private void infoButtonInit() {
        btnTripContainer.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        btnTripTitle.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
        btnInfoContainer.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        btnInfoTitle.setTextColor(getResources().getColor(R.color.colorWhite));

        separator.setVisibility(View.GONE);
        //.setVisibility(View.VISIBLE);
        informacionContrain.setVisibility(View.VISIBLE);
        rvDates.setVisibility(View.GONE);
        rvTripsV2.setVisibility(View.GONE);
    }
    private void tripButtonInit() {
        btnInfoContainer.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        btnInfoTitle.setTextColor(getResources().getColor(R.color.colorOrangeYellow));
        btnTripContainer.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        btnTripTitle.setTextColor(getResources().getColor(R.color.colorWhite));

        separator.setVisibility(View.VISIBLE);
        //componentVehicleCustomFields.setVisibility(View.GONE);
        informacionContrain.setVisibility(View.GONE);
        rvDates.setVisibility(View.VISIBLE);
        rvTripsV2.setVisibility(View.VISIBLE);
    }
    //endregion
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
    //region dialogs
    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Cargando Detalles");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    //endregion
    private void getTripsByday() {//este metodo deberia trabajar en vackground
       presenter.AsyncTaskOne(vehicleCve,datealternative+" "+timeStart,datealternative+" "+timeEnd,getContext());
    }

    @Override
    public void settripsByDay() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //region encabezado
            case R.id.selflocationgps:/** este metodo debe traer la ubicacion del usuarios*/
                if(locationP==false){
                    itemselflocation.setImageResource(R.drawable.ic_miubicacionoff);
                    locationP=true;
                    mMap.clear();
                    startMainiconMarker(vehicleLat,vehicleLng);
                    LatLng mainzoom=new LatLng(vehicleLat,vehicleLng);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainzoom, 14));
                    //hacer map clear pintar el marker de location y pintar el marker del telefono y hacer update camera entre los dos puntos
                }else {
                    itemselflocation.setImageResource(R.drawable.ic_miubicacionon);
                    locationP=false;
                    showMyOwnLocation();
                }
                break;
            case R.id.map_toolbar_item_refresh:// update completo
                buttonRefresh();
                break;
            case R.id.map_toolbar_item_tripsbyday:
                getTripsByday();
                break;
            case R.id.hdicon://boton de hdeste es el refresh del mapa debe limpiar el mapa y actualizar los datos del modulo HD
                if(HDP==false)
                {
                    itemhd.setImageResource(R.drawable.ic_hd);
                    HDP=true;
                }else
                {
                    itemhd.setImageResource(R.drawable.hd_1);
                    mMap.clear();
                    startMainiconMarker(vehicleLat,vehicleLng);
                    HDP=false;


                }
                break;
            case R.id.map_toolbar_item_navigation://boton de redireccion aq gps TODO TERMINADO
                ExternalGPSDialog externalGPSDialog = new ExternalGPSDialog();
                externalGPSDialog.setLocationVehicle(vehicleLat, vehicleLng);
                externalGPSDialog.show(getActivity().getSupportFragmentManager(), ExternalGPSDialog.TAG);
                break;
            case R.id.map_toolbar_item_terminal://intent para ir a terminal TODO TERMINADO
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
                break;
                //endregion

            //region otros menus del botonsheet VIAJES E INFORMACION
            case R.id.btn_viajes:
                tripsAndInfo=false;
                tripButtonInit();

                timers.setVisibility(View.GONE);
                break;
            case R.id.btn_informacion:
                tripsAndInfo=true;
                infoButtonInit();
                timers.setVisibility(View.GONE);
            break;
            case R.id.map_toolbar_item_back:
                goToMainMenuContainer();
                break;
            //endregion
        }
    }




}
