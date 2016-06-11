package com.dextrous.hack.boardme.listener;

import android.app.Activity;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.dextrous.hack.boardme.callback.LocationChangedCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;


public class GPSLocationListener implements LocationListener {

    private static final String TAG = GPSLocationListener.class.getName();

    public LocationChangedCallback callback;
    private Activity callingActivity;
    private GoogleApiClient googleApiClient;

    public GPSLocationListener(Activity callingActivity,
                                GoogleApiClient mGoogleApiClient,
                               LocationChangedCallback callback) {
        this.callback = callback;
        this.googleApiClient = mGoogleApiClient;
        this.callingActivity = callingActivity;
    }

    @Override
    public void onLocationChanged(Location loc) {
        Log.d(TAG, "Longitude: " + loc.getLongitude());
        Log.d(TAG,  "Latitude: " + loc.getLatitude());
        if(callback != null) {
            callback.setLocation(loc);
        }
        callback = null; // Reset to not trigger this call again
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        Toast.makeText(callingActivity, "Obtained location Longitude: "
                + loc.getLongitude() + "Latitude: "
                + loc.getLatitude(), Toast.LENGTH_LONG).show();
    }
}
