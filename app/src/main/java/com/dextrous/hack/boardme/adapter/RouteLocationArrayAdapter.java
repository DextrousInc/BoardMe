package com.dextrous.hack.boardme.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dextrous.hack.boardme.model.RouteLocation;

import java.util.List;

public class RouteLocationArrayAdapter extends ArrayAdapter<RouteLocation>{

    private final Context context;
    private final List<RouteLocation> values;

    public RouteLocationArrayAdapter(Context context, List<RouteLocation> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        label.setText(" " + values.get(position).getStopName());
        label.setHeight(150);
        label.setGravity(Gravity.LEFT | Gravity.CENTER);
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
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