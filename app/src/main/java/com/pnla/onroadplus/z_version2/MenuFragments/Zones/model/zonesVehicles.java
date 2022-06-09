package com.pnla.onroadplus.z_version2.MenuFragments.Zones.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class zonesVehicles {

    @SerializedName("cve_vehicle")
    @Expose
    private int cveVehicle;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;

    public zonesVehicles(int cveVehicle,String vehicleName, String employeeName)
    {
        this.cveVehicle=cveVehicle;
        this.vehicleName=vehicleName;
        this.employeeName=employeeName;
    }

    public int getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(int cveVehicle) {
        this.cveVehicle = cveVehicle;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

}