package com.group5.service;

import com.group5.model.District;
import com.group5.parser.DataParser;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 27/11/2015.
 */

public class DistrictServices {
    public static District getDistrict(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("District");
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        ParseObject object = new ParseObject("District");
        object = query.get(id);

        return DataParser.parseDistrict(object);
    }

    public static List<District> getDistrictsList(String cityId) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("District");
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        query.whereEqualTo("city", cityId);
        List<District> districtList = new ArrayList<>();
        List<ParseObject> listObject = query.find();
        for (ParseObject object: listObject) {
            districtList.add(DataParser.parseDistrict(object));
        }

        return districtList;
    }
}
