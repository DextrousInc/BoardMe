package com.dextrous.hack.boardme.constant;

public class BoardMeConstants {
    public static final String SERVER_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";
    public static final String SYMBOL_PERCENT = "%";
    public static final String LOCAL_SERVER_URL = "http://10.0.2.2:8090/api/";

    public static final String APP_SHARED_PREFERENCE_KEY = "BoardMeAuthPreferences";
    public static final String USER_AUTH_KEY_PREFERENCE_KEY = "userAuthKey";


    // label messages
    public static final String MSG_ROUTE_INFO_WITH_START_END = "{0} (From: {1} To: {2})";
    public static final String MSG_ROUTE_INFO_WITH_FARE = "{0} (Max Fare: {1})";
    public static final String MSG_TRAVEL_INFO_WITH_FARE = "Route: {0}: On: {1}";
    public static final String MSG_WARN_ROUTE_NOT_SELECTED = "Choose a route from list";
    public static final String MSG_GENERIC_ERROR = "Something went wrong!! Try after sometime!! \n Error:";
    public static final String MSG_ESTIMATED_TIME_AND_DISTANCE  = "Distance of {0} \n and a minimum wait time : {1}";
    public static final String MSG_PROGRESS_DIALOG_TITLE  = "Loading...";
    public static final String MSG_PROGRESS_DIALOG_MESSAGE  = "Please wait..";


    // Intent parameter keys
    public static final String INTENT_PARAM_CURRENT_USER_LOCATION_KEY = "currentUserLocationKey";
    public static final String INTENT_PARAM_RECENT_BOARD_LOCATION_KEY = "recentBoardLocationKey";
    public static final String INTENT_PARAM_SELECTED_ROUTE_KEY = "selectedRouteFromDialogKey";
    public static final String INTENT_PARAM_TRAVEL_HISTORY_ITEM_KEY = "travelHistoryItemKey";
    public static final String INTENT_PARAM_TRAVEL_HISTORY_ITEM_ID_KEY = "travelHistoryItemIdKey";
    public static final String INTENT_PARAM_USER_ITEM_KEY = "userDetailItemKey";
    public static final String INTENT_PARAM_USER_ITEM_ID_KEY = "userDetailItemIdKey";
    public static final String INTENT_PARAM_NEAREST_BEACON_ID_KEY = "nearestBeaconIdKey";
    public static final String INTENT_PARAM_ROUTES_RESPONSE_KEY = "allRoutesListResponse";

    // Tags
    public static final String TAG_DIALOG_ROUTES_LIST = "routesListDialogFragmentTag";

    // int flags
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 999;

    public static final String STRING_REGION_NAME = "Board Me Bus Region" ;
    public static final String STRING_BLANK = "";
    public static final String STRING_GPS_SETTING_FOR_ALERT_TITLE = "GPS settings";
    public static final String STRING_GPS_SETTING_FOR_ALERT_CONTENT = "GPS is not enabled. Do you want to go to configure?";
    public static final String STRING_SETTING_LINK_FOR_ALERT = "Settings";
    public static final String STRING_CANCEL_LINK_FOR_ALERT = "Cancel";


    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_ROUTE_START_ID = "routeStartId";
    public static final String FIELD_ROUTE_END_ID = "routeEndId";
    public static final String FIELD_USER_ID = "userId";

    public static final float MAP_DEFAULT_ZOOM = 13;
    public static final long UPDATE_INTERVAL = 10;  /* 10 micro secs */
    public static final long FASTEST_INTERVAL = 200; /* 0.2 sec */
}
