package com.dextrous.hack.boardme.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.model.User;

import java.util.List;

public class UserArrayAdapter extends ArrayAdapter<User> {

    private final Context context;
    private final List<User> values;
    String TAG = UserArrayAdapter.class.getName();

    public UserArrayAdapter(Context context, List<User> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.user_list_item_view, parent, false);
        TextView userNameText = (TextView) rowView.findViewById(R.id.userNameValue);
        TextView mobileText = (TextView) rowView.findViewById(R.id.userMobileValue);
        User item = values.get(position);
        userNameText.setText(item.getFullName());
        // change the icon for Windows and iPhone
        mobileText.setText(item.getMobile());
        Log.d(TAG, item.toString());
        return rowView;
    }

    @Override
    public long getItemId(int position) {
        return values.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}