package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.toglesTracking.togglesView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.MainMenuContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.presenter.UnitTrackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.toglesTracking.adaptergroup.togglesGroupsAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.toglesTracking.adapterunits.tooglesUnitsAdapter;

import java.util.ArrayList;
import java.util.List;

public class togglesViewImpl extends AppCompatActivity implements togglesView,View.OnClickListener, SearchView.OnQueryTextListener {

    private UnitTrackingPresenter presenter;
    private LinearLayout unitButton;
    private LinearLayout groupButton;
    private TextView unitTextButton;
    private TextView groupTextButton;
    private List<String> vehicles;
    private RecyclerView rvVehicles;
    private tooglesUnitsAdapter adapterVehicles;
    private LinearLayoutManager layoutManagerVehicles;
    private List<String> groups;
    private RecyclerView rvGroups;
    private togglesGroupsAdapter groupAdapter;
    private LinearLayoutManager layoutManagerGroups;
    private LinearLayout emptyGroupsImage;
    private ProgressBar progressBar;
    private CardView searchView;
    private androidx.appcompat.widget.SearchView searchViewTracking;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_unit_tracking_view_impl);

        initUnitTrackingViewImpl();

    }


    private void initUnitTrackingViewImpl() {
        ImageView toolbarImgBack = findViewById(R.id.toolbar_unit_tracking_img_back);
        ImageView toolbarImgSearch = findViewById(R.id.toolbar_unit_tracking_img_search);
        rvVehicles = findViewById(R.id.recycler_view_unit_tracking);
        rvGroups = findViewById(R.id.recycler_view_group_tracking);
        unitButton = findViewById(R.id.container_btn_unit_tracking_unit);
        groupButton = findViewById(R.id.container_btn_unit_tracking_groups);
        unitTextButton = findViewById(R.id.txt_unit_tracking_unit);
        groupTextButton = findViewById(R.id.txt_unit_tracking_group);
        emptyGroupsImage = findViewById(R.id.container_empty_groups_image);
        progressBar = findViewById(R.id.progress_bar_unit_tracking);
        searchView = findViewById(R.id.tracking_search_view_container);
        searchViewTracking = findViewById(R.id.search_view_tracking);


        unitButton.setOnClickListener(this);//botonunidades
        groupButton.setOnClickListener(this);//botongrupos
        toolbarImgBack.setOnClickListener(this);
        toolbarImgSearch.setOnClickListener(this);
        searchViewTracking.setOnQueryTextListener(this);


        Log.e("newTracking",""+UnitsInteractorImpl.dataofvehiclesgroups+" : "+UnitsInteractorImpl.dataphotoofvehicles);
        fillVehicles();
        fillGroups();
        rvVehicles.setVisibility(View.VISIBLE);
        rvGroups.setVisibility(View.GONE);
    }

    private void fillVehicles() {
        adapterVehicles = new tooglesUnitsAdapter(vehicles, this);
        rvVehicles.setAdapter(adapterVehicles);
        layoutManagerVehicles = new LinearLayoutManager(this);
        rvVehicles.setLayoutManager(layoutManagerVehicles);
    }
    private void fillGroups() {
        groupAdapter = new togglesGroupsAdapter(groups, this);
        rvGroups.setAdapter(groupAdapter);
        layoutManagerGroups = new LinearLayoutManager(this);
        rvGroups.setLayoutManager(layoutManagerGroups);
    }
/*
    @Override
    public void setGruopList(List<String> vehicles) {

    }

    @Override
    public void failureResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
*/
    @Override
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_unit_tracking_img_back:
                showTrackingMap();
            //    Toast.makeText(this, "atras", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbar_unit_tracking_img_search:
                searchView.setVisibility(View.VISIBLE);
            //    Toast.makeText(this, "filtro", Toast.LENGTH_SHORT).show();
                break;
            case R.id.container_btn_unit_tracking_unit:
                onClickUnitButton();
                break;
            case R.id.container_btn_unit_tracking_groups:
                onClickGroupButton();
                break;
        }
    }
    private void showTrackingMap(){
        Bundle bndl = new Bundle();
        bndl.putString("nav", "TRACKING");
        Intent intent = new Intent(togglesViewImpl.this, MainMenuContainerActivity.class);
        intent.putExtras(bndl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void onClickUnitButton() {
        unitButton.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        groupButton.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        unitTextButton.setTextColor(getResources().getColor(R.color.colorWhite));
        groupTextButton.setTextColor(getResources().getColor(R.color.colorOrangeYellow));

        rvVehicles.setVisibility(View.VISIBLE);
        rvGroups.setVisibility(View.GONE);
        emptyGroupsImage.setVisibility(View.GONE);

    }
    private void onClickGroupButton() {
        groupButton.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        unitButton.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        groupTextButton.setTextColor(getResources().getColor(R.color.colorWhite));
        unitTextButton.setTextColor(getResources().getColor(R.color.colorOrangeYellow));

        rvGroups.setVisibility(View.VISIBLE);
        rvVehicles.setVisibility(View.GONE);    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       /* List<String> filterVehicleList = filterVehicles(vehicles, newText);
        //List<Group> filterGroupList = filterGroups(groups, newText);
        adapterVehicles.setFilter(filterVehicleList);*/
       // Log.e("RRRR",String.valueOf(groups.size()));
      //  groupAdapter.setFilter(filterGroupList);

        return true;
    }
    private List<String> filterVehicles(List<String> vehicles, String text) {
        List<String> filteredList = new ArrayList<>();
        text = text.toLowerCase();
      /*for (String vehicle : vehicles) {
            String vehicleName = vehicle.toLowerCase();
            if (vehicleName.contains(text)) {
                filteredList.add(vehicle);
            }
        }*/
        return filteredList;
    }
}
