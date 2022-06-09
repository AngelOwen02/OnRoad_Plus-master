package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.adapter.adapterGeocercas;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.Data;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.presenter.geoCercasPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.presenter.geoCercasPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;

import java.util.ArrayList;
import java.util.List;

public class  geoCercasMainContainer extends AppCompatActivity implements View.OnClickListener, geoCercasView , SearchView.OnQueryTextListener{
    private adapterGeocercas adapter;
    public static List<String> geocercas=new ArrayList<>();
    private RecyclerView rv;
    private LinearLayoutManager geocercasLayout;
    private List<Data> data1;
    private CardView searchView;
    private List<String> names=new ArrayList<>();
    private androidx.appcompat.widget.SearchView searchViewTracking;
    //private Context context;

    private ProgressDialog progressDialog;
    private geoCercasPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_geocercas);
        initView();
        geocercas.clear();
        String[] myStrings = { "Centro Ruta 01", "Centro Ruta 02", "Centro Ruta 03", "Centro Ruta 04",
                "Centro Ruta 05", "Centro Ruta 06","Centro Ruta 07", "Centro Ruta 08", "Centro Ruta 09", "Centro Ruta 10" };
        for (int i=0;i<10;i++)
        {
            geocercas.add(myStrings[i]);
        }


    }
    private void initView()
    {
        ImageView toolbarImgBack = findViewById(R.id.toolbar_unit_tracking_img_back);
        ImageView toolbarImgSearch = findViewById(R.id.toolbar_unit_tracking_img_search);
        rv=findViewById(R.id.recycler_viewgeocercas);
        searchView = findViewById(R.id.tracking_search_view_container);
        searchViewTracking = findViewById(R.id.search_view_tracking);
        searchViewTracking.setOnQueryTextListener(this);
        progressDialog = new ProgressDialog(this);

        toolbarImgBack.setOnClickListener(this);
        toolbarImgSearch.setOnClickListener(this);
        presenter= new geoCercasPresenterImpl(this,getApplicationContext());
        presenter.getGeoCercas();
    }
    private void startTestData(){

        fillTestData();
    }
    private void fillTestData(){
        adapter= new adapterGeocercas(data1,getApplicationContext());
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
    public void showProgressDialog() {
        progressDialog.setMessage("Cargando Geocercas");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void data(List<Data> data) {
        this.data1=data;
        Log.e("dtavalues",""+data1);
        startTestData();
        // if(adapterGeocercas.toggleBackup.size()!=0) {
        List<Boolean> mytoogles = new ArrayList<>();
        for (int i = 0; i < data1.size(); i++) {
            mytoogles.add(i, false);
        }
        adapter.booleanList(mytoogles);
        if(data1!=null)
        {
            names.clear();
            for(int k=0;k<data1.size();k++)
            {
                names.add(data1.get(k).getGeofenceName());
            }
        }

        // }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        List<Data> filterVehicleList = filterVehicles(data1, newText);
        adapter.setFilter(filterVehicleList);
        return true;
    }
   private List<Data> filterVehicles(List<Data> name, String text) {
       List<Data> filteredList = new ArrayList<>();
       text = text.toLowerCase();
       if(name!=null) {
           for (Data GeoCerca : name) {
               String vehicleName = GeoCerca.getGeofenceName().toLowerCase();
               if (vehicleName.contains(text)) {
                   filteredList.add(GeoCerca);
               }
           }
       }
        return filteredList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_unit_tracking_img_search:
                // searchView.setVisibility(View.VISIBLE);
                if(searchView.getVisibility()==View.VISIBLE)
                {
                    searchView.setVisibility(View.GONE);
                }else
                {
                    searchView.setVisibility(View.VISIBLE);

                }
                break;
            case R.id.toolbar_unit_tracking_img_back:
                showTrackingMap();
                break;
        }
    }
    private void showTrackingMap(){
        Bundle bndl = new Bundle();
        bndl.putString("nav", "TRACKING");
        Intent intent = new Intent(geoCercasMainContainer.this, menuViewImpl.class);// MainMenuContainerActivity.class);
        intent.putExtras(bndl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
