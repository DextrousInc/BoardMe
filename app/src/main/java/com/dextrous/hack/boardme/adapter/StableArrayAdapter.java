package com.dextrous.hack.boardme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.model.User;

import java.util.ArrayList;
import java.util.List;

public class StableArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<User> values;

    public StableArrayAdapter(Context context, List<User> values, String[] names) {
        super(context, -1, names);
        this.context = context;
        this.values = values;
    }

    private String[] getNamesList(List<User> list){
         List<String> names = new ArrayList<String>();
        for (User user : values) {
            names.add(user.getFullName());
        }
        return names.toArray(new String[]{});
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.user_list_view, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
        TextView textView2 = (TextView) rowView.findViewById(R.id.secondLine);
        User item = values.get(position);
        textView.setText(item.getFullName());
        // change the icon for Windows and iPhone
        textView2.setText(item.getEmail());
        System.out.println(item);
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