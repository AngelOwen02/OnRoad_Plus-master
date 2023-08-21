package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view;

import static android.content.Context.LOCATION_SERVICE;
import static com.pranavpandey.android.dynamic.utils.DynamicBitmapUtils.createBitmapFromView;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.CameraUpdate;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.maps.android.SphericalUtil;
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
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.PositionV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UnitMapViewImplV3 extends Fragment implements unitMapViewV3, OnMapReadyCallback ,
                                                           GoogleMap.InfoWindowAdapter,GoogleMap.OnInfoWindowClickListener,GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener,
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
    private List<TripV2> mtrips;

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
     private ConstraintLayout informacionContrain;

        private TextView txvLastMessageResponse,txvVehicleAddressResponse,txvSatelitesResponse,txvVehicleAddressResponse1,txvVehicleAltitudeResponse,txvOdometerResponse
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
    private Polyline polylineTrip;
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

    private int mvehicleSwitch;
    private List<String> calles,tripsBDx,tripsBDy;
    private List<List<Double>> HDdoublelist=new ArrayList<>();
    private List<Double> HDlatlongHDRoute=new ArrayList<>();
    private List<String> listangles;
    private double HeadingRotation;

    private boolean HDAvailable=false;
    private List<List<Float>> resumeDotsfromInteractor=new ArrayList<>();

    private List<String> tripsBDx2;
    private List<String> tripsBDy2;

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
        mMap.setOnMapLongClickListener(this);
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
                .error(R.drawable.sedan)
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

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        // This method is called when loading the image fails
                        // You can handle the error scenario here, e.g., by using a default marker icon
                        markerOptions.position(new LatLng(lat, longt));
                        markerOptions.infoWindowAnchor(.5f, .4f);
                        markerOptions.anchor(0.5f, 0.5f);
                        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.sedantwo)); // Use a default icon
                        if(mainIconMarker==null) {
                            mainIconMarker = mMap.addMarker(markerOptions);
                            mainIconMarker.setTag("mainMarker");
                        }
                    }
                });
    }
    private void setImageBorderColor(int vehicleSwitch, Double lat, Double longt) {
        this.mvehicleSwitch=vehicleSwitch;
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
        Bitmap vehicleBorder = Bitmap.createScaledBitmap(b, 120, 120, false);
        markerOptions.position(new LatLng(lat, longt));
        markerOptions.infoWindowAnchor(.5f, .4f);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(vehicleBorder));
        if(secondaryIconMarker==null) {
            secondaryIconMarker = mMap.addMarker(markerOptions);
            secondaryIconMarker.setTag("border");
        }

    }
    private void  setSecondaryIcon(int vehicleSwitch){

        BitmapDrawable bitmapdraw ;
        if (vehicleSwitch == 1) {
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_green);
        } else if (vehicleSwitch == 2) {
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_orange);
        } else if (vehicleSwitch == 3) {
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_red);
        }else if (vehicleSwitch == 4) {
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_black);
        }
        else if (vehicleSwitch == 0) {
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_gray);
        }
        else {
            Toast.makeText(getContext(), "semaforo nulo", Toast.LENGTH_SHORT).show();
            bitmapdraw = (BitmapDrawable) getContext().getDrawable(R.drawable.circle_gray);
        }
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap vehicleBorder = Bitmap.createScaledBitmap(b, 120, 120, false);
        secondaryIconMarker.setIcon(BitmapDescriptorFactory.fromBitmap(vehicleBorder));
        //TODO remplazar por las lineas de abajo
        //Bitmap b;
        //if (vehicleSwitch == 1) {
        //    b = BitmapFactory.decodeResource(getResources(), R.drawable.circle_green);
        //} else if (vehicleSwitch == 2) {
        //    b = BitmapFactory.decodeResource(getResources(), R.drawable.circle_orange);
        //} else if (vehicleSwitch == 3) {
        //    b = BitmapFactory.decodeResource(getResources(), R.drawable.circle_red);
        //} else if (vehicleSwitch == 4) {
        //    b = BitmapFactory.decodeResource(getResources(), R.drawable.circle_black);
        //} else if (vehicleSwitch == 0) {
        //    b = BitmapFactory.decodeResource(getResources(), R.drawable.circle_gray);
        //} else {
        //    Toast.makeText(getContext(), "semaforo nulo", Toast.LENGTH_SHORT).show();
        //    b = BitmapFactory.decodeResource(getResources(), R.drawable.circle_gray);
        //}
        //
        //Bitmap vehicleBorder = Bitmap.createScaledBitmap(b, 120, 120, false);
        //secondaryIconMarker.setIcon(BitmapDescriptorFactory.fromBitmap(vehicleBorder));
    }

    private void initView(View view) {
        initViewID(view);
        getDates();       // obtiene las fechas para solicitar minimapas
        initOnClickListeners(); //inicializa
        fillVehicleDataHeader();//setea el encabezado
        bottomSheetSettings();  //configura el bottomsheet
       // buttonRefresh();       //no deberia soliciar la peticion hasta que se terminen de setear los datos iniciales
        fillDatainHeader(vehicleName,vehicleImageURL,String.valueOf(vehicleCurrentSpeed),vehicleTimeTravel,String.valueOf(vehicleKmTravel),vehicleGeoreference,vehicleSwitch,valudatebundle);

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
        itemShowtrips.setColorFilter(ContextCompat.getColor(getContext(), R.color.alfashadow));
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
        setInforTab(view);
    }

    private void setInforTab(View view) {
        txvLastMessageResponse=view.findViewById(R.id.txvLastMessageResponse) ;
        txvVehicleAddressResponse=view.findViewById(R.id.txvVehicleAddressResponse);
        txvSatelitesResponse=view.findViewById(R.id.txvSatelitesResponse);
        txvVehicleAltitudeResponse=view.findViewById(R.id.txvVehicleAltitudeResponse);
        txvOdometerResponse=view.findViewById(R.id. txvOdometerResponse);
        txvHorometerResponse=view.findViewById(R.id. txvHorometerResponse);

        txvVehicleMarkResponse=view.findViewById(R.id.txvVehicleMarkResponse);
        txvVehicleDescriptionResponse =view.findViewById(R.id.txvVehicleDescriptionResponse);
        txvVehicleModelResponse =view.findViewById(R.id.txvVehicleModelResponse);
        txvVehiclePlateResponse =view.findViewById(R.id.txvVehiclePlateResponse);
        txvVehicleSerieResponse  =view.findViewById(R.id.txvVehicleSerieResponse);
        txvVehicleInsuranceResponse =view.findViewById(R.id.txvVehicleInsuranceResponse);
        txvVehiclePolicyResponse=view.findViewById(R.id. txvVehiclePolicyResponse);
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

    private void fillDatainHeader(String vehicleName, String vehicleImage, String vehicleCurrentSpeed, String vehicleTimeTravel, String vehicleKmTravel, String vehicleGeoreference, int vehicleSwitch, String sendTime) {
        adapterH = new adapterHeader(vehicleName,vehicleImage,vehicleCurrentSpeed,vehicleTimeTravel,vehicleKmTravel,vehicleGeoreference,vehicleSwitch,sendTime, getContext());
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
        setTextFieldsInfoAndHeader(datadesc);
        if (adapterH!=null){
            adapterH.setSwitch(datadesc.getVehicleSwitch(),datadesc.getCurrentSpeed(),vehicleTimeTravel,datadesc.getKmTravel(),datadesc.getAddress());
        }
        Log.e("updateFields","Switch: "+data.getVehicleSwitch());
        Log.e("updateFields","Lat: "+vehicleLat+"  Long: "+vehicleLng);
        Log.e("updateFields","Lat: "+datadesc.getLatitude()+"  Long: "+datadesc.getLongitude());
        if(datadesc.getLatitude()!=0||datadesc.getLongitude()!=0)
        {
            this.vehicleLat=datadesc.getLatitude();
            this.vehicleLng=datadesc.getLongitude();

        }
        LatLng newlat=new LatLng(vehicleLat,vehicleLng);
        if(newlat!=null){
        startMainiconMarker(vehicleLat,vehicleLng);
        secondaryIconMarker.setPosition(newlat);
        setSecondaryIcon(data.getVehicleSwitch());
        if(mainIconMarker!=null){
        mainIconMarker.setPosition(newlat);
        }else{
            startMainiconMarker(vehicleLat,vehicleLng);
        }

        }
    }



    private void setTextFieldsInfoAndHeader(dataVehicleDescV3 datadesc) {
        txvLastMessageResponse.setText(datadesc.getLastMessage());
        txvVehicleAddressResponse.setText(datadesc.getAddress());
        txvSatelitesResponse.setText(""+datadesc.getSatelites());
        txvVehicleAltitudeResponse.setText(String.valueOf( datadesc.getAltitude()));
        txvOdometerResponse.setText("-- --");
        txvHorometerResponse.setText("-- --");

        txvVehicleMarkResponse.setText(datadesc.getDescBrand());
        txvVehicleDescriptionResponse .setText(datadesc.getDescModel());
        txvVehicleModelResponse.setText(datadesc.getVehicleYear());
        txvVehiclePlateResponse.setText(datadesc.getVehiclePlate());
        txvVehicleSerieResponse.setText(datadesc.getVehicleVin());
        txvVehicleInsuranceResponse.setText(datadesc.getInsuranceName());
        txvVehiclePolicyResponse.setText(datadesc.getPolicyNumber());
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
    //todo aqui no implrementer starmarikericon WARNING
        List<PositionV2> positionsToDraw = mtrips.get(position).getPositions();
        isClickedDrawTrip = true;
        if (positionsToDraw != null) {

            drawTrip(positionsToDraw);
        } else {
            Toast.makeText(getContext(), getString(R.string.textNotFoundTrip), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClickFinalPosition(View view, int position) {
        int height = 40;
        int width = 40;

        mMap.clear();
        Log.e("unitMapV3miniTrip "," "+vehicleSwitch);
        startMainiconMarker(vehicleLat,vehicleLng);
        double latitude = mtrips.get(position).getPositions().get(mtrips.get(position).getPositions().size() - 1).getLatitude();
        double longitude = mtrips.get(position).getPositions().get(mtrips.get(position).getPositions().size() - 1).getLongitude();
        LatLng notificationPosition = new LatLng(latitude, longitude);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.end_marker);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
    //endregion minimapas
    //region trip full day
    private void drawTrip(List<PositionV2> positions) {
        //Log.e("postrips",""+positions.size());

        mMap.clear();
        startMainiconMarker(vehicleLat,vehicleLng);
        //presenter.putVehicleMarkerInMap(vehicleLat, vehicleLng, vehicleName, vehicleImageURL, vehicleSwitch);
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
        Log.e("updateFieldsTripsBd","tripbyDay: "+vehicleCve+"  dateStart: "+datealternative+" "+timeStart+" dataEnt: "+datealternative+" "+timeEnd);
       presenter.AsyncTaskOne(vehicleCve,datealternative+" "+timeStart,datealternative+" "+timeEnd,getContext());
    }

    @Override
    public void settripsByDay() {///este metodo no se usa pero deberia remplazar a los anteriores

    }
    private void shotimer1() {
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
                //setTimers();
                mytextimer1.setText(formattedTime);
            }
        }, hour, minute,true);//No 24 hour time
        mTimePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mTimePicker.setTitle("Selecciona un horario");
        mTimePicker.show();

    }
    private void shotimer2() {
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
                //setTimers();
                mytextimer2.setText(formattedTime);
            }
        }, hour, minute,true);//No 24 hour time
        mTimePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mTimePicker.setTitle("Selecciona un horario");
        mTimePicker.show();  //  mytextimer2.setText(hour+":"+minute);
    }
    private void showtripsByday2()//boton de viajes por dia
    {
       // Log.e("updateFieldsTripsBd","tripbyDay: "+vehicleCve+"  dateStart: "+datealternative+" "+timeStart+" dataEnt: "+datealternative+" "+timeEnd);
        presenter.AsyncTaskOne(vehicleCve,datealternative+" "+timeStart,datealternative+" "+timeEnd,getContext());

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
            case R.id.clockone:
                shotimer1();
                break;
            case R.id.clocktwo:
                shotimer2();
                break;
            case R.id.consultarButton:
                mMap.clear();
                startMainiconMarker(vehicleLat,vehicleLng);
                showtripsByday2();
                timers.setVisibility(View.GONE);
                break;
            case R.id.map_toolbar_item_tripsbyday:
                if(timers.getVisibility()==View.GONE) {
                    itemShowtrips.setColorFilter(ContextCompat.getColor(getContext(), R.color.blackUI));
                    timers.setVisibility(View.VISIBLE);

                    HDAvailable=true;
                    HDP=false;
                    itemhd.setImageResource(R.drawable.hd_1);
                    mMap.clear();
                    startMainiconMarker(vehicleLat,vehicleLng);
                    getTripsByday();
                }else {
                    itemShowtrips.setColorFilter(ContextCompat.getColor(getContext(), R.color.alfashadow));
                    timers.setVisibility(View.GONE);
                    HDP=false;
                    itemhd.setImageResource(R.drawable.hd_1);
                    HDAvailable=false;
                    mMap.clear();
                    startMainiconMarker(vehicleLat,vehicleLng);
                }
                break;
            case R.id.hdicon://boton de hdeste es el refresh del mapa debe limpiar el mapa y actualizar los datos del modulo HD
                if(HDAvailable==true){
                        if(HDP==false)
                        {
                            HDP=true;
                            itemhd.setImageResource(R.drawable.ic_hd);
                            itemShowtrips.setColorFilter(ContextCompat.getColor(getContext(), R.color.alfashadow));
                            mMap.clear();
                            startMainiconMarker(vehicleLat,vehicleLng);
                            timers.setVisibility(View.GONE);
                            requestHD();
                        }else
                        {
                            HDP=false;
                            itemhd.setImageResource(R.drawable.hd_1);
                            HDAvailable=false;
                            mMap.clear();
                            startMainiconMarker(vehicleLat,vehicleLng);



                        }
                }else{
                    Toast.makeText(getContext(), "necestitas habilitar los viajes del dia", Toast.LENGTH_SHORT).show();
                    HDAvailable=false;
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

    private void requestHD() {
        presenter.getexternalAPI(HDdoublelist);
    }


    @Override
    public void onMapClick(LatLng latLng) {




    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        // Creating a marker
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting the position for the marker
        markerOptions.position(latLng);

        // Setting the title for the marker.
        // This will be displayed on taping the marker
        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

        // Clears the previously touched position
        //.clear();

        // Animating to the touched position
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        // Placing a marker on the touched position
       // mMap.addMarker(markerOptions);
    }
    @Override
    public void fillStringcalles(List<String> calles) {
        this.calles=calles;
    }

    @Override
    public void fillStringTipsbyDaylat(List<String> lats) {
        this.tripsBDx=lats;
    }

    @Override
    public void fillStringTipsbyDaylong(List<String> longs) {
        this.tripsBDy=longs;
        mMap.clear();
        startMainiconMarker(vehicleLat,vehicleLng);
        readtripsonView();
    }

    @Override
    public void drawtripdbxbdy(List<String> xdots, List<String> ydots) {
        this.tripsBDx2=ydots;
        this.tripsBDy2=xdots;
        //snapedExternalapidots();
    }

    @Override
    public void drawResumeDots(List<List<Float>> resumeDots) {
        resumeDotsfromInteractor=resumeDots;
        HDdots();
    }

    private void HDdots() {
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

        //snapedExternalapidots();
    }

    private void snapedExternalapidots() {//este metodo sirve para llenar de puntos a hdots
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
                height = 1;
                width = 1;
                String x = tripsBDy2.get(j);
                Double dx = Double.parseDouble(x);
                String y = tripsBDx2.get(j);
                Double dy = Double.parseDouble(y);
                notificationPosition= new LatLng(dx, dy);
                HeadingRotation= SphericalUtil.computeHeading(new LatLng(Double.parseDouble(tripsBDy2.get(j-1)),Double.parseDouble(tripsBDx2.get(j-1))),new LatLng(Double.parseDouble(tripsBDy2.get(j)),Double.parseDouble(tripsBDx2.get(j))) );
                listangles.add(String.valueOf(HeadingRotation));
                bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.start_marker);//.start_marker);arrowe
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

    private void readtripsonView() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        PolylineOptions options = new PolylineOptions().width(8).color(Color.BLACK).geodesic(true);
        HDdoublelist.clear();
        if ( !tripsBDx.isEmpty() && !tripsBDy.isEmpty()) {
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
                    height = 1;
                    width = 1;
                    String x = tripsBDy.get(j);
                    Double dx = Double.parseDouble(x);
                    String y = tripsBDx.get(j);
                    Double dy = Double.parseDouble(y);
                    notificationPosition= new LatLng(dx, dy);
                    HeadingRotation= SphericalUtil.computeHeading(new LatLng(Double.parseDouble(tripsBDy.get(j-1)),Double.parseDouble(tripsBDx.get(j-1))),new LatLng(Double.parseDouble(tripsBDy.get(j)),Double.parseDouble(tripsBDx.get(j))) );
                    listangles.add(String.valueOf(HeadingRotation));
                    bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.start_marker);//.start_marker);arrowe
                    b = bitmapdraw.getBitmap();
                    smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                    startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title(vehicleName).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).rotation(Float.valueOf(listangles.get(j-1))-90f)
                            .anchor(.5f,.5f).snippet(String.valueOf(calles.get(j))));//+","

                }
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
}
