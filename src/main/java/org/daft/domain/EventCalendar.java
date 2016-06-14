package org.daft.domain;

import java.util.*;

/**
 * Created by wang on 6/5/16.
 */
public class EventCalendar {
    private Map<EventDay, Map<String, Event>> events;

    EventCalendar() {
        events = new HashMap<>();
    }

    public void add(Event event) {
        EventDay day = new EventDay(event.getDate());

        Map<String, Event> eventList = null;

        if(events.containsKey(day)) {
            eventList = events.get(day);
        } else {
            eventList = new HashMap<>();
            events.put(day, eventList);
        }

        eventList.put(event.getEventName(), event);
    }

    public Map<String, Event> getByDate(Date date) {
        EventDay day = new EventDay(date);
        return events.get(day);
    }
}
