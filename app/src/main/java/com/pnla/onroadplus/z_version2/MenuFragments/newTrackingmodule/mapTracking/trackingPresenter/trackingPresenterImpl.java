package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingPresenter;

import android.content.Context;

import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingInteractor.trackingInteractor;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingInteractor.trackingInteractorImpl;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingView.TrackingViewImplements;
import com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.trackingView.trackingView;

import java.util.List;

public class trackingPresenterImpl implements trackingPresenter{

    private trackingInteractor interactor;
    private trackingView view;
    public trackingPresenterImpl (Context context)
    {
        interactor=new trackingInteractorImpl(this,context);

    }


    @Override
    public void setView(TrackingViewImplements view) {
        this.view=view;
    }

    @Override
    public void getFullVehicles() {

            interactor.getVehiclesFromAPIS();

    }

    @Override
    public void setVehiclesListToView(List<String> unitList) {

    }

    @Override
    public void failureResponse(String message) {

    }

    /*  @Override
      public void setGrupsToView(List<String> gruoplist) {

      }

      @Override
      public void failureResponse(String message) {

      }
  */
    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
}
