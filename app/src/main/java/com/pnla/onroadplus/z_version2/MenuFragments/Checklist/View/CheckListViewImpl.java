package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.checkListAdapter1;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Interactor.checkListInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Interactor.checkListInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checkListdata;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.dataChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.dataChecklistHistoric;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter.checkListPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter.checkListPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.Questions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.historicChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.interactor.DialogsInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.interactor.DialogsInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.menuDinamic.view.menuViewImpl;
import com.pnla.onroadplus.z_version2.fragments.contactV2.view.FragmentContactV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehicles.view.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CheckListViewImpl extends Fragment implements View.OnClickListener, checkListView {

    public static final String TAG = FragmentContactV2.class.getSimpleName();
    private ImageView historic_checks, search_checkList, vehicle_checklist;
    private ConstraintLayout contrainButton;
    private checkListAdapter1 adapter;
    private RecyclerView rv;
    private CardView searchViewContainer;
    private SearchView searchViewa;
    private checkListPresenter presenter;
    public static List<List<dataChecklist>> fulChecklist= null;
    private List<checkListdata> checklistData;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);

        initContactView(view);
        //updateS();

        return view;
    }

    private void initContactView(View view) {
        contrainButton=view.findViewById(R.id.contrainButton);
        contrainButton.setOnClickListener(this);

        rv=view.findViewById(R.id.recycler_checkList);
        presenter=new checkListPresenterImpl(this,getContext());
        progressDialog = new ProgressDialog(getActivity());
        presenter.requestChecklist();

        search_checkList = view.findViewById(R.id.search_checkList);
        search_checkList.setOnClickListener(this);

        searchViewContainer = view.findViewById(R.id.cheklist_search_view_container_us);
        searchViewa = (SearchView) view.findViewById(R.id.search_view_checkilist_us);
        searchViewa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<checkListdata> filterCheckList = filteredCheckList(checklistData, newText);
                adapter.setFilter(filterCheckList);
                return false;
            }
        });


        vehicle_checklist = view.findViewById(R.id.ic_vehicle_checklist_rv);

        //Cambiar color del carro
        if(menuViewImpl.selectedVehicle == false) {
            vehicle_checklist.setColorFilter(ContextCompat.getColor(getContext(), R.color.grayUI), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            vehicle_checklist.setColorFilter(ContextCompat.getColor(getContext(), R.color.blueCarChecklist), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        //Al presionar el carro
        vehicle_checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Si funciona", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                DialogsViewImpl externalGPSDialog = new DialogsViewImpl();

                externalGPSDialog.show(getActivity().getSupportFragmentManager(), DialogsViewImpl.TAG);
                fm.executePendingTransactions();


                //traficDialog trafigAlert = new traficDialog();
                //trafigAlert.show(getActivity().getSupportFragmentManager(), traficDialog.TAG);
                //fm.executePendingTransactions();
                externalGPSDialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        
                    }
                });
            }
        });

        check_datafromsshared();
    }

    private void check_datafromsshared() {
        SharedPreferences npreferences = getContext().getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String checklist = npreferences.getString(GeneralConstantsV2.CHECKLIST_DATA, null);
        Log.e("fulChecklistF", "valor shared " + checklist);
        if (checklist != null) {
            Type resultListType = new TypeToken<ArrayList<ArrayList<dataChecklist>>>(){}.getType();
            List<dataChecklist> resultObj = new Gson().fromJson(checklist, resultListType);
            Log.e("fulChecklistF",""+  resultObj);
            fulChecklist=new ArrayList<>();
            fulChecklist.add(resultObj);
            //TODO ver por seccion llos datos activos o en 0 o las fotos && llamar on activity result
        }
    }

    private void fillAdapter(List<checkListdata> data) {
        adapter = new checkListAdapter1(data,this,getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    private void menutransition() { //aqui va el historico
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        historicChecklist historic = new historicChecklist();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, historic, historicChecklist.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }

    public void goquestionaryFragment(Integer cveTripMgmSection, Integer tripMgmSection, boolean aprobador) {
        //Toast.makeText(getContext(), "ir al cuestionario", Toast.LENGTH_SHORT).show();
        questionFragment(cveTripMgmSection, tripMgmSection,aprobador);
    }

    private void questionFragment(Integer tripMgmSection, Integer cveTripMgmSection, boolean aprobador) {
        Bundle bundle = new Bundle();
        bundle.putInt("cveTripMgmSection",cveTripMgmSection); // Put anything what you want
        bundle.putInt("Section",tripMgmSection);
        bundle.putBoolean("aprobador",aprobador);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Questions questions = new Questions();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, questions, Questions.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        questions.setArguments(bundle);
    }

    private List<checkListdata> filteredCheckList(List<checkListdata> data, String text) {

        List<checkListdata> filteredList = new ArrayList<>();
        text = text.toLowerCase();
        if(data!=null) {
            for(checkListdata vehicle : data) {
                String checklistName = vehicle.getDescTripMgmSection().toLowerCase();
                if(checklistName.contains(text)) {
                    filteredList.add(vehicle);
                }
            }
        }
        return filteredList;
    }

    public void updateS() {

    }

    @Override
    public void setCheckList(List<checkListdata> data) {

        if(checklistData!=null) {
            if(checklistData==data) {
            } else {
                this.checklistData = data;
                adapter.notifyDataSetChanged();
            }
        } else {
            this.checklistData = data;
            fillAdapter(checklistData);
        }
        //fillAdapter(data);
    }

    @Override
    public void showDialog() {
        progressDialog.setMessage("Cargando Checklist");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgresDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contrainButton:
                menutransition();
                break;

            case R.id.search_checkList:
                //Toast.makeText(getContext(), "Si sirve", Toast.LENGTH_SHORT).show();
                if(searchViewContainer.getVisibility()==View.VISIBLE) {
                    searchViewContainer.setVisibility(View.GONE);
                }
                else {
                    searchViewContainer.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
