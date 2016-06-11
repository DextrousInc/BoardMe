package com.dextrous.hack.boardme.callback;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dextrous.hack.boardme.activity.BoardDistanceMapActivity;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.BoardMeLocation;
import com.dextrous.hack.boardme.response.BoardWaitResponse;
import com.dextrous.hack.boardme.response.GenericBeanResponse;

import java.text.MessageFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.INTENT_PARAM_CURRENT_USER_LOCATION_KEY;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.INTENT_PARAM_RECENT_BOARD_LOCATION_KEY;

public class BoardWaitResponseCallback extends BaseCallback implements Callback<GenericBeanResponse<BoardWaitResponse>> {
    private TextView currentRouteText;
    private TextView currentStopText;
    private TextView etaText;
    private Button getMapsButton;

    public BoardWaitResponseCallback(Context context,
                                     TextView currentRouteText,
                                     TextView currentStopText,
                                     TextView etaText,
                                     Button getMapsButton) {
        super(context);
        this.currentRouteText = currentRouteText;
        this.currentStopText = currentStopText;
        this.etaText = etaText;
        this.getMapsButton = getMapsButton;
    }

    @Override
    public void onResponse(Call<GenericBeanResponse<BoardWaitResponse>> call, Response<GenericBeanResponse<BoardWaitResponse>> response) {
        System.out.println(response.body());
        if (response.isSuccessful()
                && response.body() != null
                && response.body().getItem() != null) {
            final BoardWaitResponse waitResponse = response.body().getItem();
            currentRouteText.setText(waitResponse.getCloseStop().getRoute().getRouteName());
            currentStopText.setText(waitResponse.getCloseStop().getStopName());
            etaText.setText(MessageFormat.format(BoardMeConstants.MSG_ESTIMATED_TIME_AND_DISTANCE,
                    new Object[]{
                            waitResponse.getEta().getDistance(),
                            waitResponse.getEta().getDuration()
                    }));
            if (getMapsButton != null
                    && waitResponse.getCloseStop() != null
                    && waitResponse.getRecentBoardStop() != null) {
                getMapsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // open maps activity with the userLocation and the given bus location
                        Intent intent = new Intent(context, BoardDistanceMapActivity.class);
                        intent.putExtra(INTENT_PARAM_CURRENT_USER_LOCATION_KEY, new BoardMeLocation(waitResponse.getCloseStop()));
                        intent.putExtra(INTENT_PARAM_RECENT_BOARD_LOCATION_KEY, new BoardMeLocation(waitResponse.getRecentBoardStop()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        hideDialog();
                        context.startActivity(intent);
                    }
                });
            }
            Log.d("HTTP RESPONSE", response.body().toString());
        }
        hideDialog();
    }

    @Override
    public void onFailure(Call<GenericBeanResponse<BoardWaitResponse>> call, Throwable t) {
        Log.e("HTTP ERROR", t.getMessage(), t);
        Toast.makeText(context, BoardMeConstants.MSG_GENERIC_ERROR + t.getMessage(), Toast.LENGTH_LONG).show();
        hideDialog();
    }
}
