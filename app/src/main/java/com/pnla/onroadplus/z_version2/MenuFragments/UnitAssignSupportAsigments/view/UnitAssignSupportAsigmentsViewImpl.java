package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.BuildConfig;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.MainMenuContainerActivity;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.interactor.UnitAssignSupportInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.adapter.UnitAssignSupportAsigmentsAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data.Guardar;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data.SingleUnitSupportData;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.interactor.UnitAssignSupportAsigmentsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.interactor.UnitAssignSupportAsigmentsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.presenter.UnitAssignSupportAsigmentsPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.presenter.UnitAssignSupportAsigmentsPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.data.SingleSupportUnitData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UnitAssignSupportAsigmentsViewImpl extends AppCompatActivity implements ImageView.OnClickListener, UnitAssignSupportAsigmentsView {

    private UnitAssignSupportAsigmentsAdapter unitAssignSupportAsigmentsAdapter;
    private List<SingleSupportUnitData> soportes;
    private SingleUnitSupportData singleUnitSupportData;
    final Handler handler = new Handler();
    //private Guardar guardar;
    //private UnitAssignSupportAsigmentsPresenter presenter;
    CircleImageView imgUnitCircle;
    private ImageView toolbarImgBack;
    TextView unitGeoReference;
    TextView unitNameVehicle;
    TextView unitRuteVehicle;
    TextView unitPercentVehicle;
    TextView unitRuteVehicleRv;
    private RecyclerView rvVehiclesSupp;
    private UnitAssignSupportAsigmentsPresenter presenter;
    private int cveLayer;
    private String descLayer;
    private String cveVehicle;
    private ProgressDialog progressDialog;
    private Runnable runnable;
    //SingleUnitSupportData singleUnitSupportData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_unit_assign_support_asigments_view_impl);
        initUnitsViewImpl();
    }

    private void initUnitsViewImpl(){
        initViewID();
        initBundle();
        initPresenter();
        initOnClickListener();
        presenter.getSoportes(cveLayer);
        //update();
    }

    private void initBundle(){
        //Set Bundle
        Bundle bndle;
        bndle = getIntent().getExtras();

        cveLayer = bndle.getInt("cve_layer");
        cveVehicle = bndle.getString("cve_vehicle");
        String vehicleName = bndle.getString("vehicle_name");
        descLayer = bndle.getString("desc_layer");
        int percentComplete = bndle.getInt("percent_complete");
        int controlPoint = bndle.getInt("control_point");
        int percenttoHelp = bndle.getInt("percent_to_help");
        int Status = bndle.getInt("status");
        int helpState = bndle.getInt("help_state");
        String geoReference = bndle.getString("georeference");

        //Set Data Bundle
        //Nombre de la Unidad en la parte superior de la ventana
        unitNameVehicle.setText(vehicleName);

        //Porcentaje recorrido de la unidad en la parte superior de la ventana
        String PercentHelp = new String(String.valueOf(percenttoHelp));
        PercentHelp.equals(percenttoHelp);
        unitPercentVehicle.setText(percenttoHelp+"%");

        //Ruta de la unidad en la parte superior de la ventana
        unitRuteVehicle.setText(descLayer);

        //Locacizacion de la unidad en la parte superior de la ventana
        unitGeoReference.setText(geoReference);

        //Texto que divide la parte superior con el RecyclerView
        unitRuteVehicleRv.setText("Apoyo disponible para "+ vehicleName);

        /**Status*/
        //Para que se ilumine el aro dependiendo el Status
        if (Status == 0) {
            imgUnitCircle.setBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBorderCarRed));
            unitPercentVehicle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBorderCarRed));
        } else if (Status == 1) {
            imgUnitCircle.setBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.colorOrangeYellow));
            unitPercentVehicle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorOrangeYellow));
        } else if (Status == 2) {
            imgUnitCircle.setBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBorderCarGreen));
            unitPercentVehicle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBorderCarGreen));
        } else if (Status == 3) {
            imgUnitCircle.setBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
            unitPercentVehicle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
        }

        //Toast.makeText(getApplicationContext(), numerolayer, Toast.LENGTH_LONG).show();

        Bundle bndlSupp;
        bndlSupp = new Bundle();
        bndlSupp.putInt("cve_layer", cveLayer);
        bndlSupp.putString("cve_vehicle", cveVehicle);
        bndlSupp.putString("vehicle_name", vehicleName);
        bndlSupp.putString("desc_layer", descLayer);
        bndlSupp.putInt("percent_complete", percentComplete);
        bndlSupp.putInt("control_point", controlPoint);
        bndlSupp.putInt("percent_to_help", percenttoHelp);
        bndlSupp.putInt("status", Status);
        bndlSupp.putInt("help_state", helpState);
        bndlSupp.putString("georeference", geoReference);


    }

    private void initViewID(){
        toolbarImgBack = findViewById(R.id.back);
        imgUnitCircle = findViewById(R.id.img_unit_support);
        unitGeoReference = findViewById(R.id.txt_unit_geo_reference_single);
        unitNameVehicle = findViewById(R.id.txt_unit_name_single);
        unitRuteVehicle = findViewById(R.id.txt_unit_rute_single);
        unitPercentVehicle = findViewById(R.id.txt_unit_objetive_percent_single);
        unitRuteVehicleRv = findViewById(R.id.txt_unit_rute_title_single_rv);
        rvVehiclesSupp = findViewById(R.id.recycler_view_unit_supports_cl);
        progressDialog = new ProgressDialog(this);
    }

    private void initPresenter() {
        presenter = new UnitAssignSupportAsigmentsPresenterImpl(this,getApplicationContext());
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
                UnitAssignSupportAsigmentsInteractor unitAssignSupportAsigmentsInteractor = new UnitAssignSupportAsigmentsInteractorImpl(presenter,getBaseContext());
                //unitsInteractor.getAllVehiclesFromAPI();
                //unitAssignSupportInteractor.getGeoreferencefromAPI();
                unitAssignSupportAsigmentsInteractor.requestSoportes(cveLayer);
                //presenter.hideProgressDialog();
                //searchViewContainer.setVisibility(View.GONE);
                handler.postDelayed(this,60000);
            }
        },60000);

    }

    private void initOnClickListener(){
        toolbarImgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                goBackintomenu();
                break;
        }
    }

    @Override
    public void onBackPressed(){
        goBackintomenu();
    }

    private void goBackintomenu(){
        Intent intent = new Intent(UnitAssignSupportAsigmentsViewImpl.this, UnitAssignSupportViewImpl.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(), "Atras", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setSoportes(List<SingleSupportUnitData> data) {
        this.soportes = data;
        if(soportes!=null){
            fillSoportes(soportes);
        }
        progressDialog.dismiss();
    }

    private void fillSoportes(List<SingleSupportUnitData> soportes) {
        unitAssignSupportAsigmentsAdapter = new UnitAssignSupportAsigmentsAdapter(this ,soportes, descLayer, getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvVehiclesSupp.setLayoutManager(layoutManager);
        rvVehiclesSupp.setAdapter(unitAssignSupportAsigmentsAdapter);
    }

    @Override
    public void failureResponse(String message) {
        if(message!=null){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void hideProgressDialog(){
        progressDialog.dismiss();
    }

    @Override
    public void AssignmentSupportSuccess() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, 2000);
        //Todo verificar ciclo de Handler
    }

    @Override
    public void deleteUnitAssign() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, 2000);
        //Todo verificar ciclo de Handler
    }

    @Override
    public void showProgressDialog(){
        progressDialog.setMessage("Cargando Unidades");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void editunitspinner(String vehicleName, String descLayer) {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        //builder.setTitle("Hay una nueva versión disponible de OnRoad");
        builder.setMessage("¿Desea asignar la unidad " + vehicleName + " a la Ruta " + descLayer + "?");
        builder.setCancelable(true);
        builder.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                        presenter.requestSetAssignSupport(cveLayer, cveVehicle);
                        //presenter.showProgressDialog();
                    }
                });
        builder.create();
        builder.show();

    }

    public void eraseunitfromsupport(int cveLayer, String cve_vehicle) {
        presenter.deleteUnitAssign(cveLayer, cve_vehicle);
    }

    public void alertBuilder(final String vehicleName, final String descLayer){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        //builder.setTitle("Hay una nueva versión disponible de OnRoad");
        builder.setMessage("¿Desea borrar la unidad " + vehicleName + " a la Ruta " + descLayer + "?");
        builder.setCancelable(true);
        builder.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        })
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                        eraseunitfromsupport(cveLayer, cveVehicle);
                    }
                });
        builder.create();
        builder.show();
    }
}
