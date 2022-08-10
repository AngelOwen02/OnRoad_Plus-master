package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.container;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.UnitAssignSupportFragment;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.adapter.UnitAssignSupportAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.presenter.UnitAssignSupportPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.presenter.UnitAssignSupportPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportView;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.GroupTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.adapter.UnitTrackingAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.presenter.UnitTrackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitTracking.presenter.UnitTrackingPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.Group;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SupportUnitData;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnitAssignSupportContainer extends AppCompatActivity implements UnitAssignSupportView, View.OnClickListener {

    private UnitAssignSupportPresenter presenter;
    private List<Unit> vehicless;
    private RecyclerView rvVehicles;
    private UnitAssignSupportAdapter adapterVehicles;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_unit_assign_support_view_impl);
        initUnitTrackingViewImpl();
    }

    private void initUnitTrackingViewImpl() {
        ImageView toolbarImgBack = findViewById(R.id.back);
        rvVehicles = findViewById(R.id.recycler_view_unit_tracking2);
        progressBar = findViewById(R.id.progress_bar_unit_tracking);

        //unitButton.setOnClickListener(this);
        //groupButton.setOnClickListener(this);
        toolbarImgBack.setOnClickListener(this);
        //toolbarImgSearch.setOnClickListener(this);
        //searchViewTracking.setOnQueryTextListener(this);

        /**presenter = new UnitAssignSupportPresenterImpl();
        presenter.setView(this);
        presenter.getVehicles(this);
        //presenter.getGroups(this);*/
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
    
    //Metodo para regresar a la pantalla anterior
    private void goBackintomenu()
    {
        Bundle bndl = new Bundle();
        bndl.putString("nav", "ZONES");
        Intent intent = new Intent(UnitAssignSupportContainer.this,  menuViewImpl.class);//MainMenuContainerActivity.class);
        intent.putExtras(bndl);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        goBackintomenu();
    }

    @Override
    public void setSoportes(List<SupportUnitData> data) {

    }

    @Override
    public void failureResponse(String message) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
}
