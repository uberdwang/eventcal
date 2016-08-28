package org.daft.parse;

import org.daft.domain.Event;

import java.util.List;

/**
 * Created by wang on 8/28/16.
 */
public interface EventParser {
    List<Event> parseEvents();
}
