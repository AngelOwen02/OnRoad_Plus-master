package com.pnla.onroadplus.z_version2;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.UnitTrackingContainer;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.MarkerClusterRenderer;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.ClusterTracking;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.view.geoCercasMainContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.LOCATION_SERVICE;

public class TrackingMapFragment extends Fragment implements OnMapReadyCallback, TrackingMapView, ClusterManager.OnClusterClickListener<ClusterTracking>{

    public static final String TAG = TrackingMapFragment.class.getSimpleName();
    private ProgressDialog progressDialog;
    private MapView mView;
    private GoogleMap mMap;
    private ClusterManager<ClusterTracking> mClusterManager;
    private TrackingMapPresenter presenter;
    public Timer timer;
    public TimerTask timerTask;
    private SharedPreferences preferences;
    private List<Unit> vehicles;
    private  SharedPreferences pref;
    private Handler handler = new Handler();
    private Runnable runnable;
    private List<Unit> lastdata;
    //    private boolean firstime;
    private ClusterTracking clusterTrackingA;
    public static boolean readytorefreh=false;
    private Object myclus;
    private boolean checksize=false; /**variable de contencion de memmoria*/
    private List<String> geoCercas;
    private boolean isdraw=false;
    private boolean iconGeocercas;
    private ImageView geocercas, imageselflocation;
    private CardView shapegeoCercas,phoneLocation;
    public static  List<String> pointsLAT=new ArrayList<>();
    public static List<String> pointsLON=new ArrayList<>();
    public  static List<String> colorARGB1=new ArrayList<>();
    public static  List<String> ratioR=new ArrayList<>();
    public static List<String> typeS=new ArrayList<>();

    public static List<String> cveGeofence=new ArrayList<>();
    private int nclicks=0;


    /** POLIGONS*/
    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_WHITE_ARGB = 0xffffffff;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int COLOR_PURPLE_ARGB = 0xff81C784;
    private static final int COLOR_ORANGE_ARGB = 0xffF57F17;
    private static final int COLOR_BLUE_ARGB = 0xffF9A825;

    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;

    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem DOT = new Dot();
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    // Create a stroke pattern of a gap followed by a dash.
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private static final List<PatternItem> PATTERN_POLYGON_BETA =
            Arrays.asList(DOT, GAP, DASH, GAP);

