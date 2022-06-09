package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models;

public class NotificationsAlert {
    private double alert_lat;
    private double alert_lon;
    private String alert_text;
    private int cve_notification;
    private int cve_vehicle;
    private String heading;
    private String msg_type;
    private int origin_adm;
    private String send_time;

    /**
     * @param origin_adm
     * @param alert_text
     * @param alert_lon
     * @param send_time
     * @param alert_lat
     * @param msg_type
     * @param cve_vehicle
     * @param cve_notification
     * @param heading
     */
    public NotificationsAlert(int alert_lat, int alert_lon, String alert_text, int cve_notification, int cve_vehicle, String heading, String msg_type, int origin_adm, String send_time) {
        super();
        this.alert_lat = alert_lat;
        this.alert_lon = alert_lon;
        this.alert_text = alert_text;
        this.cve_notification = cve_notification;
        this.cve_vehicle = cve_vehicle;
        this.heading = heading;
        this.msg_type = msg_type;
        this.origin_adm = origin_adm;
        this.send_time = send_time;
    }

    public double getAlertLatitude() {
        return alert_lat;
    }

    public void setAlertLatitude(Long alert_lat) {
        this.alert_lat = alert_lat;
    }

    public double getAlertLongitude() {
        return alert_lon;
    }

    public void setAlertLongitude(Long alert_lon) {
        this.alert_lon = alert_lon;
    }

    public String getAlertText() {
        return alert_text;
    }

    public void setAlertText(String alert_text) {
        this.alert_text = alert_text;
    }

    public int getCveNotification() {
        return cve_notification;
    }

    public void setCveNotification(int cve_notification) {
        this.cve_notification = cve_notification;
    }

    public int getCveVehicle() {
        return cve_vehicle;
    }

    public void setCveVehicle(int cve_vehicle) {
        this.cve_vehicle = cve_vehicle;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getMsgType() {
        return msg_type;
    }

    public void setMsgType(String msg_type) {
        this.msg_type = msg_type;
    }

    public int getOriginAdm() {
        return origin_adm;
    }

    public void setOriginAdm(int origin_adm) {
        this.origin_adm = origin_adm;
    }

    public String getSendTime() {
        return send_time;
    }

    public void setSendTime(String send_time) {
        this.send_time = send_time;
    }

}
