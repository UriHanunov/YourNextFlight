package com.example.yournextflight;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 *
 */
public class FlightList extends ArrayAdapter<Flight> {

    private Activity context;
    private List<Flight> flightList;

    public FlightList(Activity context, List<Flight> flightList){
        super(context, R.layout.list_layout, flightList);
        this.context=context;
        this.flightList=flightList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();

        View listViewItem= inflater.inflate(R.layout.list_layout, null, true);

        TextView TextViewDestination =(TextView) listViewItem.findViewById(R.id.textViewDestination);
        TextView TextViewSource =(TextView) listViewItem.findViewById(R.id.textViewSource);
        TextView TextViewDate =(TextView) listViewItem.findViewById(R.id.textViewDate);
        TextView TextViewPrice =(TextView) listViewItem.findViewById(R.id.textViewPrice);


        Flight flight = flightList.get(position);

        TextViewDestination.setText("To: " + flight.getDestination());
        TextViewSource.setText("From: " +flight.getSource());
        TextViewDate.setText(flight.getDate());
        TextViewPrice.setText(Integer.toString(flight.getprice()));

        return listViewItem;

    }
}
