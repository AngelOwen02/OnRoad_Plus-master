 package com.pnla.onroadplus.z_version2.MenuFragments.Units.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dynatrace.android.agent.Dynatrace;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.GroupTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Group.GroupDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.Database.Unit.UnitDB;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.adapter.UnitAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.presenter.UnitsPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.presenter.UnitsPresenterImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 public class UnitsViewImpl extends Fragment implements UnitsView, SearchView.OnQueryTextListener, ImageView.OnClickListener {

    public static final String TAG =UnitsViewImpl.class.getSimpleName();
    private SearchView searchView;
    private RecyclerView rvUnits;
    private UnitAdapter unitAdapter;
    private CardView searchViewContainer;
    private List<Unit> vehicles;
    private List<Unit> undredlist=new ArrayList<>();
    private ProgressBar progressBar;
    private ImageView toolbarItem;
    private TextView txtToolbar;
    private ProgressDialog progressDialog;
    final Handler handler = new Handler();
    private Runnable runnable;
    private Parcelable state;
    private  UnitsPresenter presenter;
    private GroupTrackingAdapter groupTrackingAdapter;
    private List<Group> unitGroup;
    private List<Unit> unidades;
    int position;
    //   private boolean doitonce=false;
    private List<String> directions;
    private List<Integer> cvesalternos=new ArrayList<>();
    private List<String> itemgeosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_units_impl, container, false);

        initUnitsViewImpl(view);
        update();
        return view;
    }


    private void initUnitsViewImpl(View view) {
        initViewID(view);
        initPresenter();
        initOnClickListeners();
        //UnitsInteractorImpl.dataofvehiclesgroupsnames.clear();
    }

    private void initViewID(View view) {
        toolbarItem = view.findViewById(R.id.search_toolbar_item);
        txtToolbar = view.findViewById(R.id.search_toolbar_title);
        rvUnits = view.findViewById(R.id.recycler_view_units);
        progressBar = view.findViewById(R.id.units_view_progress_bar);
        searchViewContainer = view.findViewById(R.id.units_search_view_container);
        searchView = view.findViewById(R.id.search_view_units);
        progressDialog = new ProgressDialog(getActivity());
        txtToolbar.setText("Unidades");

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
        presenter = new UnitsPresenterImpl(getContext());
        presenter.setView(this);

//        DTXAction processUnits= Dynatrace.enterAction("processUnits");//
//        processUnits.getRequestTag();
        presenter.getFullVehicles();
   //     processUnits.leaveAction();

        // presenter.getvehiclesINgroups();


    }

    private void update(){
        //final UnitsPresenter presenter = new UnitsPresenterImpl(getContext());
        //  presenter.setView(this);

        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //presenter.getFullVehicles();
                UnitsInteractor unitsInteractor = new UnitsInteractorImpl(presenter,getContext());
                unitsInteractor.getAllVehiclesFromAPI();
                presenter.hideProgressDialog();
                searchViewContainer.setVisibility(View.GONE);
                handler.postDelayed(this,60000);
            }
        },60000);

    }

    private void initOnClickListeners() {
        searchView.setOnQueryTextListener(this);
        toolbarItem.setOnClickListener(this);
    }

    @Override
    public void setUnitList(List<Unit> unitList) throws IOException {
        /*this.vehicles = unitList;
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvUnits.setLayoutManager(linearLayoutManager);
        unitAdapter = new UnitAdapter(vehicles, getContext());
        rvUnits.setAdapter(unitAdapter);*/

        List<Unit> allUnitsList = UnitDB.getUnitList();
        List<Unit> activeUnitsList = UnitDB.getUnitListActive();
        List<Group> activeGroupslist = GroupDB.getActiveGroupList();
        this.vehicles = unitList;
      //  Log.e("partsrequestvehicles",""+vehicles.size());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvUnits.setLayoutManager(linearLayoutManager);
        List<Integer> allcves=new ArrayList<>();
        if(vehicles!=null)
        {
            for(int y=0;y<vehicles.size();y++)
            {
                allcves.add(vehicles.get(y).getCveVehicle());
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


        unitAdapter = new UnitAdapter(vehicles,getContext());
        rvUnits.setAdapter(unitAdapter);
        hideProgressDialog();
        if( vehicles.size()!=0)
        {
            //directions.get(0).contains(String.valueOf( vehicles.get(0).getCveVehicle()));

            cvesalternos.clear();
           // Log.e("bloquesdeunides2","------"+ unitAdapter.getadapterviewsize());
           // Log.e("bloquesdeunides2","------"+ unitAdapter.getItemCount());
            //if(vehicles.size()>12){

            if(unitAdapter.getItemCount()<7)
            {
                for(int k=0;k<vehicles.size();k++)
                {
                    cvesalternos.add( vehicles.get(k).getCveVehicle());
                }
            }
            else
            {
                for(int k=0;k<6;k++)
                {

                    cvesalternos.add( vehicles.get(k).getCveVehicle());
                }
            }


            // Log.e("bloquesdeunides2","cve ::: "+cvesalternos.get(0));
            //}
            position=cvesalternos.size();
            presenter.georeferenceformAPI(cvesalternos);

            unitAdapter.getAdress(directions);
            unitAdapter.notifyDataSetChanged();

        }
        rvUnits.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            //    Log.e("filterlog",""+cvesalternos);
            //    Log.e("bloquesdeunides11",""+allcves);

                if (position < unitAdapter.getadapterviewsize()) {
                    if ( searchViewContainer.getVisibility()==View.GONE)
                    {
                        cvesalternos.clear();
                    for (int k = 0; k < unitAdapter.getadapterviewsize(); k++) {

                        cvesalternos.add(vehicles.get(k).getCveVehicle());
                    }

                    //}

                        try {
                            presenter.georeferenceformAPI(cvesalternos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        unitAdapter.getAdress(directions);
                }
                }
                unitAdapter.notifyDataSetChanged();
                //Log.e("bloquesdeunides8", "///////" + directions.size());
                //Log.e("bloquesdeunides8", "///////" + directions);
            }
        });

        //rvUnits.getChildLayoutPosition(rvUnits.getFocusedChild());
        // Log.e("bloquesdeunides2",""+ rvUnits.getChildLayoutPosition(rvUnits.getFocusedChild()));
        //  unitAdapter.notifyDataSetChanged();
        // Log.e("bloquesdeunides2",""+ rvUnits.getLayoutManager().getChildCount());//+holder.getLayoutPosition()+"  ");
        // unitAdapter.getadapterviewsize();
        // Log.e("unitsthaticansaw2"," : "+UnitsInteractorImpl.dataofvehiclesgroupscve);
     /*   if(!UnitsInteractorImpl.dataofvehiclesgroupscve.isEmpty())
        {            // Log.e("unitsthaticansaw2"," data is here ");            if(doitonce==false)            {                interactorbridge();            }            else            {            }        }        else        {            // Log.e("unitsthaticansaw2"," mepty ");            // Log.e("unitsthaticansaw2"," : "+UnitsInteractorImpl.dataofvehiclesgroupscve);        }*/
        unitAdapter.notifyDataSetChanged();
    }

    @Override
    public void adressList(List<String> adress) {
        this.directions=adress;
       // Log.e("bloquesdeunides9",""+directions);

    }

    public void interactorbridge()
    {
      //  Log.e("unitsthaticansaw2"," : "+UnitsInteractorImpl.dataofvehiclesgroupscve);
        // Log.e("unitsthaticansaw2"," N: "+UnitsInteractorImpl.cveofgroup);
        for(int i=0;i<UnitsInteractorImpl.dataofvehiclesgroupscve.size();i++)
        {
            UnitsInteractorImpl.cveofgroup=Integer.parseInt( UnitsInteractorImpl.dataofvehiclesgroupscve.get(i));
           // Log.e("unitsthaticansaw2"," N: "+UnitsInteractorImpl.cveofgroup);
            //    presenter.getvehiclesINgroups();
        }
        //    doitonce=true;
    }

    @Override
    public void failureResponse(String message) {
        if(message!=null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgressDialog() {
        // progressBar.setVisibility(View.VISIBLE);
        progressDialog.setMessage("Cargando Unidades");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    public void hideProgressDialog() {
        // progressBar.setVisibility(View.INVISIBLE);
        progressDialog.dismiss();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Unit> filterUnitList = filterUnits(vehicles, newText);
        if (vehicles!=null){
            unitAdapter.setFilter(filterUnitList);
            if(filterUnitList!=null)
            {
                List<String> names2=new ArrayList<>();
                List<Integer> names=new ArrayList<>();
                names.clear();
                names2.clear();
                for(int ñ=0;ñ<filterUnitList.size();ñ++)
                {
                    names.add(filterUnitList.get(ñ).getCveVehicle());
                    names2.add(filterUnitList.get(ñ).getVehicleName());
                }


                /***/
                cvesalternos.clear();
                if(names!=null||names.size()!=0) {
                  /*  if (names.size() < 6) {
                        for (int k = 0; k < 6; k++) {

                            cvesalternos.add(names.get(k));
                        }
                    } else {*/


                        for (int k = 0; k < names.size(); k++) //  for(int k=0;k<names.size();k++)
                        {
                            cvesalternos.add(names.get(k));
                        }
                   // }

                    try {
                        presenter.georeferenceformAPI(cvesalternos);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //  Log.e("filterlog", "estoy filtrando" + names);
                  //  Log.e("filterlog", "estoy filtrando" + names2);
                    unitAdapter.getAdress(directions);
                    unitAdapter.notifyDataSetChanged();
                }
                /***/


            }
        }
        return true;
    }

    private List<Unit> filterUnits(List<Unit> vehicles, String text) {
        List<Unit> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        if(vehicles!=null) {
            for (Unit vehicle : vehicles) {
                String vehicleName = vehicle.getVehicleName().toLowerCase();
                if (vehicleName.contains(text)) {
                    filteredList.add(vehicle);
                }
            }
        }
        return filteredList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_toolbar_item:
                //searchView.setVisibility(View.VISIBLE);
                if(searchViewContainer.getVisibility()==View.VISIBLE) {
                    searchViewContainer.setVisibility(View.GONE);
                }
                else{
                    searchViewContainer.setVisibility(View.VISIBLE);
                }
                break;
        }


    /* if(searchViewContainer.getVisibility()==View.VISIBLE)
        {
          //  filterUnits(vehicles).clear();

            searchViewContainer.setVisibility(View.GONE);
        }*/
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
