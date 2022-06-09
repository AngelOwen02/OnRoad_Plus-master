package com.pnla.onroadplus.z_version2.Containers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.GroupTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.presenter.UnitTrackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.presenter.UnitTrackingPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.view.UnitTrackingView;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;

import java.util.ArrayList;
import java.util.List;

public class UnitTrackingContainer extends AppCompatActivity implements UnitTrackingView, View.OnClickListener, SearchView.OnQueryTextListener {

    private UnitTrackingPresenter presenter;
    private LinearLayout unitButton;
    private LinearLayout groupButton;
    private TextView unitTextButton;
    private TextView groupTextButton;
    private List<Unit> vehicless;
    private RecyclerView rvVehicles;
    private UnitTrackingAdapter adapterVehicles;
    private LinearLayoutManager layoutManagerVehicles;
    private List<Group> groupss;
    private RecyclerView rvGroups;
    private GroupTrackingAdapter groupAdapter;
    private LinearLayoutManager layoutManagerGroups;
    private LinearLayout emptyGroupsImage;
    private ProgressBar progressBar;
    private CardView searchView;
    private androidx.appcompat.widget.SearchView searchViewTracking;

    private SharedPreferences pref;

    private Handler handler = new Handler();
    public Runnable runnable;
    public static int integervalueforrequest;
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


        unitButton.setOnClickListener(this);
        groupButton.setOnClickListener(this);
        toolbarImgBack.setOnClickListener(this);
        toolbarImgSearch.setOnClickListener(this);
        searchViewTracking.setOnQueryTextListener(this);

