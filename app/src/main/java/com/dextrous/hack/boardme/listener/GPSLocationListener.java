package com.dextrous.hack.boardme.listener;

import android.Manifest;
import android.content.Context;
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
    private Context context;

    public GPSLocationListener(Context context, LocationChangedCallback callback, LocationManager locationManager) {
        this.callback = callback;
        this.locationManager = locationManager;
        this.context = context;
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
        if (context!=null
                && locationManager != null) {
            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
                AndroidUtil.showAlertDialog(context, "","");
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
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
