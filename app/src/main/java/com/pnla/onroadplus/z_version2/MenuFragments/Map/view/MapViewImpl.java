package com.pnla.onroadplus.z_version2.MenuFragments.Map.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.pnla.onroadplus.R;

public class MapViewImpl extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    private ImageView itemBack;
    private ImageView itemNavigation;
    private ImageView itemTerminal;
    private GoogleMap mMap;
    private MapView mapView;
 

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_view_impl, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    private void initView(View view){
        itemBack = view.findViewById(R.id.map_toolbar_item_back);
        itemNavigation = view.findViewById(R.id.map_toolbar_item_navigation);
        itemTerminal = view.findViewById(R.id.map_toolbar_item_terminal);

        itemBack.setOnClickListener(this);
        itemNavigation.setOnClickListener(this);
        itemTerminal.setOnClickListener(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());
       // mapView.setVisibility(View.VISIBLE);
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        //presenter.getDates();
       // if (!isShowedThisView) {
        //    presenter.getVehiclesFromBundleOrRealm(bundle, getContext());    /**Este método trae los vehiculos desde el bundle o realm, y de igual manera trae los 30vehiculos en caso de ser la primera vez que el usuario entró*/
       // } else {
        //    fillVehiclesListInMap(!GeneralConstantsV2.FIRST_VEHICLE_REQUEST);
         //   fillDatesInMap();
          //  putMarkersInMap();
       // }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.map_toolbar_item_back:
                break;
            case R.id.map_toolbar_item_navigation:
                break;
            case R.id.map_toolbar_item_terminal:
                break;
        }
    }
}
