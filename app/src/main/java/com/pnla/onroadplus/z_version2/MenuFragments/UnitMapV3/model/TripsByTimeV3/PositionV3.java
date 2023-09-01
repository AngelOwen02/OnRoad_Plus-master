package com.pnla.onroadplus.z_version2.MenuFragments.UnitMapV3.model.TripsByTimeV3;

import com.google.gson.annotations.SerializedName;

public class PositionV3 {


    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("orden")
    private Integer orden;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("send_time")
    private String sendTime;
    @SerializedName("georeference")
    private String georeference;
    @SerializedName("speed")
    private String speed;
    @SerializedName("hour_meter_count")
    private String hourMeterCount;
    @SerializedName("mileage")
    private Double mileage;

    public PositionV3(Double latitude, Integer orden, Double longitude, String sendTime, String georeference, String speed, String hourMeterCount, Double mileage) {
        super();
        this.latitude = latitude;
        this.orden = orden;
        this.longitude = longitude;
        this.sendTime = sendTime;
        this.georeference = georeference;
        this.speed = speed;
        this.hourMeterCount = hourMeterCount;
        this.mileage = mileage;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getGeoreference() {
        return georeference;
    }

    public void setGeoreference(String georeference) {
        this.georeference = georeference;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getHourMeterCount() {
        return hourMeterCount;
    }

    public void setHourMeterCount(String hourMeterCount) {
        this.hourMeterCount = hourMeterCount;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }
}
