package org.daft.parse;

import org.daft.domain.Event;
import org.daft.domain.Venue;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wang on 8/28/16.
 */
public class JsoupOgdenParser implements EventParser {
    private Document document;
    private Venue venue;

    JsoupOgdenParser(Document doc, Venue venue) {
        document = doc;
        this.venue = venue;
    }

    @Override
    public List<Event> parseEvents() {
        List<Event> results = new ArrayList<>();

        Element body = document.body();
        Element eventList = body.getElementById("eventList");
        Elements entries = eventList.getElementsByClass("entry");
        entries.forEach( e -> {
            Elements title1 = e.getElementsByTag("a");
            String tileStr = title1.get(0).html().trim();

            Elements title2 = e.getElementsByTag("h4");
            tileStr += " " + title2.get(0).html().trim();

            Elements date = e.getElementsByClass("date");
            String dateStr = date.get(0).ownText().trim();

            Elements time = e.getElementsByClass("time");
            String timeStr = time.get(0).ownText().trim();

            String dateTime = getDate(dateStr) + " " + getTime(timeStr);

            results.add(new Event(tileStr, convertStringToDate(dateTime), venue));
        });

        return results;
    }

    private Date convertStringToDate(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY h:mm a");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getDate(String date) {
        return date.substring(5);
    }

    private String getTime(String time) {
        return time.substring(5);
    }
}
