package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.historicAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.Historic;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.presenter.HistoricCheckListPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.presenter.HistoricCheckListPresenterImpl;
import com.pnla.onroadplus.z_version2.SplashScreenActivity;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.util.ArrayList;
import java.util.List;

public class historicChecklist extends Fragment implements View.OnClickListener,historicChecklitView{

    public static final String TAG = historicChecklist.class.getSimpleName();
    private ImageView historic_checks_back,search_checkList;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private historicAdapter adapter;
    private RecyclerView rv;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private CardView searchViewContainer;
    private SearchView searchViewa;
    private List<Historic> soportes=new ArrayList<>();
    private HistoricCheckListPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historicchecklist, container, false);

        initView(view);



        return view;
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.units_historic_view_progress_bar);
        progressDialog = new ProgressDialog(getContext());
        historic_checks_back = view.findViewById(R.id.historic_checks_back);
        historic_checks_back.setOnClickListener(this);
        rv = view.findViewById(R.id.recycler_historicTrips);

        search_checkList = view.findViewById(R.id.search_checkList);
        search_checkList.setOnClickListener(this);
        searchViewContainer = view.findViewById(R.id.units_search_view_container_us);
        searchViewa = (SearchView) view.findViewById(R.id.search_view_units_us);
        initPresenter();


    }

    private void initPresenter() {
        presenter = new HistoricCheckListPresenterImpl(getContext());
        presenter.setView(this);
        presenter.requestHistoric();
    }

    @Override
    public void setHistoric(List<Historic> data) {
        this.soportes = data;
        if(soportes!=null) {
            fillAdapter(soportes);
            searchViewa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    List<Historic> filterUnitList = filteredUnits(soportes, newText);
                    adapter.setFilter(filterUnitList);
                    return false;
                }
            });
        }
    }

    private List<Historic> filteredUnits(List<Historic> data, String text) {

        List<Historic> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        if(data!=null) {
            for(Historic vehicle : data) {
                if(vehicle!=null) {
                    String vehicleName = vehicle.getVehicleName().toLowerCase();
                    if (vehicleName.contains(text)) {
                        filteredList.add(vehicle);
                    }
                }
            }
        }
        return filteredList;
    }

    @Override
    public void failureResponse(String message) {
        if(message!=null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgressDialog() {
        //Aqui va el metodo para mostrar el ProgressDialog
        progressDialog.setMessage("Cargando Unidades");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        //Aqui va el metodo para ocultar el ProgressDialog
        progressDialog.dismiss();
    }

    private void fillAdapter(List<Historic> soportes) {
        adapter = new historicAdapter(soportes, this, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    private void menutransition() {
        manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        CheckListViewImpl checklist = new CheckListViewImpl();
        transaction.replace(R.id.conteinerMainFragments, checklist, CheckListViewImpl.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    @Override
    //Pressed return button - returns to the results menu
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    menutransition();

                    return true;
                }
                return false;
            }
        });
    }

    public void goValidation() /** dialogo*/ {

    }

    @Override
    public void requestHistoric() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.historic_checks_back:
                menutransition();
                break;

            case R.id.search_checkList:
                //searchView.setVisibility(View.VISIBLE);
                if(searchViewContainer.getVisibility()==View.VISIBLE) {
                    searchViewContainer.setVisibility(View.GONE);
                }
                else{
                    searchViewContainer.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}