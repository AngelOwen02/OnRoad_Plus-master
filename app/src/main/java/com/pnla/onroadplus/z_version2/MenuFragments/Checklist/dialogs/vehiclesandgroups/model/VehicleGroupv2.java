package com.pnla.onroadplus.z_version2.MenuFragments.Checklist.dialogs.vehiclesandgroups.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleGroupv2 {
    @SerializedName("cve_vehicle_group")
    private Integer cveVehicleGroup;
    @SerializedName("vehicle_group")
    private String vehicleGroup;
    @SerializedName("desc_vehicle_group")
    private String descVehicleGroup;
    @SerializedName("vehicles")
    private List<Vehiclec2> vehicles;

    public VehicleGroupv2(Integer cveVehicleGroup, String vehicleGroup, String descVehicleGroup, List<Vehiclec2> vehicles) {
        super();
        this.cveVehicleGroup = cveVehicleGroup;
        this.vehicleGroup = vehicleGroup;
        this.descVehicleGroup = descVehicleGroup;
        this.vehicles = vehicles;
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

    public List<Vehiclec2> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehiclec2> vehicles) {
        this.vehicles = vehicles;
    }

}
