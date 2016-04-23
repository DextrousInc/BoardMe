package com.dextrous.hack.boardme.util;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.callback.LocationChangedCallback;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.listener.GPSLocationListener;
import com.estimote.sdk.Region;
import com.google.gson.Gson;

import java.util.UUID;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.APP_SHARED_PREFERENCE_KEY;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.MY_PERMISSIONS_REQUEST_LOCATION;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.STRING_BLANK;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.STRING_CANCEL_LINK_FOR_ALERT;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.STRING_GPS_SETTING_FOR_ALERT_CONTENT;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.STRING_GPS_SETTING_FOR_ALERT_TITLE;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.STRING_SETTING_LINK_FOR_ALERT;

public class AndroidUtil {
    static String TAG = AndroidUtil.class.getName();

    public static String getStringPreferenceValue(Context context, String preferenceKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceKey, "");
    }

    public static void setObjectPreferenceAsString(Context context, String preferenceKey, Object value) {
        SharedPreferences mPrefs = context.getSharedPreferences(APP_SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value); // myObject - instance of MyObject
        prefsEditor.putString(preferenceKey, json);
        prefsEditor.commit();
    }

    public static  <T extends Object>  T getPreferenceAsObject(Context context, String preferenceKey, Class<T> type){
        String json = getStringPreferenceValue(context, preferenceKey);
        Gson gson = new Gson();
        return STRING_BLANK.equalsIgnoreCase(json) ? null : gson.fromJson(json, type) ;
    }

    public static boolean isGPSEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean checkGPSEnabled(final Activity activity, boolean warnUser) {
        boolean isEnabled = isGPSEnabled(activity);
        if (!isEnabled && warnUser) {
            showAlertDialog(activity, STRING_GPS_SETTING_FOR_ALERT_TITLE, STRING_GPS_SETTING_FOR_ALERT_CONTENT);
        }
        return isEnabled;
    }

    public static void showAlertDialog(final Activity activity, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // On pressing the Settings button.
        alertDialog.setPositiveButton(STRING_SETTING_LINK_FOR_ALERT, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.getBaseContext().startActivity(intent);
            }
        });

        // On pressing the cancel button
        alertDialog.setNegativeButton(STRING_CANCEL_LINK_FOR_ALERT, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }


    public static void getLocationsHandler(Activity activity, LocationChangedCallback callback) {
        if (isGPSEnabled(activity)) {
            LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                promptLocationPermission(activity);
                return;
            }
            Log.d(TAG, "Setting request locations");
            GPSLocationListener listener = new GPSLocationListener(activity, callback, locationManager);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        }
    }

    public static void promptLocationPermission(Activity callingActivity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(callingActivity,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

            //Prompt the user once explanation has been shown
            ActivityCompat.requestPermissions(callingActivity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);


        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(callingActivity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }
    public static Region getBeaconRegion(Context context) {
        return new Region(
                BoardMeConstants.STRING_REGION_NAME,
                UUID.fromString(context.getResources().getString(R.string.beacon_uuid)),
                Integer.parseInt(context.getResources().getString(R.string.beacon_major)),
                Integer.parseInt(context.getResources().getString(R.string.beacon_minor)));
    }

    public static ProgressDialog showProgressDialog(Context context, String titleText, String processingText) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle(titleText);
        progress.setCancelable(Boolean.FALSE);
        progress.setCanceledOnTouchOutside(Boolean.FALSE);
        progress.setMessage(processingText);
        progress.show();
        return progress;
    }

}
