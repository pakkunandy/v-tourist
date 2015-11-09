package com.group5.model;

/**
 * Ward of Place
 */
public class Ward {
    /**
     * City Id
     */
    private String mCityId;
    /**
     * District Id
     */
    private String mDistrictId;
    /**
     * Ward Id
     */
    private String mWardId;
    /**
     * Name of Ward
     */
    private String mWardName;

    /**
     * Get/Set Properties
     */
    public String getCityId() {
        return mCityId;
    }

    public void setCityId(String mCityId) {
        this.mCityId = mCityId;
    }

    public String getDistrictId() {
        return mDistrictId;
    }

    public void setDistrictId(String mDistrictId) {
        this.mDistrictId = mDistrictId;
    }

    public String getWardId() {
        return mWardId;
    }

    public void setWardId(String mWardId) {
        this.mWardId = mWardId;
    }

    public String getWardName() {
        return mWardName;
    }

    public void setWardName(String mWardName) {
        this.mWardName = mWardName;
    }

    /**
     * Constructor of Ward
     * @param mCityId: City Id
     * @param mDistrictId: District Id
     * @param mWardId: Ward Id
     * @param mWardName: Ward Name
     */
    public Ward(String mCityId, String mDistrictId, String mWardId, String mWardName) {
        this.mCityId = mCityId;
        this.mDistrictId = mDistrictId;
        this.mWardId = mWardId;
        this.mWardName = mWardName;
    }
}
