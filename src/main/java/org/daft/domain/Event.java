package org.daft.domain;

import java.util.Date;

/**
 * Created by wang on 6/5/16.
 */
public class Event {
    private String eventName;
    private Date date;
    private Venue venue;

    Event(String eventName, Date date, Venue venue) {
        this.eventName = eventName;
        this.date = date;
        this.venue = venue;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
