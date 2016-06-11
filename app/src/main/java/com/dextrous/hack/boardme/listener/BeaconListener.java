package com.dextrous.hack.boardme.listener;


import android.app.Activity;
import android.util.Log;

import com.dextrous.hack.boardme.callback.BoardMeLocationCallback;
import com.dextrous.hack.boardme.util.AndroidUtil;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class BeaconListener implements BeaconManager.MonitoringListener, BeaconManager.ServiceReadyCallback {

    private Activity callingActivity;
    private GoogleApiClient googleApiClient;
    private BeaconManager beaconManager;
    private String scanId;
    String TAG = BeaconListener.class.getName();

    public BeaconListener(Activity callingActivity,
                          GoogleApiClient mGoogleApiClient,
                          BeaconManager beaconManager) {
        this.callingActivity = callingActivity;
        this.googleApiClient = mGoogleApiClient;
        this.beaconManager = beaconManager;
        beaconManager.connect(this);
    }

    @Override
    public void onEnteredRegion(Region region, List<Beacon> list) {
        if (!list.isEmpty()) {
            Beacon selectedBeacon = list.get(0);
            double minDistance = Utils.computeAccuracy(selectedBeacon);
            for (Beacon item : list) {
                Log.d(TAG, item.toString());
                double distance = Utils.computeAccuracy(item);
                Log.d(TAG, "distance=" + distance);
                if (distance < minDistance) {
                    selectedBeacon = item;
                    minDistance = distance;
                }
                // Identify the closest beacon
            }
            // trigger callback response
            if (AndroidUtil.checkGPSEnabled(callingActivity, true)) {
                // Listener call
                Log.d(TAG, "selectedBeacon=" + selectedBeacon);
                AndroidUtil.getLocationsHandler(callingActivity, googleApiClient, new BoardMeLocationCallback(callingActivity, selectedBeacon));
                // de activate listener
                beaconManager.stopEddystoneScanning(scanId);
            }
        }
    }

    @Override
    public void onExitedRegion(Region region) {
        Log.d(TAG, "Exiting region");
        Log.d(TAG, region.toString());
    }

    @Override
    public void onServiceReady() {
        scanId = this.beaconManager.startEddystoneScanning();
    }
}
