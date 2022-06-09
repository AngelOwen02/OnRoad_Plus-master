package com.pnla.onroadplus.z_version2.fragments.notifications_v2.models;

import java.util.List;

public class NotificationsV2Data {

    private List<NotificationsV2AlertsList> alerts = null;

    /**
     * @param alerts
     */
    public NotificationsV2Data(List<NotificationsV2AlertsList> alerts) {
        super();
        this.alerts = alerts;
    }

    public List<NotificationsV2AlertsList> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<NotificationsV2AlertsList> alerts) {
        this.alerts = alerts;
    }

}
