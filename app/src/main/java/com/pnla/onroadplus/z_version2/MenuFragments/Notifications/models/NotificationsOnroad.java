package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NotificationsOnroad  implements Parcelable{
    private String imageVehicle;
    private String vehicleName;
    private String notification;
    private String dateNotification;
    private int cveVehicle;
    private double latitude;
    private double longitude;
    public NotificationsOnroad(){

    }

    public NotificationsOnroad(String imageVehicle, String vehicleName, String notification, String dateNotification, int cveVehicle,
                               double latitude, double longitude) {
        this.imageVehicle = imageVehicle;
        this.vehicleName = vehicleName;
        this.notification = notification;
        this.dateNotification = dateNotification;
        this.cveVehicle = cveVehicle;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected NotificationsOnroad(Parcel in) {
        imageVehicle = in.readString();
        vehicleName = in.readString();
        notification = in.readString();
        dateNotification = in.readString();
        cveVehicle = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Parcelable.Creator<NotificationsOnroad> CREATOR = new Parcelable.Creator<NotificationsOnroad>() {
        @Override
        public NotificationsOnroad createFromParcel(Parcel in) {
            return new NotificationsOnroad(in);
        }

        @Override
        public NotificationsOnroad[] newArray(int size) {
            return new NotificationsOnroad[size];
        }
    };

    public String getImageVehicle() {
        return imageVehicle;
    }

    public void setImageVehicle(String imageVehicle) {
        this.imageVehicle = imageVehicle;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(String dateNotification) {
        this.dateNotification = dateNotification;
    }

    public int getCveVehicle() {
        return cveVehicle;
    }

    public void setCveVehicle(int cveVehicle) {
        this.cveVehicle = cveVehicle;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageVehicle);
        dest.writeString(vehicleName);
        dest.writeString(notification);
        dest.writeString(dateNotification);
        dest.writeInt(cveVehicle);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
