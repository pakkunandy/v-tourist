package com.group5.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * VTouristService connect to Web API and get Data Information
 */
public class VTouristService {
    /**
     * Url webAPI for get Data
     */
    private static final String BASE_URL = "/api/";

    /**
     * Get User Information
     *
     * @param userName
     * @param token
     * @return JSON String User Information
     */
    public String getUser(String userName, String token) {

        /**
         * Handle url
         */
        String url = BASE_URL + "users/" + userName + "?token=" + token;

        return useGetMethodGetDataString(url);

    }

    /**
     * Get Place Information
     *
     * @param city
     * @param district
     * @param ward
     * @param type
     * @param offset
     * @param limit
     * @param query
     * @return JSON String List of Places
     */
    public String getPlace(String city, String district, String ward, String type, String offset, String limit, String query) {
        /**
         * Handle url
         */
        String url = BASE_URL + "places/";
        //Check City
        if (city != null) {
            url = url + "?city=" + city;
        }
        //Check District
        if (district != null) {
            url = url + "?district=" + district;
        }
        //Check ward
        if (ward != null) {
            url = url + "?ward=" + ward;
        }
        //Check Type
        if (type != null) {
            url = url + "?type=" + type;
        }
        //Check offset
        if (offset != null) {
            url = url + "?offset=" + offset;
        }
        //Check limit
        if (limit != null) {
            url = url + "?limit=" + limit;
        }
        //Check query
        if (query != null) {
            url = url + "?query=" + query;
        }
        return useGetMethodGetDataString(url);
    }

    /**
     * Get List of Image from place
     * @param idPlace
     * @param limit
     * @param offset
     * @return JSON String List of ImageURL
     */
    public String getImagePlace(String idPlace, String limit, String offset) {
        /**
         * Handle url
         */
        String url = BASE_URL + "places/" + idPlace + "/images";
        //Check limit
        if (limit != null) {
            url = url + "?limit=" + limit;
        }
        //Check offset
        if (offset != null) {
            url = url + "?offset=" + offset;
        }

        return useGetMethodGetDataString(url);
    }

    /**
     * Get list Rating of Place
     * @param idPlace
     * @param offset
     * @param limit
     * @return JSON String List of Rating
     */
    public String getRating(String idPlace, String offset, String limit){
        /**
         * Handle url
         */
        String url = BASE_URL + "places/" + idPlace + "/ratings";
        //Check limit
        if (limit != null) {
            url = url + "?limit=" + limit;
        }
        //Check offset
        if (offset != null) {
            url = url + "?offset=" + offset;
        }

        return useGetMethodGetDataString(url);
    }
    /**
     * Get list City
     * @param offset
     * @param limit
     * @return JSON String List of City
     */
    public String getListCity(String offset, String limit){
        /**
         * Handle url
         */
        String url = BASE_URL + "cities/";
        //Check limit
        if (limit != null) {
            url = url + "?limit=" + limit;
        }
        //Check offset
        if (offset != null) {
            url = url + "?offset=" + offset;
        }

        return useGetMethodGetDataString(url);
    }
    /**
     * Get list District of City
     * @param idCity
     * @param offset
     * @param limit
     * @return JSON String List of District
     */
    public String getListDistrictsOfCity(String idCity, String offset, String limit){
        /**
         * Handle url
         */
        String url = BASE_URL + "cities/"+idCity+"/districts/";
        //Check limit
        if (limit != null) {
            url = url + "?limit=" + limit;
        }
        //Check offset
        if (offset != null) {
            url = url + "?offset=" + offset;
        }

        return useGetMethodGetDataString(url);
    }

    /**
     * Get list Ward of District
     * @param idCity
     * @param idDistrict
     * @param offset
     * @param limit
     * @return JSON String List of Ward
     */
    public String getListWardOfDistrict(String idCity, String idDistrict, String offset, String limit){
        /**
         * Handle url
         */
        String url = BASE_URL + "cities/"+idCity+"/districts/" + idDistrict+"/ward";
        //Check limit
        if (limit != null) {
            url = url + "?limit=" + limit;
        }
        //Check offset
        if (offset != null) {
            url = url + "?offset=" + offset;
        }

        return useGetMethodGetDataString(url);
    }

    /**
     * Get Method get Data String
     * @param url
     * @return
     */
    public static String useGetMethodGetDataString(String url) {
        /**
         * Declare Connection and InputStream
         */
        HttpURLConnection con = null;
        InputStream is = null;

        try {
            /**
             * Create Connection
             */
            con = (HttpURLConnection) (new URL(url)).openConnection();
            //Set Request Method
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            //Connect
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");
            //Close Connection and Stream
            is.close();
            con.disconnect();
            //Return Data
            return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;
    }

    /**
     * Get Byte Array of Image
     * @param url
     * @return Byte Array
     */
    public static byte[] useGetByteArrayOfImage(String url) {
        /**
         * Declare Connection and InputStream
         */
        HttpURLConnection con = null;
        InputStream is = null;

        try {
            /**
             * Create Connection
             */
            con = (HttpURLConnection) (new URL(url)).openConnection();
            //Set Request Method
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            //Connect
            con.connect();

            // Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(buffer) != -1)
                baos.write(buffer);

            return baos.toByteArray();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Throwable t) {
            }
            try {
                con.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;
    }
}
