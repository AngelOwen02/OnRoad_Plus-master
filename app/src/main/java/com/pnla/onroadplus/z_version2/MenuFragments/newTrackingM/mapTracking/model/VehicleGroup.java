package com.pnla.onroadplus.z_version2.MenuFragments.newTrackingM.mapTracking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleGroup {

    @SerializedName("cve_vehicle_group")
    @Expose
    private Integer cveVehicleGroup;
    @SerializedName("vehicle_group")
    @Expose
    private String vehicleGroup;
    @SerializedName("desc_vehicle_group")
    @Expose
    private String descVehicleGroup;
    @SerializedName("vehicles")
    @Expose
    private List<Vehicle> vehicles = null;

    public VehicleGroup (Integer cveVehicleGroup, String vehicleGroup,String descVehicleGroup,List<Vehicle> vehicles)
    {
        this.cveVehicleGroup=cveVehicleGroup;
        this.vehicleGroup=vehicleGroup;
        this.descVehicleGroup=descVehicleGroup;
        this.vehicles=vehicles;
    }

    public Integer getCveVehicleGroup() {
        return cveVehicleGroup;
    }

    public void setCveVehicleGroup(Integer cveVehicleGroup) {
        this.cveVehicleGroup = cveVehicleGroup;
    }

    public String getVehicleGroup() {
        return vehicleGroup;
    }

    public void setVehicleGroup(String vehicleGroup) {
        this.vehicleGroup = vehicleGroup;
    }

    public String getDescVehicleGroup() {
        return descVehicleGroup;
    }

    public void setDescVehicleGroup(String descVehicleGroup) {
        this.descVehicleGroup = descVehicleGroup;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
