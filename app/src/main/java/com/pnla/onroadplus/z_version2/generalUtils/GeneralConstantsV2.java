package com.pnla.onroadplus.z_version2.generalUtils;

public class GeneralConstantsV2 {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILTURE = "FAILTURE";

    public static final String NO_IMAGE = "NO_IMAGE";
    public static final String EMAIL = "EMAIL";
    public static final String TOKEN = "TOKEN";

    public static final String CREDENTIALS_PREFERENCES = "CREDENTIALS_PREFERENCES";
    public static final String USER_PREFERENCES = "USER_PREFERENCES";
    public static final String URL_USER_IMAGE_PREFERENCES = "URL_USER_IMAGE_PREFERENCES";
    public static final String TOKEN_PREFERENCES = "TOKEN_PREFERENCES";
    public static final String EMAIL_PREFERENCES = "EMAIL_PREFERENCES";
    public static final String PASSWORD_PREFERENCES = "PASSWORD_PREFERENCES";
    public static final String EMPLOYEE_NAME_PREFERENCES = "EMPLOYEE_NAME_PREFERENCES";
    public static final String CLOSE_SESSION_PREFERENCES = "CLOSE_SESSION_PREFERENCES";
    public static final String NOTIFICATIONS_PREFERENCES = "NOTIFICATIONS";

    //ActivityOnlineInteractor
    public static final String JSON_VEHICLES_LIST = "JSON_VEHICLES_LIST";

    //settings
    public static final String ACTIVE_NOTIFICATIONS = "1";
    public static final String DISABLED_NOTIFICATIONS = "0";

    //responseCode
    public static final int RESPONSE_CODE_OK = 105;
    public static final int RESPONSE_CODE_SESSION_EXPIRED = 104;

    //LogIn
    public static final String INVOKED_LOGIN_SCREEN = "INVOKED_LOGIN_SCREEN";
    public static final boolean IS_FIRST_TIME = true;
    public static final boolean IS_NOT_FIRST_TIME = false;

    //vehiclesAndGroups
    public static final int REQUEST_ALL_VEHICLES = 1;
    public static final int REQUEST_SPECIFIC_VEHICLES = 0;
    public static boolean VEHICLES_LIST_VISIBLE = true;
    public static boolean VEHICLES_LIST_NOT_VISIBLE = false;

    //Map
    public static final boolean FIRST_VEHICLE_REQUEST = true;
    public static final int USER_CLICKED_ALL = -1;
    public static final int USER_CLICKED_SELECT_GROUP = -2;
    public static final int WITHOUT_CVE = 0;
    public static final String VEHICLE_NAME_SELECTED = "VEHICLE_NAME_SELECTED";
    public static final String VEHICLE_CVE_SELECTED = "VEHICLE_CVE_SELECTED";
    public static final float VEHICLE_SELECTED = 16;
    public static final float VEHICLE_FINAL_POSITION = 18;
    public static final float GENERAL_ZOOM = 6;

    //Notifications
    public static final String NOTIFICATION = "NOTIFICATION";

    //DIALOG
    public static final int TYPE_OK_COMMAND = 1;
    public static final int TYPE_OK_CONTACT = 2;
    public static final int TYPE_OK_RESTORE_PASSWORD = 3;
    public static final int TYPE_OK_UPDATE_PASSWORD = 4;
    public static final int TYPE_SEND_COMMAND = 5;
    public static final int TYPE_REQUEST_PERMISSION_STORAGE = 6;
    public static final int TYPE_REQUEST_CLOSE_SESSION = 7;

    //DIALOG_LOCATION
    public static final int LOCATION_PERMISSION = 999;

}
