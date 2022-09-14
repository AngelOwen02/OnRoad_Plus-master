package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.historicChecklitView;
import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.Historic;

import java.util.List;

public interface HistoricCheckListPresenter {

    void setView(historicChecklitView view);
    void setHistoric(List<Historic> data);
    void requestHistoric();
    void failureResponse(String message);

    void showProgressDialog();

    void hideProgressDialog();
}
