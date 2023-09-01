package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.TripsByTimeV3;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datapos {


    @SerializedName("vehicle_name")
    private String vehicleName;
    @SerializedName("company_name")
    private String companyName;
    @SerializedName("position")
    private List<PositionV3> position;

    public Datapos(String vehicleName, String companyName, List<PositionV3> position) {
        super();
        this.vehicleName = vehicleName;
        this.companyName = companyName;
        this.position = position;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<PositionV3> getPosition() {
        return position;
    }

    public void setPosition(List<PositionV3> position) {
        this.position = position;
    }
}
