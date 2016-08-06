package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dextrous.hack.boardme.activity.TravelHistoryItemActivity;
import com.dextrous.hack.boardme.adapter.UserHistoryArrayAdapter;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.TravelHistory;
import com.dextrous.hack.boardme.response.GenericListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_ERROR;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_RESPONSE;

public class UserHistoryListCallback extends BaseCallback implements Callback<GenericListResponse<TravelHistory>> {

    private ListView listView;

    public UserHistoryListCallback(Context context, ListView listView) {
        super(context);
        this.listView = listView;
    }
    @Override
    public void onResponse(Call<GenericListResponse<TravelHistory>> call, Response<GenericListResponse<TravelHistory>> response) {
        GenericListResponse<TravelHistory> apiResponse = response.body();
        if(apiResponse != null) {
            List<TravelHistory> userHistoryList = apiResponse.getItems();
            if(userHistoryList != null && !userHistoryList.isEmpty()){
                ListAdapter listAdapter = new UserHistoryArrayAdapter(context, userHistoryList);
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final TravelHistory item = (TravelHistory) parent.getItemAtPosition(position);
                        // open the travel history activity
                        Intent intent = new Intent(context, TravelHistoryItemActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(BoardMeConstants.INTENT_PARAM_TRAVEL_HISTORY_ITEM_KEY, item);
                        context.startActivity(intent);
                        Log.d("Item Selected", item.toString());
                    }

                });
            }
            Log.d(LOG_TAG_HTTP_RESPONSE, apiResponse.toString());
        }
        hideDialog();
    }

    @Override
    public void onFailure(Call<GenericListResponse<TravelHistory>> call, Throwable t) {
        Log.e(LOG_TAG_HTTP_ERROR,t.getMessage(),  t);
        hideDialog();
    }
}
