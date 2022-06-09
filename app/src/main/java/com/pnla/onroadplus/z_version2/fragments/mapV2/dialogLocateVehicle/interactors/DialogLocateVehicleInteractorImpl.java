package com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.interactors;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.dialogLocateVehicleUtils.DialogLocateVehicleUtils;
import com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.interfaces.LocateVehicleInteractor;
import com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.interfaces.LocateVehiclePresenter;

public class DialogLocateVehicleInteractorImpl implements LocateVehicleInteractor {

    private LocateVehiclePresenter presenter;
    private FusedLocationProviderClient client;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;

    public DialogLocateVehicleInteractorImpl(LocateVehiclePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void validateAppsInstalled(FragmentActivity activity) {
        String mapsPackage = activity.getString(R.string.googleMapsPackage);
        String wazePackage = activity.getString(R.string.wazePackage);
        boolean mapsInstalled = DialogLocateVehicleUtils.appInstalledOrNot(mapsPackage, activity);
        boolean wazeInstalled = DialogLocateVehicleUtils.appInstalledOrNot(wazePackage, activity);
        presenter.setAppsInstalledToView(mapsInstalled, wazeInstalled);
    }

    @Override
    public void validateLocationPermission(FragmentActivity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
            ) {
                presenter.setMessageToView("Permite la ubicación del dispositivo para trazar la ruta.");
                presenter.requestLocationPermission();
            } else {
                presenter.requestLocationPermission();
            }
        } else {
            getLocation(activity);
        }

    }

    public void getLocation(FragmentActivity activity) {
        presenter.showLoaderFromInteractor();
        client = LocationServices.getFusedLocationProviderClient(activity);
        LocationManager lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(0);
            locationRequest.setFastestInterval(0);
            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    double latitude = locationResult.getLastLocation().getLatitude();
                    double longitude = locationResult.getLastLocation().getLongitude();
                    if (fusedLocationProviderClient != null) {
                        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                    }
                    presenter.hideLoaderFromInteractor();
                    presenter.setLocationToView(latitude, longitude);
                }

            };
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                presenter.hideLoaderFromInteractor();
                return;
            }
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        } else {
            presenter.hideLoaderFromInteractor();
            presenter.setMessageToView("Habilita el GPS para trazar la ruta.");
        }
    }

}
