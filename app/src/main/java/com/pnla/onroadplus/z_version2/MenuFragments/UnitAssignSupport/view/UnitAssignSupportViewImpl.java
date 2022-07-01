package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
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
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dynatrace.android.agent.Dynatrace;
import com.pnla.onroadplus.R;
//import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.GroupTrackingAdapter;
//import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.container.UnitAssignSupportContainer;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractorImpl;

//import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
//import com.pnla.onroadplus.z_version2.MenuFragments.Units.adapter.UnitAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.adapter.UnitAssignSupportAdapter;
//import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
//import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractor;
//import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
//import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
//import com.pnla.onroadplus.z_version2.MenuFragments.Units.presenter.UnitsPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.presenter.UnitAssignSupportPresenter;
//import com.pnla.onroadplus.z_version2.MenuFragments.Units.presenter.UnitsPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.presenter.UnitAssignSupportPresenterImpl;
//import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsView;
//import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportView;
//import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsViewImpl;
//import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.UnitSupport;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnitAssignSupportViewImpl extends Fragment implements UnitAssignSupportView, ImageView.OnClickListener {

    //public static final String TAG = UnitsViewImpl.class.getSimpleName();
    public static final String TAG = UnitAssignSupportViewImpl.class.getSimpleName();
    //private SearchView searchView;
    //private RecyclerView rvUnits;
    private RecyclerView rvVehicles;
    //private UnitAdapter unitAdapter;
    private UnitAssignSupportAdapter unitAssignSupportAdapter;
    //private CardView searchViewContainer;
    //private List<Unit> vehicles;
    private List<SupportUnitData> soportes;
    //private List<Unit> undredlist=new ArrayList<>();
    private ProgressBar progressBar;
    private ImageView toolbarItem;
    private ImageView toolbarImgBack;
    //private TextView txtToolbar;
    private ProgressDialog progressDialog;
    final Handler handler = new Handler();
    private Runnable runnable;
    //private Parcelable state;
    //private UnitsPresenter presenter;
    private UnitAssignSupportPresenter presenter;
    //private GroupTrackingAdapter groupTrackingAdapter;
    //private List<Group> unitGroup;
    //private List<Unit> unidades;
    int position;
    //   private boolean doitonce=false;
    private List<String> directions;
    private List<Integer> cvesalternos=new ArrayList<>();
    //private List<String> itemgeosList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_units_impl, container, false);
        View view = inflater.inflate(R.layout.fragment_unit_assign_support_view_impl, container, false);

        initUnitsViewImpl(view);
        //update();
        return view;
    }


    private void initUnitsViewImpl(View view) {
        initViewID(view);
        initPresenter();
        initOnClickListeners();
        //UnitsInteractorImpl.dataofvehiclesgroupsnames.clear();
    }

    private void initViewID(View view) {
        /**toolbarItem = view.findViewById(R.id.search_toolbar_item);
        txtToolbar = view.findViewById(R.id.search_toolbar_title);
        rvUnits = view.findViewById(R.id.recycler_view_units);
        progressBar = view.findViewById(R.id.units_view_progress_bar);
        searchViewContainer = view.findViewById(R.id.units_search_view_container);
        searchView = view.findViewById(R.id.search_view_units);
        progressDialog = new ProgressDialog(getActivity());
        txtToolbar.setText("Unidades 2");*/

        toolbarImgBack = view.findViewById(R.id.back);
        rvVehicles = view.findViewById(R.id.recycler_view_unit_tracking2);
        progressBar = view.findViewById(R.id.progress_bar_unit_tracking);

//        Dynatrace.applyUserPrivacyOptions(UserPrivacyOptions.builder()
//                .withDataCollectionLevel(DataCollectionLevel.USER_BEHAVIOR)
//                .withCrashReportingOptedIn(true)
//                .build());

        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        // String test="TestDynatraceusername";
        Dynatrace.identifyUser( userName);
        Log.e("dynatracelog", "units user dynatrace   " + userName);
    }

    private void initPresenter() {
        //presenter = new UnitsPresenterImpl(getContext());
        presenter = new UnitAssignSupportPresenterImpl(getContext());
        presenter.setView(this);

//        DTXAction processUnits= Dynatrace.enterAction("processUnits");//
//        processUnits.getRequestTag();
        //presenter.getFullVehicles();
        //     processUnits.leaveAction();

        // presenter.getvehiclesINgroups();


    }

    /**private void update(){
        //final UnitsPresenter presenter = new UnitsPresenterImpl(getContext());
        //  presenter.setView(this);

        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //presenter.getFullVehicles();
                //UnitsInteractor unitsInteractor = new UnitsInteractorImpl(presenter,getContext());
                UnitAssignSupportInteractor unitAssignSupportInteractor = new UnitAssignSupportInteractorImpl(presenter,getContext());
                //unitsInteractor.getAllVehiclesFromAPI();
                unitAssignSupportInteractor.getGeoreferencefromAPI();
                presenter.hideProgressDialog();
                searchViewContainer.setVisibility(View.GONE);
                handler.postDelayed(this,60000);
            }
        },60000);

    }*/

    private void initOnClickListeners() {
        //searchView.setOnQueryTextListener(this);
        //toolbarItem.setOnClickListener(this);
        toolbarImgBack.setOnClickListener(this);
    }

    /**@Override
    //public void setUnitList(List<Unit> unitList) throws IOException {
    public void setUnitList(List<SupportUnitData> unitList) throws IOException {
        /*this.vehicles = unitList;
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvUnits.setLayoutManager(linearLayoutManager);
        unitAdapter = new UnitAdapter(vehicles, getContext());
        rvUnits.setAdapter(unitAdapter);*/

        //List<Unit> allUnitsList = UnitDB.getUnitList();
        //List<Unit> activeUnitsList = UnitDB.getUnitListActive();
        //List<Group> activeGroupslist = GroupDB.getActiveGroupList();
        /**this.vehicles = unitList;
        //  Log.e("partsrequestvehicles",""+vehicles.size());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //rvUnits.setLayoutManager(linearLayoutManager);
        rvVehicles.setLayoutManager(linearLayoutManager);
        List<Integer> allcves=new ArrayList<>();
        if(vehicles!=null)
        {
            for(int y=0;y<vehicles.size();y++)
            {
                //allcves.add(vehicles.get(y).getCveVehicle());
                allcves.add(vehicles.get(y).getCveLayer());
            }
        }

        //   Log.e("bloquesdeunides11",""+allcves);
        /***SnapHelper snapHelper= new PagerSnapHelper();
         rvUnits.setOnFlingListener(null);
         snapHelper.attachToRecyclerView(rvUnits);
         */
       /* if(vehicles.size()>100)
        {
            for(int i=0;i<)
            undredlist
        }else
        {

        }*/
        // SnapHelper snapHelper = new PagerSnapHelper();
        //rvUnits.setOnFlingListener(null);
        //snapHelper.attachToRecyclerView(rvUnits);

       /** unitAssignSupportAdapter = new UnitAssignSupportAdapter(vehicles,getContext());
        //rvUnits.setAdapter(unitAssignSupportAdapter);
        rvVehicles.setAdapter(unitAssignSupportAdapter);
        //hideProgressDialog();
        if( vehicles.size()!=0)
        {
            //directions.get(0).contains(String.valueOf( vehicles.get(0).getCveVehicle()));

            cvesalternos.clear();
            // Log.e("bloquesdeunides2","------"+ unitAdapter.getadapterviewsize());
            // Log.e("bloquesdeunides2","------"+ unitAdapter.getItemCount());
            //if(vehicles.size()>12){

            if(unitAssignSupportAdapter.getItemCount()<7)
            {
                for(int k=0;k<vehicles.size();k++)
                {
                    //cvesalternos.add( vehicles.get(k).getCveVehicle());
                    cvesalternos.add( vehicles.get(k).getCveLayer());
                }
            }
            else
            {
                for(int k=0;k<6;k++)
                {

                    //cvesalternos.add( vehicles.get(k).getCveVehicle());
                    cvesalternos.add( vehicles.get(k).getCveLayer());
                }
            }


            // Log.e("bloquesdeunides2","cve ::: "+cvesalternos.get(0));
            //}
            position=cvesalternos.size();
            presenter.georeferenceformAPI(cvesalternos);

            unitAssignSupportAdapter.getAdress(directions);
            unitAssignSupportAdapter.notifyDataSetChanged();

        }
        //rvUnits.addOnScrollListener(new RecyclerView.OnScrollListener() {
        /**rvVehicles.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //    Log.e("filterlog",""+cvesalternos);
                //    Log.e("bloquesdeunides11",""+allcves);

                if (position < unitAssignSupportAdapter.getadapterviewsize()) {
                    if ( searchViewContainer.getVisibility()==View.GONE)
                    {
                        cvesalternos.clear();
                        for (int k = 0; k < unitAssignSupportAdapter.getadapterviewsize(); k++) {

                            cvesalternos.add(vehicles.get(k).getCveVehicle());
                        }

                        //}

                        try {
                            presenter.georeferenceformAPI(cvesalternos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        unitAssignSupportAdapter.getAdress(directions);
                    }
                }
                unitAssignSupportAdapter.notifyDataSetChanged();
                //Log.e("bloquesdeunides8", "///////" + directions.size());
                //Log.e("bloquesdeunides8", "///////" + directions);
            }
        });*/

        //rvUnits.getChildLayoutPosition(rvUnits.getFocusedChild());
        // Log.e("bloquesdeunides2",""+ rvUnits.getChildLayoutPosition(rvUnits.getFocusedChild()));
        //  unitAdapter.notifyDataSetChanged();
        // Log.e("bloquesdeunides2",""+ rvUnits.getLayoutManager().getChildCount());//+holder.getLayoutPosition()+"  ");
        // unitAdapter.getadapterviewsize();
        // Log.e("unitsthaticansaw2"," : "+UnitsInteractorImpl.dataofvehiclesgroupscve);
     /*   if(!UnitsInteractorImpl.dataofvehiclesgroupscve.isEmpty())
        {            // Log.e("unitsthaticansaw2"," data is here ");            if(doitonce==false)            {                interactorbridge();            }            else            {            }        }        else        {            // Log.e("unitsthaticansaw2"," mepty ");            // Log.e("unitsthaticansaw2"," : "+UnitsInteractorImpl.dataofvehiclesgroupscve);        }*/
        /**unitAssignSupportAdapter.notifyDataSetChanged();
    }*/

    @Override
    public void setSoportes(List<SupportUnitData> data) {
        this.soportes = data;
        if(soportes!=null){
            fillSoportes(soportes);
        }
    }

    private void fillSoportes(List<SupportUnitData> soportes){
        unitAssignSupportAdapter = new UnitAssignSupportAdapter(soportes,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvVehicles.setLayoutManager(layoutManager);
        rvVehicles.setAdapter(unitAssignSupportAdapter);
    }

    @Override
    public void failureResponse(String message) {
        if(message!=null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                goBackintomenu();
                break;

            /**case R.id.search_toolbar_item:
                //searchView.setVisibility(View.VISIBLE);
                if(searchViewContainer.getVisibility()==View.VISIBLE) {
                    searchViewContainer.setVisibility(View.GONE);
                }
                else{
                    searchViewContainer.setVisibility(View.VISIBLE);
                }
                break;*/
        }
    }

    private void goBackintomenu()
    {
        /**Bundle bndl = new Bundle();
        bndl.putString("nav", "ZONES");
        Intent intent = new Intent(UnitAssignSupportViewImpl.this,  menuViewImpl.class);//MainMenuContainerActivity.class);
        intent.putExtras(bndl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();*/
        Toast.makeText(getContext(), "ATRASSSSS", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);

    }

    @Override
    public void onResume() {
        super.onResume();
        initPresenter();

        // Toast.makeText(getContext(), "no me fui limpio", Toast.LENGTH_SHORT).show();
        handler.postDelayed(runnable,200);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
