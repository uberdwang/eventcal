package org.daft.domain;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wang on 6/5/16.
 */
public class EventDay {
    private long day;
    private Date date;

    public EventDay(Date date) {
        this.date = date;
        this.day = TimeUnit.MILLISECONDS.toDays(date.getTime());
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof EventDay)) {
            return false;
        }

        EventDay otherDay = (EventDay) other;

        return day == otherDay.getDay();
    }

    @Override
    public int hashCode() {
        return (int) day;
    }

    public long getDay() {
        return day;
    }
}
