package com.dextrous.hack.boardme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.callback.BoardWaitResponseCallback;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.BoardMeLocation;
import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.response.BoardWaitResponse;
import com.dextrous.hack.boardme.response.GenericBeanResponse;
import com.dextrous.hack.boardme.service.BoardMeAPIService;
import com.dextrous.hack.boardme.util.AndroidUtil;
import com.dextrous.hack.boardme.wrapper.RetrofitWrapper;

import retrofit2.Call;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.USER_AUTH_KEY_PREFERENCE_KEY;

public class BoardWaitActivity extends AppCompatActivity {
    String TAG = BoardWaitActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_wait);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String BASE_URL = getResources().getString(R.string.base_api_url);
        Log.d(TAG, "BASE_URL=" + BASE_URL);

        BoardMeAPIService apiService = RetrofitWrapper.build(BASE_URL);

        Intent intent = getIntent();
        Object temp = intent.getSerializableExtra(BoardMeConstants.INTENT_PARAM_CURRENT_USER_LOCATION_KEY);
        BoardMeLocation userLocation = temp != null ? (BoardMeLocation)temp : null;

        temp = intent.getSerializableExtra(BoardMeConstants.INTENT_PARAM_SELECTED_ROUTE_KEY);
        Route selectedRoute = temp != null ? (Route)temp : null;

        // API call 3
        User userAuthObject = AndroidUtil.getPreferenceAsObject(getApplicationContext(), USER_AUTH_KEY_PREFERENCE_KEY, User.class);
        Log.d(TAG, "User auth=" + userAuthObject);
        Log.d(TAG, "userLocation=" + userLocation);
        if(userAuthObject != null
                && userLocation != null
                && selectedRoute != null) {
            Call<GenericBeanResponse<BoardWaitResponse>> boardWaitCall = apiService.getBoardWaitData(
                    String.valueOf(selectedRoute.getId()),
                    String.valueOf(userAuthObject.getId()),
                    String.valueOf(userLocation.getLatitude()),
                    String.valueOf(userLocation.getLongitude()));
            TextView currentRouteText = (TextView) findViewById(R.id.currentRouteValueLabel);
            TextView currentStopText = (TextView) findViewById(R.id.currentStopValueLabel);
            TextView etaValueText = (TextView) findViewById(R.id.etaValueLabel);
            boardWaitCall.enqueue(new BoardWaitResponseCallback(this,
                    currentRouteText,
                    currentStopText,
                    etaValueText));
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
