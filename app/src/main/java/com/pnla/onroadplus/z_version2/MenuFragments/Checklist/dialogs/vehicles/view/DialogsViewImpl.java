package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.adapter.DialogsAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.model.DialogsData;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.presenter.DialogsPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.presenter.DialogsPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DialogsViewImpl extends DialogFragment implements View.OnClickListener, DialogsView , DialogInterface.OnDismissListener {

    public static final String TAG = DialogsViewImpl.class.getSimpleName();
    private Button externalconstraint;
    private ConstraintLayout externalconstraint2;
    private RecyclerView rv;
    private DialogsAdapter adapter;
    private List<DialogsData> dialogData;
    private DialogsPresenter presenter;
    private CardView searchViewContainer;
    private SearchView searchViewa;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_vehicles_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.grayBD);
        setCancelable(true);

        initDialog(view);
        //setFonts();
        return view;
    }

    private void initDialog(View view) {

        rv = view.findViewById(R.id.recycler_checkList_dialog);
        presenter = new DialogsPresenterImpl(this, getContext());
        //
        presenter.requestVehicles();

        externalconstraint = view.findViewById(R.id.externalconstraint);
        externalconstraint.setOnClickListener(this);

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
                List<DialogsData> filterDialogList = filteredDialogList(dialogData, newText);
                adapter.setFilter(filterDialogList);
                return false;
            }
        });
    }

    private void fillAdapter(List<DialogsData> data) {
        adapter = new DialogsAdapter(data, this, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    private List<DialogsData> filteredDialogList(List<DialogsData> data, String text) {

        List<DialogsData> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        if(data!=null) {
            for(DialogsData vehicle : data) {
                String dialogsName = vehicle.getVehicleName().toLowerCase();
                if(dialogsName.contains(text)) {
                    filteredList.add(vehicle);
                }
            }
        }
        return filteredList;
    }

    @Override
    public void setVehicles(List<DialogsData> data) {
        this.dialogData = data;
        if(dialogData!=null){

            fillAdapter(dialogData);
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    public void closeDialog() {
        onDismiss();
        this.dismiss();

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
//            case R.id.externalconstraint2:
//                closeDialog();
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
}