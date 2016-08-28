package org.daft.domain

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

    def "It stores multiple events by event name" () {
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

    def "It stores multiple events by day" () {
        given:
        Date day1 = CreateDay(1);
        Date day2 = CreateDay(2);

        Event testEvent1 = new Event("eventName1", day1, testVenue);
        eventCalendar.add(testEvent1);

        Event testEvent2 = new Event("eventName2", day2, testVenue);
        eventCalendar.add(testEvent2);

        when:
        Map<String, Event> storedEvents1= eventCalendar.getByDate(day1);
        Map<String, Event> storedEvents2= eventCalendar.getByDate(day2);
        
        then:
        assert storedEvents1 != null;
        assert storedEvents1.size() == 1;
        assert storedEvents1.get("eventName1") == testEvent1;
        assert storedEvents2 != null;
        assert storedEvents2.size() == 1;
        assert storedEvents2.get("eventName2") == testEvent2;
    }

    def "It retrieves event by name" () {
        given:
        Date day1 = CreateDay(1);
        Date day2 = CreateDay(2);
        Event testevent1 = new Event("eventName1", day1, testVenue);
        Event testevent2 = new Event("eventName1", day2, testVenue);
        eventCalendar.add(testevent1);
        eventCalendar.add(testevent2);
        
        when:
        List<Event> events = eventCalendar.getByName("eventName1");

        then:
        assert events != null;
        assert events.size() == 2;
        assert events.get(0).getEventName() == "eventName1";
        assert events.get(1).getEventName() == "eventName1";
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

    def Date CreateDay(int daysToAdd) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, daysToAdd);
        
        return cal.getTime();
    }
}
