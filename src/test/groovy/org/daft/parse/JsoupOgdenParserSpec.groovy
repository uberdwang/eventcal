package org.daft.parse

import org.jsoup.nodes.Document
import spock.lang.Specification

/**
 * Created by wang on 8/28/16.
 */
class JsoupOgdenParserSpec extends Specification {
    EventParser testParser;
    Document doc;

    def setup() {
        doc = setupTestDoc();

        testParser = new JsoupOgdenParser(doc);
    }

    def "It parses a document" () {
        given:
        when:
        then:
        assert true;
    }

    def Document setupTestDoc() {
        Document temp = new Document();

        //All our fake document set up

        return temp;
    }
}