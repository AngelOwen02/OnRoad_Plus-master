package com.pnla.onroadplus.z_version2.MenuFragments.Units.data;

public class SupportUnitData {

    private String cve_vehicle;
    private String vehicle_name;
    private String cve_layer;
    private String desc_layer;
    private String help_state;
    private String percent_complete;
    private String percent_to_help;
    private String status;

    public SupportUnitData (String cve_vehicle, String vehicle_name, String cve_layer, String desc_layer, String help_state, String percent_complete, String percent_to_help, String status)
    {
        super();
        this.cve_vehicle=cve_vehicle;
        this.vehicle_name=vehicle_name;
        this.cve_layer=cve_layer;
        this.desc_layer=desc_layer;
        this.help_state=help_state;
        this.percent_complete=percent_complete;
        this.percent_to_help=percent_to_help;
        this.status=status;
    }

    public String getCve_vehicle(){
        return cve_vehicle;
    }

    public void setCve_vehicle(String cve_vehicle){
        this.cve_vehicle = cve_vehicle;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getDesc_layer(){
        return desc_layer;
    }

    public void setDesc_layer(String desc_layer){
        this.desc_layer = desc_layer;
    }

    public String getHelp_state(){
        return help_state;
    }

    public void setHelp_state(String help_state){
        this.help_state = help_state;
    }

    public String getPercent_complete(){
        return percent_complete;
    }

    public void setPercent_complete(String percent_complete){
        this.percent_complete = percent_complete;
    }

    public String getPercent_to_help(){
        return percent_to_help;
    }

    public void setPercent_to_help(String percent_to_help){
        this.percent_to_help = percent_to_help;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
