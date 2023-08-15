package com.pnla.onroadplus.z_version2.MenuFragments.UnitsV3.model.unitV3;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataresponseUnitsV3 {
    @SerializedName("out_vehicle_plate")
    private String outVehiclePlate;
    @SerializedName("out_heading")
    private String outHeading;
    @SerializedName("out_nick_name")
    private String outNickName;
    @SerializedName("out_origin_adm_employee")
    private Integer outOriginAdmEmployee;
    @SerializedName("out_vehicle_image")
    private String outVehicleImage;
    @SerializedName("out_cve_vehicle")
    private Integer outCveVehicle;
    @SerializedName("out_cve_employee")
    private Integer outCveEmployee;
    @SerializedName("out_vehicle_name")
    private String outVehicleName;
    @SerializedName("out_send_time")
    private String outSendTime;
    @SerializedName("out_tail_positions")
    private List<Object> outTailPositions;
    @SerializedName("out_longitude")
    private Float outLongitude;
    @SerializedName("out_vehicle_driver_mode")
    private String outVehicleDriverMode;
    @SerializedName("out_vehicle_status")
    private Integer outVehicleStatus;
    @SerializedName("out_msg_type")
    private String outMsgType;
    @SerializedName("out_mileage")
    private Double outMileage;
    @SerializedName("out_latitude")
    private Float outLatitude;
    @SerializedName("location")
    private String location;
    @SerializedName("out_speed")
    private String outSpeed;
    @SerializedName("out_origin_adm")
    private Integer outOriginAdm;
    @SerializedName("out_hour_meter_count")
    private String outHourMeterCount;
    @SerializedName("maintenance")
    private String maintenance;
    @SerializedName("out_employee_name")
    private String driver = null;
    public dataresponseUnitsV3(String outVehiclePlate, String outHeading, String outNickName, Integer outOriginAdmEmployee,
                               String outVehicleImage, Integer outCveVehicle, Integer outCveEmployee, String outVehicleName,
                               String outSendTime, List<Object> outTailPositions, Float outLongitude, String outVehicleDriverMode,
                               Integer outVehicleStatus, String outMsgType, Double outMileage, Float outLatitude, String location,
                               String outSpeed, Integer outOriginAdm, String outHourMeterCount, String maintenance,String driver) {
        super();
        this.outVehiclePlate = outVehiclePlate;
        this.outHeading = outHeading;
        this.outNickName = outNickName;
        this.outOriginAdmEmployee = outOriginAdmEmployee;
        this.outVehicleImage = outVehicleImage;
        this.outCveVehicle = outCveVehicle;
        this.outCveEmployee = outCveEmployee;
        this.outVehicleName = outVehicleName;
        this.outSendTime = outSendTime;
        this.outTailPositions = outTailPositions;
        this.outLongitude = outLongitude;
        this.outVehicleDriverMode = outVehicleDriverMode;
        this.outVehicleStatus = outVehicleStatus;
        this.outMsgType = outMsgType;
        this.outMileage = outMileage;
        this.outLatitude = outLatitude;
        this.location = location;
        this.outSpeed = outSpeed;
        this.outOriginAdm = outOriginAdm;
        this.outHourMeterCount = outHourMeterCount;
        this.maintenance = maintenance;
        this.driver= driver;
    }

    public String getOutVehiclePlate() {
        return outVehiclePlate;
    }

    public void setOutVehiclePlate(String outVehiclePlate) {
        this.outVehiclePlate = outVehiclePlate;
    }

    public String getOutHeading() {
        return outHeading;
    }

    public void setOutHeading(String outHeading) {
        this.outHeading = outHeading;
    }

    public String getOutNickName() {
        return outNickName;
    }

    public void setOutNickName(String outNickName) {
        this.outNickName = outNickName;
    }

    public Integer getOutOriginAdmEmployee() {
        return outOriginAdmEmployee;
    }

    public void setOutOriginAdmEmployee(Integer outOriginAdmEmployee) {
        this.outOriginAdmEmployee = outOriginAdmEmployee;
    }

    public String getOutVehicleImage() {
        return outVehicleImage;
    }

    public void setOutVehicleImage(String outVehicleImage) {
        this.outVehicleImage = outVehicleImage;
    }

    public Integer getOutCveVehicle() {
        return outCveVehicle;
    }

    public void setOutCveVehicle(Integer outCveVehicle) {
        this.outCveVehicle = outCveVehicle;
    }

    public Integer getOutCveEmployee() {
        return outCveEmployee;
    }

    public void setOutCveEmployee(Integer outCveEmployee) {
        this.outCveEmployee = outCveEmployee;
    }

    public String getOutVehicleName() {
        return outVehicleName;
    }

    public void setOutVehicleName(String outVehicleName) {
        this.outVehicleName = outVehicleName;
    }

    public String getOutSendTime() {
        return outSendTime;
    }

    public void setOutSendTime(String outSendTime) {
        this.outSendTime = outSendTime;
    }

    public List<Object> getOutTailPositions() {
        return outTailPositions;
    }

    public void setOutTailPositions(List<Object> outTailPositions) {
        this.outTailPositions = outTailPositions;
    }

    public Float getOutLongitude() {
        return outLongitude;
    }

    public void setOutLongitude(Float outLongitude) {
        this.outLongitude = outLongitude;
    }

    public String getOutVehicleDriverMode() {
        return outVehicleDriverMode;
    }

    public void setOutVehicleDriverMode(String outVehicleDriverMode) {
        this.outVehicleDriverMode = outVehicleDriverMode;
    }

    public Integer getOutVehicleStatus() {
        return outVehicleStatus;
    }

    public void setOutVehicleStatus(Integer outVehicleStatus) {
        this.outVehicleStatus = outVehicleStatus;
    }

    public String getOutMsgType() {
        return outMsgType;
    }

    public void setOutMsgType(String outMsgType) {
        this.outMsgType = outMsgType;
    }

    public Double getOutMileage() {
        return outMileage;
    }

    public void setOutMileage(Double outMileage) {
        this.outMileage = outMileage;
    }

    public Float getOutLatitude() {
        return outLatitude;
    }

    public void setOutLatitude(Float outLatitude) {
        this.outLatitude = outLatitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOutSpeed() {
        return outSpeed;
    }

    public void setOutSpeed(String outSpeed) {
        this.outSpeed = outSpeed;
    }

    public Integer getOutOriginAdm() {
        return outOriginAdm;
    }

    public void setOutOriginAdm(Integer outOriginAdm) {
        this.outOriginAdm = outOriginAdm;
    }

    public String getOutHourMeterCount() {
        return outHourMeterCount;
    }

    public void setOutHourMeterCount(String outHourMeterCount) {
        this.outHourMeterCount = outHourMeterCount;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }
    // return vehicleName != null ? vehicleName : "";

    public String getDriver() {
        if (driver == null || driver.equals("driver not found dk:null")) {
            return "";
        } else {
            return driver;
        }
    }

    public void setDriver(String driver) {
        if (driver == null || driver.equals("driver not found dk:null")) {
            this.driver = "";
        } else {
            this.driver = driver;
        }
    }
}
