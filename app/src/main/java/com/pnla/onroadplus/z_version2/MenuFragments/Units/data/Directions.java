package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Directions {
    @SerializedName("vehicles")
    private List<georeference> unitList = null;

    public Directions(List<georeference> unitList) {
        super();
        this.unitList = unitList;
    }

    public List<georeference> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<georeference> unitList) {
        this.unitList = unitList;
    }
}
