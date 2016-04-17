package com.dextrous.hack.boardme.model;


import java.io.Serializable;

public class TravelHistory implements Serializable{
    private String createdTS;
    private RouteLocation endRoute;
    private Double fareAmount;
    private String lastUpdatedTS;
    private Double latitude;
    private Double longitude;
    private Integer routeEndId;
    private Integer routeStartId;
    private RouteLocation startRoute;
    private User user;
    private Integer userId;
    private Integer id;

    public String getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(String createdTS) {
        this.createdTS = createdTS;
    }

    public RouteLocation getEndRoute() {
        return endRoute;
    }

    public void setEndRoute(RouteLocation endRoute) {
        this.endRoute = endRoute;
    }

    public Double getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(Double fareAmount) {
        this.fareAmount = fareAmount;
    }

    public String getLastUpdatedTS() {
        return lastUpdatedTS;
    }

    public void setLastUpdatedTS(String lastUpdatedTS) {
        this.lastUpdatedTS = lastUpdatedTS;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getRouteEndId() {
        return routeEndId;
    }

    public void setRouteEndId(Integer routeEndId) {
        this.routeEndId = routeEndId;
    }

    public Integer getRouteStartId() {
        return routeStartId;
    }

    public void setRouteStartId(Integer routeStartId) {
        this.routeStartId = routeStartId;
    }

    public RouteLocation getStartRoute() {
        return startRoute;
    }

    public void setStartRoute(RouteLocation startRoute) {
        this.startRoute = startRoute;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TravelHistory{" +
                "createdTS='" + createdTS + '\'' +
                ", endRoute=" + endRoute +
                ", fareAmount=" + fareAmount +
                ", lastUpdatedTS='" + lastUpdatedTS + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", routeEndId=" + routeEndId +
                ", routeStartId=" + routeStartId +
                ", startRoute=" + startRoute +
                ", user=" + user +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
