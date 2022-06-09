package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.asignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class vehicleZones {


    @SerializedName("cve_driver")
    @Expose
    private int cveDriver;
    @SerializedName("cve_vehicle")
    @Expose
    private int cveVehicle;
    public vehicleZones(int cveDriver,int cveVehicle)
    {
        this.cveDriver=cveDriver;
        this.cveVehicle=cveVehicle;
    }

    public int getCveDriver() {
        return cveDriver;
    }

    public void setCveDriver(int cveDriver) {
        this.cveDriver = cveDriver;
    }

    public int getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(int cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

}
