package com.pnla.onroadplus.z_version2.MenuFragments.UnitMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMap.view.UnitMapViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view.UnitMapViewImplV3;
import com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.view.unitMapViewV3;
import com.pnla.onroadplus.z_version2.fragments.mapV2.components.ComponentVehicleHeader;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UnitMapContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initUnitMapContainer();
    }

    public void initUnitMapContainer() {

        Bundle bndl;
        bndl = getIntent().getExtras();
        int vehicleCve = bndl.getInt("vehicleCve");
        int vehicleSwitch = bndl.getInt("vehicleSwitch");
        String vehicleName = bndl.getString("vehicleName");
        String vehicleImage = bndl.getString("vehicleImage");
        String vehicleSendTime = bndl.getString("vehicleSendTime");
        String vehicleDescBrand = bndl.getString("vehicleDescBrand");
        String vehicleDescModel = bndl.getString("vehicleDescModel");
        String vehicleYear = bndl.getString("vehicleYear");
        String vehicleVin = bndl.getString("vehicleVin");
        String vehiclePlate = bndl.getString("vehiclePlate");
        String vehicleGeoreference = bndl.getString("vehicleGeoreference");
        String vehicleTimeTravel = bndl.getString("vehicleTimeTravel");
        String vehicleTimeElapsed = bndl.getString("vehicleTimeElapsed");
        double vehicleLatitude = bndl.getDouble("vehicleLatitude");
        double vehicleLongitude = bndl.getDouble("vehicleLongitude");
        String vehicleMileage = bndl.getString("vehicleMileage");
        double vehicleKmTravel = bndl.getDouble("vehicleKmTravel");
        double vehicleCurrentSpeed = bndl.getDouble("vehicleCurrentSpeed");
        double vehicleMaxSpeed = bndl.getDouble("vehicleMaxSpeed");

        Bundle bndlMap;
        bndlMap = new Bundle();
        bndlMap.putInt("vehicleCve", vehicleCve);
        bndlMap.putInt("vehicleSwitch",vehicleSwitch);
        bndlMap.putString("vehicleName", vehicleName);
        bndlMap.putString("vehicleImage", vehicleImage);
        bndlMap.putString("vehicleSendTime", vehicleSendTime);
        bndlMap.putString("vehicleDescBrand",vehicleDescBrand );
        bndlMap.putString("vehicleDescModel", vehicleDescModel);
        bndlMap.putString("vehicleYear", vehicleYear);
        bndlMap.putString("vehicleVin", vehicleVin);
        bndlMap.putString("vehiclePlate",vehiclePlate );
        bndlMap.putString("vehicleGeoreference", vehicleGeoreference);
        bndlMap.putString("vehicleTimeTravel",vehicleTimeTravel );
        bndlMap.putString("vehicleTimeElapsed", vehicleTimeElapsed);
        bndlMap.putDouble("vehicleLatitude",vehicleLatitude );
        bndlMap.putDouble("vehicleLongitude",vehicleLongitude );
        bndlMap.putString("vehicleMileage", vehicleMileage);
        bndlMap.putDouble("vehicleKmTravel", vehicleKmTravel);
        bndlMap.putDouble("vehicleCurrentSpeed", vehicleCurrentSpeed);
        bndlMap.putDouble("vehicleMaxSpeed", vehicleMaxSpeed);
        showFragment(new UnitMapViewImplV3(), bndlMap);

    }


    public void showFragment(Fragment fragment, Bundle bndlMap) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bndlMap);
        ft.replace(R.id.map_container, fragment);
        ft.commit();
    }
}
