package com.group5.controller;

import android.util.Log;


import com.group5.model.C16DayForecastsModel;
import com.group5.model.WeatherInformation;
import com.group5.parser.WeatherDataParser;
import com.group5.service.WeatherMapService;

import org.json.JSONException;

/**
 * Created by Duy on 9/27/2015.
 */
public class WeatherHelper {

    public WeatherInformation getCurrentWeatherbyCoord(String lon, String lat){
        WeatherMapService weatherMapService = new WeatherMapService();
        String data = weatherMapService.getCurrentWeatherData(lon, lat);
        try {
            return WeatherDataParser.parseCurrentWeatherInformation(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public C16DayForecastsModel get16DayForecastsbyCityName(String cityname, String countryCode, String cnt){
        WeatherMapService weatherMapService = new WeatherMapService();

        String data = weatherMapService.get16dayForecastData(cityname, countryCode, cnt);

        try {
            return WeatherDataParser.parse16DayForecast(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public C16DayForecastsModel get16DayForecastsbyCoordinate(String lon, String lat, String cnt){
        WeatherMapService weatherMapService = new WeatherMapService();

        String data = weatherMapService.get16dayForecastDataCoordinate(lon, lat, cnt);

        try {
            return WeatherDataParser.parse16DayForecast(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
