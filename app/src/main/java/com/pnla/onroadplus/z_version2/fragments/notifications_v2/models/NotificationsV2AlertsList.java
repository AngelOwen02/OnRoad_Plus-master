package com.pnla.onroadplus.z_version2.fragments.notifications_v2.models;

import java.util.List;

public class NotificationsV2AlertsList {

    private List<NotificationAlert> alerts = null;
    private int cve_vehicle;
    private String vehicle_name;
    private String vehicle_image;

    /**
     * @param alerts
     * @param cve_vehicle
     */
    public NotificationsV2AlertsList(List<NotificationAlert> alerts, int cve_vehicle, String vehicle_name, String vehicle_image) {
        super();
        this.alerts = alerts;
        this.cve_vehicle = cve_vehicle;
        this.vehicle_name = vehicle_name;
        this.vehicle_image = vehicle_image;
    }

    public List<NotificationAlert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<NotificationAlert> alerts) {
        this.alerts = alerts;
    }

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


}
