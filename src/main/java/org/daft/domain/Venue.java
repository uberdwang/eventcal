package org.daft.domain;

/**
 * Created by wang on 6/5/16.
 */
public class Venue {
    private String venueName;
    private String address;

    Venue() {

    }

    public Venue(String venueName, String address) {
        this.venueName = venueName;
        this.address = address;
    }


    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
