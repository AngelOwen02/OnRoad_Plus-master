package com.pnla.onroadplus.z_version2.MenuFragments.ZonesDrivers.model.checkAsigment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class asignVehicles {


    @SerializedName("cve_vehicle")
    @Expose
    private int cveVehicle;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName( "cve_employee")
    @Expose
    private String cveEmploye;

    public asignVehicles(int cveVehicle,String vehicleName, String employeeName,String cveEmploye)
    {
        this.cveVehicle=cveVehicle;
        this.vehicleName=vehicleName;
        this.employeeName=employeeName;
       this.cveEmploye=cveEmploye;
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
    public String getCveEmploye() {
        return cveEmploye;
    }

    public void setCveEmploye(String cveEmploye) {
        this.cveEmploye = cveEmploye;
    }
}
