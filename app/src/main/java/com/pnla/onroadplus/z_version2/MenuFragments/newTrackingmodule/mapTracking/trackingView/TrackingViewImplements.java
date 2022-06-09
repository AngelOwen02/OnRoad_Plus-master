package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingView;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.ClusterTracking;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.interactor.UnitsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingPresenter.trackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingPresenter.trackingPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.toglesTracking.togglesView.togglesViewImpl;

public class TrackingViewImplements extends Fragment implements trackingView,OnMapReadyCallback, ClusterManager.OnClusterClickListener<ClusterTracking>  {

    private MapView mView;
    private GoogleMap mMap;
    private Handler handler = new Handler();
    private Runnable runnable;
    private trackingPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking_map, container, false);
        initTrackingMapFragment(view, savedInstanceState);
        return view;
    }
    private void initTrackingMapFragment(View view, Bundle savedInstanceState) {
        bindViews(view);
        onCreateViewMap(savedInstanceState);
        initpresenter();

    }
    private void bindViews(View view) {
        mView = view.findViewById(R.id.map_view_tracking);
        ImageView toolbar = view.findViewById(R.id.search_toolbar_tracking_map);//ojo
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUnitTrackingViewImpl();
            }
        });
    }
    private void showUnitTrackingViewImpl() {
        Intent intent = new Intent(getContext(), togglesViewImpl.class);
        startActivity(intent);
    }
    private void onCreateViewMap(Bundle savedInstanceState) {
        mView.onCreate(savedInstanceState);
        if (mView != null) {
            mView.getMapAsync(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mView.onResume();
        handler.postDelayed(runnable,10000);
        Log.e("onResume", "OK");



    }
    private void initpresenter()
    {
        presenter= new trackingPresenterImpl(getContext());
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 2));

        uiSettingsMap(mMap);
        SharedPreferences prefs = getContext().getSharedPreferences("Login:FirstTime", Context.MODE_PRIVATE);
        boolean isFirstLogin = prefs.getBoolean("isFirst", true);
        Log.e("First Login?", String.valueOf(isFirstLogin));

        knowzoom();

    }
    private void knowzoom()
    {
        presenter.getFullVehicles();
        try
        {
            Thread.sleep(2000);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.3775177, -99.2354137), 4.5f));

        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        Log.e("newTracking",""+ UnitsInteractorImpl.dataofvehiclesgroups+" : "+UnitsInteractorImpl.dataofvehicles);

        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                Log.e("requestdeunitsagain","zoom: "+mMap.getCameraPosition().zoom);

                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
    private void uiSettingsMap(GoogleMap googleMap) {
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setRotateGesturesEnabled(false);
    }
    @Override
    public boolean onClusterClick(Cluster<ClusterTracking> cluster) {
        return false;
    }
}