    /***/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking_map, container, false);
        initTrackingMapFragment(view, savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        pref = getContext().getSharedPreferences("Haha", Context.MODE_PRIVATE);

        readytorefreh=false;

//pref  = ggetShared.getPreferences(Context.MODE_PRIVATE);
        return view;
    }

    private void initTrackingMapFragment(View view, Bundle savedInstanceState) {
        bindViews(view);
        onCreateViewMap(savedInstanceState);
        initPresenter();
    }

    private void onCreateViewMap(Bundle savedInstanceState) {
        mView.onCreate(savedInstanceState);
        Log.e("onCreateViewMap", "OK");

        if (mView != null) {
            mView.getMapAsync(this);
        }
    }


    private void bindViews(View view) {
        mView = view.findViewById(R.id.map_view_tracking);
        progressDialog = new ProgressDialog(getActivity());
        ImageView toolbar = view.findViewById(R.id.search_toolbar_tracking_map);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUnitTrackingViewImpl();
            }
        });
        shapegeoCercas=view.findViewById(R.id.geocercaCardView);
        geocercas = view.findViewById(R.id.geocerca);

        shapegeoCercas.setVisibility(View.GONE);
        geocercas.setVisibility(View.GONE);
        geocercas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                geocercasViewImpl();
            }
        });


        phoneLocation=view.findViewById(R.id.findlocation);
        imageselflocation=view.findViewById(R.id.selflocation);
        phoneLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nclicks=nclicks+1;
                if(nclicks==2)
                {
                    nclicks=0;
                    Log.e("BOTONLOCATION","2"+nclicks);
                    imageselflocation.setImageResource(R.drawable.ic_miubicacionoff);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 4.5f));
                    mMap.clear();
                    mClusterManager.clearItems();
                    initPresenter();
                    starproces();


                }else if(nclicks==1)
                {

                    Log.e("BOTONLOCATION","1"+nclicks);
                    imageselflocation.setImageResource(R.drawable.ic_miubicacionon);
                    showMyLocationrelative();
                }
                else
                {
                    imageselflocation.setImageResource(R.drawable.ic_miubicacionoff);

                    Log.e("BOTONLOCATION","0"+nclicks);
                }

            }
        });


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
    private void showMyLocationrelative()
    {

        /***here we need to have the location and make a small zoom */      //lat       //long     //zoom
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 4.5f));

        LocationManager locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);

        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSEnabled) {
            Location location = null;
            if (location == null) {
                //check the network permission
                if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10.5f));
                    }
                }
            }
        }

    }
    private void stopgps()
    {
//        if(locationManager != null){
//            locationManager.removeUpdates(GpsTracker.this);
//        }
    }

    private void initPresenter() {
        presenter = new TrackingMapPresenterImpl(getContext());
        presenter.setView(this);

        // hideProgressDialog();
        presenter.getFullVehicles();//esta linea llega al interactor de la API que trae toda la informacion de los vehiculos


    }


    @Override
    public void setUnitList(List<Unit> vehicles) {

        this.vehicles = vehicles;
        if(vehicles.size()>400)
        {
            checksize=true;
        }
        Log.e("zoomData","zoom: "+mMap.getCameraPosition().zoom);
        //  Log.e("requestdeunitsagain",""+ UnitDB.getUnitList() );
        // Log.e("requestdeunitsagain","fragmen     :::::"+vehicles);
        //  UnitDB.deleteDB();
        // createUnitListDB(vehicles);
/*
        List<Unit> allUnitsList = UnitDB.getUnitList();
        List<Unit> activeUnitsList = UnitDB.getUnitListActive();
        // UnitDB.updateCheckedStatus(vehicle.getVehicleName(),true);*/
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e("onMapReady", "OK");

        mMap = googleMap;
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 2.0f));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 4.5f));


        uiSettingsMap(mMap);
        setUpClusterer();
        SharedPreferences prefs = getContext().getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
        boolean isFirstLogin = prefs.getBoolean("isFirst", true);
        Log.e("First Login?", String.valueOf(isFirstLogin));


        presenter.getMarkers();
        presenter.hideshowgeoCercasRequest();
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

            private float currentZoom = -1;

            @Override
            public void onCameraChange(CameraPosition pos) {
                if (pos.zoom != currentZoom){
                    currentZoom = pos.zoom;
                    Log.e("zoomzoom",""+currentZoom);
                    // do you action here
                    if(currentZoom>3.5)
                    {
                        if(isdraw==false)
                        {
                            geoCercasData();
                        }
                    }
                }
            }
        });
        // initagain();
        // knowzoom();

    }
    private void knowzoom()
    {
    /*    handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
              //   Log.e("requestdeunitsagain","zoom: "+mMap.getCameraPosition().zoom);

                handler.postDelayed(this, 1000);
            }
        }, 1000);*/
    }
    private void starproces()
    {
        uiSettingsMap(mMap);
        setUpClusterer();
        if(checksize==true) {
            readytorefreh=false;
        }

        presenter.getMarkers();


    }
    private void initagain()
    {

        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                mMap.clear();
                mClusterManager.clearItems();

                try
                {
                    mMap.clear();
                    mClusterManager.clearItems();
                    Thread.sleep(500);

                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                Log.e("requestdeunitsagain","presentador iniciado");
                readytorefreh=true;
                initPresenter();
                starproces();
                try
                {
                    Log.e("requestdeunitsagain","zoom: "+mMap.getCameraPosition().zoom);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude), mMap.getCameraPosition().zoom));
                    Thread.sleep(500);

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude), mMap.getCameraPosition().zoom));

                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                //  presenter.getFullVehicles();

                handler.postDelayed(this, 30000);
            }
        }, 30000);
    }



    private void uiSettingsMap(GoogleMap googleMap) {
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setRotateGesturesEnabled(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mView.onStart();
       /* Log.e("OnStart", "OK");
        if (pref.getBoolean("Refresh", true)){
            SharedPreferences.Editor editor = pref.edit();

            editor.putBoolean("Refresh", false);
            editor.apply();
            refreshFragment();
        }*/
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        handler.removeCallbacks(runnable);
    }
    @Override
    public void onResume() {
        super.onResume();
        mView.onResume();
        handler.postDelayed(runnable,10000);
        Log.e("onResume", "OK");



    }

    private void refreshFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        mView.onPause();
        handler.removeCallbacks(runnable);
        Log.e("onPause", "OK");

    }

    @Override
    public void onStop() {
        super.onStop();
        mView.onStop();
        handler.removeCallbacks(runnable);
        Log.e("onStop", "OK");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mView.onLowMemory();
    }

    @Override
    public void addMarkersToMap(ClusterTracking clusterTracking) {//esto se ejecuta siempre
        this.clusterTrackingA=clusterTracking;

        mClusterManager.addItem(clusterTrackingA);
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(clusterTracking.getPosition());

    }


    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void setUpClusterer() {

        mClusterManager = new ClusterManager<ClusterTracking>(getActivity(), mMap);
        mClusterManager.setAnimation(true);
        onClusterClickListener();
        mClusterManager.setRenderer(new MarkerClusterRenderer(getActivity(), mMap, mClusterManager));
        mClusterManager.cluster();



    }

    private void onClusterClickListener() {
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);

    }


    @Override
    public boolean onClusterClick(Cluster<ClusterTracking> cluster) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                cluster.getPosition(), (float) Math.floor(mMap
                        .getCameraPosition().zoom + 3)), 300,
                null);
        return true;
    }

    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Rastreo OnRoad");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void hideShowiconGeoCercas(boolean accessFlag) {
        this.iconGeocercas=accessFlag;
        if(iconGeocercas==false)
        {
            shapegeoCercas.setVisibility(View.GONE);
            geocercas.setVisibility(View.GONE);
            pointsLAT.clear();
            pointsLON.clear();
            colorARGB1.clear();
            ratioR.clear();
            typeS.clear();
            cveGeofence.clear();
        }
        else
        {
            shapegeoCercas.setVisibility(View.VISIBLE);
            geocercas.setVisibility(View.VISIBLE);
        }
    }

    private void showUnitTrackingViewImpl() {
        Intent intent = new Intent(getContext(), UnitTrackingContainer.class);
        startActivity(intent);
    }

    private void geocercasViewImpl() {
        Intent intent = new Intent(getContext(), geoCercasMainContainer.class);
        startActivity(intent);
    }

    /*  @Override
      public void onCameraMove() {
          Log.e("zoomzoom","zoom: "+mMap.setOnCameraMoveStartedListener().zoom);
      }*/
    private void geoCercasData()
    {
        isdraw=true;


        List<String> latitudeList=new ArrayList<>();
        List<String> longitudeList=new ArrayList<>();


        List<String> latitudeListallbounds=new ArrayList<>();
        List<String> longitudeListallbounds=new ArrayList<>();
        longitudeListallbounds.clear();
        latitudeListallbounds.clear();

        Log.e("geoCercas",""+pointsLAT);//+'\n'+adapterGeocercas.pointsLON);
        if(!pointsLAT.isEmpty()) {/**comprueba que la lista  es diferente de empty*/
            Log.e("geoCercas3", "" + pointsLAT.size() + '\n' +pointsLON.size());

            Log.e("cvegeofence", "" +cveGeofence);
            Log.e("colorpolygon", "" + colorARGB1);
            Log.e("colorpolygon", "" + typeS);
            for(int i=0; i<pointsLAT.size();i++)/**para cada item de pointsLAt  */
            {
                String lislats = String.valueOf(pointsLAT.get(i));
                String lislongs = String.valueOf(pointsLON.get(i));
                String color=colorARGB1.get(i);
                String ratio=ratioR.get(i);
                String type=typeS.get(i);
                String[] parts1 = lislats.split(",");
                String[] parts2 = lislongs.split(",");
                for (int j = 0; j < lislats.split(",").length; j++) {// separa los items con split por,
                    latitudeList.add(parts1[j]);
                    longitudeList.add(parts2[j]);
                    latitudeListallbounds.add(parts1[j]);
                    longitudeListallbounds.add(parts2[j]);
                }
                Log.e("listasfinales",""+latitudeList);
                Log.e("listasfinales",""+longitudeList);
                doubleConvertion(latitudeList,longitudeList,color,ratio,type);
                if(i==pointsLAT.size()-1) {/** size-1 es e numero de iteraciones maximas alas que llega*/
                    fitbounds(latitudeListallbounds, longitudeListallbounds);
                }
                latitudeList.clear();
                longitudeList.clear();
                color="";
                ratio="";
                type="";



            }

        }else
        {
            longitudeListallbounds.clear();
            latitudeListallbounds.clear();
        }
 /*
        Polygon polygon2 = mMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(

                        new LatLng(20.5505176083416998, -103.174634081891995),
                        new LatLng(20.5412441487422015, -103.174004093134002),
                        new LatLng(20.5406895483210015, -103.179172803243006),
                        new LatLng(20.5461758561266983, -103.176848080864005)));

        polygon2.setTag("beta");
        stylePolygon(polygon2);
        mMap.setOnPolygonClickListener(this);*/

    }

    private void fitbounds(List<String> latitudeList, List<String> longitudeList) {
        List<LatLng> points=new ArrayList<>();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        if (!latitudeList.isEmpty()) {
            for (int z = 0; z < latitudeList.size(); z++) {
                String x = latitudeList.get(z);
                Log.e("geoCercas4", "Doubles: " + x);

                Double dx = Double.parseDouble(x);
                String y = longitudeList.get(z);
                Double dy = Double.parseDouble(y);
                LatLng point = new LatLng(dx, dy);

                builder.include(point);
            }
            Log.e("geocercasSize", ""+latitudeList.size());
            LatLngBounds bounds = builder.build();
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200));
        }
    }

    private void doubleConvertion(List<String> latitudeList,List<String> longitudeList,String Color,String Ratio,String type)
    {
        List<LatLng> points=new ArrayList<>();
        String colorPolygon=Color;
        String ratio=Ratio;
        String types=type;


        if (!latitudeList.isEmpty()) {
            for (int z = 0; z < latitudeList.size(); z++) {
                String x = latitudeList.get(z);
                Log.e("geoCercas4", "Doubles: " + x);

                Double dx = Double.parseDouble(x);
                String y = longitudeList.get(z);
                Double dy = Double.parseDouble(y);
                LatLng point = new LatLng(dx, dy);

                points.add(point);

                //if (z == latitudeList.size() - 1) {
                //     DrawPolygon(points);
                // }
                //  builder.include(point);
                //  options.add(point);
            }


            Log.e("poligonsize", "P: "+ points.size());
            if(points.size()==1) {
                DrawCircle(points,ratio,colorPolygon);

            }else{
                if(Integer.valueOf (types)==4)
                {
                    DrawnPoliline(points,colorPolygon);
                }else
                {
                    DrawPolygon(points,colorPolygon);
                }
            }

            points.clear();
        }
    }
    /***/
    private void DrawnPoliline(List<LatLng> latLngsList,String color)
    {
        int alphaColor = ColorUtils.setAlphaComponent(Color.parseColor(color),200);
        Polyline polyline = mMap.addPolyline(new PolylineOptions()
                .addAll(latLngsList)
                .width(4)
                .color(alphaColor));


    }
    private void DrawCircle(List<LatLng> latLngsList,String Ratio,String color)
    {
        int alphaColor = ColorUtils.setAlphaComponent(Color.parseColor(color),60);
        CircleOptions circleOptions = new CircleOptions()
                .center(latLngsList.get(0))
                .radius(Integer.valueOf(Ratio))
                .fillColor(alphaColor); // In meters

// Get back the mutable Circle
        Circle circle = mMap.addCircle(circleOptions);
    }
    private void DrawPolygon(List<LatLng> latLngList,String ColorPolygon) {

        String myColor=ColorPolygon;
        //Color.parseColor(myColor)
        int alphaColor = ColorUtils.setAlphaComponent(Color.parseColor(myColor),60);
        Polygon polygon2 = mMap.addPolygon(new PolygonOptions()
                .addAll(latLngList)
                .strokeColor(Color.RED)
                .fillColor(alphaColor));
        // Store a data object with the polygon, used here to indicate an arbitrary type.
        //polygon2.setTag("beta");
        //  mMap.setOnPolygonClickListener(this);

        //   polygon.setTag("alpha");
        polygon2.setStrokeWidth(3);
        polygon2.setStrokeColor(Color.BLACK);
        polygon2.setFillColor(alphaColor);

    }

    /***/
    //  private void
    /*  myMap.addPolygon(new PolygonOptions().strokeColor(Color.GREEN).fillColor(0x7F228B22).add(latLngList));*/
    /*
 //   @Override
  //  public void onPolygonClick(Polygon polygon) {
     //   int color = polygon.getStrokeColor() ^ 0x00ffffff;
       // polygon.setStrokeColor(color);
        //color = polygon.getFillColor() ^ 0x00ffffff;
        //polygon.setFillColor(color);
   // }
/*
    private void stylePolygon(Polygon polygon) {
        String type = "";
        // Get the data object stored with the polygon.
        if (polygon.getTag() != null) {
            type = polygon.getTag().toString();
        }

        List<PatternItem> pattern = null;
        int strokeColor = COLOR_BLACK_ARGB;
        int fillColor = COLOR_WHITE_ARGB;

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "alpha":
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA;
                strokeColor = COLOR_GREEN_ARGB;
                fillColor = COLOR_PURPLE_ARGB;
                break;
            case "beta":
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_BETA;
                strokeColor = COLOR_ORANGE_ARGB;
                fillColor = COLOR_BLUE_ARGB;
                break;
        }

        polygon.setStrokePattern(pattern);
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
        polygon.setStrokeColor(strokeColor);
        polygon.setFillColor(fillColor);
    }
*/

    /**
     *
     *
     *
     *
     *
     *
     */


}