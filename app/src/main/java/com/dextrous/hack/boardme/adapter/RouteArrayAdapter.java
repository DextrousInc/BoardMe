package com.dextrous.hack.boardme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.Route;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class RouteArrayAdapter extends ArrayAdapter<Route> {

    private final Context context;
    private final List<Route> values;

    public RouteArrayAdapter(Context context, List<Route> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.route_item_view, parent, false);
        TextView routeInfoText = (TextView) rowView.findViewById(R.id.routeInfoLabel);
        TextView fromStopText = (TextView) rowView.findViewById(R.id.fromStopValueLabel);
        TextView toStopText = (TextView) rowView.findViewById(R.id.toStopValueLabel);
        if(routeInfoText != null
                && fromStopText != null
                && toStopText != null) {
            Route item = values.get(position);
            routeInfoText.setText(MessageFormat.format(BoardMeConstants.MSG_ROUTE_INFO_WITH_FARE,
                    new Object[]{
                            item.getRouteName(),
                            item.getRouteFare()
                    }));
            // change the icon for Windows and iPhone
            fromStopText.setText(item.getRouteStart());
            toStopText.setText(item.getRouteEnd());
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