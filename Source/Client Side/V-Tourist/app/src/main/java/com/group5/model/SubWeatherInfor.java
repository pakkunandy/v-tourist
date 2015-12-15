package com.group5.model;

/**
 * Created by Duy on 9/27/2015.
 */
public class SubWeatherInfor {
    public SubWeatherInfor() {}
    //temp
    public float mTempDay;
    public float mTempMin;
    public float mTempMax;
    public float mTempNight;
    public float mTempEve;
    public float mTempMorn;

    public long mPressure;
    public long mHumidity;
    //weather
    public long idWeather;
    public String mMainWeather;
    public String mWeatherDescription;
    public String mIconid;

    public float mWindSpeed;
    public float mWindDegree;
    public long mCloudPercent;
    public float mRain;
    public String dttxt;
}
