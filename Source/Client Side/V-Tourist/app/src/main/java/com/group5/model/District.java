package com.group5.model;

/**
 * District of Place
 */
public class District {
    /**
     * District Id
     */
    private String mDistrictId;
    /**
     * City contain District
     */
    private City mCity;
    /**
     * Name of District
     */
    private String mDistrictName;

    /**
     * Get/Set Properties
     */
    public String getDistrictId() {
        return mDistrictId;
    }

    public void setDistrictId(String mDistrictId) {
        this.mDistrictId = mDistrictId;
    }

    public City getCity() {
        return mCity;
    }

    public void setCity(City mCity) {
        this.mCity = mCity;
    }

    public String getDistrictName() {
        return mDistrictName;
    }

    public void setDistrictName(String mDistrictName) {
        this.mDistrictName = mDistrictName;
    }

    /**
     * Constructor of District
     * @param mDistrictId : District Id
     * @param mCity : City
     * @param mDistrictName: District Name
     */
    public District(String mDistrictId, City mCity, String mDistrictName) {
        this.mDistrictId = mDistrictId;
        this.mCity = mCity;
        this.mDistrictName = mDistrictName;
    }

    public District() {
    }
}
