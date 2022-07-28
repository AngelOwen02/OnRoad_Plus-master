package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Adapter.checkListAdapter1;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter.checkListPresenter;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter.checkListPresenterImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.Questions.view.Questions;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.historicChecklist;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.view.UnitsViewImpl;
import com.pnla.onroadplus.z_version2.fragments.contactV2.view.FragmentContactV2;

public class CheckListViewImpl extends Fragment implements View.OnClickListener, checkListView{
    public static final String TAG = FragmentContactV2.class.getSimpleName();
    private ImageView historic_checks,search_checkList;
    private ConstraintLayout contrainButton;
    private checkListAdapter1 adapter;
    private RecyclerView rv;
    private checkListPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);

        initContactView(view);

        return view;
    }

    private void initContactView(View view) {
        contrainButton=view.findViewById(R.id.contrainButton);
        contrainButton.setOnClickListener(this);
        rv=view.findViewById(R.id.recycler_checkList);
        presenter=new checkListPresenterImpl(this,getContext());
        presenter.requestChecklist();

    }
    private void fillAdapter()
    {
      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
      rv.setLayoutManager(layoutManager);
      adapter=new checkListAdapter1(this,getContext());
      rv.setAdapter(adapter);
    }
    private void menutransition()//aqui va el historico
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        historicChecklist historic = new historicChecklist();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, historic, historicChecklist.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }
    private void questionFragment()
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Questions questions = new Questions();//transaction.addToBackStack(UnitsViewImpl.TAG);
        transaction.replace(R.id.conteinerMainFragments, questions, Questions.TAG).commit();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    }
    public void goquestionaryFragment() {
        //Toast.makeText(getContext(), "ir al cuestionario", Toast.LENGTH_SHORT).show();
        questionFragment();
    }
    @Override
    public void setCheckList() {
        fillAdapter();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contrainButton:
                menutransition();
                break;
        }
    }



}
