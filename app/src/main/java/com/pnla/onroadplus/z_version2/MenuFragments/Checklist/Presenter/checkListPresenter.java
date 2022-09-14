package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checkListdata;

import java.util.List;

public interface checkListPresenter {
    void requestChecklist();
    void setChecList(List<checkListdata> data);

    void showpDialog();

    void hideDialog();
}
