package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupport.view.UnitAssignSupportViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.adapter.UnitAssignSupportAsigmentsAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data.Guardar;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.data.SingleUnitSupportData;

import java.util.List;

public class UnitAssignSupportAsigmentsViewImpl extends AppCompatActivity implements ImageView.OnClickListener {

    private UnitAssignSupportAsigmentsAdapter unitAssignSupportAsigmentsAdapter;
    private List<SingleUnitSupportData> data;
    private SingleUnitSupportData singleUnitSupportData;
    final Handler handler = new Handler();
    //private Guardar guardar;
    //private UnitAssignSupportAsigmentsPresenter presenter;
    private ImageView toolbarImgBack;
    TextView unitGeoReference;
    TextView unitNameVehicle;
    TextView unitRuteVehicle;
    TextView unitPercentVehicle;
    TextView unitRuteVehicleRv;
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
        initOnClickListener();

        //SingleUnitSupportData singleUnitSupportData = new SingleUnitSupportData();
        //String vehiclename = singleUnitSupportData.getVehicleName();
        //Toast.makeText(getApplicationContext(), data.toString(), Toast.LENGTH_LONG).show();

        /**String nombre = "";
        Guardar guardar = (Guardar) getApplicationContext().getApplicationContext();
        nombre = guardar.getVehicleName();
        Toast.makeText(getApplicationContext(), nombre, Toast.LENGTH_LONG).show();*/

        /**String address = "";
        String dg = "";
        Guardar guardar = new Guardar(address);
        guardar.setAddress(address);*/

        //unitGeoReference.setText(guardar.GetAddress() + "Si");
        //Toast.makeText(getApplicationContext(), dg.toString(), Toast.LENGTH_SHORT).show();
    }

    private void initBundle(){
        Bundle bndle;
        bndle = getIntent().getExtras();

        int cveLayer = bndle.getInt("cve_layer");
        String cveVehicle = bndle.getString("cve_vehicle");
        String vehicleName = bndle.getString("vehicle_name");
        String descLayer = bndle.getString("desc_layer");
        int percentComplete = bndle.getInt("percent_complete");
        int controlPoint = bndle.getInt("control_point");
        int percenttoHelp = bndle.getInt("percent_to_help");
        int Status = bndle.getInt("status");
        int helpState = bndle.getInt("help_state");
        String geoReference = bndle.getString("georeference");

        unitNameVehicle.setText(vehicleName);

        String PercentHelp = new String(String.valueOf(percenttoHelp));
        PercentHelp.equals(percenttoHelp);
        unitPercentVehicle.setText(percenttoHelp+"%");

        unitRuteVehicle.setText(descLayer);
        unitGeoReference.setText(geoReference);

        unitRuteVehicleRv.setText("Apoyo disponible para "+vehicleName);

        String nombre = Guardar.getDefaultsPreference("usuario", this);
        Toast.makeText(getApplicationContext(), nombre, Toast.LENGTH_LONG).show();

        //Toast.makeText(getApplicationContext(), geoReference,Toast.LENGTH_LONG).show();

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
        unitGeoReference = findViewById(R.id.txt_unit_geo_reference_single);
        unitNameVehicle = findViewById(R.id.txt_unit_name_single);
        unitRuteVehicle = findViewById(R.id.txt_unit_rute_single);
        unitPercentVehicle = findViewById(R.id.txt_unit_objetive_percent_single);
        unitRuteVehicleRv = findViewById(R.id.txt_unit_rute_title_single_rv);
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
}
