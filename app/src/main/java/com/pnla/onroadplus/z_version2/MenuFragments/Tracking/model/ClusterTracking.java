package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;

public class ClusterTracking implements com.google.maps.android.clustering.ClusterItem {

    private final LatLng mPosition;
    private String title;
    private BitmapDescriptor icon;

    public ClusterTracking(LatLng latLng) {
        mPosition = latLng;
    }

    public ClusterTracking(LatLng latLng, String title, BitmapDescriptor icon) {
        mPosition = latLng;
        this.title = title;
        this.icon = icon;
    }

    public LatLng getmPosition() {
        return mPosition;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BitmapDescriptor getIcon() {
        return icon;
    }

    public void setIcon(BitmapDescriptor icon) {
        this.icon = icon;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getSnippet() {
        return "";
    }

}
