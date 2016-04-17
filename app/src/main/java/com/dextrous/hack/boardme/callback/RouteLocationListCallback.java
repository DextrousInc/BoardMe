package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dextrous.hack.boardme.adapter.RouteLocationListArrayAdapter;
import com.dextrous.hack.boardme.model.RouteLocation;
import com.dextrous.hack.boardme.response.GenericListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteLocationListCallback implements Callback<GenericListResponse<RouteLocation>> {

    private Context context;
    private ListView listView;

    public RouteLocationListCallback(Context context, ListView listView) {
        this.context = context;
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
            Log.d("HTTP RESPONSE", apiResponse.toString());
        }
    }

    @Override
    public void onFailure(Call<GenericListResponse<RouteLocation>> call, Throwable t) {

    }
}
