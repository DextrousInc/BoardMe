package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.dextrous.hack.boardme.activity.BoardWaitActivity;
import com.dextrous.hack.boardme.model.BoardMeLocation;
import com.dextrous.hack.boardme.model.Route;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.INTENT_PARAM_CURRENT_USER_LOCATION_KEY;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.INTENT_PARAM_SELECTED_ROUTE_KEY;


public class BoardWaitLocationCallback extends BaseCallback implements LocationChangedCallback {

    private Route selectedRoute;

    public BoardWaitLocationCallback(Context context, Route selectedRoute) {
        super(context);
        this.selectedRoute = selectedRoute;
    }
    @Override
    public void setLocation(Location location) {
        BoardMeLocation boardMeLocation = new BoardMeLocation(location);
        Intent intent = new Intent(context, BoardWaitActivity.class);
        intent.putExtra(INTENT_PARAM_CURRENT_USER_LOCATION_KEY, boardMeLocation);
        intent.putExtra(INTENT_PARAM_SELECTED_ROUTE_KEY, selectedRoute);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hideDialog();
        context.startActivity(intent);
    }
}
