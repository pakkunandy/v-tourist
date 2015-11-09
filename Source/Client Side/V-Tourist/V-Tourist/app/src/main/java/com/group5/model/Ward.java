package com.group5.model;

/**
 * Ward of Place
 */
public class Ward {

    /**
     * District contain Ward
     */
    private District mDistrict;
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
    public District getDistrict() {
        return mDistrict;
    }

    public void setDistrict(District mDistrict) {
        this.mDistrict = mDistrict;
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
     * @param mDistrict: District contain Ward
     * @param mWardId: Ward Id
     * @param mWardName: Ward Name
     */
    public Ward(District mDistrict, String mWardId, String mWardName) {
        this.mDistrict = mDistrict;
        this.mWardId = mWardId;
        this.mWardName = mWardName;
    }

    public Ward() {
    }
}
