package com.dextrous.hack.boardme.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.TravelHistory;
import com.dextrous.hack.boardme.service.BoardMeAPIService;
import com.dextrous.hack.boardme.wrapper.RetrofitWrapper;

public class TravelHistoryItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_history_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Object temp = getIntent().getSerializableExtra(BoardMeConstants.INTENT_PARAM_TRAVEL_HISTORY_ITEM_KEY);

        TextView selectedRouteText = (TextView) findViewById(R.id.currentRouteValueLabel);
        TextView selectedStartRouteText = (TextView) findViewById(R.id.currentStopValueLabel);
        TextView selectedEndRouteText = (TextView) findViewById(R.id.selectedDestinationValueLabel);
        TextView fareAmountText = (TextView) findViewById(R.id.fareValueLabel);
        if(temp != null
                && temp instanceof  TravelHistory) {
            TravelHistory travelHistory = (TravelHistory) temp;
            System.out.println(temp);
            selectedRouteText.setText(travelHistory.getStartRoute().getRoute().getRouteName());
            selectedStartRouteText.setText(travelHistory.getStartRoute().getStopName());
            selectedEndRouteText.setText(travelHistory.getEndRoute().getStopName());
            fareAmountText.setText(travelHistory.getUser().getCurrencyType() + travelHistory.getFareAmount());
        } else {
            temp = getIntent().getSerializableExtra(BoardMeConstants.INTENT_PARAM_TRAVEL_HISTORY_ITEM_ID_KEY);
            if (temp != null) {
                // Create a callback
                // send the input fields
                // execute the service function
                String BASE_URL = getResources().getString(R.string.base_api_url);
                Log.d("BASE_URL", BASE_URL);

                RetrofitWrapper.start(BASE_URL);
                BoardMeAPIService apiService = RetrofitWrapper.build();

            }
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
