package com.dextrous.hack.boardme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.callback.RouteLocationListCallback;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.model.RouteLocation;
import com.dextrous.hack.boardme.response.GenericListResponse;
import com.dextrous.hack.boardme.service.BoardMeAPIService;
import com.dextrous.hack.boardme.wrapper.RetrofitWrapper;

import retrofit2.Call;

public class RouteLocationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String BASE_URL = getResources().getString(R.string.base_api_url);
        Log.d("BASE_URL", BASE_URL);

        BoardMeAPIService apiService = RetrofitWrapper.build(BASE_URL);

        Intent intent = getIntent();
        Object temp = intent.getSerializableExtra(BoardMeConstants.INTENT_PARAM_SELECTED_ROUTE_KEY);
        Route selectedRoute = temp != null ? (Route)temp : null;
        if(selectedRoute != null) {
            // get the sub list view
            ListView stopsListView = (ListView) findViewById(R.id.stopListView);
            // construct network call
            Call<GenericListResponse<RouteLocation>> allStopsInRoute = apiService.getAllStopsInRoute(String.valueOf(selectedRoute.getId()));
            allStopsInRoute.enqueue(new RouteLocationListCallback(this, stopsListView));
            // send the call back object
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
