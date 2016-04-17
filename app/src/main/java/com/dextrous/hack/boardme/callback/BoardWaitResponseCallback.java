package com.dextrous.hack.boardme.callback;


import android.util.Log;
import android.widget.TextView;

import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.response.BoardRouteResponse;
import com.dextrous.hack.boardme.response.BoardWaitResponse;
import com.dextrous.hack.boardme.response.GenericBeanResponse;

import java.text.MessageFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardWaitResponseCallback implements Callback<GenericBeanResponse<BoardWaitResponse>> {
    private TextView currentRouteText;
    private TextView currentStopText;
    private TextView etaText;

    public BoardWaitResponseCallback(TextView currentRouteText, TextView currentStopText, TextView etaText) {

        this.currentRouteText = currentRouteText;
        this.currentStopText = currentStopText;
        this.etaText = etaText;
    }

    @Override
    public void onResponse(Call<GenericBeanResponse<BoardWaitResponse>> call, Response<GenericBeanResponse<BoardWaitResponse>> response) {
        System.out.println(response.isSuccessful());
        System.out.println(response.body());
        if(response.isSuccessful()
                && response.body() != null
                && response.body().getItem() != null) {
            BoardWaitResponse waitResponse = response.body().getItem();
            currentRouteText.setText(waitResponse.getCloseStop().getRoute().getRouteName());
            currentStopText.setText(waitResponse.getCloseStop().getStopName());
            etaText.setText(MessageFormat.format(BoardMeConstants.MSG_ESTIMATED_TIME_AND_DISTANCE,
                    new Object[]{
                            waitResponse.getEta().getDistance(),
                            waitResponse.getEta().getDuration()
                    }));
            Log.d("HTTP RESPONSE", response.body().toString());
        }
    }

    @Override
    public void onFailure(Call<GenericBeanResponse<BoardWaitResponse>> call, Throwable t) {
        Log.e("HTTP ERROR", t.getMessage(), t);
    }
}
