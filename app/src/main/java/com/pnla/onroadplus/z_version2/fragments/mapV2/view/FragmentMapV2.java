package com.pnla.onroadplus.z_version2.fragments.mapV2.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.commands.view.FragmentCommandsV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.adapters.AdapterDatesV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.adapters.AdapterTripsV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.adapters.AdapterVehiclesV2Map;
import com.pnla.onroadplus.z_version2.fragments.mapV2.components.ComponentVehicleCustomFields;
import com.pnla.onroadplus.z_version2.fragments.mapV2.components.ComponentVehicleHeader;
import com.pnla.onroadplus.z_version2.fragments.mapV2.dialogLocateVehicle.view.DialogLocateVehicle;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Data;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.mapV2.interfaces.MapV2View;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.datesList.DateV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.markersV2.VehicleBitmapDescriptorV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.markersV2.VehicleMarkerV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.PositionV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription.VehicleDescriptionData;
import com.pnla.onroadplus.z_version2.fragments.mapV2.presenters.FragmentMapV2PresenterImpl;
import com.pnla.onroadplus.z_version2.fragments.mapV2.utils.MapV2Utils;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

import java.util.ArrayList;
import java.util.List;

public class FragmentMapV2 extends Fragment implements MapV2View, OnMapReadyCallback, AdapterVehiclesV2Map.OnClickVehiclesV2MapListener, View.OnClickListener, AdapterDatesV2.OnDateClickListener, AdapterTripsV2.OnClickTripListener, ComponentVehicleHeader.OnClickVehicleHeaderListener {

    public static final String TAG = FragmentMapV2.class.getSimpleName();

    private Bundle bundle;
    private MapV2Presenter presenter;
    private DialogTrackingLoader loader;

    private ImageView btnBackButtonBottomSheet;     /**Regresa a la lista de vehículos*/

    /**
     * HeaderComponents
     * Botones para enviar comando e ifo del vehículo
     */
    private ConstraintLayout constraintVehicleInfo, constraintVehicleCommand;
    private ConstraintLayout constraintMainHeader;
    private ComponentVehicleHeader componentVehicleHeader;

    private GoogleMap mMap;
    private MapView mapView;

    /**
     * Bottomsheet
     */
    private BottomSheetBehavior bottomSheetBehavior;
    private ConstraintLayout linearLayoutBSheet;

    /**
     * Lista de marcadores
     */
    private List<VehicleMarkerV2> markers;
    private List<VehicleBitmapDescriptorV2> vehiclesImages;

    /**
     * Lista vehiculos
     */
    private RecyclerView rvVehiclesV2Map;
    private AdapterVehiclesV2Map adapterVehiclesV2Map;
    private LinearLayoutManager layoutManagerVehiclesV2Map;
    private List<VehicleV2Map> vehicles;

    /**
     * Lista de fechas
     */
    private List<DateV2> dates;
    private AdapterDatesV2 adapterDatesV2;
    private RecyclerView rvDates;

    /**
     * Lista de viajes
     */
    private List<TripV2> trips;
    private RecyclerView rvTripsV2;
    private AdapterTripsV2 adapterTripsV2;

    /**
     * CustomFieldsComponent
     */
    private ComponentVehicleCustomFields componentVehicleCustomFields;

    /**
     * VehicleToSendCommand
     */
    private VehicleV2Map vehicleSelected;

    /**
     * toDrawTrip
     */
    private Polyline polylineTrip;
    private Marker startMaker, endMarker;

    /**
     * toolbarTitle
     */
    private MapV2Data mapV2Data;

