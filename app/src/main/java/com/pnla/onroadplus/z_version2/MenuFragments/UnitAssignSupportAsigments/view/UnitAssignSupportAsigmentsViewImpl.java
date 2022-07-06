package com.pnla.onroadplus.z_version2.MenuFragments.UnitAssignSupportAsigments.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pnla.onroadplus.R;

public class UnitAssignSupportAsigmentsViewImpl extends AppCompatActivity implements ImageView.OnClickListener {

    private ImageView toolbarImgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_unit_assign_support__asigments_view_impl);
        initUnitsViewImpl();
    }

    private void initUnitsViewImpl(){
        initViewID();
        initOnClickListener();
    }

    private void initViewID(){
        toolbarImgBack = findViewById(R.id.back);
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
