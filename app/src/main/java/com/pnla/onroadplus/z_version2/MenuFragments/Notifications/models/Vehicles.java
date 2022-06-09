package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
public class Vehicles extends RealmObject implements Parcelable {

    @PrimaryKey
    private int cve_vehicle;
    private String userVehicle;
    private String vehicle_name;
    private String vehicle_image;
    private double latitude;
    private double longitude;
    private boolean selected;
    private int positionItem;

    public Vehicles() {
    }

    public Vehicles(String userVehicle, String vehicle_name, String vehicle_image, double latitude,
                     double longitude, boolean selected, int cve_vehicle, int positionItem) {
        this.userVehicle = userVehicle;
        this.vehicle_name = vehicle_name;
        this.vehicle_image = vehicle_image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.selected = selected;
        this.cve_vehicle = cve_vehicle;
        this.positionItem = positionItem;
    }

    protected Vehicles(Parcel in) {
        userVehicle = in.readString();
        vehicle_name = in.readString();
        vehicle_image = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        selected = in.readByte() != 0;
        cve_vehicle = in.readInt();
        positionItem = in.readInt();
    }

    public static final Creator<Vehicles> CREATOR = new Creator<Vehicles>() {
        @Override
        public Vehicles createFromParcel(Parcel in) {
            return new Vehicles(in);
        }

        @Override
        public Vehicles[] newArray(int size) {
            return new Vehicles[size];
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
