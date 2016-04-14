package com.dextrous.hack.boardme.model;

import android.location.Location;

import java.io.Serializable;

public class BoardMeLocation implements Serializable{
    public BoardMeLocation(Location location) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "BoardMeLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
