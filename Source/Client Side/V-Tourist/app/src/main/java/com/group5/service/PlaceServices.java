package com.group5.service;

import com.group5.model.District;
import com.group5.model.Image;
import com.group5.model.Place;
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

public class PlaceServices {
    public static Place getPlace(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject object = new ParseObject("Place");
        object = query.get(id);

        return DataParser.parsePlace(object);
    }
    private static ParseQuery<ParseObject> createPlaceCityQuery(String city) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        List<String> listDistrictIds = new ArrayList<>();
        List<District> listDistricts = new ArrayList<>();
        List<String> listWardIds = new ArrayList<>();
        List<Ward> listWards = new ArrayList<>();
        listDistricts = DistrictServices.getDistrictsList(city);
        for (District district: listDistricts) {
            listWards = WardServices.getWardsList(district.getDistrictId());
            for (Ward ward: listWards ) {
                listWardIds.add(ward.getWardId());
            }
        }
        query.whereContainedIn("ward", listWardIds);
        return query;
    }
    private static ParseQuery<ParseObject> createPlaceDistrictQuery(String district) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        List<String> listWardIds = new ArrayList<>();
        List<Ward> listWards = new ArrayList<>();
        listWards = WardServices.getWardsList(district);
        for (Ward ward: listWards ) {
            listWardIds.add(ward.getWardId());
        }
        query.whereContainedIn("ward", listWardIds);
        return query;
    }

    private static ParseQuery<ParseObject> createPlaceWardQuery(String ward)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.whereEqualTo("ward", ward);
        return query;
    }
    private static ParseQuery<ParseObject> createPlaceTypeQuery(String type)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.whereEqualTo("type", type);
        return query;
    }


    public List<Place> getPlacesList(String city, String district, String ward, String type, Integer offset, Integer limit) throws ParseException {
        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();

        if (city != null)
            queries.add(createPlaceCityQuery(city));
        else if (district != null)
            queries.add(createPlaceDistrictQuery(district));
        else if (ward != null)
            queries.add(createPlaceWardQuery(ward));
        if (type != null)
            queries.add(createPlaceTypeQuery(type));
        if (offset == null)
            offset = 0;
        if (limit == null)
            limit = 20;
        ParseQuery<ParseObject> mainQuery = ParseQuery.or(queries);
        mainQuery.setLimit(limit);
        mainQuery.setSkip(offset);
        mainQuery.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        List<Place> placesList = new ArrayList<>();

        List<ParseObject> listObject = mainQuery.find();
        for (ParseObject object: listObject) {
            placesList.add(DataParser.parsePlace(object));
        }

        return placesList;
    }



    List<Image> getImagesList(Place placeId)
    {
        return null;
    }
}
