package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

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
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.view.ZonesViewR;
import com.pnla.onroadplus.z_version2.MenuFragments.ZonesRecycler.view.zonesRecyclerView;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UnitAssignFragment extends AppCompatActivity implements View.OnClickListener, ZonesViewR {
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
        setContentView(R.layout.fragment_unit_assign);
        initView();
    }

    private void initView() {
        ImageView toolbarImgBack = findViewById(R.id.back);
        //ImageView toolbarImgSearch = findViewById(R.id.search_zones);
        //rv=findViewById(R.id.recycler_viewgeocercas);
        //searchView = findViewById(R.id.tracking_search_view_container);
        //togleConstrain=findViewById(R.id.toggleContrain);
        //switchBday=findViewById(R.id.switch1);
        //)
        //progressDialog = new ProgressDialog(this);

        toolbarImgBack.setOnClickListener(this);
        //toolbarImgSearch.setOnClickListener(this);

       //presenter=new presenterRecyclerZonesImpl(this,getApplicationContext());
        //presenter.getZones();
        //Log.e("zonesNames",""+ zonesFragment.fullZones);
        //presenter.requestvisitedPoints(zonesFragment.fullZones);
    }
    private void fillTestData(){
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
            //Boton de retroceso que esta en la ventana de Asignar unidades
            case R.id.back:
                //Toast.makeText(this,"Si funciona", Toast.LENGTH_LONG).show();
                goBackintomenu();
                break;
            /**case R.id. search_zones:
                Toast.makeText(this, "No hace nada.", Toast.LENGTH_SHORT).show();
                break;*/

        }
    }

    //@Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }
    @Override
    public void onBackPressed() {
        goBackintomenu();
    }

    private void goBackintomenu()
    {
        Bundle bndl = new Bundle();
        bndl.putString("nav", "ZONES");
        Intent intent = new Intent(UnitAssignFragment.this,  menuViewImpl.class);//MainMenuContainerActivity.class);
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