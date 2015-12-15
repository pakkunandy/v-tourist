package com.group5.model;

import java.util.List;

/**
 * Created by Duy on 9/27/2015.
 */
public class C16DayForecastsModel {
    //city
    public double mLon;
    public double mLat;
    public String mCountryName;
    public String mCityName;

    //cnt
    public int cnt;

    //list
    public List<SubWeatherInfor> lstDayForecasts;

    public C16DayForecastsModel() {
    }
}