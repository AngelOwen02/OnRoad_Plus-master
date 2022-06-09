package com.pnla.onroadplus.z_version2.MenuFragments.geocercas.presenter;

import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.Data;
import com.pnla.onroadplus.z_version2.MenuFragments.geocercas.model.Geofences;

import java.util.List;

public interface geoCercasPresenter {
    void getGeoCercas();
    void showProgressDialog();
    void hideProgressDialog();
    void setGeoCercass(List<Geofences> geoCercas);
    void setDataofGeozones(List<Data> data);
}
