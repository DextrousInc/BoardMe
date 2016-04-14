package com.dextrous.hack.boardme.model;


public class ETAData {
    private String from_location;
    private String to_location;
    private String duration;
    private String distance;

    public String getFrom_location() {
        return from_location;
    }

    public void setFrom_location(String from_location) {
        this.from_location = from_location;
    }

    public String getTo_location() {
        return to_location;
    }

    public void setTo_location(String to_location) {
        this.to_location = to_location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "ETAData{" +
                "from_location='" + from_location + '\'' +
                ", to_location='" + to_location + '\'' +
                ", duration='" + duration + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
