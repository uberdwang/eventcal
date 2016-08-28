package org.daft.parse

import org.daft.domain.Event
import org.daft.domain.Venue
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import spock.lang.Specification

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Created by wang on 8/28/16.
 */
class JsoupOgdenParserSpec extends Specification {
    EventParser testParser;
    Venue testVenue;
    Document doc;

    def setup() {
        testVenue = new Venue("Ogden", "Ogden's address");
        doc = Jsoup.parse(fakeDocStr);

        testParser = new JsoupOgdenParser(doc, testVenue);
    }

    def "It parses a document" () {
        when:
            List<Event> events = testParser.parseEvents();

        then:
        assert events != null;
        assert events.size() == 2;

        Event event1 = events.get(0)
        assert event1.getEventName() == "Mac DeMarco with Homebody";
        assert event1.getDate() == convertStringToDate("Aug 30, 2016 9:00 pm");
        assert event1.getVenue() == testVenue;
        Event event2 = events.get(1)
        assert event2.getEventName() == "Blind Pilot with The River Whyless";
        assert event2.getDate() == convertStringToDate("Sep 7, 2016 8:00 pm");
        assert event2.getVenue() == testVenue;
    }

    def Date convertStringToDate(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY h:mm a");
        return dateFormat.parse(dateStr);
    }

    String fakeDocStr = '''
        <html>
            <head></head>
            <body>
                <div id="container">
                    <div class="search_container></div>
                    <div id="toolbar"></div>
                    <header></header>
                    <div id="layout">
                        <div id="content">
                            <div class="page_header_container eventlist_header_container"></div>
                            <div class>
                                <div id="eventList">
                                    <div class="entry">
                                        <div class="thumb"></div>
                                        <div class="info">
                                            <div class="title">
                                                <h5 class="accentColor animated"></h5>
                                                <h5></h5>
                                                <h3 class="carousel_item_title_small">
                                                <a href="http://www.ogdentheatre.com/events/detail/312450" title="More Info" class="">Mac DeMarco</a>
                                                </h3>
                                                <h4 class="animated">with Homebody</h4>
                                            </div>
                                            <div class="date-time-container">
                                                <span class="date">
                                                    <span class="fa fa-calendar-o"></span>
                                                    Tue, Aug 30, 2016
                                                </span>
                                                <span class="time">
                                                    <span class="fa fa-clock-o"></span>
                                                    Show 9:00 PM
                                                </span>
                                            </div>
                                            <div class="buttons"></div>
                                        </div>
                                    </div>
                                    <div class="entry">
                                        <div class="thumb"></div>
                                        <div class="info">
                                            <div class="title">
                                                <h5 class="accentColor animated">97.3 KBCO Presents</h5>
                                                <h5></h5>
                                                <h3 class="carousel_item_title_small">
                                               <a href="http://www.ogdentheatre.com/events/detail/311029" title="More Info" class="">

                                                                        Blind Pilot                 </a>
                                                </h3>
                                                <h4 class="animated">with The River Whyless</h4>
                                            </div>
                                            <div class="date-time-container">
                                                <span class="date">
                                                    <span class="fa fa-calendar-o"></span>

                                                                    Wed, Sep 7, 2016
                                                </span>
                                                <span class="time">
                                                    <span class="fa fa-clock-o"></span>

                                                                    Show
                                                        8:00 PM


                                                </span>
                                            </div>
                                            <div class="buttons"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </body>
        </htm>
    ''';
}