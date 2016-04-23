package com.dextrous.hack.boardme.listener;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.dextrous.hack.boardme.callback.LocationChangedCallback;
import com.dextrous.hack.boardme.callback.SimpleLocationChangeCallback;
import com.dextrous.hack.boardme.util.AndroidUtil;


public class GPSLocationListener implements LocationListener {

    private static final String TAG = "SomeTag";

    public LocationChangedCallback callback;
    private LocationManager locationManager;
    private Activity callingActivity;

    public GPSLocationListener(Activity callingActivity,
                               LocationChangedCallback callback,
                               LocationManager locationManager) {
        this.callback = callback;
        this.locationManager = locationManager;
        this.callingActivity = callingActivity;
    }

    public GPSLocationListener() {
        this.callback = new SimpleLocationChangeCallback();
    }

    @Override
    public void onLocationChanged(Location loc) {
        String longitude = "Longitude: " + loc.getLongitude();
        Log.d(TAG, longitude);
        callback.setLocation(loc);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.d(TAG, latitude);
        if (callingActivity != null
                && locationManager != null) {
            if (ActivityCompat.checkSelfPermission(callingActivity.getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
                AndroidUtil.promptLocationPermission(callingActivity);
                return;
            }
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
