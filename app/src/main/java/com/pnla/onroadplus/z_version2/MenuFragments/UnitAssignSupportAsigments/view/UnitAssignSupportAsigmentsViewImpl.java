package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.view;

import android.graphics.text.LineBreaker;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pnla.onroadplus.R;

public class UnitAssignSupportAsigmentsViewImpl extends AppCompatActivity implements ImageView.OnClickListener {

    private ImageView toolbarImgBack;
    TextView unitGeoReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_unit_assign_support__asigments_view_impl);
        initUnitsViewImpl();
    }

    private void initUnitsViewImpl(){
        initViewID();
        initBundle();
        initOnClickListener();
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

        unitGeoReference.setText(geoReference);

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
        unitGeoReference = findViewById(R.id.txt_unit_geo_reference);
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

    private void goBackintomenu(){
        Toast.makeText(getApplicationContext(), "Atras", Toast.LENGTH_LONG).show();
    }
}
