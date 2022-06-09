package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.presenter;

import android.content.Context;
import android.util.Log;

import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.interactor.trackingInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.interactor.trackingInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.view.trackingmapView;

public class trackingPresenterImpl  implements  trackingPresenter{

    private Context context;
    private trackingmapView view;
    private trackingInteractor interactor;

    public trackingPresenterImpl(trackingmapView view,Context context)
    {
        this.view=view;
        this.context=context;
        this.interactor=new trackingInteractorImpl(this,context);
    }


    @Override
    public void requestNoGeo() {
        if(view!=null)
        {
            Log.e("nogeoNewModule","succes view");
            interactor.requestNoGeo();
        }else
        {
            Log.e("nogeoNewModule","view==null");
        }

    }
    @Override
    public void requestGroups() {
        if(view!=null)
        {
            interactor.requestGroups();
        }
    }
}
