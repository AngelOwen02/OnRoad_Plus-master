package com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DialogLocateVehicle extends DialogFragment implements View.OnClickListener, LocateVehicleView {
    public static final String TAG = DialogLocateVehicle.class.getSimpleName();

    /**
     * Presenter
     */
    private LocateVehiclePresenter presenter;

    /**
     * Components
     */
    private ProgressBar progressBarRoute;
    private TextView txvMessageRoute, txvTextGoogleMaps, txvTextWaze;
    private ConstraintLayout constraintLayoutGoogleMaps, constraintLayoutWaze, constraintLayoutDialogRoute;
    private View viewDividerRouteDialog;

    /**
     * Apps instaled
     */
    private boolean mapsIsInstalled, wazeIsInstalled;

    /**
     * Location
     */
    private double latitude, longitude;

    /**
     * VehicleLocation
     */
    private double vehicleLatitude, vehicleLongitude;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_locate_vehicle, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(com.phoenix2.top_driver_ui.R.color.customTransparent);
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
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //VALIDADO OK
                    //makeCall(callIntent);
                    /**
                     *
                     *      SOLICITAR LOCALIZACION
                     *
                     */
                    presenter.validateLocationPermission(getActivity());
                } else {
                    showMessage("Agrega el permiso de ubicación para mostrar ruta.");
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constraintLayoutGoogleMaps:
                openGoogleMaps();
                break;
            case R.id.constraintLayoutWaze:
                openWaze();
                break;
            case R.id.constraintLayoutDialogRoute:
                closeDialog();
                break;
        }
    }

    @Override
    public void showLoader() {
        progressBarRoute.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBarRoute.setVisibility(View.GONE);
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
        txvMessageRoute.setVisibility(View.VISIBLE);
        txvTextGoogleMaps.setVisibility(View.VISIBLE);
        txvTextWaze.setVisibility(View.VISIBLE);
        constraintLayoutGoogleMaps.setVisibility(View.VISIBLE);
        constraintLayoutWaze.setVisibility(View.VISIBLE);
        viewDividerRouteDialog.setVisibility(View.VISIBLE);
    }

    public void setLocationVehicle(double vehicleDestinationlatitude, double vehicleDestinationlongitude) {
        this.vehicleLatitude = vehicleDestinationlatitude;
        this.vehicleLongitude = vehicleDestinationlongitude;
    }

    private void openGoogleMaps() {
        if (mapsIsInstalled) {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/?api=1&" + "origin=" + latitude + "," + longitude + "&" + "destination=" + vehicleLatitude + "," + vehicleLongitude + "&travelmode=driving"));
            startActivity(intent);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.googleMapsPlayStore))));
        }
        closeDialog();
    }

    private void openWaze() {
        if (wazeIsInstalled) {
            String uri = "waze://?ll=" + vehicleLatitude + "," + vehicleLongitude + "&navigate=yes";
            startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.wazePlayStore))));
        }
        closeDialog();
    }

    private void closeDialog() {
        this.dismiss();
    }

    private void initDialog(View view) {
        progressBarRoute = view.findViewById(R.id.progressBarLocateVehicle);
        txvMessageRoute = view.findViewById(R.id.txvMessageRoute);
        txvTextGoogleMaps = view.findViewById(R.id.txvTextGoogleMaps);
        txvTextWaze = view.findViewById(R.id.txvTextWaze);
        constraintLayoutGoogleMaps = view.findViewById(R.id.constraintLayoutGoogleMaps);
        constraintLayoutWaze = view.findViewById(R.id.constraintLayoutWaze);
        constraintLayoutDialogRoute = view.findViewById(R.id.constraintLayoutDialogRoute);
        viewDividerRouteDialog = view.findViewById(R.id.viewDividerRouteDialog);

        constraintLayoutGoogleMaps.setOnClickListener(this);
        constraintLayoutWaze.setOnClickListener(this);
        constraintLayoutDialogRoute.setOnClickListener(this);
        txvMessageRoute.setOnClickListener(this);

        presenter = new DialogLocateVehiclePresenterImpl();
        presenter.setView(this);
        presenter.validateAppsInstalled(getActivity());
        presenter.validateLocationPermission(getActivity());
    }

    private void setFonts() {
        Typeface latoBold = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
        txvMessageRoute.setTypeface(latoBold);
        txvTextGoogleMaps.setTypeface(latoBold);
        txvTextWaze.setTypeface(latoBold);
    }

}
