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
     * Attribute Name
     */
    private String mAttributeName;

    /**
     * Get/Set Properties
     */
    public String getAttributeName() {
        return mAttributeName;
    }

    public void setAttributeName(String mAttributeName) {
        this.mAttributeName = mAttributeName;
    }

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
     * @param mAttributeName : Attribute Name
     */
    public City(String mCityId, String mCityName, String mAttributeName) {
        this.mCityId = mCityId;
        this.mCityName = mCityName;
        this.mAttributeName = mAttributeName;
    }
}
