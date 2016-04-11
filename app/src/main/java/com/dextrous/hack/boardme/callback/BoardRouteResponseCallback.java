package com.dextrous.hack.boardme.callback;


import android.util.Log;

import com.dextrous.hack.boardme.response.BoardRouteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardRouteResponseCallback implements Callback<BoardRouteResponse> {
    @Override
    public void onResponse(Call<BoardRouteResponse> call, Response<BoardRouteResponse> response) {
        Log.d("HTTP RESPONSE", response.body().toString());
    }

    @Override
    public void onFailure(Call<BoardRouteResponse> call, Throwable t) {
        Log.e("HTTP ERROR", t.getMessage(), t);
    }
}
