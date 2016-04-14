package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.dextrous.hack.boardme.activity.BoardWaitActivity;
import com.dextrous.hack.boardme.model.BoardMeLocation;

import static com.dextrous.hack.boardme.constant.BoardmeConstants.CURRENT_USER_LOCATION_KEY;


public class BoardWaitLocationCallback implements LocationChangedCallback {

    private Context context;

    public BoardWaitLocationCallback(Context context) {

        this.context = context;
    }
    @Override
    public void setLocation(Location location) {
        BoardMeLocation boardMeLocation = new BoardMeLocation(location);
        Intent intent = new Intent(context, BoardWaitActivity.class);
        intent.putExtra(CURRENT_USER_LOCATION_KEY, boardMeLocation);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
