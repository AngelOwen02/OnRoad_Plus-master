package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View.history.model.Historic;

import java.util.List;

public interface historicChecklitView {

    void requestHistoric();
    void setHistoric(List<Historic> data);
    void failureResponse(String message);
    void showProgressDialog();
    void hideProgressDialog();
}
