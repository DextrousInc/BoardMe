package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.response.GenericBeanResponse;
import com.dextrous.hack.boardme.util.AndroidUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.USER_AUTH_KEY_PREFERENCE_KEY;


public class LoginResponseCallback implements Callback<GenericBeanResponse<User>> {

    private int indexOfTab;
    private ViewFlipper flipper;
    private Context context;

    public LoginResponseCallback(Context context, ViewFlipper flipper, int tabIndex) {
        this.flipper = flipper;
        this.indexOfTab = tabIndex;
        this.context = context;
    }

    @Override
    public void onResponse(Call<GenericBeanResponse<User>> call, Response<GenericBeanResponse<User>> response) {

        GenericBeanResponse<User> apiResponse = response.body();
        if(response != null) {
        Log.d("HTTP RESPONSE", response.body().toString());
            if(apiResponse.getSuccess()){
                User user = apiResponse.getItem();
                AndroidUtil.setObjectPreferenceAsString(context, USER_AUTH_KEY_PREFERENCE_KEY, user);
                flipper.setDisplayedChild(indexOfTab);
            } else {
                // show error message
                Toast.makeText(context, "Error authenticating!!!" + apiResponse.getError(), Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onFailure(Call<GenericBeanResponse<User>> call, Throwable t) {

    }
}
