package com.dextrous.hack.boardme.callback;

import android.location.Location;
import android.util.Log;

public class SimpleLocationChangeCallback implements LocationChangedCallback {
    @Override
    public void setLocation(Location location) {
        Log.d("LOCATOPM_RECEIVED", location.toString());
    }
}
