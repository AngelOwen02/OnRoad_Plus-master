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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.dynatrace.android.agent.Dynatrace;
import com.pnla.onroadplus.R;
//import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.GroupTrackingAdapter;
//import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.container.UnitAssignSupportContainer;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractor;
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
import com.pnla.onroadplus.z_version2.MenuFragments.Zones.view.zonesFragment;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnitAssignSupportViewImpl extends AppCompatActivity implements UnitAssignSupportView, ImageView.OnClickListener {

    public static final String TAG = UnitAssignSupportViewImpl.class.getSimpleName();
    private RecyclerView rvVehicles;
    private UnitAssignSupportAdapter unitAssignSupportAdapter;
    private List<SupportUnitData> soportes;
    private ProgressBar progressBar;
    private ImageView toolbarItem;
    private ImageView toolbarImgBack;
    private ProgressDialog progressDialog;
    final Handler handler = new Handler();
    private Runnable runnable;
    private UnitAssignSupportPresenter presenter;
    int position;
    private List<String> directions;
    private List<Integer> cvesalternos=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_unit_assign_support_view_impl);
        initUnitsViewImpl();
    }

    private void initUnitsViewImpl() {
        initViewID();
        initPresenter();
        initOnClickListeners();
        update();
    }

    private void initViewID() {
        progressBar = findViewById(R.id.units_view_progress_bar);
        toolbarImgBack = findViewById(R.id.back);
        rvVehicles = findViewById(R.id.recycler_view_unit_tracking2);
        progressDialog = new ProgressDialog(this);

//        Dynatrace.applyUserPrivacyOptions(UserPrivacyOptions.builder()
//                .withDataCollectionLevel(DataCollectionLevel.USER_BEHAVIOR)
//                .withCrashReportingOptedIn(true)
//                .build());

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String userName = preferences.getString(GeneralConstantsV2.USER_PREFERENCES, null);
        // String test="TestDynatraceusername";
        //Dynatrace.identifyUser( userName);
        Log.e("dynatracelog", "units user dynatrace   " + userName);
    }

    private void initPresenter() {
        presenter = new UnitAssignSupportPresenterImpl(getApplicationContext());
        presenter.setView(this);

//        DTXAction processUnits= Dynatrace.enterAction("processUnits");//
//        processUnits.getRequestTag();
        //presenter.getFullVehicles();
        //     processUnits.leaveAction();

        // presenter.getvehiclesINgroups();


    }

    private void update(){
        //final UnitsPresenter presenter = new UnitsPresenterImpl(getContext());
        //  presenter.setView(this);

        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                progressDialog.show();
                //presenter.getFullVehicles();
                //UnitsInteractor unitsInteractor = new UnitsInteractorImpl(presenter,getContext());
                UnitAssignSupportInteractor unitAssignSupportInteractor = new UnitAssignSupportInteractorImpl(presenter,getBaseContext());
                //unitsInteractor.getAllVehiclesFromAPI();
                //unitAssignSupportInteractor.getGeoreferencefromAPI();
                unitAssignSupportInteractor.requestSoportes();
                //presenter.hideProgressDialog();
                //searchViewContainer.setVisibility(View.GONE);
                handler.postDelayed(this,60000);
            }
        },60000);
    }

    private void initOnClickListeners() {
        toolbarImgBack.setOnClickListener(this);
    }

    @Override
    public void setSoportes(List<SupportUnitData> data) {

        if(soportes!=null){
            if (soportes==data){
            } else {
                this.soportes = data;
                unitAssignSupportAdapter.notifyDataSetChanged();
            }
        } else {
            this.soportes = data;
            fillSoportes(soportes);
        }
        progressDialog.dismiss();
    }

    private void fillSoportes(List<SupportUnitData> soportes){
        unitAssignSupportAdapter = new UnitAssignSupportAdapter(soportes,getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvVehicles.setLayoutManager(layoutManager);
        rvVehicles.setAdapter(unitAssignSupportAdapter);
    }

    @Override
    public void failureResponse(String message) {
        if(message!=null) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void hideProgressDialog(){
        progressDialog.dismiss();
    }

    @Override
    public void showProgressDialog(){
        progressDialog.setMessage("Cargando Unidades");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void onBackPressed() {
        goBackintomenu();
    }

    private void goBackintomenu()
    {
        Bundle bndl = new Bundle();
        bndl.putString("nav", "ZONES");
        Intent intent = new Intent(UnitAssignSupportViewImpl.this,  menuViewImpl.class);//MainMenuContainerActivity.class);
        intent.putExtras(bndl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        //Toast.makeText(getApplicationContext(), "ATRASSSSS", Toast.LENGTH_LONG).show();
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
