package com.pnla.onroadplus.z_version2.MenuFragments.Tracking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.pnla.onroadplus.z_version2.realmOnRoad.BaseRealmApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class VehicleTracking extends RealmObject implements Parcelable {


    @PrimaryKey
    private int id;
    private int cve_vehicle;
    private String userVehicle;
    private String vehicle_name;
    private String vehicle_image;
    private double latitude;
    private double longitude;
    private boolean selected;
    private int positionItem;
    private int vehicle_switch;


    public VehicleTracking() {
    }

    public VehicleTracking(String userVehicle, String vehicle_name, String vehicle_image, double latitude,
                           double longitude, boolean selected, int cve_vehicle, int positionItem, int vehicle_switch) {
        this.id = BaseRealmApplication.VEHICLE_ID.incrementAndGet();
        this.userVehicle = userVehicle;
        this.vehicle_name = vehicle_name;
        this.vehicle_image = vehicle_image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.selected = selected;
        this.cve_vehicle = cve_vehicle;
        this.positionItem = positionItem;
        this.vehicle_switch = vehicle_switch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected VehicleTracking(Parcel in) {
        userVehicle = in.readString();
        vehicle_name = in.readString();
        vehicle_image = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        selected = in.readByte() != 0;
        cve_vehicle = in.readInt();
        positionItem = in.readInt();
    }

    public static final Creator<VehicleTracking> CREATOR = new Creator<VehicleTracking>() {
        @Override
        public VehicleTracking createFromParcel(Parcel in) {
            return new VehicleTracking(in);
        }

        @Override
        public VehicleTracking[] newArray(int size) {
            return new VehicleTracking[size];
        }
    };

    public String getUserVehicle() {
        return userVehicle;
    }

    public void setUserVehicle(String userVehicle) {
        this.userVehicle = userVehicle;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getVehicleSwitch() {
        return vehicle_switch;
    }

    public void setVehicleSwitch(int vehicle_switch) {
        this.vehicle_switch = vehicle_switch;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getCveVehicle() {
        return cve_vehicle;
    }

    public void setCveVehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public int getPositionItem() {
        return positionItem;
    }

    public void setPositionItem(int positionItem) {
        this.positionItem = positionItem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userVehicle);
        dest.writeString(vehicle_name);
        dest.writeString(vehicle_image);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeByte((byte) (selected ? 1 : 0));
        dest.writeInt(cve_vehicle);
        dest.writeInt(positionItem);
    }
}
