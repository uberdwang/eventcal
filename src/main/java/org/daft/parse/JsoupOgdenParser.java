package org.daft.parse;

import org.daft.domain.Event;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by wang on 8/28/16.
 */
public class JsoupOgdenParser implements EventParser {
    private Document document;

    JsoupOgdenParser(Document doc) {
        document = doc;
    }

    @Override
    public List<Event> parseEvents() {
        return null;
    }
}
