package com.dextrous.hack.boardme.model;


import java.io.Serializable;

public class RouteLocation implements Serializable {
    private String createdTS;
    private Integer farePercent;
    private Integer id;
    private String lastUpdateTS;
    private Double locationLatitute;
    private Double locationLongitute;
    private Route route;
    private Integer routeId;
    private String stopName;
    private Integer stopOrder;

    public String getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(String createdTS) {
        this.createdTS = createdTS;
    }

    public Integer getFarePercent() {
        return farePercent;
    }

    public void setFarePercent(Integer farePercent) {
        this.farePercent = farePercent;
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

    public Double getLocationLatitute() {
        return locationLatitute;
    }

    public void setLocationLatitute(Double locationLatitute) {
        this.locationLatitute = locationLatitute;
    }

    public Double getLocationLongitute() {
        return locationLongitute;
    }

    public void setLocationLongitute(Double locationLongitute) {
        this.locationLongitute = locationLongitute;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Integer getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(Integer stopOrder) {
        this.stopOrder = stopOrder;
    }

    @Override
    public String toString() {
        return "RouteLocation{" +
                "createdTS='" + createdTS + '\'' +
                ", farePercent=" + farePercent +
                ", id=" + id +
                ", lastUpdateTS='" + lastUpdateTS + '\'' +
                ", locationLatitute=" + locationLatitute +
                ", locationLongitute=" + locationLongitute +
                ", route='" + route + '\'' +
                ", routeId=" + routeId +
                ", stopName='" + stopName + '\'' +
                ", stopOrder=" + stopOrder +
                '}';
    }
}
