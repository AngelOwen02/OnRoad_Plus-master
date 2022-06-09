package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

import com.google.gson.annotations.SerializedName;

public class georeference {

    private String georeference;
    @SerializedName("cve_vehicle")
    private int cveVehicle;  @SerializedName("vehicle_name")
    private String vehicleName;
    public georeference(String georeference,int cveVehicle,String vehicleName)

    {
        this.georeference=georeference;
        this.cveVehicle=cveVehicle;
        this.vehicleName = vehicleName;
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

    public String getGeoreference() {
        return georeference;
    }

    public void setGeoreference(String georeference) {
        this.georeference = georeference;
    }

}
