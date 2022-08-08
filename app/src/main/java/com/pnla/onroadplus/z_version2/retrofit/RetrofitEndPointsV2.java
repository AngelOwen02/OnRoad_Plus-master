package com.pnla.onroadplus.z_version2.retrofit;

public class RetrofitEndPointsV2 {
    //esta es la chida
    public static final String URL_SERVER = "http://35.194.83.10/WS-phoenixApiPROD/";
    public static final String URL_SERVER_TEST = "http://192.168.1.13:8080/WS-phoenixApiPROD/";

    //public static final String URL_SERVER = "https://cerberusenlinea.com/WS-phoenixApiPROD/";
    public static final String URL_MAP_API="http://newlandapps.com/";
    public static final String URL_MAP_API_ROUTE="route";
    public static final String LOGIN = "login/loginApp";
    public static final String GET_MENUOBJECT="login/menuAppbar";/**itemobject*/
    public static final String CLOSE_SESSION = "login/closeSession";
    public static final String GET_ALL_VEHICLES = "vehicles/getAllVehicleList";
    public static final String GET_VEHICLE_GROUP_LIST = "vehicles/getVehicleGroupList";
    public static final String GET_VEHICLES_GROUP_LIST_VEHICLES = "vehicles/getVehiclesInGroups";
    public static final String GET_VEHICLE_LIST = "vehicles/getVehicleList";
    public static final String SEND_EMAIL = "contact/sendEmail";
    public static final String GET_GRAPHIC_PER_WEEK_VEHICLE = "graphics/getKMPerWeekVehicle";
    public static final String GET_GRAPHIC_GROUP = "graphics/getKMPerWeekVehicleGroup";
    public static final String RESTORE_PASSWORD = "password/restorePassword";
    public static final String CHANGE_PASSWORD = "password/changePassword";
    public static final String GET_ALL_NOTIFICATIONS = "alerts/getAlertDayByVehicles";
    public static final String GET_NOTIFICATION_BY_VEHICLE = "alerts/getAlertDayByVehicle";
    public static final String GET_VEHICLE_DESCRIPTION = "vehicles/getVehicleDescripcion";
    public static final String GET_VEHICLE_DESCRIPTION_POSITION = "vehicles/getVehicleDescriptionPosition";
    public static final String GET_VEHICLE_DESCRIPTION_INSIRANCE = "vehicles/getVehicledescriptionInsurance";
    public static final String GET_VEHICLE_COMMANDS = "routines/getAllRoutinesList";
    public static final String GET_FULL_VEHICLES = "vehicles/getFullAllVehiclesNogeo";
    public static final String GET_FULL_GEOREFERENCE = "vehicles/getFullAllVehicles";
    public static final String GET_VEHICLE_TRIP = "dashboard/getTripDetails";
    public static final String SEND_COMMAND = "routines/sendCommand";
    public static final String GET_TRIPSBYDAY= "dashboard/getTripByDay";
    public static final String GET_TRIPSBYTIME= "dashboard/getTripByHour";
    public static final String  GET_LASPOSITIOMITEM="vehicles/getLastPositionFullVehicles";
    public static final String GET_VERSION="login/VersionControl";
    public static final String GET_GEOCERCAS="geofences/getGeofences";
    public static final String GET_MENUBAR = "login/menuAppbar";
    public static final String GET_ZONES= "geofences/getZones";
    public static final String GET_ZONEPOINTS= "geofences/getZonePoints";
    public static final String GET_VISITINGPOITNS= "geofences/getVisitInterestPoints";
    public static final String GET_ASIGNMENTS= "geofences/getZones";
    public static final String GET_ASIGNMENTSNEW= "geofences/getVehiclesAndDriver";
    public static final String GET_UNITS="catalog/getUnit";
    public static final String GET_DRIVERS="catalog/getEmployees";
    public static final String POST_ASIGNMENTS="geofences/setVehiclesAndDriver";
    public static final String POST_ASIGNMENTSNEW="geofences/setVehiclesAndDriverNew";
    public static final String POST_AUDIT_TRAIL="login/setAuditTrail";
    public static final String POST_SETZONES="geofences/setZones";

    //Nuevo(s) EndPoints
    public static final String GET_SUPPORTROUTES="geofences/getSupportRoutes";
    public static final String GET_SUPPORTVEHICLES="geofences/getSupportVehicles";
    public static final String SET_ASSIGNSUPPORT="geofences/setAssignSupport";
    public static final String SET_CANCELSUPPORT="geofences/setCancelSupport";

    public static final String GET_CHECKLIST = "logistic/getListSections";
    public static final String GET_HISTORIC ="dashboard/getHistoricTrips" ;

    public static final String GET_SECTIONS = "logistic/getListSections";
    public static final String GET_QUESTIONS = "logistic/getListQuestions";
}
