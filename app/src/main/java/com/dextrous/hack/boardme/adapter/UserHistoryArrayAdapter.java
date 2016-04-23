package com.dextrous.hack.boardme.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dextrous.hack.boardme.R;
import com.dextrous.hack.boardme.constant.BoardMeConstants;
import com.dextrous.hack.boardme.model.TravelHistory;

import java.text.MessageFormat;
import java.util.List;

public class UserHistoryArrayAdapter extends ArrayAdapter<TravelHistory> {

    private final Context context;
    private final List<TravelHistory> values;
    String TAG = UserHistoryArrayAdapter.class.getName();

    public UserHistoryArrayAdapter(Context context, List<TravelHistory> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.history_item_view, parent, false);
        TextView historyInfoText = (TextView) rowView.findViewById(R.id.historyInfoLabel);
        TextView fromStopText = (TextView) rowView.findViewById(R.id.fromStopValueLabel);
        TextView toStopText = (TextView) rowView.findViewById(R.id.toStopValueLabel);
        TravelHistory item = values.get(position);
        if(historyInfoText != null
                && fromStopText != null
                && toStopText != null) {
            historyInfoText.setText(MessageFormat.format(BoardMeConstants.MSG_TRAVEL_INFO_WITH_FARE,
                    new Object[]{
                            item.getStartRoute().getRoute().getRouteName(),
                            item.getCreatedTS()
                    }));
            fromStopText.setText(item.getStartRoute().getStopName());
            toStopText.setText(item.getEndRoute().getStopName());
        }
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