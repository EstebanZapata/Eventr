package com.estebanzapata.eventr;

/**
 * Created by Esteban on 1/24/2016.
 */
public class Event {

    private String location;
    private double latitude;
    private double longitude;
    private String tags;
    private String eventStart;
    private String name;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;

    public Event(String uid, String eventStart, String name, String tags, double longitude, String location, double latitude, String host) {
        this.uid = uid;
        this.eventStart = eventStart;
        this.name = name;
        this.tags = tags;
        this.longitude = longitude;
        this.location = location;
        this.latitude = latitude;
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String host;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


}
