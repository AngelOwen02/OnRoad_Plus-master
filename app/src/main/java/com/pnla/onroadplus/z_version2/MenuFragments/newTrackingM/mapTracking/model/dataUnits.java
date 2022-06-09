package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataUnits {


    @SerializedName("vehicles")
    private List<newUnits> unitList = null;

    public dataUnits(List<newUnits> unitList) {
        super();
        this.unitList = unitList;
    }

    public List<newUnits> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<newUnits> unitList) {
        this.unitList = unitList;
    }
}
