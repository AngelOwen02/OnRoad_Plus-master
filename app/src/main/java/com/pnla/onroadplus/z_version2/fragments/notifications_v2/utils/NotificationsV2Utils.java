package com.pnla.onroadplus.z_version2.fragments.notifications_v2.utils;

import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationAlert;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationV2OnRoad;
import com.pnla.onroadplus.z_version2.fragments.notifications_v2.models.NotificationsV2AlertsList;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationsV2Utils {

    public static String getCurrentDate() {
        Calendar calander = Calendar.getInstance();
        int day = calander.get(Calendar.DAY_OF_MONTH);
        int month = calander.get(Calendar.MONTH) + 1;
        int year = calander.get(Calendar.YEAR);
        String sDay = "" + day;
        String sMonth = "" + month;
        if (sDay.length() == 1) {
            sDay = "0" + sDay;
        }
        if (sMonth.length() == 1) {
            sMonth = "0" + sMonth;
        }
        return sDay + "/" + sMonth + "/" + year;
    }

    public static List<Integer> getCveVehicle(List<VehicleV2> vehicles) {
        List<Integer> vehicleCves = new ArrayList<>();
        for (int i = 0; i < vehicles.size(); i++) {
            vehicleCves.add(vehicles.get(i).getCveVehicle());
        }
        return vehicleCves;
    }

    public static List<NotificationV2OnRoad> getOnRoadNotifications(List<NotificationsV2AlertsList> alertsLists) {
        List<NotificationV2OnRoad> notifications = new ArrayList<>();
        for (int i = 0; i < alertsLists.size(); i++) {
            int cve = alertsLists.get(i).getCveVehicle();
            String vehicleImage = alertsLists.get(i).getVehicleImage();
            String vehicleName = alertsLists.get(i).getVehicleName();
            List<NotificationAlert> alertList = alertsLists.get(i).getAlerts();
            for (int j = 0; j < alertList.size(); j++) {
                String alert = alertList.get(j).getAlertText();
                String dateString = alertList.get(j).getSendTime();
                String[] separated = dateString.split("T");
                String date = separated[0];             // this will contain "2016-10-02"
                String ds2=null;
                try {
                    String ds1 = date;
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                    ds2 = sdf2.format(sdf1.parse(ds1));
                } catch (Exception e) {
                }

                String time = separated[1];             // this will contain "00:00:00.000Z"
                String realDate = ds2 + " |  " + time;//    se cambio | por un salto \n
                int cveVehicle = alertList.get(j).getCveVehicle();
                double latitude = alertList.get(j).getAlertLatitude();
                double longitude = alertList.get(j).getAlertLongitude();
                notifications.add(new NotificationV2OnRoad(vehicleImage, vehicleName, alert, realDate, cveVehicle, latitude, longitude));
            }
        }
        return notifications;
    }


}
