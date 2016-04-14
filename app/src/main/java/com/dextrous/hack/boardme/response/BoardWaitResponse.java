package com.dextrous.hack.boardme.response;


import com.dextrous.hack.boardme.model.ETAData;
import com.dextrous.hack.boardme.model.RouteLocation;

public class BoardWaitResponse {
    private RouteLocation closeStop;
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

    @Override
    public String toString() {
        return "BoardWaitResponse{" +
                "closeStop=" + closeStop +
                ", eta=" + eta +
                '}';
    }
}
