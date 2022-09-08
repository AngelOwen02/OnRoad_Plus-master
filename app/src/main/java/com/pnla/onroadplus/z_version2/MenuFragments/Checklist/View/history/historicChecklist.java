package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.historicAdapter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.data.HistoricData;

import java.util.List;

import javax.annotation.Nullable;

public class historicChecklist extends AppCompatActivity implements View.OnClickListener, historicChecklitView{

    public static final String TAG = historicChecklist.class.getSimpleName();
    private ImageView historic_checks_back,search_checkList;
    private historicAdapter adapter;
    private RecyclerView rv;
    private FragmentManager manager;
    private FragmentTransaction transaction;
 //   private checkListPresenter presenter;

    private List<HistoricData> historic;
    private historicAdapter historicAdapter;

    /**@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historicchecklist, container, false);

        initView(view);

        return view;
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.fragment_historicchecklist);
        initView();
    }

    private void initView() {
        historic_checks_back= findViewById(R.id.historic_checks_back);
        historic_checks_back.setOnClickListener(this);
        rv= findViewById(R.id.recycler_historicTrips);
        fillAdapter(historic);
    }

    private void fillAdapter(List<HistoricData> historic) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        adapter=new historicAdapter(historic,getApplicationContext());
        rv.setAdapter(adapter);
    }

    /**private void menutransition() {
         manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        CheckListViewImpl checklist = new CheckListViewImpl();
        transaction.replace(R.id.conteinerMainFragments, checklist, CheckListViewImpl.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

    }*/

    /**@Override
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
    }*/

    @Override
    public void setHistoric(List<HistoricData> data) {
        if(historic!=null){
            if(historic==data){
            } else {
                this.historic = data;
                historicAdapter.notifyDataSetChanged();
            }
        } else {
            this.historic = data;
            fillAdapter(historic);
        }
        hideProgressDialog();
    }

    public void goValidation()/** dialogo*/ {
    }

    @Override
    public void requestHistoric() {

    }

    @Override
    public void failureResponse(String message) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.historic_checks_back:
                //menutransition();
                Toast.makeText(getBaseContext(), "back", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
