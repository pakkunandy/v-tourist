package com.group5.service;

import com.group5.model.Ward;
import com.group5.parser.DataParser;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 27/11/2015.
 */
public class WardServices {
    public static Ward getWard(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ward");
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        ParseObject object = new ParseObject("Ward");
        object = query.get(id);

        return DataParser.parseWard(object);
    }

    public static List<Ward> getWardsList(String districtId) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ward");
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        query.whereEqualTo("district", districtId);
        List<Ward> wardsList = new ArrayList<>();

        List<ParseObject> listObject = query.find();
        for (ParseObject object: listObject) {
            wardsList.add(DataParser.parseWard(object));
        }

        return wardsList;
    }

}