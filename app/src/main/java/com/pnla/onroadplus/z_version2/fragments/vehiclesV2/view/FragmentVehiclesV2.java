package com.pnla.onroadplus.z_version2.fragments.vehiclesV2.view;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.adapters.AdapterGroupsV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.adapters.AdapterVehiclesV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.FragmentVehiclesV2Data;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.VehiclesV2Presenter;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.interfaces.VehiclesV2View;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.groupsv2.GroupV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.presenters.VehiclesV2PresenterImpl;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.phoenix2.top_driver_ui.tracking_app.DialogTrackingLoader;

import java.util.ArrayList;
import java.util.List;

public class FragmentVehiclesV2 extends Fragment implements VehiclesV2View, View.OnClickListener, AdapterVehiclesV2.VehicleAndGroupSelected, SearchView.OnQueryTextListener, AdapterGroupsV2.GroupSelected {

    public static final String TAG = FragmentVehiclesV2.class.getSimpleName();

    private DialogTrackingLoader loader;
    private VehiclesV2Presenter presenter;

    /**
     * Para enviar datos a la activity
     */
    private FragmentVehiclesV2Data fragmentVehiclesV2Data;

    /**
     * Lista de los vehiculos
     */
    private List<VehicleV2> vehicles;
    private RecyclerView rvVehicles;
    private AdapterVehiclesV2 adapterVehiclesV2;
    private LinearLayoutManager layoutManagerVehicles;

    /**
     * Lista de grupos
     */
    private List<GroupV2> groups;
    private RecyclerView rvGroups;
    private AdapterGroupsV2 adapterGroupsV2;
    private LinearLayoutManager layoutManagerGroups;

    /**
     * Components
     */
    private Button btnShowAllVehicles, btnShowAllGrpups;
    private ImageView imgvShowSearchView, imgvCloseSearchView;
    private ConstraintLayout constraintLayoutSearchView;
    private SearchView searchView;

