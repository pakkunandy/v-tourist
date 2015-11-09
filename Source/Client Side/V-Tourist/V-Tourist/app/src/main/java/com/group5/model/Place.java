package com.group5.model;


public class Place {
    /**
     * Place Id
     */
    private String mPlaceId;
    /**
     * Description of Place
     */
    private String mPlaceDescription;
    /**
     * Address of Place
     */
    private String mAddress;
    /**
     * Longitude of Place (Coordinate)
     */
    private long mLongitude;
    /**
     * Latitude of Place (Coordinate)
     */
    private long mLatitude;
    /**
     * Long Description of Place
     */
    private String mLongDescription;
    /**
     * City of Place
     */
    private City mCity;
    /**
     * District of Place
     */
    private District mDistrict;
    /**
     * Ward of Place
     */
    private Ward mWard;
    /**
     * Phone Number
     */
    private String mPhone;
    /**
     * Type of Place
     */
    private Type mType;

    /**
     * Get/Set Properties
     */
    public String getPlaceId() {
        return mPlaceId;
    }

    public void setPlaceId(String mPlaceId) {
        this.mPlaceId = mPlaceId;
    }

    public String getPlaceDescription() {
        return mPlaceDescription;
    }

    public void setPlaceDescription(String mPlaceDescription) {
        this.mPlaceDescription = mPlaceDescription;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public long getLongitude() {
        return mLongitude;
    }

    public void setLongitude(long mLongitude) {
        this.mLongitude = mLongitude;
    }

    public long getLatitude() {
        return mLatitude;
    }

    public void setLatitude(long mLatitude) {
        this.mLatitude = mLatitude;
    }

    public String getLongDescription() {
        return mLongDescription;
    }

    public void setLongDescription(String mLongDescription) {
        this.mLongDescription = mLongDescription;
    }

    public City getCity() {
        return mCity;
    }

    public void setCity(City mCity) {
        this.mCity = mCity;
    }

    public District getDistrict() {
        return mDistrict;
    }

    public void setDistrict(District mDistrict) {
        this.mDistrict = mDistrict;
    }

    public Ward getWard() {
        return mWard;
    }

    public void setWard(Ward mWard) {
        this.mWard = mWard;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type mType) {
        this.mType = mType;
    }

    /**
     * Constructor of Place
     * @param mPlaceId: Place Id
     * @param mPlaceDescription: Place Description
     * @param mAddress: Address
     * @param mLongitude: Longitude
     * @param mLatitude: Latitude
     * @param mLongDescription: Long Description
     * @param mCity: City
     * @param mDistrict: District
     * @param mWard: Ward
     * @param mPhone: Phone
     * @param mType: Type
     */
    public Place(String mPlaceId, String mPlaceDescription, String mAddress, long mLongitude, long mLatitude, String mLongDescription, City mCity, District mDistrict, Ward mWard, String mPhone, Type mType) {
        this.mPlaceId = mPlaceId;
        this.mPlaceDescription = mPlaceDescription;
        this.mAddress = mAddress;
        this.mLongitude = mLongitude;
        this.mLatitude = mLatitude;
        this.mLongDescription = mLongDescription;
        this.mCity = mCity;
        this.mDistrict = mDistrict;
        this.mWard = mWard;
        this.mPhone = mPhone;
        this.mType = mType;
    }
}