    private boolean isVisibleVehicleHeader = false;
    private boolean isVisibleCustomFields = false;
    public static boolean isShowedThisView = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_v2, container, false);
        initMapV2(view);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mapV2Data = (MapV2Data) context;
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.cancelRequest();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG_MAP", "onResume");
       // mapV2Data.setMapTitleToolbar("");
        presenter.getVehiclesToUpdate(vehicles, getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isShowedThisView = false;
        presenter.cancelRequest();
        presenter.setView(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackButtonBottomSheet:
                showVehiclesList();
                break;

        }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity());
        mapView.setVisibility(View.VISIBLE);
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        presenter.getDates();
        if (!isShowedThisView) {
            presenter.getVehiclesFromBundleOrRealm(bundle, getContext());    /**Este método trae los vehiculos desde el bundle o realm, y de igual manera trae los 30vehiculos en caso de ser la primera vez que el usuario entró*/
        } else {
            fillVehiclesListInMap(!GeneralConstantsV2.FIRST_VEHICLE_REQUEST);
            fillDatesInMap();
            putMarkersInMap();
        }
    }

    @Override
    public void showLoader() {
        loader = new DialogTrackingLoader();
        loader.show(getActivity().getSupportFragmentManager(), DialogTrackingLoader.TAG);
    }

    @Override
    public void hideLoader() {
        if (loader != null) {
            loader.dismiss();
            loader = null;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sessionExpired(String message) {
        showMessage(message);
        getActivity().finish();
    }

    @Override
    public void showFragmentVehicles(Fragment fragment) {
        //FragmentManager manager = getActivity().getSupportFragmentManager();
        //FragmentTransaction transaction = manager.beginTransaction();
        //transaction.replace(R.id.containerFragmentsOnLineV2, fragment, FragmentVehiclesV2.TAG).commit()
        mapV2Data.setFragmentVehicles(fragment);
    }

    @Override
    public void fillDatesList(List<DateV2> dates) {
        this.dates = dates;
        fillDatesInMap();
    }

    @Override
    public void fillVehiclesList(List<VehicleV2Map> vehicles, boolean isFirstRequest) {
        this.vehicles = vehicles;
        fillVehiclesListInMap(isFirstRequest);
    }

    @Override
    public void fillVehicleDescription(VehicleDescriptionData data) {
        componentVehicleCustomFields.setVehicleDescription(data);
        adapterDatesV2.setPosition1Clicked();                                   /**     Seleccionamos la primer fecha           */
    }

    @Override
    public void fillTripsToView(List<TripV2> trips) {
        this.trips = trips;
        fillTripsInMap();
    }

    @Override
    /**     Este método se ejecuta una sola vez cada que inicia este fragment
     *      guarda los markers en la lista "markers" y ademas coloca los markers en el mapa.    horometro, odometro
     *      Aqui se empieza a consumir el WS de los veiculos para realizar el update de sus posiciones
     *  */
    public void setVehicleBitmapDescriptorToView(List<VehicleBitmapDescriptorV2> vehiclesImages) {
        this.vehiclesImages = vehiclesImages;
        putMarkersInMap();
        presenter.getVehiclesToUpdate(vehicles, getContext());       /**Pedimos los vehiculos para que se actualizen*/
    }

    @Override
    public void updateVehiclesMarkers(List<VehicleV2Map> vehicleList) {
        //en un principio a cada marcador le asignamos un "id" que es la clave del vehiculo
        //realizamos una busqueda en la lista de los marcadores para setearle su nueva posici+ón
        for (int i = 0; i < vehicleList.size(); i++) {
            int newCveVehicle = vehicleList.get(i).getCveVehicle();

            for (int j = 0; j < markers.size(); j++) {
                int cve = markers.get(j).getCveVehicle();
                if (newCveVehicle == cve) {

                    /**
                     * Si el vehiculo viene con posición 0 se esconde el marker del vehiculo
                     */
                    if (vehicleList.get(i).getLatitude() == 0.0 && vehicleList.get(i).getLongitude() == 0.0) {
                        markers.get(j).getMarker().setVisible(false);
                    } else {
                        markers.get(j).getMarker().setVisible(true);
                    }
                    markers.get(j).getMarker().setPosition(new LatLng(vehicleList.get(i).getLatitude(), vehicleList.get(i).getLongitude()));
                    break;
                }
            }
        }
    }

    @Override
    public void showZoomByVehicleSelected(double latitude, double longitude) {
        zoomToVehicle(latitude, longitude, GeneralConstantsV2.VEHICLE_SELECTED);
    }

    @Override    // evento click de la lista de vehiculos
    public void onClickVehiclesV2Map(int position) {
        mapV2Data.showOrHideItemRoute(true);                    /**Se activa el boton para localizar unidad con google maps o waze*/
        presenter.getVehicleDescription(vehicles.get(position).getCveVehicle(), getContext());       /**     Se solicita descripcion del vehículo    */
        isVisibleVehicleHeader = true;
        vehicleSelected = vehicles.get(position);                               /**     Vehiculo seleccionado, se envia a pantalla comandos*/
        btnBackButtonBottomSheet.setVisibility(View.VISIBLE);                   /**     Se muestra boton back                   */
        rvVehiclesV2Map.setVisibility(View.GONE);                               /**     Escondemos la lista de vehículos        */
        constraintMainHeader.setVisibility(View.VISIBLE);                       /**     Mostramos detalle del vehículo          */
        componentVehicleHeader.setVehicleDataHeader(vehicles.get(position));    /**     Se manda info del vehiculo a mostrar    */
        zoomToVehicle(vehicleSelected.getLatitude(), vehicleSelected.getLongitude(), GeneralConstantsV2.VEHICLE_SELECTED);  /**Realizamos zoom sobre el vehiculo seleccionado*/
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);      /**     El bottomsheet baja                     */
        rvDates.scrollToPosition(0);                                            /**     Realizamos scroll a la primer posicion  */
    }

    @Override   // evento click de la lista de fechas detalle vehiculo
    public void onDate(String date, int position) {
        if (adapterTripsV2 != null && adapterTripsV2.getItemCount() > 0) {/**Limpiamos los viajes en caso de que exista mas de 1*/
            adapterTripsV2.clearRecyclerView();
        }
        presenter.getTripsByDate(vehicleSelected.getCveVehicle(), date, getContext());
    }

    @Override
    public void onClickGoogleImage(View view, int position) {
        List<PositionV2> positionsToDraw = trips.get(position).getPositions();
        if (positionsToDraw != null) {
            deletePolylineAndTripMarkers();
            drawTrip(positionsToDraw);
        } else {
            Toast.makeText(getContext(), getString(R.string.textNotFoundTrip), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClickFinalPosition(View view, int position) {
        int height = 40;                                /**pintamos la posicion final del viaje*/
        int width = 40;
        deletePolylineAndTripMarkers();
        double latitude = trips.get(position).getPositions().get(trips.get(position).getPositions().size() - 1).getLatitude();
        double longitude = trips.get(position).getPositions().get(trips.get(position).getPositions().size() - 1).getLongitude();
        LatLng notificationPosition = new LatLng(latitude, longitude);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.end_marker);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        zoomToVehicle(latitude, longitude, GeneralConstantsV2.VEHICLE_FINAL_POSITION);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);      /**El bottomsheet baja*/
    }

    @Override
    public void onClickVehicleHeader() {
        presenter.getLocationUpdatedByVehicleSeledted(vehicleSelected, vehicles);
    }

    private void initMapV2(View view) {
        /**View's*/
        btnBackButtonBottomSheet = view.findViewById(R.id.btnBackButtonBottomSheet);

        rvVehiclesV2Map = view.findViewById(R.id.rvVehiclesV2Map);
        rvDates = view.findViewById(R.id.rvDates);
        rvTripsV2 = view.findViewById(R.id.rvEvents);

        //constraintVehicleInfo = view.findViewById(R.id.constraintVehicleInfo);
        //constraintVehicleCommand = view.findViewById(R.id.constraintVehicleCommand);
        constraintMainHeader = view.findViewById(R.id.constraintMainHeader);

        componentVehicleHeader = view.findViewById(R.id.vehicleDataHeader);
        //componentVehicleCustomFields = view.findViewById(R.id.componentVehicleCustomFields);

        /**Bottomsheet*/
        linearLayoutBSheet = view.findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBSheet);

        /**ToolbarTitle*/
        //mapV2Data.setMapTitleToolbar("");

        /**OnClickListener*/
        btnBackButtonBottomSheet.setOnClickListener(this);
        constraintVehicleInfo.setOnClickListener(this);
        constraintVehicleCommand.setOnClickListener(this);
        componentVehicleHeader.setOnClickVehicleHeaderListener(this);

        /***
         * Presenter, los vehiculos se traen dentro del método onMapReady
         */
        presenter = new FragmentMapV2PresenterImpl();
        presenter.setView(this);
    }

    private void deletePolylineAndTripMarkers() {
        if (polylineTrip != null) {     /**si existe una polylinea la borramos*/
            polylineTrip.remove();
        }
        if (startMaker != null) {       /**si existen marcadores inicio y fin los borramos*/
            startMaker.remove();
        }
        if (endMarker != null) {
            endMarker.remove();
        }
    }

    private void showOrHideCustomFields() {
        if (isVisibleCustomFields) {
            isVisibleCustomFields = false;
            componentVehicleCustomFields.setVisibility(View.GONE);
        } else {
            isVisibleCustomFields = true;
            componentVehicleCustomFields.setVisibility(View.VISIBLE);
        }
    }

    private void showVehiclesList() {
        isVisibleCustomFields = false;
        isVisibleVehicleHeader = false;
        vehicleSelected = null;
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);     /**     El bottomsheet baja*/
        deletePolylineAndTripMarkers();                                        /**     Borramos polyline y marcadores del viaje*/
        zoomToVehicle(vehicles.get(0).getLatitude(), vehicles.get(0).getLongitude(), GeneralConstantsV2.GENERAL_ZOOM);   /** zoom para mostrar todos los vehiculos*/
        mapV2Data.showOrHideItemRoute(false);                   /**     Se esconde el boton para localizar ruta     */
        componentVehicleCustomFields.cleanCustomFields();                      /**     Se limpian todos los campos                  */
        componentVehicleCustomFields.setVisibility(View.GONE);                 /**     Se esconde customFields                      */
        btnBackButtonBottomSheet.setVisibility(View.GONE);                     /**     Se esconde boton back                        */
        rvVehiclesV2Map.setVisibility(View.VISIBLE);                           /**     Se muestra lis de vehiculos                  */
        constraintMainHeader.setVisibility(View.GONE);                         /**     Escondemos detalle del vehículo              */
        if (adapterTripsV2 != null && adapterTripsV2.getItemCount() > 0) {     /**     Se limpia la lista de viajes                 */
            adapterTripsV2.clearRecyclerView();
        }
    }

    private void fillVehiclesListInMap(boolean isFirstRequest) {
        rvVehiclesV2Map.setNestedScrollingEnabled(false);
        adapterVehiclesV2Map = new AdapterVehiclesV2Map(vehicles, getContext());
        rvVehiclesV2Map.setAdapter(adapterVehiclesV2Map);
        layoutManagerVehiclesV2Map = new LinearLayoutManager(getContext());
        rvVehiclesV2Map.setLayoutManager(layoutManagerVehiclesV2Map);
        adapterVehiclesV2Map.setOnClickVehiclesV2MapListener(FragmentMapV2.this);

        if (isFirstRequest)     /**Solicitamos los marcadores 1 sola vez*/ {
            presenter.getVehiclesMarkers(vehicles);
        }

        /**Si el usuario esta en el detalle de vehiculo se actualiza tambien ese componente,
         * siguiendo estos pasos:
         * 1. buscamos el vehiculo seleccionado en la lista "vehicles"
         * 2. una vez encontrado se camda ese vehiculo al componente
         * */
        if (isVisibleVehicleHeader) {
            componentVehicleHeader.setVehicleDataHeader(MapV2Utils.findVehicle(vehicles, vehicleSelected));
        }

        rvVehiclesV2Map.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    Log.e("RV_LISTENER", "d0>0");
                    int visibleItemCount = layoutManagerVehiclesV2Map.getChildCount();
                    int totalItemCount = layoutManagerVehiclesV2Map.getItemCount();
                    int pastVisibleItems = layoutManagerVehiclesV2Map.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        Log.e("RV_LISTENER", "LLEGAMOS AL FINAL DE LA LISTA");
                    } else {
                        Log.e("RV_LISTENER", "NO SE HA LLEGADO AL FINAL DE LA LISTA");
                    }
                } else {
                    Log.e("RV_LISTENER", "d0 <0");
                }
            }
        });

    }

    private void fillDatesInMap() {
        rvDates.setNestedScrollingEnabled(false);
        adapterDatesV2 = new AdapterDatesV2(dates, getContext());
        adapterDatesV2.setOnClickDateListener(FragmentMapV2.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDates.setLayoutManager(layoutManager);
        rvDates.setAdapter(adapterDatesV2);
    }

    private void fillTripsInMap() {
        rvTripsV2.setNestedScrollingEnabled(false);
        adapterTripsV2 = new AdapterTripsV2(trips, getContext());
        adapterTripsV2.setOnClickTripListener(FragmentMapV2.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTripsV2.setLayoutManager(layoutManager);
        rvTripsV2.setAdapter(adapterTripsV2);
    }

    private void showCommandsFragment() {
        FragmentCommandsV2 fragmentCommandsV2 = new FragmentCommandsV2();
        Bundle bundle = new Bundle();
        bundle.putString(GeneralConstantsV2.VEHICLE_NAME_SELECTED, vehicleSelected.getVehicleName());
        bundle.putInt(GeneralConstantsV2.VEHICLE_CVE_SELECTED, vehicleSelected.getCveVehicle());
        fragmentCommandsV2.setArguments(bundle);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentCommandsV2.TAG);
        transaction.replace(R.id.containerFragmentsOnLineV2, fragmentCommandsV2, FragmentCommandsV2.TAG).commit();
    }

    public void drawRouteWithGoogleMapsOrWaze() {  /**Metodo se invoca desde el actvity, cuando se da click al boton de la toolbar*/
        DialogLocateVehicle dialogLocateVehicle = new DialogLocateVehicle();
        dialogLocateVehicle.setLocationVehicle(vehicleSelected.getLatitude(), vehicleSelected.getLongitude());
        dialogLocateVehicle.show(getActivity().getSupportFragmentManager(), DialogLocateVehicle.TAG);
    }

    private void zoomToVehicle(double latitude, double longitude, float zoom) {
        /**se realiza zoom solo si la posición es distinta de 0*/
        if (latitude != 0.0 && longitude != 0.0) {
            LatLng notificationPosition = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(notificationPosition, zoom));
        }
    }

    private void drawTrip(List<PositionV2> positions) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        PolylineOptions options = new PolylineOptions().width(3).color(Color.BLACK).geodesic(true);

        for (int z = 0; z < positions.size(); z++) {
            LatLng point = new LatLng(positions.get(z).getLatitude(), positions.get(z).getLongitude());
            builder.include(point);
            options.add(point);
        }

        LatLngBounds bounds = builder.build();          /**create the bounds from latlngBuilder to set into map camera*/
        int padding = 50;                               /**initialize the padding for map boundary*/
        final CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);       /**create the camera with bounds and padding to set into map*/
        mMap.animateCamera(cu);
        polylineTrip = mMap.addPolyline(options);

        int height = 20;                                /**Agregamos el primer y el ultimo marker*/
        int width = 20;
        LatLng notificationPosition = new LatLng(positions.get(0).getLatitude(), positions.get(0).getLongitude());
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.start_marker);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        startMaker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        height = 30;
        width = 30;
        notificationPosition = new LatLng(positions.get(positions.size() - 1).getLatitude(), positions.get(positions.size() - 1).getLongitude());
        bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.end_marker);
        b = bitmapdraw.getBitmap();
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        endMarker = mMap.addMarker(new MarkerOptions().position(notificationPosition).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void putMarkersInMap() {
        markers = new ArrayList<>();
        for (int i = 0; i < vehiclesImages.size(); i++) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(vehiclesImages.get(i).getVehicle().getLatitude(), vehiclesImages.get(i).getVehicle().getLongitude()))
                    .title(vehiclesImages.get(i).getVehicle().getVehicleName())
                    .anchor(0.5f, 0.5f)
                    .icon(vehiclesImages.get(i).getVehicleImage()));

            /**Se esconde el marker si la posición es 0*/
            if (vehiclesImages.get(i).getVehicle().getLatitude() == 0.0 && vehiclesImages.get(i).getVehicle().getLongitude() == 0.0) {
                marker.setVisible(false);
            }

            markers.add(new VehicleMarkerV2(vehiclesImages.get(i).getCveVehicle(), marker));
        }
        /** Realizamos zoom sobre el primer vehiculo*/
        zoomToVehicle(vehicles.get(0).getLatitude(), vehicles.get(0).getLongitude(), GeneralConstantsV2.GENERAL_ZOOM);
    }

}