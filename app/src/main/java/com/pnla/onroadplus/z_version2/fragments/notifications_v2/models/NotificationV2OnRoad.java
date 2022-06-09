package com.pnla.onroadplus.z_version2.fragments.notifications_v2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NotificationV2OnRoad implements Parcelable {
    private String imageVehicle;
    private String vehicleName;
    private String notification;
    private String dateNotification;
    private int cveVehicle;
    private double latitude;
    private double longitude;
    public NotificationV2OnRoad(){

    }

    public NotificationV2OnRoad(String imageVehicle, String vehicleName, String notification, String dateNotification, int cveVehicle,
                                double latitude, double longitude) {
        this.imageVehicle = imageVehicle;
        this.vehicleName = vehicleName;
        this.notification = notification;
        this.dateNotification = dateNotification;
        this.cveVehicle = cveVehicle;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected NotificationV2OnRoad(Parcel in) {
        imageVehicle = in.readString();
        vehicleName = in.readString();
        notification = in.readString();
        dateNotification = in.readString();
        cveVehicle = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<NotificationV2OnRoad> CREATOR = new Creator<NotificationV2OnRoad>() {
        @Override
        public NotificationV2OnRoad createFromParcel(Parcel in) {
            return new NotificationV2OnRoad(in);
        }

        @Override
        public NotificationV2OnRoad[] newArray(int size) {
            return new NotificationV2OnRoad[size];
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
