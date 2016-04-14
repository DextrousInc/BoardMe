package com.dextrous.hack.boardme.model;


import java.io.Serializable;

public class Route implements Serializable {
    private String beaconId;
    private String createdTS;
    private Integer id;
    private String lastUpdateTS;
    private String routeEnd;
    private Integer routeFare;
    private String routeName;
    private String routeStart;

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public String getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(String createdTS) {
        this.createdTS = createdTS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastUpdateTS() {
        return lastUpdateTS;
    }

    public void setLastUpdateTS(String lastUpdateTS) {
        this.lastUpdateTS = lastUpdateTS;
    }

    public String getRouteEnd() {
        return routeEnd;
    }

    public void setRouteEnd(String routeEnd) {
        this.routeEnd = routeEnd;
    }

    public Integer getRouteFare() {
        return routeFare;
    }

    public void setRouteFare(Integer routeFare) {
        this.routeFare = routeFare;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteStart() {
        return routeStart;
    }

    public void setRouteStart(String routeStart) {
        this.routeStart = routeStart;
    }

    @Override
    public String toString() {
        return "Route{" +
                "beaconId='" + beaconId + '\'' +
                ", createdTS='" + createdTS + '\'' +
                ", id=" + id +
                ", lastUpdateTS='" + lastUpdateTS + '\'' +
                ", routeEnd='" + routeEnd + '\'' +
                ", routeFare=" + routeFare +
                ", routeName='" + routeName + '\'' +
                ", routeStart='" + routeStart + '\'' +
                '}';
    }
}
