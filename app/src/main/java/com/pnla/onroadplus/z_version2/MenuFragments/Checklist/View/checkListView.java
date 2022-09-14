package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.View;

import com.pnla.onroadplus.z_version2.MenuFragments.Checklist.Model.checkListdata;

import java.util.List;

public interface checkListView {
    void setCheckList(List<checkListdata> data);

    void showDialog();
    void hideProgresDialog();
}
