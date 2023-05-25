package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3;

import com.google.gson.annotations.SerializedName;

public class dataRequest {
    @SerializedName("origin_adm")
    private Integer originAdm;
    @SerializedName("cve_vehicle")
    private Integer cveVehicle;

    public dataRequest(Integer originAdm, Integer cveVehicle) {
        super();
        this.originAdm = originAdm;
        this.cveVehicle = cveVehicle;
    }

    public Integer getOriginAdm() {
        return originAdm;
    }

    public void setOriginAdm(Integer originAdm) {
        this.originAdm = originAdm;
    }

    public Integer getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(Integer cveVehicle) {
        this.cveVehicle = cveVehicle;
    }
}
