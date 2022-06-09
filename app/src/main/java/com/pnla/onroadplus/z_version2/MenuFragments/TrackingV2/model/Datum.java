package com.pnla.onroadplus.z_version2.MenuFragments.TrackingV2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {


    @SerializedName("cve_object")
    @Expose
    private String cveObject;
    @SerializedName("obj_name")
    @Expose
    private String objName;
    @SerializedName("access_flag")
    @Expose
    private boolean accessFlag;

    public String getCveObject() {
        return cveObject;
    }

    public void setCveObject(String cveObject) {
        this.cveObject = cveObject;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public boolean getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(boolean accessFlag) {
        this.accessFlag = accessFlag;
    }


}