package com.group5.model;

/**
 * Created by DELL on 30/11/2015.
 */
public class Bookmark {
    /**
     * Id
     */
    private String mId;

    /**
     * Score
     */
    private Place mPlace;


    /**
     * Get/Set properties
     */

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public Place getPlace() {
        return mPlace;
    }

    public void setPlace(Place place) {
        this.mPlace = place;
    }



    /**
     * Constructor
     * @param id
     * @param place
     */
    public Bookmark(String id, Place place) {
        this.mId = id;
        this.mPlace = place;
    }

    /**
     * Constructor
     */
    public Bookmark() {
    }
}
