package com.dextrous.hack.boardme.callback;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.adapter.StableArrayAdapter;
import com.dextrous.hack.boardme.model.User;
import com.dextrous.hack.boardme.response.GenericListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListCallback implements Callback<GenericListResponse<User>> {

    private Context context;
    private ListView listView;

    public UserListCallback(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }
    @Override
    public void onResponse(Call<GenericListResponse<User>> call, Response<GenericListResponse<User>> response) {
        GenericListResponse<User> apiResponse = response.body();
        if(apiResponse != null) {
            List<User> userList = apiResponse.getItems();
            if(userList != null && !userList.isEmpty()){
                System.out.println((listView != null) + " is it null");
                List<String> names = new ArrayList<>();
                for (User user : userList) {
                    names.add(user.getFullName());
                }
                ListAdapter listAdapter = new StableArrayAdapter(context, userList, names.toArray(new String[]{}));
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        Log.d("Item Selected", item);
                                /*view.animate().setDuration(2000).alpha(0)
                                        .withEndAction(new Runnable() {
                                            @Override
                                            public void run() {
                                                list.remove(item);
                                                adapter.notifyDataSetChanged();
                                                view.setAlpha(1);
                                            }
                                        });*/
                    }

                });
            }
            Log.d("HTTP RESPONSE", apiResponse.toString());
        }
    }

    @Override
    public void onFailure(Call<GenericListResponse<User>> call, Throwable t) {

    }
}
