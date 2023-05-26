package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view;

import static com.pranavpandey.android.dynamic.utils.DynamicBitmapUtils.createBitmapFromView;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.adapter.TripAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.Adapter.AdapterDatesV3;
import com.pnla.onroadplus.z_version2.fragments.mapV2.components.ComponentVehicleCustomFields;
import com.pnla.onroadplus.z_version2.fragments.mapV2.components.ComponentVehicleHeader;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pranavpandey.android.dynamic.utils.DynamicUnitUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UnitMapViewImplV3 extends Fragment implements unitMapViewV3, OnMapReadyCallback ,
                                                           GoogleMap.InfoWindowAdapter,GoogleMap.OnInfoWindowClickListener,
                                                           GoogleMap.OnMarkerClickListener,View.OnClickListener ,AdapterDatesV3.OnDateClickListener{

    //Map
    private MapView mapView;
    private GoogleMap mMap;

    //Header
    private ComponentVehicleHeader componentVehicleHeader;
    private ImageView itemBack, itemRefresh,itemNavigation,itemShowtrips,checkBytime,timer1,timer2,itemhd,itemselflocation,itemTerminal;
    private ConstraintLayout linearLayoutBSheet,timers,btnTrips,btnInfo;

    //progres
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;

    //rv fechas
    private RecyclerView rvDates,rvTripsV2;
    private TripAdapter tripAdapter;

    private LinearLayout btnTripContainer;
    private TextView btnTripTitle,mytextimer1,mytextimer2,btnInfoTitle;
    private LinearLayout btnInfoContainer;

    //info botonsheet
    private View separator;
    private BottomSheetBehavior bottomSheetBehavior;
    private boolean isClickedDrawTrip=false;
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
    private ComponentVehicleCustomFields componentVehicleCustomFields;
    private List<DateV2> dates;


    //markers y mapas
    private Marker startMaker;
    private Marker endMarker;
    private Marker mainIconMarker;
//    private static BitmapDescriptor vehicleIcon;
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
        startMainiconMarker();

    }

    private void startMainiconMarker() {
        MarkerOptions markerOptions = new MarkerOptions();

       /* Bitmap bitmapMarker = getBitmapFromURL(vehicleImageURL);
        if (bitmapMarker != null) {
            Bitmap bitmap = Bitmap.createScaledBitmap(bitmapMarker, 80, 80, false);
            View marker = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.unit_marker, null);
            vehicleIcon = BitmapDescriptorFactory.fromBitmap(createBitmapFromView(marker, 160, 160, bitmap, vehicleName, vehicleSwitch));
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sedan);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, 160, 160, true);
            vehicleIcon = BitmapDescriptorFactory.fromBitmap(resized);
        }*/
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.bluedot);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap vehicleIcon = Bitmap.createScaledBitmap(b, 50, 50, false);
        markerOptions.position(new LatLng(vehicleLat, vehicleLng));
        markerOptions.infoWindowAnchor(.5f, .4f);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(vehicleIcon));
//assss

        mainIconMarker=mMap.addMarker(markerOptions);
    }
  /*  private Bitmap createBitmapFromView(@NonNull View view, int width, int height, Bitmap resource, String name, int vehicleSwitch) {

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
            circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGreen));
        } else if (vehicleSwitch == 2) {
            circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarOrange));
        } else if (vehicleSwitch == 3) {
            circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarRed));
        }else if (vehicleSwitch == 4) {
            circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBlack));
        }
        else if (vehicleSwitch == 0) {
            circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGray));
        }
        else {
            circleImageView.setBorderColor(getContext().getResources().getColor(R.color.colorBorderCarGray));
        }
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
    }*/
    private void initView(View view) {
        initViewID(view);
        initPresenter();
        getDates();
        initOnClickListeners();
        fillVehicleDataHeader();
        bottomSheetSettings();
        buttonRefresh();
    }


    private void initViewID(View view) {
        mapView = view.findViewById(R.id.map1);
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
        linearLayoutBSheet = view.findViewById(R.id.bottomSheet);
        mytextimer1=view.findViewById(R.id.textimer1);
        mytextimer2=view.findViewById(R.id.textimer2);

        btnInfoContainer = view.findViewById(R.id.btn_info_container);
        btnInfoTitle = view.findViewById(R.id.btn_info_title);
        componentVehicleCustomFields = view.findViewById(R.id.componentVehicleCustomFields);
        separator = view.findViewById(R.id.view123);
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



    public void setMainIconMarker(Marker marker){

    }
    public void fillDataDescription(){

    }
    //region ciclo de vida
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();

    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();

    }
    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();

    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
    //endregion
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


    public void getCurrentDate() {//este es para obtener trips by date del dia
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String currentDate = df.format(cal.getTime());
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
      //  fillDatesInMap();

    }
    @Override
    public void onDate(String date, int position) {// este metodo envia peticiones para cada dia selecionado
        if (tripAdapter != null && tripAdapter.getItemCount() > 0) {
            tripAdapter.clearRecyclerView();
        }
     //   presenter.getTripsByDate(vehicleCve, date, getContext());
    }
    private void fillDatesInMap() {
        rvDates.setNestedScrollingEnabled(false);
        AdapterDatesV3 adapterDatesV3 = new AdapterDatesV3(dates, getContext());
        adapterDatesV3.setOnClickDateListener(UnitMapViewImplV3.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDates.setLayoutManager(layoutManager);
        rvDates.setAdapter(adapterDatesV3);
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

    }


}
