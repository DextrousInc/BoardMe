package com.dextrous.hack.boardme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.adapter.RouteLocationArrayAdapter;
import com.dextrous.hack.boardme.callback.BoardRouteResponseCallback;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.BoardMeLocation;
import com.dextrous.hack.boardme.model.RouteLocation;
import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.response.BoardRouteResponse;
import com.dextrous.hack.boardme.service.BoardMeAPIService;
import com.dextrous.hack.boardme.util.AndroidUtil;
import com.dextrous.hack.boardme.wrapper.RetrofitWrapper;

import java.util.ArrayList;

import retrofit2.Call;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.USER_AUTH_KEY_PREFERENCE_KEY;

public class BoardMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_me);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String BASE_URL = getResources().getString(R.string.base_api_url);
        Log.d("BASE_URL", BASE_URL);

        RetrofitWrapper.start(BASE_URL);
        BoardMeAPIService apiService = RetrofitWrapper.build();

        Intent intent = getIntent();
        Object temp = intent.getSerializableExtra(BoardMeConstants.INTENT_PARAM_CURRENT_USER_LOCATION_KEY);
        BoardMeLocation userLocation = temp != null ? (BoardMeLocation)temp : null;

        String beaconId = intent.getStringExtra(BoardMeConstants.INTENT_PARAM_NEAREST_BEACON_ID_KEY);
        //TODO remove on getting actual beacon ID
        beaconId = beaconId != null ? beaconId : "jdksfh3423479231dsd";
        // API call 2
        User userAuthObject = AndroidUtil.getPreferenceAsObject(getApplicationContext(), USER_AUTH_KEY_PREFERENCE_KEY, User.class);
        System.out.println("User auth=" + userAuthObject);
        System.out.println("userLocation=" + userLocation);
        if(userAuthObject != null
                && userLocation != null) {
            Call<BoardRouteResponse> boardMeCall = apiService.getBoardingRouteData(beaconId,
                    String.valueOf(userAuthObject.getId()),
                    String.valueOf(userLocation.getLatitude()),
                    String.valueOf(userLocation.getLongitude()));
            TextView currentRouteText = (TextView) findViewById(R.id.currentRouteValueLabel);
            TextView currentStopText = (TextView) findViewById(R.id.currentStopValueLabel);
            Spinner destinationsSpinner = (Spinner) findViewById(R.id.destinationsSpinnerInput);
            Button payNowButton = (Button) findViewById(R.id.payButton);
            ArrayAdapter<RouteLocation> routeLocationsArrayAdapter = new RouteLocationArrayAdapter(getApplicationContext(),
                    new ArrayList<RouteLocation>());
            routeLocationsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            destinationsSpinner.setAdapter(routeLocationsArrayAdapter);
            boardMeCall.enqueue(new BoardRouteResponseCallback(getApplicationContext(), destinationsSpinner,
                                                            currentRouteText, currentStopText,
                                                            routeLocationsArrayAdapter, payNowButton));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
