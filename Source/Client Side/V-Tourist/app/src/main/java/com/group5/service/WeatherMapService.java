package com.group5.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Duy on 9/26/2015.
 */
public class WeatherMapService {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String RAW_URL = "http://api.openweathermap.org/data/2.5/weather?";
    private static String IMG_URL = "http://openweathermap.org/img/w/";

    private static String BASE16_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    private static String BASE5_URL = "http://api.openweathermap.org/data/2.5/forecast?";

    public String getCurrentWeatherData(String lon, String lat) {
        String url = RAW_URL + "lat=" + lat + "&lon=" + lon;

        return getWeatherData(url);
    }

    public String get5dayForecastData(String lon, String lat) {
        String url = BASE5_URL + "lat=35&lon=139";

        return getWeatherData(url);
    }

    public String get16dayForecastData(String cityname, String countryCode, String cnt) {
        String url = BASE16_URL + "q=" + cityname + "," + countryCode + "&cnt=" + cnt;

        return getWeatherData(url);
    }

    public String get16dayForecastDataCoordinate(String lon, String lat, String cnt) {
        String url = BASE16_URL + "lat=" + lat + "&lon=" + lon + "&cnt=" + cnt + "&appid=2de143494c0b295cca9337e1e96b00e0"; // API ID

        return getWeatherData(url);
    }

    public String getWeatherData(String url) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;
    }

}
