package com.pnla.onroadplus.z_version2.fragments.mapV2.utils;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.maps.android.PolyUtil;
import com.pnla.onroadplus.R;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.VehicleV2Map;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.PositionV2;
import com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips.TripV2;
import com.pnla.onroadplus.z_version2.fragments.vehiclesV2.models.vehiclesv2.VehicleV2;
import com.pnla.onroadplus.z_version2.generalUtils.GeneralConstantsV2;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MapV2Utils {

    public static List<VehicleV2Map> setDefaultImage(List<VehicleV2Map> vehicles) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleImage() == null) {
                vehicles.get(i).setVehicleImage(GeneralConstantsV2.NO_IMAGE);
            }
        }
        return vehicles;
    }

    public static List<VehicleV2> getVehiclesFromBundle(Bundle bundle) {
        List<VehicleV2> vehicles;
        String string = bundle.getString(GeneralConstantsV2.JSON_VEHICLES_LIST);
        Gson gson = new Gson();
        Type type = new TypeToken<List<VehicleV2>>() {
        }.getType();
        vehicles = gson.fromJson(string, type);
        return vehicles;
    }

    public static List<Integer> getVehiclesCves(List<VehicleV2> vehicles) {
        List<Integer> vehiclesCves = new ArrayList<>();
        for (int i = 0; i < vehicles.size(); i++) {
            vehiclesCves.add(vehicles.get(i).getCveVehicle());
        }
        return vehiclesCves;
    }

    public static List<Integer> getVehiclesV2MapCves(List<VehicleV2Map> vehicles) {
        List<Integer> vehiclesCves = new ArrayList<>();
        for (int i = 0; i < vehicles.size(); i++) {
            vehiclesCves.add(vehicles.get(i).getCveVehicle());
        }
        return vehiclesCves;
    }

    public static List<VehicleV2> setImageDefaultToEveryVehicle(List<VehicleV2> vehicles) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleImage() == null) {
                vehicles.get(i).setVehicleImage(GeneralConstantsV2.NO_IMAGE);
            }
        }
        return vehicles;
    }

    public static String validateVehicleDescriptionDataToRequest(String token, int cve) {
        String validation = GeneralConstantsV2.SUCCESS;

        if (token == null || token.length() == 0) {
            return validation = "Ocurrió un error al obtener información de usuario.";
        }
        if (cve < 1) {
            return validation = "La clave de vehículo no es válida.";
        }
        return validation;
    }
    public  static String validategettripbydatetoRequest(int cve,String sendtime,String token)
    {
        String validation1 = GeneralConstantsV2.SUCCESS;
        if(cve < 1) {
            return validation1 = "La clave de vehículo no es válida.";
        }
        if(sendtime == null || sendtime.length() == 0 || sendtime == null || sendtime.length() == 0) {
            validation1 = "Ocurrió un error al calcular la fecha solicitada.";
            return validation1;
        }
        if(token == null || token.length() == 0) {
            return validation1 = "Ocurrió un error al obtener información de usuario.";
        }
        return validation1;
    }
    public  static String validategettripbytinetoRequest(int cve,String sendtime,String sendtime1,String token)
    {
        String validation1 = GeneralConstantsV2.SUCCESS;
        if(cve < 1) {
            return validation1 = "La clave de vehículo no es válida.";
        }
        if(sendtime == null || sendtime.length() == 0 || sendtime == null || sendtime.length() == 0) {
            validation1 = "Ocurrió un error al calcular la fecha solicitada.";
            return validation1;
        }
        if(sendtime1 == null || sendtime1.length() == 0 || sendtime1 == null || sendtime1.length() == 0) {
            validation1 = "Ocurrió un error al calcular la fecha solicitada.";
            return validation1;
        }
        if(token == null || token.length() == 0) {
            return validation1 = "Ocurrió un error al obtener información de usuario.";
        }
        return validation1;
    }
    public static String getUtcStartDate(String dateToConvert) {
        String result = "";
        String input = dateToConvert + "T00:00:00Z";

        TimeZone utc = TimeZone.getTimeZone("GMT-5");
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss'Z'");
        f.setTimeZone(utc);
        GregorianCalendar cal9 = new GregorianCalendar(utc);
        try {
            cal9.setTime(f.parse(input));
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        Date date = null;
        try {
            date = formatter.parse(cal9.getTime().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result = format.format(date);
        return result;
    }

    public static String getUtcEndDate(String dateToConvert) {
        String result = "";
        String inputEnd = dateToConvert + "T24:00:00Z";

        TimeZone utc = TimeZone.getTimeZone("GMT-5");
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss'Z'");
        f.setTimeZone(utc);
        GregorianCalendar cal9 = new GregorianCalendar(utc);

        f.setTimeZone(utc);
        try {
            cal9.setTime(f.parse(inputEnd));
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        Date date = null;
        try {
            date = formatter.parse(cal9.getTime().toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return result;
        }

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result = format.format(date);
        return result;
    }

    public static String validateTripsData(String token, int vehicleCve, String startUtcDate, String endUtcDate) {
        String validation = GeneralConstantsV2.SUCCESS;
        if (token == null || token.length() == 0) {
            validation = "Ocurrió un error al obtener la información del usuario.";
            return validation;
        }
        if (vehicleCve < 1) {
            validation = "La clave del vehículo no es válida.";
            return validation;
        }
        if (startUtcDate == null || startUtcDate.length() == 0 || endUtcDate == null || endUtcDate.length() == 0) {
            validation = "Ocurrió un error al calcular la fecha solicitada.";
            return validation;
        }
        return validation;
    }

    public static List<TripV2> orderPositions(List<TripV2> tripsList) {
        for (int i = 0; i < tripsList.size(); i++) {
            Collections.sort(tripsList.get(i).getPositions(), new Comparator<PositionV2>() {
                @Override
                public int compare(PositionV2 o1, PositionV2 o2) {
                    if (o1.getOrder() > o2.getOrder()) {
                        return 1;
                    } else if (o1.getOrder() < o2.getOrder()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
        }
        return tripsList;
    }
/*
    public static List<Data> orderPositionGeoCercas(List<Data> geofencesList )
    { for (int i = 0; i < geofencesList.size(); i++) {
        Collections.sort(geofencesList.get(i).getGeofences(), new Comparator<Geofences>() {
            @Override
            public int compare(Geofences o1, Geofences o2) {
                if (o1.getGeoPoint() > o2.getGeoPoint()) {
                    return 1;
                } else if (o1.getGeoPoint() < o2.getGeoPoint()) {
                    return -1;
                } else {
                    return 0;
                }

            }
        });
    }
        return  geofencesList;
    }*/

    public static List<TripV2> buildGoogleUrlImage(List<TripV2> tripsList, Context context) {
        String urlMapImage = "";
        String baseUrlImageMap = "http://maps.googleapis.com/maps/api/staticmap?";
        String sizeImageMap = "size=600x295&";
        String googleKey = "key=" + context.getString(R.string.keyGoogleMaps) + "&";

        String markerStar = "markers=color:black|label:s|";
        String latitudeMarkerStart = "";
        String longitudeMarkerStart = "";

        String markerEnd = "markers=icon:https://cdn.zeplin.io/5d51a93546e50e9b42934a50/assets/C06FE264-944C-421A-8E01-68646B10D08F.png|label:s|";
        String latitudeMarkerEnd = "";
        String longitudeMarkerEnd = "";

        String path = "&path=color:0x000000ff|weight:3%7Cenc:";
        String baseUlrPolyline = "path=color:0x000000ff|weight:3|";
        String sensor = "sensor=false";

        List<LatLng> recorridos = new ArrayList<>();
        List<PositionV2> positions;
        for (int i = 0; i < tripsList.size(); i++) {
            positions = tripsList.get(i).getPositions();

            if (positions != null && positions.size() > 0) {
                PositionV2 startPosition = positions.get(0);
                latitudeMarkerStart = startPosition.getLatitude() + "";
                longitudeMarkerStart = startPosition.getLongitude() + "";

                PositionV2 endPosition = positions.get(positions.size() - 1);
                latitudeMarkerEnd = endPosition.getLatitude() + "";
                longitudeMarkerEnd = endPosition.getLongitude() + "";

                for (int j = 0; j < positions.size(); j++) {
                    recorridos.add(new LatLng(positions.get(j).getLatitude(), positions.get(j).getLongitude()));

                }
               // Log.e("mydaytrips",""+recorridos);
                String encode = PolyUtil.encode(recorridos);
                String googleImage = baseUrlImageMap + sizeImageMap + googleKey + markerStar + latitudeMarkerStart + "," + longitudeMarkerStart + "&" + markerEnd + latitudeMarkerEnd + "," + longitudeMarkerEnd + "&" + path + encode;

                tripsList.get(i).setUrlMapImage(googleImage);
                recorridos.clear();
            }

        }

        return tripsList;
    }

    public static String getTimeValue(String dateStart, String dateEnd) {
        SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;

        try {
            d1 = fullFormat.parse(dateStart);
            d2 = fullFormat.parse(dateEnd);

            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff / (1000) % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            String finalHours = "" + diffHours;
            String finalMinutes = "" + diffMinutes;
            String finalSeconds = "" + diffSeconds;

            if (finalHours.length() == 1)
                finalHours = "0" + finalHours;

            if (finalMinutes.length() == 1)
                finalMinutes = "0" + finalMinutes;

            if (finalSeconds.length() == 1)
                if(finalSeconds.equals("0"))
                {
                    finalSeconds="00";
                }else {
                    finalSeconds = "" + finalSeconds;
                }

            return finalHours + ":" + finalMinutes + ":" + finalSeconds;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static VehicleV2Map findVehicle(List<VehicleV2Map> vehicles, VehicleV2Map findThisvehicle) {
        VehicleV2Map myVehicle = null;
        int vehicleCveToFind = findThisvehicle.getCveVehicle();
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getCveVehicle() == vehicleCveToFind) {
                myVehicle = vehicles.get(i);
                break;
            }
        }
        return myVehicle;
    }

}
