package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDSupervisor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zone {

    @SerializedName("cve_employee")
    @Expose
    private Integer cveEmployee;

    public Zone(Integer cveEmployee)
    {
        this.cveEmployee=cveEmployee;
    }


    public Integer getCveEmployee() {
        return cveEmployee;
    }

    public void setCveEmployee(Integer cveEmployee) {
        this.cveEmployee = cveEmployee;
    }
}
