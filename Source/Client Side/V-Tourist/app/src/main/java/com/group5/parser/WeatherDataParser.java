package com.group5.parser;

import com.group5.model.C16DayForecastsModel;
import com.group5.model.SubWeatherInfor;
import com.group5.model.WeatherInformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Duy on 9/26/2015.
 */
public class WeatherDataParser {

    public static WeatherInformation parseCurrentWeatherInformation(String data) throws JSONException {
        WeatherInformation rs = new WeatherInformation();

        JSONObject jObj = new JSONObject(data);

        JSONObject coordObj = getObject("coord", jObj);
        rs.setmLat(getFloat("lat", coordObj));
        rs.setmlon(getFloat("lon", coordObj));

        JSONObject sysObj = getObject("sys", jObj);
        rs.setmCountryName(getString("country", sysObj));
        rs.setmSunrise(getInt("sunrise", sysObj));
        rs.setmSunset(getInt("sunset", sysObj));
        rs.setmCityName(getString("name", jObj));

        JSONArray jArr = jObj.getJSONArray("weather");

        // We use only the first value
        JSONObject JSONWeather = jArr.getJSONObject(0);
        rs.setIdWeather(getInt("id", JSONWeather));
        rs.setmWeatherDescription(getString("description", JSONWeather));
        rs.setmMainWeather(getString("main", JSONWeather));
        rs.setmIconid(getString("icon", JSONWeather));

        JSONObject mainObj = getObject("main", jObj);
        rs.setmHumidity(getInt("humidity", mainObj));
        rs.setmPressure(getInt("pressure", mainObj));
        rs.setmTempMax(getFloat("temp_max", mainObj));
        rs.setmTempMin(getFloat("temp_min", mainObj));
        rs.setmTemp(getFloat("temp", mainObj));

        // Wind
        JSONObject wObj = getObject("wind", jObj);
        rs.setmWindSpeed(getFloat("speed", wObj));
        rs.setmWindDegree(getFloat("deg", wObj));

        // Clouds
        JSONObject cObj = getObject("clouds", jObj);
        rs.setmCloudPercent(getInt("all", cObj));

        return rs;
    }

    public static C16DayForecastsModel parse16DayForecast(String data) throws JSONException {
        C16DayForecastsModel rs = new C16DayForecastsModel();

        JSONObject jObj = new JSONObject(data);
        //city
        JSONObject cityObj = getObject("city", jObj);
        rs.mCityName = getString("name", cityObj);
        rs.mCountryName = getString("country", cityObj);
        JSONObject coordObj = getObject("coord", cityObj);
        rs.mLat = getFloat("lat", coordObj);
        rs.mLon = getFloat("lon", coordObj);
        //cnt
        rs.cnt = getInt("cnt", jObj);
        //list
        JSONArray listObj = getArray("list", jObj);
        rs.lstDayForecasts = new ArrayList<>();
        for(int i = 0; i < rs.cnt; ++i) {
            JSONObject item = listObj.getJSONObject(i);
            SubWeatherInfor subwt = new SubWeatherInfor();
            JSONObject temp = getObject("temp", item);
            subwt.mTempDay = getFloat("day", temp)  - (float)272.15;
            subwt.mTempMin = getFloat("min", temp)  - (float)272.15;
            subwt.mTempMax = getFloat("max", temp)  - (float)272.15;
            subwt.mTempNight = getFloat("night", temp)  - (float)272.15;
            subwt.mTempEve = getFloat("eve", temp)  - (float)272.15;
            subwt.mTempMorn = getFloat("morn", temp)  - (float)272.15;

            subwt.mPressure = getInt("pressure", item);
            subwt.mHumidity = getInt("humidity", item);

            JSONObject wt = getArray("weather", item).getJSONObject(0);
            subwt.idWeather = getInt("id", wt);
            subwt.mMainWeather = getString("main", wt);
            subwt.mWeatherDescription = getString("description", wt);
            subwt.mIconid = getString("icon", wt);

            subwt.mWindSpeed = getFloat("speed", item);
            subwt.mWindDegree = getFloat("deg", item);
            subwt.mCloudPercent = getInt("clouds", item);
            int day = getInt("dt", item);
            subwt.dttxt = String.valueOf(day);
            rs.lstDayForecasts.add(subwt);
        }
        return rs;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

    private static double  getDouble(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getDouble(tagName);
    }

    private static JSONArray getArray(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getJSONArray(tagName);
    }
}
