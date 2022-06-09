package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models;

import java.util.List;

public class NotificationsData {
    private List<NotificationsAlertList> alerts = null;

    /**
     * @param alerts
     */
    public NotificationsData(List<NotificationsAlertList> alerts) {
        super();
        this.alerts = alerts;
    }

    public List<NotificationsAlertList> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<NotificationsAlertList> alerts) {
        this.alerts = alerts;
    }
}
