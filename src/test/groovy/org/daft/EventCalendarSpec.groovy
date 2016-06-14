package org.daft

import org.daft.domain.Event
import org.daft.domain.EventCalendar
import org.daft.domain.Venue
import spock.lang.Specification

/**
 * Created by wang on 6/5/16.
 */
class EventCalendarSpec extends Specification {
    EventCalendar eventCalendar;
    Event testEvent;
    Venue testVenue;

    def setup() {
        testVenue = new Venue("venueName", "address");
        testEvent = new Event("eventName", new Date(), testVenue);
        eventCalendar = new EventCalendar();
    }

    def "It stores a new event" () {
        given:
        eventCalendar.add(testEvent);

        when:
        Map<String, Event> storedEvents = eventCalendar.getByDate(new Date());

        then:
        assert storedEvents != null;
        assert storedEvents.size() > 0;
        assert storedEvents.get("eventName") == testEvent;
    }

    def "It stores multiple events" () {
        given:
        eventCalendar.add(testEvent);

        Event testEvent2 = new Event("eventName2", new Date(), testVenue);
        eventCalendar.add(testEvent2);

        Event testEvent3 = new Event("eventName3", new Date(), testVenue);
        eventCalendar.add(testEvent3);

        when:
        Map<String, Event> storedEvents = eventCalendar.getByDate(new Date());

        then:
        assert storedEvents != null;
        assert storedEvents.size() == 3;
        assert storedEvents.get("eventName") == testEvent;
        assert storedEvents.get("eventName2") == testEvent2;
        assert storedEvents.get("eventName3") == testEvent3;
    }


    def "It updates events" () {
        given:
        eventCalendar.add(testEvent);

        Venue updatedVenue = new Venue("updatedVenue", "updatedPlace");
        Event testEvent2 = new Event("eventName", new Date(), updatedVenue);
        eventCalendar.add(testEvent2);

        when:
        Map<String, Event> storedEvents = eventCalendar.getByDate(new Date());

        then:
        assert storedEvents != null;
        assert storedEvents.size() == 1;
        assert storedEvents.get("eventName").getVenue() == updatedVenue;
    }
}
