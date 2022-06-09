package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.view;

import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.Data;

import java.util.List;

public interface geoCercasView {

    void showProgressDialog();

    void hideProgressDialog();

    void data(List<Data> data);
}
