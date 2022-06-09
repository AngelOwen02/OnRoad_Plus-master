package com.pnla.onroadplus.z_version2.MenuFragments.Tracking;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model.ClusterTracking;

public class MarkerClusterRenderer extends DefaultClusterRenderer<ClusterTracking> {

    private Context mContext;

    public MarkerClusterRenderer(Context context, GoogleMap map, ClusterManager<ClusterTracking> clusterManager) {
        super(context, map, clusterManager);
        mContext = context;

    }

    @Override
    protected void onBeforeClusterItemRendered(ClusterTracking item, MarkerOptions markerOptions) {
        markerOptions.icon(item.getIcon());
        markerOptions.title(item.getTitle());
        markerOptions.anchor(.5f,.7f);
    }

}
