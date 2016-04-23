package com.dextrous.hack.boardme.callback;


import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.response.BoardWaitResponse;
import com.dextrous.hack.boardme.response.GenericBeanResponse;

import java.text.MessageFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardWaitResponseCallback extends BaseCallback implements Callback<GenericBeanResponse<BoardWaitResponse>> {
    private TextView currentRouteText;
    private TextView currentStopText;
    private TextView etaText;

    public BoardWaitResponseCallback(Context context,
                                     TextView currentRouteText,
                                     TextView currentStopText,
                                     TextView etaText) {
        super(context);
        this.currentRouteText = currentRouteText;
        this.currentStopText = currentStopText;
        this.etaText = etaText;
    }

    @Override
    public void onResponse(Call<GenericBeanResponse<BoardWaitResponse>> call, Response<GenericBeanResponse<BoardWaitResponse>> response) {
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
        hideDialog();
    }

    @Override
    public void onFailure(Call<GenericBeanResponse<BoardWaitResponse>> call, Throwable t) {
        Log.e("HTTP ERROR", t.getMessage(), t);
        Toast.makeText(context, BoardMeConstants.MSG_GENERIC_ERROR + t.getMessage(), Toast.LENGTH_LONG).show();
        hideDialog();
    }
}