        presenter = new UnitTrackingPresenterImpl();
        presenter.setView(this);
        presenter.getVehicles(this);
        presenter.getGroups(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,500);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void vehiclesinsiderequest() {
        Log.e("dothetogles1","soy el handler" +integervalueforrequest+"<");
        presenter.getVehiclesinGroups(getBaseContext());
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillVehiclesList(List<Unit> vehicles) {
        this.vehicless = vehicles;
        List<String> names=new ArrayList<>();
        names.clear();
        fillVehicles();
        List<Boolean> mytoogles=new ArrayList<>();
        for(int i=0; i<vehicless.size();i++)
        {
            mytoogles.add(vehicless.get(i).isVehicleStatus());
          names.add(vehicless.get(i).getVehicleName());
        }
        Log.e("groupsitems","im in units"+ names );
            adapterVehicles.booleanList(mytoogles);
    }

    @Override
    public void fillGroupsList(List<Group> groups) {
        this.groupss = groups;
      //  Log.e("unitsthaticansaw3"," toogles adapter: " +groupss.size());
       // Log.e("unitsthaticansaw","im in units"+ groups.toString());
        List<String> names=new ArrayList<>();
        names.clear();
        fillGroups();
        List<Boolean> mytoogles=new ArrayList<>();
        for(int i =0;i<groupss.size();i++)
        {
            mytoogles.add(groupss.get(i).isSelected());
            names.add(groupss.get(i).getVehicle_group() );
        }
        Log.e("groupsitems","im in groups"+ names );
        groupAdapter.booleanList(mytoogles);

       /* if (GroupTrackingAdapter.request==false) {
            Log.e("dothetogles1","ima doing once");
        }

        else{
           presenter.getVehiclesinGroups(getBaseContext());
          Log.e("dothetogles","ima doing once");
        }//*/
    }

    @Override
    public void hideUnitRV() {
        rvVehicles.setVisibility(View.GONE);
    }

    @Override
    public void showUnitRV() {
        rvVehicles.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGroupRV() {
        rvGroups.setVisibility(View.GONE);
    }

    @Override
    public void showGroupRV() {
        rvGroups.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyGroupsImage() {
        emptyGroupsImage.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyGroupsImage() {
        emptyGroupsImage.setVisibility(View.VISIBLE);
    }

    private void fillVehicles() {
        adapterVehicles = new UnitTrackingAdapter(vehicless, this);
        rvVehicles.setAdapter(adapterVehicles);
        layoutManagerVehicles = new LinearLayoutManager(this);
        rvVehicles.setLayoutManager(layoutManagerVehicles);
        //Log.e("unitsthaticansaw","im in units");
        //adapterVehiclesV2.setVehicleAndGroupSelectedListener(FragmentVehiclesV2.this);
    }

    private void fillGroups() {
        groupAdapter = new GroupTrackingAdapter(groupss, this);
        rvGroups.setAdapter(groupAdapter);
        layoutManagerGroups = new LinearLayoutManager(this);
        rvGroups.setLayoutManager(layoutManagerGroups);


        //Log.e("unitsthaticansaw","im in groups");
        // adapterGroupsV2.setGroupSelectedListener(FragmentVehiclesV2.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_unit_tracking_img_back:

              /*  SharedPreferences userDetails = getApplicationContext().getSharedPreferences("Haha", MODE_PRIVATE);
                SharedPreferences.Editor edit = userDetails.edit();
                edit.clear();
                edit.putBoolean("Refresh",true);
                edit.apply();

                super.onBackPressed();*/

              showTrackingMap();




                // getActivity().onBackPressed();
                break;
            case R.id.toolbar_unit_tracking_img_search:
                searchView.setVisibility(View.VISIBLE);
                break;
            case R.id.container_btn_unit_tracking_unit:
                onClickUnitButton();
                handler.removeCallbacks(runnable);
                break;
            case R.id.container_btn_unit_tracking_groups:
                onClickGroupButton();
                handler.postDelayed(runnable = new Runnable() {
                    @Override
                    public void run() {
                        integervalueforrequest=GroupTrackingAdapter.intforrequest;
                        presenter.doiteverytime();

                        handler.postDelayed(this,500);
                    }
                },500);
                break;
        }
    }

    private void showTrackingMap(){
        Bundle bndl = new Bundle();
        bndl.putString("nav", "TRACKING");
        Intent intent = new Intent(UnitTrackingContainer.this,  menuViewImpl.class);//MainMenuContainerActivity.class);
        intent.putExtras(bndl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private List<Unit> getUnitList() {
        return UnitDB.getUnitList();
    }

    private void updateUnitDB(List<Unit> unitList) {
        for (Unit unit : unitList) {
            UnitDB.updateUnits(unit.getId(), unit.isVehicleStatus(), unit.getCveVehicle(), unit.getVehicleSwitch(), unit.getVehicleName(), unit.getVehicleImage(), unit.getSendTime(), unit.getDescBrand(), unit.getDescModel(), unit.getVehicleYear(), unit.getVehicleVin(), unit.getVehiclePlate(), unit.getGeoreference(), unit.getTimeTravel(), unit.getTimeElapsed(), unit.getLatitude(), unit.getLongitude(), unit.getMileage(), unit.getKmTravel(), unit.getCurrentSpeed(), unit.getMaxSpeed());
        }
    }

    private void onClickUnitButton() {
        presenter.getVehicles(this);

        unitButton.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        groupButton.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        unitTextButton.setTextColor(getResources().getColor(R.color.colorWhite));
        groupTextButton.setTextColor(getResources().getColor(R.color.colorOrangeYellow));

        rvVehicles.setVisibility(View.VISIBLE);
        rvGroups.setVisibility(View.GONE);
        emptyGroupsImage.setVisibility(View.GONE);


    }

    private void onClickGroupButton() {
        presenter.getGroups(this);

        groupButton.setBackgroundColor(getResources().getColor(R.color.colorOrangeYellow));
        unitButton.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        groupTextButton.setTextColor(getResources().getColor(R.color.colorWhite));
        unitTextButton.setTextColor(getResources().getColor(R.color.colorOrangeYellow));

        rvGroups.setVisibility(View.VISIBLE);
        rvVehicles.setVisibility(View.GONE);



    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Unit> filterVehicleList = filterVehicles(vehicless, newText);
        List<Group> filterGroupList = filterGroups(groupss, newText);
        adapterVehicles.setFilter(filterVehicleList);
        Log.e("RRRR",String.valueOf(groupss.size()));
        groupAdapter.setFilter(filterGroupList);

        return true;
    }

    private List<Unit> filterVehicles(List<Unit> vehicles, String text) {
        List<Unit> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        for (Unit vehicle : vehicles) {
            String vehicleName = vehicle.getVehicleName().toLowerCase();
            if (vehicleName.contains(text)) {
                filteredList.add(vehicle);
            }
        }
        return filteredList;
    }

    private List<Group> filterGroups(List<Group> groups, String text) {
        if (groups != null){
            List<Group> filteredList = new ArrayList<>();
            text = text.toLowerCase();
            for (Group group : groups) {
                String groupName = group.getVehicle_group().toLowerCase();
                if (groupName.contains(text)) {
                    filteredList.add(group);
                }
            }
            return filteredList;
        }
        return null;
    }
}
