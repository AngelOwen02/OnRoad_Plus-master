package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models;

import java.util.List;

public class NotificationsRequest {
    private List<Integer> cve_vehicles = null;
    private String day;
    private String token;

    /**
     * @param token
     * @param cve_vehicles
     * @param day
     */
    public NotificationsRequest(List<Integer> cve_vehicles, String token, String day) {
        super();
        this.cve_vehicles = cve_vehicles;
        this.day = day;
        this.token = token;
    }

    public List<Integer> getCveVehicles() {
        return cve_vehicles;
    }

    public void setCveVehicles(List<Integer> cve_vehicles) {
        this.cve_vehicles = cve_vehicles;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
