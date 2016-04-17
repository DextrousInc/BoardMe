package com.dextrous.hack.boardme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.adapter.RouteLocationArrayAdapter;
import com.dextrous.hack.boardme.callback.BoardRouteResponseCallback;
import com.dextrous.hack.boardme.callback.RouteLocationListCallback;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.BoardMeLocation;
import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.model.RouteLocation;
import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.response.BoardRouteResponse;
import com.dextrous.hack.boardme.response.GenericListResponse;
import com.dextrous.hack.boardme.service.BoardMeAPIService;
import com.dextrous.hack.boardme.util.AndroidUtil;
import com.dextrous.hack.boardme.wrapper.RetrofitWrapper;

import java.util.ArrayList;

import retrofit2.Call;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.USER_AUTH_KEY_PREFERENCE_KEY;

public class RouteLocationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String BASE_URL = getResources().getString(R.string.base_api_url);
        Log.d("BASE_URL", BASE_URL);

        RetrofitWrapper.start(BASE_URL);
        BoardMeAPIService apiService = RetrofitWrapper.build();

        Intent intent = getIntent();
        Object temp = intent.getSerializableExtra(BoardMeConstants.INTENT_PARAM_SELECTED_ROUTE_KEY);
        Route selectedRoute = temp != null ? (Route)temp : null;
        if(selectedRoute != null) {
            // get the sub list view
            ListView stopsListView = (ListView) findViewById(R.id.stopListView);
            // construct network call
            Call<GenericListResponse<RouteLocation>> allStopsInRoute = apiService.getAllStopsInRoute(String.valueOf(selectedRoute.getId()));
            allStopsInRoute.enqueue(new RouteLocationListCallback(getApplicationContext(), stopsListView));
            // send the call back object
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
