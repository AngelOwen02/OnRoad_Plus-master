package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.Containers.UnitTrackingContainer;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.presenter.TrackingPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.presenter.TrackingPresenterImpl;


public class TrackingViewImpl extends Fragment implements OnMapReadyCallback, TrackingView, View.OnClickListener {

    ProgressBar progressBar;
    TrackingPresenter presenter;

    private GoogleMap mMap;
    public TrackingViewImpl() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking_impl, container, false);
        initTrackinViewImpl(view);
        return view;
    }

    private void initTrackinViewImpl(View view){

        progressBar = view.findViewById(R.id.tracking_view_progress_bar);
        ImageView toolbarItem = view.findViewById(R.id.search_toolbar_tracking_map);
        toolbarItem.setOnClickListener(this);
        presenter = new TrackingPresenterImpl(getContext());
        presenter.setView(this);
        presenter.getFullVehicles();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapView mapView = view.findViewById(R.id.map_tracking);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        presenter.zoomVehicleInMap(19.3775177, -99.2354137);

    }

    @Override
    public void setMarkerOptions(MarkerOptions markerOptions) {
        mMap.addMarker(markerOptions);
    }

    @Override
    public void setZoomOptions(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void zoomToVehicle(double latitude, double longitude, float zoom) {
        if (latitude != 0.0 && longitude != 0.0) {
            LatLng notificationPosition = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(notificationPosition, zoom));
        }
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_toolbar_tracking_map:
                showUnitTrackingViewImpl();
                break;
        }
    }

    private void showUnitTrackingViewImpl(){
        Intent intent = new Intent(getContext(), UnitTrackingContainer.class);
        startActivity(intent);
    }
}
