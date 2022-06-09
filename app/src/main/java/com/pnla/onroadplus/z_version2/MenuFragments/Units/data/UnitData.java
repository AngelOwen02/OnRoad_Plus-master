package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import com.google.gson.annotations.SerializedName;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.List;

public class UnitData {

    @SerializedName("vehicles")
    private List<Unit> unitList = null;

    public UnitData(List<Unit> unitList) {
        super();
        this.unitList = unitList;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }
}
