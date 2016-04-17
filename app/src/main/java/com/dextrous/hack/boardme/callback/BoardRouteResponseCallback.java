package com.dextrous.hack.boardme.callback;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.RouteLocation;
import com.dextrous.hack.boardme.model.TravelHistory;
import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.response.BoardRouteResponse;
import com.dextrous.hack.boardme.response.GenericBeanResponse;
import com.dextrous.hack.boardme.service.BoardMeAPIService;
import com.dextrous.hack.boardme.util.AndroidUtil;
import com.dextrous.hack.boardme.wrapper.RetrofitWrapper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardRouteResponseCallback implements Callback<BoardRouteResponse> {
    private Context context;
    private Spinner destionationSpinner;
    private TextView currentRouteText;
    private TextView currentStopText;
    private Button payNowButton;
    private ArrayAdapter<RouteLocation> routeLocationsArrayAdapter;

    public BoardRouteResponseCallback(Context context, Spinner destionationSpinner,
                                      TextView currentRouteText, TextView currentStopText,
                                      ArrayAdapter<RouteLocation> routeLocationsAdapter, Button payNowButton) {
        this.context = context;
        this.destionationSpinner = destionationSpinner;

        this.currentRouteText = currentRouteText;
        this.currentStopText = currentStopText;
        this.routeLocationsArrayAdapter = routeLocationsAdapter;
        this.payNowButton = payNowButton;
    }

    @Override
    public void onResponse(Call<BoardRouteResponse> call, Response<BoardRouteResponse> response) {
        final User loggedUser = AndroidUtil.getPreferenceAsObject(context, BoardMeConstants.USER_AUTH_KEY_PREFERENCE_KEY, User.class);
        if(response.isSuccessful()
                && response.body() != null
                && response.body().getSelectedRoute() != null
                && loggedUser != null) {
            final BoardRouteResponse boardRouteResponse = response.body();
            currentRouteText.setText(boardRouteResponse.getSelectedRoute().getRouteName());
            currentStopText.setText(boardRouteResponse.getSelectedStop().getStopName());
            routeLocationsArrayAdapter.addAll(boardRouteResponse.getNextStops());
            routeLocationsArrayAdapter.notifyDataSetChanged();
            payNowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RouteLocation selectedDestination = routeLocationsArrayAdapter.getItem(destionationSpinner.getSelectedItemPosition());
                    // Create the BoardMeRequestBody
                    Map<String, Object> requestBodyMap = new HashMap<>();
                    requestBodyMap.put("routeStartId", boardRouteResponse.getSelectedStop().getId());
                    requestBodyMap.put("userId", loggedUser.getId());
                    requestBodyMap.put("routeEndId", selectedDestination.getId());

                    // make another network call to the service
                    BoardMeAPIService apiService = RetrofitWrapper.build();
                    String BASE_URL = context.getResources().getString(R.string.base_api_url);
                    Log.d("BASE_URL", BASE_URL);
                    RetrofitWrapper.start(BASE_URL);
                    Call<GenericBeanResponse<TravelHistory>> genericBeanResponseCall = apiService.addTravelHistory(requestBodyMap);
                    genericBeanResponseCall.enqueue(new BoardMePayTicketResponseCallback(context));
                }
            });
            Log.d("HTTP RESPONSE", response.body().toString());
        }
    }

    @Override
    public void onFailure(Call<BoardRouteResponse> call, Throwable t) {
        Log.e("HTTP ERROR", t.getMessage(), t);
    }
}
