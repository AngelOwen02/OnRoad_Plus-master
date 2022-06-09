package com.pnla.onroadplus.z_version2.fragments.mapV2.models.trips;

import java.util.List;

public class TripV2 {

    private double end_mileage;
    private String end_trip;
    private double km;
    private List<PositionV2> positions = null;
    private double start_mileage;
    private String start_trip;
    private String time_trip;
    private double vel_prom;

    private String tripKm;
    private String hourTrip;
    private String urlMapImage;
    private String descriptionTrip;
    private String hourDescripton;
    private String time_shutdown;

    /**
     * @param end_mileage
     * @param end_trip
     * @param km
     * @param positions
     * @param start_mileage
     * @param start_trip
     * @param time_trip
     * @param vel_prom
     * @param tripKm
     * @param hourTrip
     * @param urlMapImage
     * @param descriptionTrip
     * @param hourDescripton
     */
    public TripV2(double end_mileage, String end_trip, double km, List<PositionV2> positions, double start_mileage, String start_trip, String time_trip, double vel_prom,
                  String tripKm, String hourTrip, String urlMapImage, String descriptionTrip, String hourDescripton, String time_shutdown) {
        super();
        this.end_mileage = end_mileage;
        this.end_trip = end_trip;
        this.km = km;
        this.positions = positions;
        this.start_mileage = start_mileage;
        this.start_trip = start_trip;
        this.time_trip = time_trip;
        this.vel_prom = vel_prom;
        this.tripKm = tripKm;
        this.hourTrip = hourTrip;
        this.urlMapImage = urlMapImage;
        this.descriptionTrip = descriptionTrip;
        this.hourDescripton = hourDescripton;
        this.time_shutdown = time_shutdown;
    }

    public double getEndMileage() {
        return end_mileage;
    }

    public void setEndMileage(double end_mileage) {
        this.end_mileage = end_mileage;
    }

    public String getEndTrip() {
        return end_trip;
    }

    public void setEndTrip(String end_trip) {
        this.end_trip = end_trip;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public List<PositionV2> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionV2> positions) {
        this.positions = positions;
    }

    public double getStartMileage() {
        return start_mileage;
    }

    public void setStartMileage(double start_mileage) {
        this.start_mileage = start_mileage;
    }

    public String getStartTrip() {
        return start_trip;
    }

    public void setStartTrip(String start_trip) {
        this.start_trip = start_trip;
    }

    public String getTimeTrip() {
        return time_trip;
    }

    public void setTimeTrip(String time_trip) {
        this.time_trip = time_trip;
    }

    public double getVelProm() {
        return vel_prom;
    }

    public void setVelProm(double vel_prom) {
        this.vel_prom = vel_prom;
    }

    public String getTripKm() {
        return tripKm;
    }

    public void setTripKm(String tripKm) {
        this.tripKm = tripKm;
    }

    public String getHourTrip() {
        return hourTrip;
    }

    public void setHourTrip(String hourTrip) {
        this.hourTrip = hourTrip;
    }

    public String getUrlMapImage() {
        return urlMapImage;
    }

    public void setUrlMapImage(String urlMapImage) {
        this.urlMapImage = urlMapImage;
    }

    public String getDescriptionTrip() {
        return descriptionTrip;
    }

    public void setDescriptionTrip(String descriptionTrip) {
        this.descriptionTrip = descriptionTrip;
    }

    public String getHourDescripton() {
        return hourDescripton;
    }

    public void setHourDescripton(String hourDescripton) {
        this.hourDescripton = hourDescripton;
    }

    public String getTimeShutdown() {
        return time_shutdown;
    }

    public void setTimeShutdown(String time_shutdown) {
        this.time_shutdown = time_shutdown;
    }

}
