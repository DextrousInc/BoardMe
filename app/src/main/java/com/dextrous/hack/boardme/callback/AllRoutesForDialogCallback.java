package com.dextrous.hack.boardme.callback;

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.fragment.RoutesDialogFragment;
import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.response.GenericListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_ERROR;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_RESPONSE;

public class AllRoutesForDialogCallback extends BaseCallback implements Callback<GenericListResponse<Route>> {

    private FragmentManager fragmentManager;

    public AllRoutesForDialogCallback(Context context, FragmentManager fragmentManager) {
        super(context);
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
            Log.d(LOG_TAG_HTTP_RESPONSE, response.body().toString());
        }
        hideDialog();
    }

    @Override
    public void onFailure(Call<GenericListResponse<Route>> call, Throwable t) {
        Log.e(LOG_TAG_HTTP_ERROR, t.getMessage(), t);
        Toast.makeText(context, BoardMeConstants.MSG_GENERIC_ERROR +t.getMessage(), Toast.LENGTH_LONG).show();
        hideDialog();
    }
}
