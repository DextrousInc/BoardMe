package com.dextrous.hack.boardme.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.Route;
import com.dextrous.hack.boardme.model.RouteLocation;

import java.text.MessageFormat;
import java.util.List;

public class RouteLocationListArrayAdapter extends ArrayAdapter<RouteLocation>{

    private final Context context;
    private final List<RouteLocation> values;

    public RouteLocationListArrayAdapter(Context context, List<RouteLocation> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.route_location_item_view, parent, false);
        TextView stopNameText = (TextView) rowView.findViewById(R.id.stopNameLabel);
        TextView stopOrderText = (TextView) rowView.findViewById(R.id.stopOrderValueLabel);
        TextView farePercentText = (TextView) rowView.findViewById(R.id.farePercentValueLabel);
        if(stopNameText != null
                && stopOrderText != null
                && farePercentText != null) {
            RouteLocation item = values.get(position);
            stopNameText.setText(item.getStopName());
            // change the icon for Windows and iPhone
            stopOrderText.setText(String.valueOf(item.getStopOrder()));
            farePercentText.setText(item.getFarePercent() + BoardMeConstants.SYMBOL_PERCENT);
            System.out.println(item);
        }
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