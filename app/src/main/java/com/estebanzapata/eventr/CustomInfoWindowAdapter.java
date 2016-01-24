package com.estebanzapata.eventr;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Esteban on 1/24/2016.
 */


public class CustomInfoWindowAdapter implements InfoWindowAdapter {
    private ArrayList<Event> events;
    private View markerView = null;
    private LayoutInflater inflater = null;

    public CustomInfoWindowAdapter(LayoutInflater inflater) {
        this.inflater = inflater;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        if(markerView == null) {
            markerView = inflater.inflate(R.layout.infowindow, null);
        }

        String uid = marker.getTitle();
        Event event = MainActivity.eventHashMap.get(uid);

        TextView infoName = (TextView) markerView.findViewById(R.id.info_name);
        infoName.setText(event.getName());

        TextView infoHost = (TextView) markerView.findViewById(R.id.info_host);
        infoHost.setText(event.getHost());

        TextView infoLocation = (TextView)markerView.findViewById(R.id.info_location);
        infoLocation.setText(event.getLocation());

        TextView infoEventStart = (TextView)markerView.findViewById(R.id.info_event_start);
        String eventStart = event.getEventStart();
        eventStart = eventStart.substring(0, eventStart.length() - 3);
        infoEventStart.setText(eventStart);

        TextView infoTags = (TextView) markerView.findViewById(R.id.info_tags);
        infoTags.setText(event.getTags());


        /*TextView name = (TextView) markerView.findViewById(R.id.infowindow_name);
        TextView host = (TextView) markerView.findViewById(R.id.infowindow_name);
        TextView location = (TextView) markerView.findViewById(R.id.infowindow_name);
        TextView time = (TextView) markerView.findViewById(R.id.infowindow_name);
        TextView tags = (TextView) markerView.findViewById(R.id.infowindow_name);*/

        //name.setText("FFFFFFFFFF\nUUUUUUUUU");
        //host.setText(event.getHost());
        //location.setText(event.getLocation());
        //time.setText(event.getEventStart());
        //tags.setText(event.getTags());



        return markerView;
    }


}
