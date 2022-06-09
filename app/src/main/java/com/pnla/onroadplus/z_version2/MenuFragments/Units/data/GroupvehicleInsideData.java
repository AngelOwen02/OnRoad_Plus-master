package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class GroupvehicleInsideData {


    private String cve_vehicle;
    private String vehicle_name;

    public GroupvehicleInsideData (String cve_vehicle,String vehicle_name)
    {
        super();
        this.cve_vehicle=cve_vehicle;
        this.vehicle_name=vehicle_name;
    }



    public String getCve_vehicle() {
        return cve_vehicle;
    }

    public void setCve_vehicle(String cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }


}
