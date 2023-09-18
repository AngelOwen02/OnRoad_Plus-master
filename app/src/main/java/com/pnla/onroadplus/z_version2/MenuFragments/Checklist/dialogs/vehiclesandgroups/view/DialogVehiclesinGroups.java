package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.DialogsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.adapter.adapterVehiclesinGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model.DataGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model.VehicleGroupv2;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model.Vehiclec2;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.presenter.presenterVehicleInGroups;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.presenter.presenterVehicleInGroupsImpl;
import com.pnla.onroadplus.z_version2.SplashScreenActivity;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

public class DialogVehiclesinGroups extends DialogFragment implements View.OnClickListener,dialogViewGroups, DialogInterface.OnDismissListener {
    public static final String TAG = DialogVehiclesinGroups.class.getSimpleName();
    private Button externalconstraint,btn_accept_dialog;
    private ConstraintLayout externalconstraint2;
    private RecyclerView rv;
    private adapterVehiclesinGroups adapter;
    private List<DialogsData> dialogData;
    private DataGroups data;
    private presenterVehicleInGroupsImpl presenter;
    private CardView searchViewContainer;
    private SearchView searchViewa;
    private Spinner spinnerGroups;
    private List<VehicleGroupv2> vehicleGroupsList=new ArrayList<>();
    List<Vehiclec2> mvehicles=new ArrayList<>();
    private String value=null;
    private String newVehicle=null;
    private Integer newCVEVehicle=null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
        Bundle args = getArguments();
        if (args != null) {
           value = args.getString("nameAsigment");
            // Use the retrieved value as needed
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_vehicles_dialog_groups, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.grayBD);
        setCancelable(true);
        initDialog(view);
        //setFonts();
        return view;
    }
    private void initDialog(View view) {

        rv = view.findViewById(R.id.recycler_checkList_dialog);
        presenter = new presenterVehicleInGroups(this, getContext());
        presenter.requestGroups();
        spinnerGroups=view.findViewById(R.id.spinnerGroups);
        externalconstraint = view.findViewById(R.id.externalconstraint);
        externalconstraint.setOnClickListener(this);
//        btn_accept_dialog= view.findViewById(R.id.btn_accept_dialog);
//        btn_accept_dialog.setOnClickListener(this);
        externalconstraint2 = view.findViewById(R.id.externalconstraint2);
        externalconstraint2.setOnClickListener(this);

        searchViewContainer = view.findViewById(R.id.cheklist_search_view_container_us);
        searchViewa = (SearchView) view.findViewById(R.id.search_view_checkilist_us);
        searchViewa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Vehiclec2> filterDialogList = filteredDialogList(mvehicles, newText);
                if(mvehicles!=null&&!mvehicles.isEmpty()) {
                    adapter.setFilter(filterDialogList);
                }else {
                    Toast.makeText(getContext(), "Este grupo no contiene unidades", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void fillAdapter(List<Vehiclec2>  data,String value) {
        adapter = new adapterVehiclesinGroups(data,value, this, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    private List<Vehiclec2> filteredDialogList(List<Vehiclec2> data, String text) {

        List<Vehiclec2> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        if(data!=null) {
            for(Vehiclec2 vehicle : data) {
                String dialogsName = vehicle.getVehicleName().toLowerCase();
                if(dialogsName.contains(text)) {
                    filteredList.add(vehicle);
                }
            }
        }
        return filteredList;
    }


    public void closeDialog() {
        onDismiss();
        this.dismiss();

    }

    @Override
    public void newValue(String vehicleName, Integer cveVehicle) {
        this.newVehicle=vehicleName;
        this.newCVEVehicle=cveVehicle;
        SharedPreferences preferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GeneralConstantsV2.NAME_CHECKLIST_VEHICLE, newVehicle);
        editor.putString(GeneralConstantsV2.CVE_CHECKLIST_VEHICLE, String.valueOf(newCVEVehicle));
        editor.commit();
        value=newVehicle;
        closeDialog();
//        rv.post(new Runnable()
//        {
//            @Override
//            public void run() {
//            //    adapter.mnotify();
//            }
//        });

    }

    public void onDismiss() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        CheckListViewImpl contacto = new CheckListViewImpl();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, contacto, CheckListViewImpl.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btn_accept_dialog:
//
//                break;

            case R.id.externalconstraint:
                closeDialog();
                break;
            //case    R.id.:
            //Toast.makeText(getContext(), "ir al menu", Toast.LENGTH_SHORT).show();
            //    closeDialog();

            // break;
        }
    }

    @Override
    public void setVehiclesGroups(DataGroups mdata) {
        this.data=mdata;
        if(data!=null){
            vehicleGroupsList.clear();
            vehicleGroupsList= data.getVehicleGroups();
            List<String> descVehicleGroups = new ArrayList<>();
            descVehicleGroups.clear();
            for (VehicleGroupv2 group : vehicleGroupsList) {
                    descVehicleGroups.add(group.getVehicleGroup());
            }
            String selected="";
            //  spinnerGroups.setPrompt("Pick One");
            descVehicleGroups.add(0, "Selecciona una opción");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,descVehicleGroups);
            //  spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, arrayAdapter);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerGroups.setAdapter(adapter);
            spinnerGroups.setSelection(0, false);
            spinnerGroups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view,int i, long l) {
                    String choice=adapterView.getItemAtPosition(i).toString();
                    if(i!=0) {
                        Log.e("spinnerPosition", "item: " + i);
                        List<Vehiclec2> vehicles = new ArrayList<>();
                        vehicles.clear();
                        vehicles.addAll(data.getVehicleGroups().get(i-1).getVehicles());//se usa i-1 ya que el arreglo esta desfazado en 1 debido a que se agrega para que contenga texto
                        if(vehicles!=null&&!vehicles.isEmpty()) {
                            mvehicles=vehicles;
                            for (Vehiclec2 vf : vehicles) {
                                Log.e("spinnerPosition", "item: " + vf.getVehicleName());
                            }
                            fillAdapter(vehicles,value);
                        }else {
                            List<Vehiclec2> vehicles2 = new ArrayList<>();
                            vehicles2.clear();
                            fillAdapter(vehicles2,value);
                            Toast.makeText(getContext(), "Este grupo no contiene unidades", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        List<Vehiclec2> vehicles = new ArrayList<>();
                        vehicles.clear();
                        fillAdapter(vehicles,value);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
}
