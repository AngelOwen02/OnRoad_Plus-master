package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class responseUnitsV3 {

    @SerializedName("found")
    private List<dataresponseUnitsV3> found;
    public responseUnitsV3(List<dataresponseUnitsV3> found) {
        super();
        this.found = found;
    }

    public List<dataresponseUnitsV3> getFound() {
        return found;
    }

    public void setFound(List<dataresponseUnitsV3> found) {
        this.found = found;
    }
}
