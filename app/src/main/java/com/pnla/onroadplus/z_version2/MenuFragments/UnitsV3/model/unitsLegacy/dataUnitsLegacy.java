package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitsLegacy;

import com.google.gson.annotations.SerializedName;
import com.pnla.onroadplus.z_version2.MenuFragments.Units.model.Unit;

import java.util.List;

public class dataUnitsLegacy {
    @SerializedName("vehicles")
    private List<Unitlegacy> unitList = null;

    public dataUnitsLegacy(List<Unitlegacy> unitList) {
        super();
        this.unitList = unitList;
    }

    public List<Unitlegacy> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unitlegacy> unitList) {
        this.unitList = unitList;
    }
}
