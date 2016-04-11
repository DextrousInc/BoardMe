package com.dextrous.hack.boardme.response;


import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.model.RouteLocation;

import java.util.List;

public class BoardRouteResponse {
    List<RouteLocation> nextStops;
    Route selectedRoute;
    RouteLocation selectedStop;

    public List<RouteLocation> getNextStops() {
        return nextStops;
    }

    public void setNextStops(List<RouteLocation> nextStops) {
        this.nextStops = nextStops;
    }

    public Route getSelectedRoute() {
        return selectedRoute;
    }

    public void setSelectedRoute(Route selectedRoute) {
        this.selectedRoute = selectedRoute;
    }

    public RouteLocation getSelectedStop() {
        return selectedStop;
    }

    public void setSelectedStop(RouteLocation selectedStop) {
        this.selectedStop = selectedStop;
    }

    @Override
    public String toString() {
        return "BoardRouteResponse{" +
                "nextStops=" + nextStops +
                ", selectedRoute=" + selectedRoute +
                ", selectedStop=" + selectedStop +
                '}';
    }
}
