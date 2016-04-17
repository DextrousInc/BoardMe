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
    public static final String MSG_ESTIMATED_TIME_AND_DISTANCE  = "Distance of {0} \n and a minimum wait time : {1}";


    // Intent parameter keys
    public static final String INTENT_PARAM_CURRENT_USER_LOCATION_KEY = "currentUserLocationKey";
    public static final String INTENT_PARAM_SELECTED_ROUTE_KEY = "selectedRouteFromDialogKey";
    public static final String INTENT_PARAM_TRAVEL_HISTORY_ITEM_KEY = "travelHistoryItemKey";
    public static final String INTENT_PARAM_TRAVEL_HISTORY_ITEM_ID_KEY = "travelHistoryItemIdKey";
    public static final String INTENT_PARAM_NEAREST_BEACON_ID_KEY = "nearestBeaconIdKey";
    public static final String INTENT_PARAM_ROUTES_RESPONSE_KEY = "allRoutesListResponse";

    // Tags
    public static final String TAG_DIALOG_ROUTES_LIST = "routesListDialogFragmentTag";
}
