package com.group5.model;

/**
 * Created by Duy on 9/26/2015.
 */
public class WeatherInformation {
    double mlon;
    double mLat;
    String mMainWeather;
    String mWeatherDescription;
    String mIconid;
    float mTemp;
    long mPressure;
    long mHumidity;
    float mTempMax;
    float mTempMin;
    float mWindSpeed;
    float mWindDegree;
    long mCloudPercent;
    long mDate;
    String mCountryName;
    String mCityName;
    long mSunrise;
    long mSunset;
    long idWeather;

    public WeatherInformation() {
    }

    public long getIdWeather() {
        return idWeather;
    }

    public void setIdWeather(long idWeather) {
        this.idWeather = idWeather;
    }

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public double getmlon() {
        return mlon;
    }

    public void setmlon(double mlon) {
        this.mlon = mlon;
    }

    public double getmLat() {
        return mLat;
    }

    public void setmLat(double mLat) {
        this.mLat = mLat;
    }

    public String getmMainWeather() {
        return mMainWeather;
    }

    public void setmMainWeather(String mMainWeather) {
        this.mMainWeather = mMainWeather;
    }

    public String getmWeatherDescription() {
        return mWeatherDescription;
    }

    public void setmWeatherDescription(String mWeatherDescription) {
        this.mWeatherDescription = mWeatherDescription;
    }

    public String getmIconid() {
        return mIconid;
    }

    public void setmIconid(String mIconid) {
        this.mIconid = mIconid;
    }

    public float getmTemp() {
        return mTemp;
    }

    public void setmTemp(float mTemp) {
        this.mTemp = mTemp;
    }

    public long getmPressure() {
        return mPressure;
    }

    public void setmPressure(long mPressure) {
        this.mPressure = mPressure;
    }

    public long getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(long mHumidity) {
        this.mHumidity = mHumidity;
    }

    public float getmTempMax() {
        return mTempMax;
    }

    public void setmTempMax(float mTempMax) {
        this.mTempMax = mTempMax;
    }

    public float getmTempMin() {
        return mTempMin;
    }

    public void setmTempMin(float mTempMin) {
        this.mTempMin = mTempMin;
    }

    public float getmWindSpeed() {
        return mWindSpeed;
    }

    public void setmWindSpeed(float mWindSpeed) {
        this.mWindSpeed = mWindSpeed;
    }

    public float getmWindDegree() {
        return mWindDegree;
    }

    public void setmWindDegree(float mWindDegree) {
        this.mWindDegree = mWindDegree;
    }

    public long getmCloudPercent() {
        return mCloudPercent;
    }

    public void setmCloudPercent(long mCloudPercent) {
        this.mCloudPercent = mCloudPercent;
    }

    public long getmDate() {
        return mDate;
    }

    public void setmDate(long mDate) {
        this.mDate = mDate;
    }

    public String getmCountryName() {
        return mCountryName;
    }

    public void setmCountryName(String mCountryName) {
        this.mCountryName = mCountryName;
    }

    public long getmSunrise() {
        return mSunrise;
    }

    public void setmSunrise(long mSunrise) {
        this.mSunrise = mSunrise;
    }

    public long getmSunset() {
        return mSunset;
    }

    public void setmSunset(long mSunset) {
        this.mSunset = mSunset;
    }
}
