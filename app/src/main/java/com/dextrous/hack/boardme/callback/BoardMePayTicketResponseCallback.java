package com.dextrous.hack.boardme.callback;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dextrous.hack.boardme.activity.TravelHistoryItemActivity;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.TravelHistory;
import com.dextrous.hack.boardme.response.GenericBeanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardMePayTicketResponseCallback implements Callback<GenericBeanResponse<TravelHistory>> {

    private Context context;

    public BoardMePayTicketResponseCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<GenericBeanResponse<TravelHistory>> call, Response<GenericBeanResponse<TravelHistory>> response) {
        if(response.isSuccessful()
                && response.body() != null
                && response.body().getSuccess()
                && response.body().getItem()!= null) {
            TravelHistory travelHistory = response.body().getItem();
            Intent intent = new Intent(context, TravelHistoryItemActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(BoardMeConstants.INTENT_PARAM_TRAVEL_HISTORY_ITEM_KEY, travelHistory);
            context.startActivity(intent);
            Log.d("HTTP RESPONSE", travelHistory.toString());
        }
    }

    @Override
    public void onFailure(Call<GenericBeanResponse<TravelHistory>> call, Throwable t) {
        Log.e("HTTP ERROR", t.getMessage(), t);
    }
}
