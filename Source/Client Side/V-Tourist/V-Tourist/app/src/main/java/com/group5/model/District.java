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
     * City Id
     */
    private String mCityId;
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

    public String getCityId() {
        return mCityId;
    }

    public void setCityId(String mCityId) {
        this.mCityId = mCityId;
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
     * @param mCityId : City Id
     * @param mDistrictName: District Name
     */
    public District(String mDistrictId, String mCityId, String mDistrictName) {
        this.mDistrictId = mDistrictId;
        this.mCityId = mCityId;
        this.mDistrictName = mDistrictName;
    }
}
