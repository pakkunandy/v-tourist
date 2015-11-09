package com.group5.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Parse Data from JSON to Model
 */
public class DataParser {


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
