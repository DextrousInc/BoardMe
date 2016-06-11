package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.dextrous.hack.boardme.activity.BoardMeActivity;
import com.dextrous.hack.boardme.model.BoardMeLocation;
import com.estimote.sdk.Beacon;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.INTENT_PARAM_CURRENT_USER_LOCATION_KEY;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.INTENT_PARAM_NEAREST_BEACON_ID_KEY;


public class BoardMeLocationCallback extends BaseCallback implements LocationChangedCallback {

    private Beacon beacon;

    public BoardMeLocationCallback(Context context, Beacon beacon) {
        super(context);
        this.beacon = beacon;
    }
    @Override
    public void setLocation(Location location) {
        BoardMeLocation boardMeLocation = new BoardMeLocation(location);
        Intent intent = new Intent(context, BoardMeActivity.class);
        intent.putExtra(INTENT_PARAM_CURRENT_USER_LOCATION_KEY, boardMeLocation);
        intent.putExtra(INTENT_PARAM_NEAREST_BEACON_ID_KEY, beacon!= null? beacon.getMacAddress().toString() : null);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hideDialog();
        context.startActivity(intent);
    }
}
