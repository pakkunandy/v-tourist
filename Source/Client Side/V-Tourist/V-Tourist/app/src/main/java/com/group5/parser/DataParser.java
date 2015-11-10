package com.group5.parser;

import com.group5.model.City;
import com.group5.model.District;
import com.group5.model.Place;
import com.group5.model.Rating;
import com.group5.model.Type;
import com.group5.model.User;
import com.group5.model.Ward;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse Data from JSON to Model
 */
public class DataParser {

    /**
     * Parse JSON Data get from Service about Places
     * @param data: JSON String Data List of Place
     * @return List of Place for Display
     * @throws JSONException
     */
    public static List<Place> parsePlaces(String data) throws JSONException {

        /**
         * Cast data String to JSONObject
         */
        JSONObject jObj = new JSONObject(data);

        /**
         * Create List Place result
         */
        List<Place> rs = new ArrayList<Place>();

        /**
         * Get JSONArray
         */
        JSONArray jsonArrayPlace = jObj.getJSONArray("data");

        /**
         * Foreach Data Member
         */
        for(int i = 0; i < jsonArrayPlace.length(); i++){
            JSONObject jsonObjectPlace = jsonArrayPlace.getJSONObject(i);
            Place place = new Place();
            place.setPlaceId(getString("id",jsonObjectPlace));
            place.setAddress(getString("address", jsonObjectPlace));
            place.setPlaceName(getString("name", jsonObjectPlace));
            place.setPlaceDescription(getString("description", jsonObjectPlace));
            place.setLongDescription(getString("long_description", jsonObjectPlace));
            place.setLongitude(getLong("longitude", jsonObjectPlace));
            place.setLongitude(getLong("latitude", jsonObjectPlace));

            /**
             * Handle Object Type
             */
            Type type = new Type();
            JSONObject jsonObjectType = getObject("type", jsonObjectPlace);
            type.setTypeId(getString("id", jsonObjectType));
            type.setName(getString("name", jsonObjectType));
            type.setDescription(getString("description", jsonObjectType));
            //Set Type for Place
            place.setType(type);

            /**
             * Handle Object Ward
             */
            Ward ward = new Ward();
            JSONObject jsonObjectWard = getObject("ward", jsonObjectPlace);
            ward.setWardId(getString("id", jsonObjectWard));
            ward.setWardName(getString("name", jsonObjectWard));

            /**
             * Handle Object City
             */
            City city = new City();
            JSONObject jsonObjectCity = getObject("city", jsonObjectPlace);
            city.setCityId(getString("id", jsonObjectCity));
            city.setName(getString("name", jsonObjectCity));
            city.setAttributeName(getString("attribute_name", jsonObjectCity));

            /**
             * Handle Object District
             */
            District district = new District();
            JSONObject jsonObjectDistrict = getObject("district", jsonObjectPlace);
            district.setDistrictId(getString("id", jsonObjectCity));
            district.setDistrictName(getString("name", jsonObjectCity));
            //Set City for District
            district.setCity(city);

            //Set District for Ward
            ward.setDistrict(district);
            //Set Ward for place
            place.setWard(ward);

            rs.add(place);
        }

        return rs;
    }

    public static int parseRating(String data, Place srcPlace) throws JSONException {
        //Cast DataString to jsonObject
        JSONObject jsonObject = new JSONObject(data);

        //Get list JSON Rating
        JSONArray jsonArrayRating = getArray("data", jsonObject);
        

        //Handle each Rating
        for(int i = 0; i< jsonArrayRating.length(); i++){
            JSONObject jObjRating = jsonArrayRating.getJSONObject(i);
            User user = new User();
            user.setUsername(getString("username", jObjRating));

            Rating rating = new Rating();
            rating.setScore(getFloat("score", jObjRating));
            rating.setComment(getString("comment",jObjRating));
            rating.setUserRate(user);

        }

        return 0;
    }
    /**
     * Get object from JSON Object by tagName
     *
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static JSONObject getObject(String tagName, JSONObject jObj) throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    /**
     * Get String value from JSONObject by tagName
     *
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    /**
     * Get float value from JSONObject by tagName
     *
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static float getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    /**
     * Get int value from JSONObject by tagName
     *
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static int getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

    /**
     * Get long value from JSONObject by tagName
     *
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static long getLong(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getLong(tagName);
    }

    /**
     * Get double value from JSONObject by tagName
     *
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static double getDouble(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getDouble(tagName);
    }

    /**
     * Get Array JSON from JSONObject by tagName
     *
     * @param tagName
     * @param jObj
     * @return
     * @throws JSONException
     */
    private static JSONArray getArray(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getJSONArray(tagName);
    }
}
