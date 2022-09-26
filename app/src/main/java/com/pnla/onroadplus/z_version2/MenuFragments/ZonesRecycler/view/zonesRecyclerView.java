package com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.adapter.zonesAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.view.zonesFragment;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.pointsData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.visitedData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.model.zonesData1;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.presenter.presenterRecyclerZones;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.presenter.presenterRecyclerZonesImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import io.fabric.sdk.android.services.common.SafeToast;

public class zonesRecyclerView  extends AppCompatActivity implements View.OnClickListener,ZonesViewR {
    public static List<String> geoZonas = new ArrayList<>();
    private RecyclerView rv;
    private CardView searchView;
    private SearchView searchViewTracking;
    private ProgressDialog progressDialog;
    private zonesAdapter adapter;
    private presenterRecyclerZones presenter;
    private zonesData1[] zonesdata;
    private List<visitedData1> visitedPoints;
    private List<Integer> cveZonesfullList=new ArrayList<>();
    private ConstraintLayout togleConstrain;
    private Switch switchBday;
    private List<String> daysOnWeek;
    public static  List<String> volante;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_geozonas);
        initView();
/*
        geoZonas.clear();
        String[] myStrings = {"Centro Ruta 01", "Centro Ruta 02", "Centro Ruta 03", "Centro Ruta 04",
                "Centro Ruta 05", "Centro Ruta 06", "Centro Ruta 07", "Centro Ruta 08", "Centro Ruta 09", "Centro Ruta 10"};
        for (int i = 0; i < 10; i++) {
            geoZonas.add(myStrings[i]);
        }*/
    }
    private void initView() {
        ImageView toolbarImgBack = findViewById(R.id.back);
        ImageView toolbarImgSearch = findViewById(R.id.search_zones);
        rv=findViewById(R.id.recycler_viewgeocercas);
        searchView = findViewById(R.id.tracking_search_view_container);
        togleConstrain=findViewById(R.id.toggleContrain);
        switchBday=findViewById(R.id.switch1);
        searchViewTracking = (SearchView) this.findViewById(R.id.search_view_tracking1);
        searchViewTracking.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<visitedData1> visitedDataList = filterZones(visitedPoints, newText);
                if(visitedPoints!=null) {
                    adapter.setFilter(visitedDataList);
                }
                return false;
            }
        });
        switchBday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("calendarFilter","toggle stuatus: "+ isChecked );

                if (isChecked==true) {
                    Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_WEEK);
                    Log.e("calendarFilter",""+daysOnWeek);
                    switch (day) {
                        case Calendar.SUNDAY:
                            Log.e("calendarFilter", "Domingo");
                            List<visitedData1> sundayList = new ArrayList<>();
                            sundayList.clear();
                            for (int i=0;i<daysOnWeek.size();i++ )/**   itera sobre la lista de dias*/
                            {
                                if(daysOnWeek.get(i).contains("Do"))
                                {
                                    String[] parts=daysOnWeek.get(i).split("#");
                                    String part1=parts[1];
                                    Log.e("calendarFilter1",""+parts[1]);

                                    for (int k=0;k<visitedPoints.size();k++)
                                    {
                                        if(visitedPoints.get(k).getCveLayer().equals(part1))/** si */
                                        {
                                            sundayList.add(visitedPoints.get(k));
                                        }
                                    }
                                    adapter.setFilter(sundayList);

                                }else
                                {
                                    adapter.setFilter(sundayList);
                                }
                            }
                            break;
                        case Calendar.MONDAY:
                            Log.e("calendarFilter", "Lunes     " + daysOnWeek.size());
                            List<visitedData1> mondayList = new ArrayList<>();
                            mondayList.clear();
                            for (int i=0;i<daysOnWeek.size();i++ )/**   itera sobre la lista de dias*/
                            {
                                if(daysOnWeek.get(i).contains("Lu"))
                                {
                                    String[] parts=daysOnWeek.get(i).split("#");
                                    String part1=parts[1];
                                    Log.e("calendarFilter1",""+parts[1]);

                                    for (int k=0;k<visitedPoints.size();k++)
                                    {
                                        if(visitedPoints.get(k).getCveLayer().equals(part1))/** si */
                                        {
                                            mondayList.add(visitedPoints.get(k));
                                        }
                                    }
                                    adapter.setFilter(mondayList);

                                }else
                                {
                                    adapter.setFilter(mondayList);
                                }
                            }
                            break;
                        case Calendar.TUESDAY:
                            Log.e("calendarFilter", "Martes");
                            List<visitedData1> tuesdayList = new ArrayList<>();
                            tuesdayList.clear();
                            for (int i=0;i<daysOnWeek.size();i++ )/**   itera sobre la lista de dias*/
                            {
                                if(daysOnWeek.get(i).contains("Ma"))
                                {
                                    String[] parts=daysOnWeek.get(i).split("#");
                                    String part1=parts[1];
                                    Log.e("calendarFilter1",""+parts[1]);

                                    for (int k=0;k<visitedPoints.size();k++)
                                    {
                                        if(visitedPoints.get(k).getCveLayer().equals(part1))/** si */
                                        {
                                            tuesdayList.add(visitedPoints.get(k));
                                        }
                                    }
                                    adapter.setFilter(tuesdayList);

                                }else
                                {
                                    adapter.setFilter(tuesdayList);
                                }
                            }
                            break;
                        case Calendar.WEDNESDAY:
                            Log.e("calendarFilter", "Miercoles");
                            List<visitedData1> wednesdayList = new ArrayList<>();
                            wednesdayList.clear();
                            for (int i=0;i<daysOnWeek.size();i++ )/**   itera sobre la lista de dias*/
                            {
                                if(daysOnWeek.get(i).contains("Mi"))
                                {
                                    String[] parts=daysOnWeek.get(i).split("#");
                                    String part1=parts[1];
                                    Log.e("calendarFilter1",""+parts[1]);

                                    for (int k=0;k<visitedPoints.size();k++)
                                    {
                                        if(visitedPoints.get(k).getCveLayer().equals(part1))/** si */
                                        {
                                            wednesdayList.add(visitedPoints.get(k));
                                        }
                                    }
                                    adapter.setFilter(wednesdayList);

                                }else
                                {
                                    adapter.setFilter(wednesdayList);
                                }
                            }
                            break;
                        case Calendar.THURSDAY:
                            Log.e("calendarFilter", "Jueves");
                            List<visitedData1> thursdayList = new ArrayList<>();
                            thursdayList.clear();
                            for (int i=0;i<daysOnWeek.size();i++ )/**   itera sobre la lista de dias*/
                            {
                                if(daysOnWeek.get(i).contains("Ju"))
                                {
                                    String[] parts=daysOnWeek.get(i).split("#");
                                    String part1=parts[1];
                                    Log.e("calendarFilter1",""+parts[1]);

                                    for (int k=0;k<visitedPoints.size();k++)
                                    {
                                        if(visitedPoints.get(k).getCveLayer().equals(part1))/** si */
                                        {
                                            thursdayList.add(visitedPoints.get(k));
                                        }
                                    }
                                    adapter.setFilter(thursdayList);

                                }else
                                {
                                    adapter.setFilter(thursdayList);
                                }
                            }
                            break;
                        case Calendar.FRIDAY:
                            Log.e("calendarFilter", "Viernes");
                            List<visitedData1> fridayList = new ArrayList<>();
                            fridayList.clear();
                            for (int i=0;i<daysOnWeek.size();i++ )/**   itera sobre la lista de dias*/
                            {
                                if(daysOnWeek.get(i).contains("Vi"))
                                {
                                    String[] parts=daysOnWeek.get(i).split("#");
                                    String part1=parts[1];
                                    Log.e("calendarFilter1",""+parts[1]);

                                    for (int k=0;k<visitedPoints.size();k++)
                                    {
                                        if(visitedPoints.get(k).getCveLayer().equals(part1))/** si */
                                        {
                                            fridayList.add(visitedPoints.get(k));
                                        }
                                    }
                                    adapter.setFilter(fridayList);

                                }else
                                {
                                    adapter.setFilter(fridayList);
                                }
                            }
                            break;
                        case Calendar.SATURDAY:
                            Log.e("calendarFilter", "Sabado" + daysOnWeek.size());
                            List<visitedData1> saturdayList = new ArrayList<>();
                            saturdayList.clear();
                            for (int i=0;i<daysOnWeek.size();i++ )/**   itera sobre la lista de dias*/
                            {
                                if(daysOnWeek.get(i).contains("Sa"))
                                {
                                    String[] parts=daysOnWeek.get(i).split("#");
                                    String part1=parts[1];
                                    Log.e("calendarFilter1",""+parts[1]);

                                    for (int k=0;k<visitedPoints.size();k++)
                                    {
                                        if(visitedPoints.get(k).getCveLayer().equals(part1))/** si */
                                        {
                                            saturdayList.add(visitedPoints.get(k));
                                        }
                                    }
                                    adapter.setFilter(saturdayList);

                                }else
                                {
                                    adapter.setFilter(saturdayList);
                                }
                            }
                            break;
                    }
                }else
                {
                    adapter.setFilter(visitedPoints);
                  //  searchViewTracking.setQuery("",false);

                }

            }
        });
        progressDialog = new ProgressDialog(this);

        toolbarImgBack.setOnClickListener(this);
        toolbarImgSearch.setOnClickListener(this);

        presenter=new presenterRecyclerZonesImpl(this,getApplicationContext());
         presenter.getZones();
        Log.e("zonesNames",""+zonesFragment.fullZones);
        //presenter.requestvisitedPoints(zonesFragment.fullZones);
    }

    private void fillTestData() {
        Log.e("listazonas", "" + visitedPoints);
        adapter= new zonesAdapter(visitedPoints,getApplicationContext());
        //geocercasLayout= new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);

        rv.setAdapter(adapter);
/*        if(rv.getAdapter().getItemCount()!=0)
        {
       //     Toast.makeText(getApplicationContext(), ""+rv.getAdapter().getItemCount(), Toast.LENGTH_SHORT).show();

        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id. back:
                goBackintomenu();
                break;
            case R.id. search_zones:
                if(searchView.getVisibility()==View.VISIBLE)
                {
                    searchView.setVisibility(View.GONE);
                    togleConstrain.setVisibility(View.GONE);
                }else
                {
                    searchView.setVisibility(View.VISIBLE);
                    togleConstrain.setVisibility(View.VISIBLE);


                }
                break;
        }
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        goBackintomenu();
    }

    private void goBackintomenu() {
        Bundle bndl = new Bundle();
        bndl.putString("nav", "ZONES");
        Intent intent = new Intent(zonesRecyclerView.this,  menuViewImpl.class);//MainMenuContainerActivity.class);
        intent.putExtras(bndl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void getZonesNamesAndColors(zonesData1[] zones) {/**esto trae todas las zonas*/
        this.zonesdata=zones;
        Log.e("zonesNames","zonesData: array "+ zonesdata.length);
        cveZonesfullList.clear();
        if(zonesdata!=null)
        {
            for(int i=0;i<zones.length;i++)
            {

              Log.e("zonesnames&colors12","zonesData: array "+ zonesdata[i].getCveLayer()+"  / "+zonesdata[i].getCve_employee()+"  / "+zonesdata[i].getSupervisor_name());//.getCveLayer()+" | " +zonesdata.get(i).getTabLayerColor());
                cveZonesfullList.add(zonesdata[i].getCveLayer());
            }
            if(cveZonesfullList!=null)
            {
                Log.e("zonesNames",""+zonesFragment.fullZones);
                Log.e("zonesnames&colors12","zonesData: full cve "+ cveZonesfullList);
                presenter.requestvisitedPoints(cveZonesfullList);

            }
        }
    }

    @Override
    public void setGreenDots(List<List<String>> visited) {

    }

    @Override
    public void setDayofweek(List<String> dayAvalable) {
        this.daysOnWeek=dayAvalable;

    }

    @Override
    public void setColorVolante(List<String> adapterCheck) {
        this.volante=adapterCheck;


    }

    @Override
    public void showProgressDialog() {
        progressDialog.setMessage("Cargando Zonas");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void setZonesAndColors(List<String> zonesAndColors) {

    }

    @Override
    public void setPointsPerZone(List<pointsData1> data) {

    }

/**SETEO DE DATA DE DESCIPCION*/
    @Override
    public void setPointsVisited(List<visitedData1> dataVisited) {
       this.visitedPoints=dataVisited;

        if(volante!=null)
        {
            if(volante.size()==visitedPoints.size()) {

                for (int i = 0; i < volante.size(); i++) {
   //                 Log.e("asignaciondeConductores", "" + volante.get(i));
                    visitedPoints.get(i).setColorVolante( volante);
                }
            }

        }
        progressDialog.dismiss();
        setdataSupervisor();
        fillTestData();//AQUI SE LLENAN LOS DATOS

    }
    private void setdataSupervisor()
    {
        for (int i=0;i<visitedPoints.size();i++)
        {
//     esto da null----> validar       Log.e("zonesnames&colors13",""+visitedPoints.get(i).getCveLayer()+"  N: "+visitedPoints.get(i).getSupervisor_name()+"  cve "+visitedPoints.get(i).getCve_employee());//+"   "+visitedPoints.size()+" z "+zonesdata.length);
            if(zonesdata!=null || !(zonesdata.length ==0))
            {
                for(int l=0; l<zonesdata.length;l++)
                {
                    if(visitedPoints.get(i).getCveLayer().equals(String.valueOf( zonesdata[l].getCveLayer())))
                    {
                        visitedPoints.get(i).setSupervisor_name(zonesdata[l].getSupervisor_name());
                        visitedPoints.get(i).setCve_employee(Integer.valueOf(  zonesdata[l].getCve_employee()));
                    }
                }
            }
  //          Log.e("zonesnames&colors14",""+visitedPoints.get(i).getCveLayer()+"  N: "+visitedPoints.get(i).getSupervisor_name()+"  cve "+visitedPoints.get(i).getCve_employee());
   //         Log.e("calendarFilter",""+visitedPoints.get(i).getCveLayer());/**158*/
        }
    }
    private List<visitedData1> filterZones(List<visitedData1> vData, String text){
        List<visitedData1> visitedDataList = new ArrayList<>();
        text =  text.toLowerCase();
        if (vData != null){
            for (visitedData1 zones : vData){
                if (zones.getDescLayer() != null){
                    String zoneName = zones.getDescLayer().toLowerCase();
                    if (zoneName != null) {
                        if (zoneName.contains(text)) {
                            visitedDataList.add(zones);

                        }
                    }
                }
            }
        }
        return visitedDataList;
    }

    private  List<visitedData1> filterByDay(List<visitedData1> dataAdapter,String day)
    {
        List<visitedData1> adapterbydayList=new ArrayList<>();
        if(dataAdapter!=null)
        {
//            for()
//            {
//
//            }
        }
        return adapterbydayList;
    }

    @Override
    public void setColorofZones(List<String> colorsZones) {

    }

    @Override
    public void draRedDots(List<String> dotsCve,List<LatLng> pointsdouble) {

    }
}