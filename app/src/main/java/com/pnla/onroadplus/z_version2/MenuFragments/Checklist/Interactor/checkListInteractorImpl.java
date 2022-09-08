package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter.checkListPresenter;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

public class checkListInteractorImpl implements checkListInteractor{


    private Context context;
    private checkListPresenter presenter;
 //   private checkListService service;
    public checkListInteractorImpl( checkListPresenter presenter,Context context)
    {
        this.presenter=presenter;
        this.context=context;

    }

    @Override
    public void requestCheckList() {
        SharedPreferences preferences = context.getSharedPreferences(GeneralConstantsV2.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String token = preferences.getString(GeneralConstantsV2.TOKEN_PREFERENCES, null);
        if(token!=null)
        {
            Log.e("questionsConf","token: "+token);
            presenter.setChecList();
        }
    }
}
