package com.pnla.onroadplus.z_version2.MenuFragments.ExternalGPSApp.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.interfaces.LocateVehiclePresenter;
import com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.interfaces.LocateVehicleView;
import com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.presenters.DialogLocateVehiclePresenterImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

public class ExternalGPSDialog extends DialogFragment implements View.OnClickListener, LocateVehicleView, CheckBox.OnCheckedChangeListener {
    public static final String TAG = ExternalGPSDialog.class.getSimpleName();
    private boolean externalAppsaved = false;
    private LocateVehiclePresenter presenter;
    private ProgressBar progressBarRoute;
    private LinearLayout googleMaps, waze;
    private TextView txtCloseDialog;
    private boolean mapsIsInstalled, wazeIsInstalled;
    private double latitude, longitude;
    private double vehicleLatitude, vehicleLongitude;
    private CheckBox checkBoxExternalGPSApp;
    private SharedPreferences externalGPSAppPrefs;
    private SharedPreferences.Editor externalGPSAppEditor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.external_gps_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(com.phoenix2.top_driver_ui.R.color.customTransparent) ;
        setCancelable(false);
        initDialog(view);
        setFonts();
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case GeneralConstantsV2.LOCATION_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.validateLocationPermission(getActivity());
                } else {
                    showMessage("Agrega el permiso de ubicación para mostrar ruta.");
                }
                return;
            }
        }
    }

    @Override
    public void showLoader() {
        //  progressBarRoute.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        //   progressBarRoute.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successLocationPermission() {

    }

    @Override
    public void appsInstalled(boolean mapsIsInstalled, boolean wazeIsInstaled) {
        this.mapsIsInstalled = mapsIsInstalled;
        this.wazeIsInstalled = wazeIsInstaled;
    }

    @Override
    public void requestLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, GeneralConstantsV2.LOCATION_PERMISSION);
    }

    @Override
    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLocationVehicle(double vehicleDestinationlatitude, double vehicleDestinationlongitude) {
        this.vehicleLatitude = vehicleDestinationlatitude;
        this.vehicleLongitude = vehicleDestinationlongitude;
    }


    private void closeDialog() {
        this.dismiss();
    }

    private void initDialog(View view) {
        googleMaps = view.findViewById(R.id.googlemaps);
        waze = view.findViewById(R.id.waze);
        txtCloseDialog = view.findViewById(R.id.cancelar);
        checkBoxExternalGPSApp = view.findViewById(R.id.checkbox_external_gps_app);
        checkBoxExternalGPSApp.setOnCheckedChangeListener(this);
        ConstraintLayout constraintLayout = view.findViewById(R.id.externalgpsconstraint);
        SharedPreferences prefs = getContext().getSharedPreferences("ExternalGPSApp", Context.MODE_PRIVATE);
        String valueGPSAppPrefs = prefs.getString("Prefs", "");
        if (valueGPSAppPrefs.equals("GoogleMaps")) {
            openGoogleMaps();
        } else if (valueGPSAppPrefs.equals("Waze")) {
            openWaze();
        }
        constraintLayout.setOnClickListener(this);
        googleMaps.setOnClickListener(this);
        waze.setOnClickListener(this);
        txtCloseDialog.setOnClickListener(this);
        externalGPSAppPrefs = getContext().getSharedPreferences("ExternalGPSApp", Context.MODE_PRIVATE);
        externalGPSAppEditor = externalGPSAppPrefs.edit();
        presenter = new DialogLocateVehiclePresenterImpl();
        presenter.setView(this);
        presenter.validateAppsInstalled(getActivity());
        presenter.validateLocationPermission(getActivity());

    }

    private void setFonts() {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            externalAppsaved = true;
        } else {
            externalAppsaved = false;
        }
    }

    private void initGoogleRequest() {
        if (externalAppsaved) {
            externalGPSAppEditor.putString("Prefs", "GoogleMaps");
            externalGPSAppEditor.commit();
            openGoogleMaps();
        } else {
            externalGPSAppEditor.putString("Prefs", "");
            externalGPSAppEditor.commit();
            openGoogleMaps();
        }
    }

    private void initWazeRequest() {
        if (externalAppsaved) {
            externalGPSAppEditor.putString("Prefs", "Waze");
            externalGPSAppEditor.commit();
            openWaze();
        } else {
            externalGPSAppEditor.putString("Prefs", "");
            externalGPSAppEditor.commit();
            openWaze();
        }
    }

    private void openGoogleMaps() {
        if (mapsIsInstalled) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&" + "origin=" + latitude + "," + longitude + "&" + "destination=" + vehicleLatitude + "," + vehicleLongitude + "&travelmode=driving"));
            startActivity(intent);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.googleMapsPlayStore))));
        }
        closeDialog();
    }

    private void openWaze() {
        if (wazeIsInstalled) {
            String uri = "waze://?ll=" + vehicleLatitude + "," + vehicleLongitude + "&navigate=yes";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.wazePlayStore))));
        }
        closeDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.googlemaps:
                initGoogleRequest();
                break;
            case R.id.waze:
                initWazeRequest();
                break;
            case R.id.cancelar:
                closeDialog();
                break;
            case R.id.externalgpsconstraint:
                closeDialog();
                break;
        }
    }
}
