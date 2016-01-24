package com.estebanzapata.eventr;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Esteban on 1/23/2016.
 */
public class SpinnerAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> listOfStrings;
    private Context context;

    public SpinnerAdapter(ArrayList<String> listOfStrings, Context context) {
        this.listOfStrings = listOfStrings;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.listOfStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listOfStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.spinner_item, null);

        String spinnerText = this.listOfStrings.get(position);

        TextView menuText = (TextView) view.findViewById(R.id.spinner_text);

        menuText.setText(spinnerText);

        return view;
    }
}
