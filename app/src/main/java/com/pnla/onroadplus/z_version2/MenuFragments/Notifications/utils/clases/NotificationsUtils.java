package com.pnla.onroadplus.z_version2.MenuFragments.Notifications.utils.clases;

import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsAlert;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsAlertList;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.NotificationsOnroad;
import com.pnla.onroadplus.z_version2.MenuFragments.Notifications.models.Vehicles;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationsUtils {
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

    public static List<Integer> getCveVehicle(List<Vehicles> vehicles) {
        List<Integer> vehicleCves = new ArrayList<>();
        for (int i = 0; i < vehicles.size(); i++) {
            vehicleCves.add(vehicles.get(i).getCveVehicle());
        }
        return vehicleCves;
    }

    public static List<NotificationsOnroad> getOnRoadNotifications(List<NotificationsAlertList> alertsLists) {
        List<NotificationsOnroad> notifications = new ArrayList<>();
        for (int i = 0; i < alertsLists.size(); i++) {
            int cve = alertsLists.get(i).getCveVehicle();
            String vehicleImage = alertsLists.get(i).getVehicleImage();
            String vehicleName = alertsLists.get(i).getVehicleName();
            List<NotificationsAlert> alertList = alertsLists.get(i).getAlerts();
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
                notifications.add(new NotificationsOnroad(vehicleImage, vehicleName, alert, realDate, cveVehicle, latitude, longitude));
            }
        }
        return notifications;
    }


}
