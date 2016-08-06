package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dextrous.hack.boardme.adapter.RouteLocationListArrayAdapter;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.RouteLocation;
import com.dextrous.hack.boardme.response.GenericListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_ERROR;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_RESPONSE;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.MSG_GENERIC_ERROR;

public class RouteLocationListCallback extends BaseCallback implements Callback<GenericListResponse<RouteLocation>> {

    private ListView listView;

    public RouteLocationListCallback(Context context, ListView listView) {
        super(context);
        this.listView = listView;
    }
    @Override
    public void onResponse(Call<GenericListResponse<RouteLocation>> call, Response<GenericListResponse<RouteLocation>> response) {
        GenericListResponse<RouteLocation> apiResponse = response.body();
        if(apiResponse != null) {
            List<RouteLocation> routeLocationList = apiResponse.getItems();
            if(routeLocationList != null && !routeLocationList.isEmpty()){
                ListAdapter listAdapter = new RouteLocationListArrayAdapter(context, routeLocationList);
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final RouteLocation item = (RouteLocation) parent.getItemAtPosition(position);
                        Log.d("Item Selected", item.toString());
                    }

                });
            }
            Log.d(LOG_TAG_HTTP_RESPONSE, apiResponse.toString());
        }
        hideDialog();
    }

    @Override
    public void onFailure(Call<GenericListResponse<RouteLocation>> call, Throwable t) {
        Log.e(LOG_TAG_HTTP_ERROR, t.getMessage(), t);
        Toast.makeText(context, MSG_GENERIC_ERROR + t.getMessage(), Toast.LENGTH_LONG).show();
        hideDialog();
    }
}
