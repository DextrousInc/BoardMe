package com.dextrous.hack.boardme.response;


import com.dextrous.hack.boardme.model.ETAData;
import com.dextrous.hack.boardme.model.RouteLocation;

public class BoardWaitResponse {
    private RouteLocation closeStop;
    private RouteLocation recentBoardStop;
    private ETAData eta;

    public RouteLocation getCloseStop() {
        return closeStop;
    }

    public void setCloseStop(RouteLocation closeStop) {
        this.closeStop = closeStop;
    }

    public ETAData getEta() {
        return eta;
    }

    public void setEta(ETAData eta) {
        this.eta = eta;
    }

    public RouteLocation getRecentBoardStop() {
        return recentBoardStop;
    }

    public void setRecentBoardStop(RouteLocation recentBoardStop) {
        this.recentBoardStop = recentBoardStop;
    }

    @Override
    public String toString() {
        return "BoardWaitResponse{" +
                "closeStop=" + closeStop +
                ", recentBoardStop=" + recentBoardStop +
                ", eta=" + eta +
                '}';
    }
}
