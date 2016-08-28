package org.daft.domain;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Created by wang on 6/5/16.
 */
public class EventCalendar {
    private Map<EventDay, Map<String, Event>> events;

    public EventCalendar() {
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
    
    public List<Event> getByName(String name) {
        List<Event> result = new ArrayList<>();

        events.forEach( (k,v) -> {
            if (v.containsKey(name)) {
                result.add(v.get(name));
            }
        });

        return result;
    }
    
}