    private boolean isVisibleVehicles = true;
    private boolean isVisibleGroups = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicles_v2, container, false);
        initVehicleV2View(view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //fragmentVehiclesV2Data = (FragmentVehiclesV2Data) context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowAllVehicles:
                showVehicles();
                break;
            case R.id.btnShowAllGroups:
                showGroups();
                break;
            case R.id.imgvShowSearchView:
                showSearchView();
                break;
            case R.id.imgvCloseSearchView:
                hideSearchView();
                break;
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void fillVehiclesList(List<VehicleV2> vehicles) {
        this.vehicles = vehicles;
//        fragmentVehiclesV2Data.setVehiclesListToActivity(vehicles);     /**Enviamos lista al activity para que las valide cuando se presiona el boton de la toolbar (Mapa)*/
        fillVehicles();
    }

    @Override
    public void fillGroupsList(List<GroupV2> groups) {
        this.groups = groups;
        fragmentVehiclesV2Data.setGroupsListToActivity(groups);         /**Enviamos lista al activity para que las valide cuando se presiona el boton de la toolbar (Mapa)*/
        fillGroups();
    }

    @Override  // click de la lista de vehiculos
    public void vehicleSelected(View view, int positionListComplete, int positionFilterList) {
        boolean newState = !vehicles.get(positionListComplete).isSelected();                    /**Obtenemos valor actual*/
        adapterVehiclesV2.changedData(positionFilterList, newState);                            /**Enviamos ese valor al adpter para actualizar la lista*/
        fragmentVehiclesV2Data.setNewStateToVehicleList(positionListComplete, newState);        /**Enviamos ese valor a la lista de la activity, para el caso que se desea guardar los vehiculos en la db.*/
    }

    @Override  // click de la lista de grupos
    public void groupSelected(View view, int positionListComplete, int positionFilterList) {
        boolean newState = !groups.get(positionListComplete).isSelected();                      /**Obtenemos valor actual*/
        adapterGroupsV2.changedData(positionFilterList, newState);                              /**Enviamos ese valor al adpter para actualizar la lista*/
        fragmentVehiclesV2Data.setNewStateToGroupsList(positionListComplete, newState);         /**Enviamos ese valor a la lista de la activity, para el caso que se desea guardar los grupos en la db.*/
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!isVisibleVehicles) {
            if (newText == null || newText.equals("")) {
                imgvCloseSearchView.setVisibility(View.VISIBLE);
            } else {
                imgvCloseSearchView.setVisibility(View.GONE);
            }
            List<GroupV2> filterGroupsList = filterGroups(groups, newText);
            adapterGroupsV2.setFilter(filterGroupsList);
        } else {
            if (newText == null || newText.equals("")) {
                imgvCloseSearchView.setVisibility(View.VISIBLE);
            } else {
                imgvCloseSearchView.setVisibility(View.GONE);
            }

            List<VehicleV2> filterVehicleV2List = filterVehicles(vehicles, newText);
            adapterVehiclesV2.setFilter(filterVehicleV2List);
        }
        return false;
    }

    private void initVehicleV2View(View view) {
        btnShowAllVehicles = view.findViewById(R.id.btnShowAllVehicles);
        btnShowAllGrpups = view.findViewById(R.id.btnShowAllGroups);
        imgvShowSearchView = view.findViewById(R.id.imgvShowSearchView);
        imgvCloseSearchView = view.findViewById(R.id.imgvCloseSearchView);
        constraintLayoutSearchView = view.findViewById(R.id.constraintLayout3);
        rvVehicles = view.findViewById(R.id.rvVehicles);
        rvGroups = view.findViewById(R.id.rvGroups);
        searchView = view.findViewById(R.id.searchView2);

        //fragmentVehiclesV2Data.setVehiclesTitleToolbar(getString(R.string.textItemCars));                               //  toolbarTitle
//        fragmentVehiclesV2Data.setVehiclesListState(GeneralConstantsV2.VEHICLES_LIST_VISIBLE);    /**El usuario empieza  visualizando la seccion de la lsita de vehiculos, se lo notificamos al activity*/

        searchView.setOnQueryTextListener(this);
        btnShowAllVehicles.setOnClickListener(this);
        btnShowAllGrpups.setOnClickListener(this);
        imgvShowSearchView.setOnClickListener(this);
        imgvCloseSearchView.setOnClickListener(this);

        presenter = new VehiclesV2PresenterImpl();
        presenter.setView(this);
        presenter.getVehiclesAndGroups(getContext());
    }

    private void fillVehicles() {
        adapterVehiclesV2 = new AdapterVehiclesV2(vehicles, getContext());
        rvVehicles.setAdapter(adapterVehiclesV2);
        layoutManagerVehicles = new LinearLayoutManager(getContext());
        rvVehicles.setLayoutManager(layoutManagerVehicles);
        adapterVehiclesV2.setVehicleAndGroupSelectedListener(FragmentVehiclesV2.this);
    }

    private void fillGroups() {
        adapterGroupsV2 = new AdapterGroupsV2(groups, getContext());
        rvGroups.setAdapter(adapterGroupsV2);
        layoutManagerGroups = new LinearLayoutManager(getContext());
        rvGroups.setLayoutManager(layoutManagerGroups);
        adapterGroupsV2.setGroupSelectedListener(FragmentVehiclesV2.this);
    }

    private void showSearchView() {
        constraintLayoutSearchView.setVisibility(View.VISIBLE);
    }

    private void hideSearchView() {
        constraintLayoutSearchView.setVisibility(View.GONE);
    }

    private List<VehicleV2> filterVehicles(List<VehicleV2> vehicles, String text) {
        List<VehicleV2> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        for (VehicleV2 vehicle : vehicles) {
            String vehicleName = vehicle.getVehicleName().toLowerCase();
            if (vehicleName.contains(text)) {
                filteredList.add(vehicle);
            }
        }
        return filteredList;
    }

    private List<GroupV2> filterGroups(List<GroupV2> groups, String text) {
        List<GroupV2> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        for (GroupV2 group : groups) {
            String groupName = group.getVehicle_group().toLowerCase();
            if (groupName.contains(text)) {
                filteredList.add(group);
            }
        }
        return filteredList;
    }

    private void showVehicles() {
        if (!isVisibleVehicles)     /** Si no estan visibles los vehiculos entonces se muestran */ {
            rvGroups.setVisibility(View.GONE);
            rvVehicles.setVisibility(View.VISIBLE);
            isVisibleVehicles = true;
            isVisibleGroups = false;
            fragmentVehiclesV2Data.setVehiclesListState(GeneralConstantsV2.VEHICLES_LIST_VISIBLE);  /**le decimos a la activity que la lista de vehiculos esta visible, para que en caso de presionar el boton de mapa se envien los vehiculos y los tome de la lista vehiculos de la misma activity*/
            fragmentVehiclesV2Data.setVehiclesTitleToolbar("Unidades");                             /**Cambiamos nombre a la toolbar*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                btnShowAllVehicles.setElevation(6);
                btnShowAllGrpups.setElevation(0);
            }
        }
    }

    private void showGroups() {
        if (!isVisibleGroups)   /**     Si no estan visibles los grupos entonces se muestran    */ {
            if (groups != null && groups.size() > 0) {
                rvGroups.setVisibility(View.VISIBLE);
                rvVehicles.setVisibility(View.GONE);
                isVisibleGroups = true;
                isVisibleVehicles = false;
                fragmentVehiclesV2Data.setVehiclesListState(GeneralConstantsV2.VEHICLES_LIST_NOT_VISIBLE);  /**le decimos a la activity que la lista de vehiculos NO esta visible, para que en caso de presionar el boton de mapa se envien los vehiculos y los tome de la lista Grupos de la misma activity */
                fragmentVehiclesV2Data.setVehiclesTitleToolbar("Grupos");                                   /**Cambiamos nombre a la toolbar*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btnShowAllVehicles.setElevation(0);
                    btnShowAllGrpups.setElevation(6);
                }
            } else {
                showMessage("No se cuenta con grupos");
            }
        }
    }

}