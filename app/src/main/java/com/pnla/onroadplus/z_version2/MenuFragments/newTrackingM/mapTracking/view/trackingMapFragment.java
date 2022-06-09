package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.presenter.trackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.presenter.trackingPresenterImpl;

public class trackingMapFragment extends Fragment implements OnMapReadyCallback ,trackingmapView{
    public static final String TAG = trackingMapFragment.class.getSimpleName();
    private MapView mView;
    private GoogleMap mMap;
    private trackingPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking_map, container, false);


        mView = view.findViewById(R.id.map_view_tracking);
        mView.onCreate(savedInstanceState);

        if (mView != null) {
            mView.getMapAsync(this);
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        presenterMethods();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 2.0f));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 4.5f));
        uiSettingsMap(mMap);



    }

    private void presenterMethods() {
        Log.e("nogeoNewModule","view method");
        presenter=new trackingPresenterImpl(this,getContext());
        presenter.requestNoGeo();
        presenter.requestGroups();
    }

    private void uiSettingsMap(GoogleMap googleMap) {
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setRotateGesturesEnabled(false);
    }
    @Override
    public void onResume() {
        mView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mView.onLowMemory();
    }



    @Override
    public void units() {

    }

    @Override
    public void groups() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
}