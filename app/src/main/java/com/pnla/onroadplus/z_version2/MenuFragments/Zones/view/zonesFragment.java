package com.pnla.onroadplus.z_version2.MenuFragments.Zones.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.UnitAssignSupportFragment;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.container.UnitAssignSupportContainer;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.pointsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.visitedData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.model.zonesData;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.presenter.zonesPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.presenter.zonesPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.view.zonesRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class zonesFragment  extends Fragment implements OnMapReadyCallback,zonesView{
    public static final String TAG = zonesFragment.class.getSimpleName();
    private MapView mView;
    private GoogleMap mMap;
    private ProgressDialog progressDialog;
    public static List<String> geocercas=new ArrayList<>();
    private Handler handler = new Handler();
    private Runnable runnable;
    private zonesPresenter presenter;
    private List<Integer> cveZonesfullList=new ArrayList<>();
    private zonesData[] zonesdata;
    private  List<pointsData> pointsZones;
    private List<String> zonesandColors;
    private List<String> Colors;
    private boolean isdraw=false;
    public static List<String>  pointInZone=new ArrayList<>();
    public static List<Integer> fullZones= new ArrayList<>();
    private List<LatLng> redDots;
    private List<String> allDots=new ArrayList<>();
    private List<List<String>> greenDots=new ArrayList<>();
    List<LatLng> pointsGreenDouble=new ArrayList<>();
    List<String> lats=new ArrayList<>();
    List<String> longs=new ArrayList<>();
    private List<String> positionGreenDots=new ArrayList<>();
    private boolean handlerfirst=false;
    private  int handlertime=8000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking_zones, container, false);
        bindViews(view);
        bindViews2(view);
        initTrackingMapFragment(view,savedInstanceState);
       // Log.e("pointZoneAdapter","points= "+pointInZone);
        return view;
    }
    private void initTrackingMapFragment(View view, Bundle savedInstanceState) {

        onCreateViewMap(savedInstanceState);

    }
    private void bindViews(View view) {
        mView = view.findViewById(R.id.map_view_zones);
        ImageView toolbar = view.findViewById(R.id.eye_zones);//ojo
        progressDialog = new ProgressDialog(getActivity());
       presenter=new zonesPresenterImpl(this,getContext());

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                geoZonasViewImpl();
                positionGreenDots.clear();
            }
        });
    }

    //Cuando presionemos el boton de asignacion de apoyos
    private void bindViews2(View view){
        mView = view.findViewById(R.id.map_view_zones);
        ImageView toolbar = view.findViewById(R.id.eye_zones2);//ojo
        progressDialog = new ProgressDialog(getActivity());
        //presenter=new zonesPresenterImpl(this,getContext());

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Si funciona", Toast.LENGTH_LONG).show();
                asignarUnidadImp();
                //positionGreenDots.clear();
            }
        });
    }

    //Esto es para enviar al fragmento de Asignacion de Apoyos
    private void asignarUnidadImp(){
        Intent intent = new Intent(getContext(), UnitAssignSupportContainer.class);
        startActivity(intent);
    }

    private void geoZonasViewImpl() {
        Intent intent = new Intent(getContext(), zonesRecyclerView.class);
       // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void onCreateViewMap(Bundle savedInstanceState) {
        mView.onCreate(savedInstanceState);
   //     Log.e("onCreateViewMap", "OK");

        if (mView != null) {
            mView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 2.0f));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 4.5f));
        uiSettingsMap(mMap);
       //knowzoom();
        presenter.getZones();
    }
    private void knowzoom()
    {
//        handler.postDelayed(runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.e("requestdeunitsagain","zoom: "+mMap.getCameraPosition().zoom);
//
//                handler.postDelayed(this, 1000);
//            }
//        }, 1000);
    }
    private void uiSettingsMap(GoogleMap googleMap) {
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setRotateGesturesEnabled(false);
    }

    @Override
    public void onResume() {
        mView.onResume();
        super.onResume();
        handler.postDelayed(runnable,6000);
    }

    @Override
    public void onStart() {
        super.onStart();
        mView.onStart();
       // refreshFragment();
    }

    private void refreshFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        mView.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onStop() {
        super.onStop();
        mView.onStop();
        handler.removeCallbacks(runnable);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mView.onLowMemory();
    }

    @Override
    public void method() {

    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void getZonesNamesAndColors(zonesData[] zones) {/**   OBTINENE LAS CVES DE LAS ZONAS*/
    this.zonesdata=zones;
        cveZonesfullList.clear();
        fullZones.clear();
      //  Log.e("zonesnames&colors",""+zonesdata);
        if(pointInZone==null||pointInZone.isEmpty()==true) {
            if (zonesdata != null) {
                for (int i = 0; i < zones.length; i++) {
                  //  Log.e("allcveZones", "list integer zones NULL OR EMPTY :" + pointInZone);
                    fullZones.add(zonesdata[i].getCveLayer());
                    Log.e("allcveZones", "list integer zones NULL OR EMPTY :" + fullZones);
                   // Log.e("zonesnames&colors2", "" + zonesdata[i].getCveLayer());//.getCveLayer()+" | " +zonesdata.get(i).getTabLayerColor());
                    cveZonesfullList.add(zonesdata[i].getCveLayer());
                }
                if (cveZonesfullList != null) {

                    Log.e("allcveZones", "list integer zones :" + fullZones);


                    presenter.requestZonePoints(cveZonesfullList);
                    //  presenter.requestvisitedPoints(cveZonesfullList);
                }
            }
        }
        else
        {

            final List <Integer> aloneZones=new ArrayList<>();
           for(int i=0;i<pointInZone.size();i++)
            {
                aloneZones.add(Integer.valueOf( pointInZone.get(i)));
            }
            presenter.requestZonePoints(aloneZones);
            handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //Log.e("requestdeunitsagain","zoom: "+mMap.getCameraPosition().zoom);

                presenter.requestvisitedPoints(aloneZones);
                mMap.clear();
                positionGreenDots.clear();
                drawAllZones(lats, longs, Colors);
                drawRedDots(redDots);
                drawGreenDots(pointsGreenDouble);
                requestHandlerGreenDots();
                handlerStatus();
                handler.postDelayed(this, handlertime);
            }
        }, handlertime);
          if(zonesdata!=null)
          {
              Log.e("allcveZones", "list integer zones NULL OR EMPTY :" + zonesdata.length);
              if(zonesdata.length>0)
              {
                  for(int i=0;i<zonesdata.length;i++)
                  {
                      fullZones.add(zonesdata[i].getCveLayer());
                  }
              }
          }
            Log.e("allcveZones", "list integer zones NULL OR EMPTY :" + fullZones);

        }
    }

    private void  handlerStatus()
    {
        if(handlerfirst==true)
        {
            handlertime=30000;
        }
        handlerfirst=true;


    }

    @Override
    public void setPointsVisited( List<visitedData> dataVisited) {/**esta info son los porcentajes por zona*/

    }




    @Override
    public void setColorofZones(List<String> colorsZones) {
        this.Colors=colorsZones;
    }

    @Override
    public void draRedDots(List<String> dotsCve,List<LatLng> pointsdouble) {
        this.allDots=dotsCve;
       this.redDots=pointsdouble;

  //     Log.e("dotsGreen","  all Dots  "+allDots.size()+"   "+allDots);//"latslong size "+redDots.size()+
    //    Log.e("dotsRed","  all Dots  "+redDots.size()+"   "+redDots);
       drawRedDots(pointsdouble);

//        handler.postDelayed(runnable = new Runnable() {
//            @Override
//            public void run() {
//                //Log.e("requestdeunitsagain","zoom: "+mMap.getCameraPosition().zoom);
//
//                handler.postDelayed(this, 5000);
//            }
//        }, 5000);

        requestHandlerGreenDots();
    }

    @Override
    public void setGreenDots(List<List<String>> visited) {
        this.greenDots=visited;
        if(greenDots!=null) {
         for (int i=0;i<greenDots.size();i++)
           {
             if (greenDots.contains("0")) {
                 greenDots.remove("0");
             }

           }
          //  Log.e("dotsGreen", "greenDots  " + greenDots.size() + "   " + greenDots + '\n');
        }
        requestHandlerGreenDots();

    }
    private void requestHandlerGreenDots() {

        if(allDots!=null)
        {
            for(int h=0;h<allDots.size();h++)
            {
                for (int i=0;i<greenDots.size();i++)
                {
                    if (greenDots.get(i).contains(allDots.get(h))) {
                        positionGreenDots.add(String.valueOf(h));
                    } else {

                    }
                   //
                }

               // iterationgreendots();
               // positionGreenDots.clear();
            }
            Log.e("dotsGreen","size: "+positionGreenDots.size()+" "+positionGreenDots);
            iterationgreendots();


        }
    }
    private void iterationgreendots()
    {

        pointsGreenDouble.clear();
     //   Log.e("dotsGreen","size: "+positionGreenDots.size()+" "+positionGreenDots);

        if(positionGreenDots!=null)
        {
            for(int m=0;m<positionGreenDots.size();m++)
            {

                pointsGreenDouble.add(  redDots.get(Integer.valueOf( positionGreenDots.get(m))));
           //     Log.e("dotsGreen","dot: "+Integer.valueOf( positionGreenDots.get(m))+" | "+allDots.get(Integer.valueOf( positionGreenDots.get(m))));//allDots

            }
         //   Log.e("compareList",""+pointsGreenDouble+'\n');
            drawGreenDots(pointsGreenDouble);
        }
    }
    private void drawRedDots(List<LatLng> pointsdouble)
    {
        int height = 15;
        int width = 15;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.red_dot);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        if(pointsdouble!=null) {
            for(int i=0;i<pointsdouble.size();i++) {
                mMap.addMarker(new MarkerOptions()
                        .position(pointsdouble.get(i)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            }
        }

    }
    private void drawGreenDots(List<LatLng> pointsdouble)
    {
        int height = 20;
        int width = 20;
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.green_dot);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        if(pointsdouble!=null) {
            for(int i=0;i<pointsdouble.size();i++) {
                mMap.addMarker(new MarkerOptions()
                        .position(pointsdouble.get(i)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
            }
        }

    }
    @Override
    public void setZonesAndColors(List<String> zonesAndColors) {/**   OBTINENE LAS CVES DE LAS ZONAS Y LOS COLORES*/
        this.zonesandColors=zonesAndColors;
        if(zonesandColors!=null)
        {
            for (int i=0;i<zonesandColors.size();i++) {
            //    Log.e("zonesnames&colors1", "" + zonesandColors.get(i));
            }
        }


    }

    @Override
    public void setPointsPerZone( List<pointsData> data) {/***/
        this.pointsZones=data;
        if(pointsZones!=null)
        {

            List<String> alonePoints=new ArrayList<>();
            lats.clear();
            longs.clear();
            alonePoints.clear();


            if(pointInZone==null||pointInZone.isEmpty()==true) {
//                Log.e("alonePoints :", "no iteramos puntos" );
//                Log.e("alonePoints :", ""+pointInZone );

            }else
            {
//                Log.e("alonePoints :", "size "+pointsZones.size() );
//                for (int i = 0; i < pointsZones.size(); i++) {
//                    Log.e("alonePoints :", "iteracion: " + i);
//                            for (int j = 0; j < lats.size(); j++) {
//
//                                alonePoints.add(pointsZones.get(i).getInterests().get(j).getInterestLat() + "|" + pointsZones.get(i).getInterests().get(j).getInterestLon());
//                            }
//
//                    Log.e("alonePoints :", "puntos: " + alonePoints);
//
//                }

            }


            for (int i=0;i<pointsZones.size();i++) {
            //    Log.e("setZones", "" + pointsZones.get(i).getCveLayer());
                lats.add( pointsZones.get(i).getLats());
                longs.add( pointsZones.get(i).getLngs());

            }
            if(!lats.isEmpty())
            {

                if(isdraw==false) {
                    drawAllZones(lats, longs, Colors);
                    progressDialog.dismiss();

                }
            }else
            {
                lats.clear();
                longs.clear();
            }
        }
    }



    private void drawAllZones( List<String> lats, List<String> longs,List<String> Colors) {
        isdraw=true;

        List<String> latitudeList=new ArrayList<>();
        List<String> longitudeList=new ArrayList<>();

        List<String> latitudeListallbounds=new ArrayList<>();
        List<String> longitudeListallbounds=new ArrayList<>();
        longitudeListallbounds.clear();
        latitudeListallbounds.clear();
        for (int i=0;i<lats.size();i++)
        {
            String lislats =String.valueOf(lats.get(i));
            String lislongs = String.valueOf(longs.get(i));
            String color =Colors.get(i);
            String[] parts1 = lislats.split(",");
            String[] parts2 = lislongs.split(",");
            for (int j = 0; j < lislats.split(",").length; j++) {// separa los items con split por,
                latitudeList.add(parts1[j]);
                longitudeList.add(parts2[j]);
                latitudeListallbounds.add(parts1[j]);
                longitudeListallbounds.add(parts2[j]);
            }
            doubleConvertion(latitudeList,longitudeList,color);
            if(i==lats.size()-1) {/** size-1 es e numero de iteraciones maximas alas que llega*/
                fitbounds(latitudeListallbounds, longitudeListallbounds);
            }
            latitudeList.clear();
            longitudeList.clear();
        }

    }

    private void doubleConvertion( List<String> latitudeList, List<String> longitudeList,String Colors) {
        List<LatLng> points=new ArrayList<>();


        if (!latitudeList.isEmpty()) {
            for (int z = 0; z < latitudeList.size(); z++) {
                String x = latitudeList.get(z);
               // Log.e("geoCercas4", "Doubles: " + x);

                Double dx = Double.parseDouble(x);
                String y = longitudeList.get(z);
                Double dy = Double.parseDouble(y);
                LatLng point = new LatLng(dx, dy);

                points.add(point);
            }
            DrawPolygon(points,Colors);//,colorPolygon);
            points.clear();
        }
    }
    private void DrawPolygon(List<LatLng> latLngList, String colors){//,String ColorPolygon) {
        String myColor=colors;
        int alphaColor = ColorUtils.setAlphaComponent(Color.parseColor(myColor),60);
        Polygon polygon2 = mMap.addPolygon(new PolygonOptions()
                .addAll(latLngList)
                .strokeColor(Color.RED)
                .fillColor(alphaColor));
        polygon2.setStrokeWidth(3);
        polygon2.setStrokeColor(Color.BLACK);
        polygon2.setFillColor(alphaColor);


    }

    private void fitbounds(List<String> latitudeList, List<String> longitudeList) {
        List<LatLng> points=new ArrayList<>();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        if (!latitudeList.isEmpty()) {
            for (int z = 0; z < latitudeList.size(); z++) {
                String x = latitudeList.get(z);
               // Log.e("geoCercas4", "Doubles: " + x);

                Double dx = Double.parseDouble(x);
                String y = longitudeList.get(z);
                Double dy = Double.parseDouble(y);
                LatLng point = new LatLng(dx, dy);

                builder.include(point);
            }
          //  Log.e("geocercasSize", ""+latitudeList.size());
            LatLngBounds bounds = builder.build();
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200));
        }
    }
    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Cargando Zonas");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

}


