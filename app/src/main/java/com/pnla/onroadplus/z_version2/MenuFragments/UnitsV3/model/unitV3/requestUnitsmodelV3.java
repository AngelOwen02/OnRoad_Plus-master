package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class requestUnitsmodelV3 {

    @SerializedName("request")
    private List<dataRequest> request;

    public requestUnitsmodelV3(List<dataRequest> request) {
        super();
        this.request = request;
    }

    public List<dataRequest> getRequest() {
        return request;
    }

    public void setRequest(List<dataRequest> request) {
        this.request = request;
    }
}
