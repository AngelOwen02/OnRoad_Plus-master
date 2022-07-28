package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.checkListAdapter1;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.checkListAdapter2;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter.checkListPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.CheckListViewImpl;
import com.pnla.onroadplus.z_version2.fragments.contactV2.view.FragmentContactV2;

public class historicChecklist  extends Fragment implements View.OnClickListener,historicChecklitView{
    public static final String TAG = historicChecklist.class.getSimpleName();
    private ImageView historic_checks_back,search_checkList;
    private checkListAdapter2 adapter;
    private RecyclerView rv;
    private  FragmentManager manager;
    private FragmentTransaction transaction;
 //   private checkListPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historicchecklist, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        historic_checks_back=view.findViewById(R.id.historic_checks_back);
        historic_checks_back.setOnClickListener(this);
        rv=view.findViewById(R.id.recycler_historicTrips);
        fillAdapter();
    }
    private void fillAdapter()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        adapter=new checkListAdapter2(this,getContext());
        rv.setAdapter(adapter);
    }
    private void menutransition()
    {
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
    public void goValidation()/** dialogo*/
    {
    }
    @Override
    public void requestHistoric() {

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.historic_checks_back:
                menutransition();
               //Toast.makeText(getContext(), "back", Toast.LENGTH_SHORT).show();

                break;
        }
    }



}
