package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.dextrous.hack.boardme.activity.UserDetailsActivity;
import com.dextrous.hack.boardme.adapter.UserArrayAdapter;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.response.GenericListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_ERROR;
import static com.dextrous.hack.boardme.constant.BoardMeConstants.LOG_TAG_HTTP_RESPONSE;

public class UserListCallback extends BaseCallback implements Callback<GenericListResponse<User>> {
    private ListView listView;

    public UserListCallback(Context context, ListView listView) {
        super(context);
        this.listView = listView;
    }
    @Override
    public void onResponse(Call<GenericListResponse<User>> call, Response<GenericListResponse<User>> response) {
        GenericListResponse<User> apiResponse = response.body();
        if(apiResponse != null) {
            List<User> userList = apiResponse.getItems();
            if(userList != null && !userList.isEmpty()){
                ListAdapter listAdapter = new UserArrayAdapter(context, userList);
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final User item = (User) parent.getItemAtPosition(position);
                        Log.d("Item Selected", item.toString());
                        Intent intent = new Intent(context, UserDetailsActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(BoardMeConstants.INTENT_PARAM_USER_ITEM_KEY, item);
                        context.startActivity(intent);
                    }

                });
            }
            Log.d(LOG_TAG_HTTP_RESPONSE, apiResponse.toString());
        }
        hideDialog();
    }

    @Override
    public void onFailure(Call<GenericListResponse<User>> call, Throwable t) {
        Log.e(LOG_TAG_HTTP_ERROR, t.getMessage(), t);
        Toast.makeText(context, BoardMeConstants.MSG_GENERIC_ERROR + t.getMessage(), Toast.LENGTH_LONG).show();
        hideDialog();
    }
}
