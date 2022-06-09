package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingmodule.mapTracking.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class lastData {

    @SerializedName("data")
    private List<lastlatlong> unitList = null;

    public lastData(List<lastlatlong> unitList){
        super();
        this.unitList=unitList;
    }



    public List<lastlatlong> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<lastlatlong> unitList) {
        this.unitList = unitList;
    }


}
