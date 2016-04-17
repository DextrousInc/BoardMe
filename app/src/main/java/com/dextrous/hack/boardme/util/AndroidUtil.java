package com.dextrous.hack.boardme.util;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.dextrous.hack.boardme.callback.LocationChangedCallback;
import com.dextrous.hack.boardme.listener.GPSLocationListener;
import com.google.gson.Gson;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.APP_SHARED_PREFERENCE_KEY;

public class AndroidUtil {
    private static final String STRING_BLANK = "";

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

    public static boolean checkGPSEnabled(final Context context, boolean warnUser) {
        boolean isEnabled = isGPSEnabled(context);
        if (!isEnabled && warnUser) {
            showAlertDialog(context, "","");
        }
        return isEnabled;
    }

    public static void showAlertDialog(final Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("GPS settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to configure?");

        // On pressing the Settings button.
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        // On pressing the cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        10);
                return;
            }
            System.out.println("Setting reqeust locations");
            GPSLocationListener listener = new GPSLocationListener(activity, callback, locationManager);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        }
    }
}
