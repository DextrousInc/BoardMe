package com.dextrous.hack.boardme.callback;

import android.app.FragmentManager;
import android.util.Log;

import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.fragment.RoutesDialogFragment;
import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.response.GenericListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllRoutesForDialogCallback implements Callback<GenericListResponse<Route>> {

    private FragmentManager fragmentManager;

    public AllRoutesForDialogCallback(FragmentManager fragmentManager) {

        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onResponse(Call<GenericListResponse<Route>> call, Response<GenericListResponse<Route>> response) {
        if(response.isSuccessful()
                && response.body() != null
                && response.body().getSuccess()
                && response.body().getItems()!= null
                && !response.body().getItems().isEmpty()) {
            RoutesDialogFragment routesDialogFragment = RoutesDialogFragment.newInstance(response.body());
            routesDialogFragment.show(fragmentManager, BoardMeConstants.TAG_DIALOG_ROUTES_LIST);
            Log.d("HTTP RESPONSE", response.body().toString());
        }
    }

    @Override
    public void onFailure(Call<GenericListResponse<Route>> call, Throwable t) {
        Log.e("HTTP ERROR", t.getMessage(), t);
    }
}
