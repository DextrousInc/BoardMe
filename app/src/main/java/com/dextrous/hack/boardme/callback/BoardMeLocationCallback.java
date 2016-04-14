package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.dextrous.hack.boardme.activity.BoardMeActivity;
import com.dextrous.hack.boardme.model.BoardMeLocation;

import static com.dextrous.hack.boardme.constant.BoardmeConstants.CURRENT_USER_LOCATION_KEY;


public class BoardMeLocationCallback implements LocationChangedCallback {

    private Context context;

    public BoardMeLocationCallback(Context context) {
        this.context = context;
    }
    @Override
    public void setLocation(Location location) {
        BoardMeLocation boardMeLocation = new BoardMeLocation(location);
        Intent intent = new Intent(context, BoardMeActivity.class);
        intent.putExtra(CURRENT_USER_LOCATION_KEY, boardMeLocation);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
