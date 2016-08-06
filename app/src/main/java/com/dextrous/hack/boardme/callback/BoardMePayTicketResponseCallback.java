package com.dextrous.hack.boardme.callback;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.dextrous.hack.boardme.activity.TravelHistoryItemActivity;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.TravelHistory;
import com.dextrous.hack.boardme.response.GenericBeanResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_ERROR;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_RESPONSE;

public class BoardMePayTicketResponseCallback extends BaseCallback  implements Callback<GenericBeanResponse<TravelHistory>> {


    public BoardMePayTicketResponseCallback(Context context) {
        super(context);
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
            Log.d(LOG_TAG_HTTP_RESPONSE, travelHistory.toString());
            context.startActivity(intent);
        }
        hideDialog();
    }

    @Override
    public void onFailure(Call<GenericBeanResponse<TravelHistory>> call, Throwable t) {
        Log.e(LOG_TAG_HTTP_ERROR, t.getMessage(), t);
        Toast.makeText(context, BoardMeConstants.MSG_GENERIC_ERROR + t.getMessage(), Toast.LENGTH_LONG).show();
        hideDialog();
    }
}
