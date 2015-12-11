package com.group5.model;

/**
 * City of Place
 */
public class City {
    /**
     * City ID
     */
    private String mCityId;
    /**
     * Name of City
     */
    private String mCityName;


    /**
     * Get/Set Properties
     */

    public String imageURL;

    public String getName() {
        return mCityName;
    }

    public void setName(String mName) {
        this.mCityName = mName;
    }

    public String getCityId() {
        return mCityId;
    }

    public void setCityId(String mCityId) {
        this.mCityId = mCityId;
    }

    /**
     * Constructor of Cities
     * @param mCityId : City Id
     * @param mCityName : City Name
     */
    public City(String mCityId, String mCityName) {
        this.mCityId = mCityId;
        this.mCityName = mCityName;
    }

    public City() {
    }
}
