package com.estebanzapata.eventr;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class NewEventActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        setUpActionBar();
        setUpSpinners();
    }

    private void setUpActionBar() {
        setTitle(R.string.new_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_event_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_save:
                saveEvent();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpSpinners() {
        ArrayList<String> months = new ArrayList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        ArrayList<String> days = new ArrayList<>();
        for (int i = 0; i < 32; ) {
            days.add(Integer.toString(++i));
        }

        ArrayList<String> years = new ArrayList<>();
        for (int i = 2015; i < 2025; ) {
            years.add(Integer.toString(++i));
        }

        SpinnerAdapter monthAdapter = new SpinnerAdapter(months, this);
        SpinnerAdapter dayAdapter = new SpinnerAdapter(days, this);
        SpinnerAdapter yearAdapter = new SpinnerAdapter(years, this);

        Spinner monthSpinner = (Spinner) findViewById(R.id.month);
        Spinner daySpinner = (Spinner) findViewById(R.id.day);
        Spinner yearSpinner = (Spinner) findViewById(R.id.year);

        monthSpinner.setAdapter(monthAdapter);
        daySpinner.setAdapter(dayAdapter);
        yearSpinner.setAdapter(yearAdapter);

        ArrayList<String> hours = new ArrayList<>();
        hours.add(Integer.toString(12));
        for (int i = 0; i < 12; ) {
            hours.add(Integer.toString(++i));
        }

        ArrayList<String> minutes = new ArrayList<>();
        for (int i = 0; i < 60; ) {
            if (i < 10) {
                minutes.add("0" + Integer.toString(i++));
                continue;
            }
            minutes.add(Integer.toString(i++));
        }

        ArrayList<String> amPm = new ArrayList<>();
        amPm.add("AM");
        amPm.add("PM");

        SpinnerAdapter hourAdapter = new SpinnerAdapter(hours, this);
        SpinnerAdapter minuteAdapter = new SpinnerAdapter(minutes, this);
        SpinnerAdapter amPmAdapter = new SpinnerAdapter(amPm, this);

        Spinner hourSpinner = (Spinner) findViewById(R.id.hour);
        Spinner minuteSpinner = (Spinner) findViewById(R.id.minute);
        Spinner amPmSpinner = (Spinner) findViewById(R.id.am_pm);

        hourSpinner.setAdapter(hourAdapter);
        minuteSpinner.setAdapter(minuteAdapter);
        amPmSpinner.setAdapter(amPmAdapter);
    }

    private void saveEvent() {
        TextView nameField = (TextView) findViewById(R.id.name);
        TextView hostField = (TextView) findViewById(R.id.host);
        TextView locationField = (TextView) findViewById(R.id.location);
        TextView tagsField = (TextView) findViewById(R.id.extra_tags);

        Spinner monthSpinner = (Spinner) findViewById(R.id.month);
        Spinner daySpinner = (Spinner) findViewById(R.id.day);
        Spinner yearSpinner = (Spinner) findViewById(R.id.year);

        Spinner hourSpinner = (Spinner) findViewById(R.id.hour);
        Spinner minuteSpinner = (Spinner) findViewById(R.id.minute);
        Spinner amPmSpinner = (Spinner) findViewById(R.id.am_pm);

        CheckBox twentyOnePlus = (CheckBox) findViewById(R.id.twenty_one_plus);
        CheckBox alcohol = (CheckBox) findViewById(R.id.alcohol);
        CheckBox rave = (CheckBox) findViewById(R.id.rave);
        CheckBox casual = (CheckBox) findViewById(R.id.casual);
        CheckBox sports = (CheckBox) findViewById(R.id.sports);
        CheckBox birthday = (CheckBox) findViewById(R.id.birthday);

        String name = nameField.getText().toString();
        String host = hostField.getText().toString();
        String location = locationField.getText().toString();
        String extraTags = tagsField.getText().toString();

        if (name.equals("") && location.equals("")) {
            Toast.makeText(getApplicationContext(), R.string.name_and_location_required, Toast.LENGTH_LONG).show();
            return;
        } else if (name.equals("")) {
            Toast.makeText(getApplicationContext(), R.string.event_name_required, Toast.LENGTH_LONG).show();
            return;
        } else if (location.equals("")) {
            Toast.makeText(getApplicationContext(), R.string.location_required, Toast.LENGTH_LONG).show();
            return;
        }

        ArrayList<String> tags = new ArrayList<>();


        if (twentyOnePlus.isChecked()) {
            tags.add(getString(R.string.twenty_one_plus) + ",");
        }


        if (alcohol.isChecked()) {
            tags.add(getString(R.string.alcohol) + ",");
        }

        if (rave.isChecked()) {
            tags.add(getString(R.string.rave) + ",");
        }

        if (casual.isChecked()) {
            tags.add(getString(R.string.casual) + ",");
        }

        if (sports.isChecked()) {
            tags.add(getString(R.string.sports) + ",");
        }

        if (birthday.isChecked()) {
            tags.add(getString(R.string.birthday) + ",");
        }

        String finalTags = "";
        for (String tag : tags) {
            finalTags += tag;
        }

        finalTags += extraTags;

        String year = yearSpinner.getItemAtPosition(yearSpinner.getSelectedItemPosition()).toString();
        String month = monthSpinner.getItemAtPosition(monthSpinner.getSelectedItemPosition()).toString();
        String day = daySpinner.getItemAtPosition(daySpinner.getSelectedItemPosition()).toString();
      //  Toast.makeText(getApplicationContext(), name + host + location + finalTags + year + month, Toast.LENGTH_LONG).show();

        String monthNumber = "";

        switch (month) {
            case "January":
                monthNumber = "1";
                break;
            case "February":
                monthNumber = "2";
                break;
            case "March":
                monthNumber = "3";
                break;
            case "April":
                monthNumber = "4";
                break;
            case "May":
                monthNumber = "5";
                break;
            case "June":
                monthNumber = "6";
                break;
            case "July":
                monthNumber = "7";
                break;
            case "August":
                monthNumber = "8";
                break;
            case "September":
                monthNumber = "9";
                break;
            case "October":
                monthNumber = "10";
                break;
            case "November":
                monthNumber = "11";
                break;
            case "December":
                monthNumber = "12";
                break;

        }

        String hour = hourSpinner.getItemAtPosition(hourSpinner.getSelectedItemPosition()).toString();
        String minute = minuteSpinner.getItemAtPosition(minuteSpinner.getSelectedItemPosition()).toString();
        String amPm = amPmSpinner.getItemAtPosition(amPmSpinner.getSelectedItemPosition()).toString();

        if (amPm.equals("PM")) {
            hour = Integer.toString(Integer.parseInt(hour) + 12);
        }

        String date = String.format("%s-%s-%s %s:%s:%s", year, monthNumber, day, hour, minute, "0");


        HttpPost post = new HttpPost("http://66.231.131.25/NewEvent.php");
        HashMap<String, String> parameters = post.parameters;

        parameters.put("name", name);
        parameters.put("host", host);
        parameters.put("location", location);
        parameters.put("tags", finalTags);
        parameters.put("event_start", date);

        post.asyncTaskRunner.execute();

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "");
        setResult(1000, returnIntent);

        finish();
    }




}
