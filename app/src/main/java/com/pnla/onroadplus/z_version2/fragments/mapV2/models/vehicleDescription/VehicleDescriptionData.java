package com.pnla.onroadplus.z_version2.fragments.mapV2.models.vehicleDescription;

public class VehicleDescriptionData {

    private int vehicle_switch;
    private String km_travel;
    private String current_speed;
    private int cve_vehicle;
    private String vehicle_name;
    private String vehicle_image;
    private String desc_brand;
    private String desc_model;
    private String vehicle_year;
    private String vehicle_vin;
    private String vehicle_plate;
    private String policy_number;
    private String insurance_name;
    private String telephone;
    private String last_message;
    private String address;
    private String odometer;
    private String horometer;
    private int satelites;
    private double altitude;

    /**
     * @param vehicle_plate
     * @param vehicle_vin
     * @param vehicle_year
     * @param vehicle_name
     * @param vehicle_image
     * @param desc_model
     * @param cve_vehicle
     * @param desc_brand
     */
    public VehicleDescriptionData(int vehicle_switch, String km_travel, String current_speed,int cve_vehicle, String vehicle_name, String vehicle_image, String desc_brand, String desc_model
            , String vehicle_year, String vehicle_vin, String vehicle_plate
            , String policy_number, String insurance_name, String telephone, String last_message, String address
            , int satelites, double altitude, String odometer, String horometer) {
        super();
        this.vehicle_switch = vehicle_switch;
        this.km_travel = km_travel;
        this.current_speed = current_speed;
        this.cve_vehicle = cve_vehicle;
        this.vehicle_name = vehicle_name;
        this.vehicle_image = vehicle_image;
        this.desc_brand = desc_brand;
        this.desc_model = desc_model;
        this.vehicle_year = vehicle_year;
        this.vehicle_vin = vehicle_vin;
        this.vehicle_plate = vehicle_plate;
        this.policy_number = policy_number;
        this.insurance_name = insurance_name;
        this.telephone = telephone;
        this.last_message = last_message;
        this.address = address;
        this.horometer = horometer;
        this.odometer = odometer;
        this.satelites = satelites;
        this.altitude = altitude;
    }

    public  int getVehicle_switch(){return vehicle_switch;}

    public  void setVehicle_switch(int vehicle_switch){this.vehicle_switch = vehicle_switch;}

    public String getVehicle_km(){return km_travel;}

    public  void setVehicle_km(String km_travel){this.km_travel = km_travel;}

    public String getVehicle_speed(){return current_speed;}

    public void setVehicle_speed(String current_speed){this.current_speed = current_speed;}

    public int getCveVehicle() {
        return cve_vehicle;
    }

    public void setCveVehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getVehicleName() {
        return vehicle_name;
    }

    public void setVehicleName(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicleImage() {
        return vehicle_image;
    }

    public void setVehicleImage(String vehicle_image) {
        this.vehicle_image = vehicle_image;
    }

    public String getDescBrand() {
        return desc_brand;
    }

    public void setDescBrand(String desc_brand) {
        this.desc_brand = desc_brand;
    }

    public String getDescModel() {
        return desc_model;
    }

    public void setDescModel(String desc_model) {
        this.desc_model = desc_model;
    }

    public String getVehicleYear() {
        return vehicle_year;
    }

    public void setVehicleYear(String vehicle_year) {
        this.vehicle_year = vehicle_year;
    }

    public String getVehicleVin() {
        return vehicle_vin;
    }

    public void setVehicleVin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getVehiclePlate() {
        return vehicle_plate;
    }

    public void setVehiclePlate(String vehicle_plate) {
        this.vehicle_plate = vehicle_plate;
    }

    public String getPolicyNumber() {
        return policy_number;
    }

    public void setPolicyNumber(String policy_number) {
        this.policy_number = policy_number;
    }

    public String getInsuranceName() {
        return insurance_name;
    }

    public void setInsuranceName(String insurance_name) {
        this.insurance_name = insurance_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLastMessage() {
        return last_message;
    }

    public void setLastMessage(String last_message) {
        this.last_message = last_message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHorometer() {
        return horometer;
    }

    public void setHorometer(String horometer) {
        this.horometer = horometer;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public int getSatelites() {
        return satelites;
    }

    public void setSatelites(int satelites) {
        this.satelites = satelites;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

}
