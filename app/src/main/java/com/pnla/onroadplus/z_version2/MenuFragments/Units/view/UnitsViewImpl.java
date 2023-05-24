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

//import com.dynatrace.android.agent.Dynatrace;
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
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataRequest;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3.dataresponseUnitsV3;
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
    private boolean isMorethan20=false;
    private boolean isfirstOnthisScreen=true;
    private String origin;
     private List<dataresponseUnitsV3> datageos;
     private String inputTextIndicator;

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
        configLocalpreferences();
        toolbarItem = view.findViewById(R.id.search_toolbar_item);
        txtToolbar = view.findViewById(R.id.search_toolbar_title);
        rvUnits = view.findViewById(R.id.recycler_view_units);
        progressBar = view.findViewById(R.id.units_view_progress_bar);
        searchViewContainer = view.findViewById(R.id.units_search_view_container);
        searchView = view.findViewById(R.id.search_view_units);
        progressDialog = new ProgressDialog(getActivity());
        txtToolbar.setText("Unidades");
        inputTextIndicator="";

//        Dynatrace.applyUserPrivacyOptions(UserPrivacyOptions.builder()
//                .withDataCollectionLevel(DataCollectionLevel.USER_BEHAVIOR)
//                .withCrashReportingOptedIn(true)
//                .build());

        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
       // String test="TestDynatraceusername";
        //Dynatrace.identifyUser( userName);
        Log.e("dynatracelog", "units user dynatrace   " + userName);
    }

    private void initPresenter() {
        presenter = new UnitsPresenterImpl(getContext());
        presenter.setView(this);

//        DTXAction processUnits= Dynatrace.enterAction("processUnits");//
//        processUnits.getRequestTag();
        presenter.getFullVehicles(isMorethan20);
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
                unitsInteractor.getAllVehiclesFromAPI(isMorethan20);
                presenter.hideProgressDialog();
                searchViewContainer.setVisibility(View.GONE);
                handler.postDelayed(this,30000);
            }
        },30000);

    }

    private void initOnClickListeners() {
        searchView.setOnQueryTextListener(this);
        toolbarItem.setOnClickListener(this);
    }

    @Override
    public void setUnitList(List<Unit> unitList) throws IOException {
        this.vehicles=unitList;
        if(vehicles!=null){
            fillAdapter(vehicles);
            hideProgressDialog();
            filterBySearch();
            //filterByStatus();
            callGeofencesnewEndpoint(vehicles);
        }


    }

     private void filterBySearch() {
         if(inputTextIndicator!=null){
             if(!inputTextIndicator.equals("")){
                 onQueryTextChange(inputTextIndicator);
             }
         }
     }

     private void fillAdapter(List<Unit> vehicles) {
         if(unitAdapter==null) {
             unitAdapter = new UnitAdapter(vehicles, isMorethan20, getContext());
             final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
             rvUnits.setLayoutManager(linearLayoutManager);
             rvUnits.setAdapter(unitAdapter);
         }else{
             unitAdapter.UpdateVehicles(vehicles);
         }
     }

     private void callGeofencesnewEndpoint(List<Unit> vehicles) {
         List<dataRequest> askgeofences=new ArrayList<>();
         askgeofences.clear();
         for(Unit vehiculo: vehicles){
             dataRequest datareq = new dataRequest(Integer.valueOf(origin), vehiculo.getCveVehicle());
             askgeofences.add(datareq);
         }
         presenter.askgeofences(askgeofences);
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
     private void configLocalpreferences() {
         SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
         String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
         origin = preferences.getString(GeneralConstantsV2.ORIGIN, null);
         Log.e("origin", origin);
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
     public void setGeos(List<dataresponseUnitsV3> geos) {
         this.datageos=geos;
         List<Unit> updatedVehicles=new ArrayList<>();
         updatedVehicles.clear();
         for (dataresponseUnitsV3 newgeos:datageos){
             Integer cves=newgeos.getOutCveVehicle();
             for(Unit vehiculos:vehicles)
             {
                 if(vehiculos.getCveVehicle()==cves){
                     if(newgeos.getLocation()!=null) {
                         vehiculos.setGeoreference(newgeos.getLocation());
                     }else{
                         vehiculos.setGeoreference("");
                     }
                 }
             }

         }
         unitAdapter.UpdateGeos(vehicles);
     }

     @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Unit> filterUnitList = filterUnits(vehicles, newText);
        inputTextIndicator = newText;
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
