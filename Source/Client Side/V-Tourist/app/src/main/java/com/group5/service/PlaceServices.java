package com.group5.service;

import com.group5.model.District;
import com.group5.model.Image;
import com.group5.model.Place;
import com.group5.model.Ward;
import com.group5.parser.DataParser;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 27/11/2015.
 */

public class PlaceServices {
    public static ParseObject getObject(String id) throws ParseException{
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject object = new ParseObject("Place");
        return query.get(id);
    }
    public static Place getPlace(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
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


    public static ArrayList<Place> getPlacesList(String city, String district, String ward, String type, Integer offset, Integer limit) throws ParseException {
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
        ArrayList<Place> placesList = new ArrayList<>();

        List<ParseObject> listObject = mainQuery.find();
        for (ParseObject object: listObject) {
            placesList.add(DataParser.parsePlace(object));
        }

        return placesList;
    }

    public static ArrayList<Place> getPlacesList(String city, int limit, int skip, ParseQuery.CachePolicy cachePolicy) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.setCachePolicy(cachePolicy);
        query.setLimit(limit);
        query.setSkip(skip);
        query.orderByAscending("name");
        ParseQuery<ParseObject> cityQuery = ParseQuery.getQuery("City");
        cityQuery.setCachePolicy(cachePolicy);
        ParseObject cityOb = cityQuery.get(city);
        query.whereEqualTo("city", cityOb);
        ArrayList<Place> placeList = new ArrayList<>();
        List<ParseObject> listObject = query.find();
        for (ParseObject object: listObject) {
            Place p = DataParser.parsePlace(object);
            ParseRelation<ParseObject> relation = object.getRelation("images");
            ParseQuery query2 = relation.getQuery();
            query2.setCachePolicy(cachePolicy);
            ParseObject imageObject = null;
            try {
                imageObject = query2.getFirst();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (imageObject != null)
            {
                ParseFile imageFile = imageObject.getParseFile("img");
                p.firstImageURL = imageFile.getUrl();
            }
            placeList.add(p);
        }

        return placeList;
    }

    public static ArrayList<Place> getLastedPlacesList(ParseQuery.CachePolicy cachePolicy, int limit) throws ParseException {
        ArrayList<Place> lastedPlaceList = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.setCachePolicy(cachePolicy);
        query.orderByDescending("createdAt").setLimit(limit);
        try {
            List<ParseObject> places = query.find();
            for (ParseObject place : places)
            {
                if (place != null)
                {
                    Place p = DataParser.parsePlace(place);
                    ParseRelation<ParseObject> relation = place.getRelation("images");
                    ParseQuery query2 = relation.getQuery();
                    query2.setCachePolicy(cachePolicy);
                    ParseObject imageObject = query2.getFirst();
                    if (imageObject != null)
                    {
                        ParseFile imageFile = imageObject.getParseFile("img");
                        p.firstImageURL = imageFile.getUrl();
                    }
                    lastedPlaceList.add(p);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lastedPlaceList;
    }

    public static List<Place> getPlacesListByGeo(Double latitude, Double longtitude, Double distance) throws ParseException {
        ParseGeoPoint location = new ParseGeoPoint(latitude, longtitude);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Place");
        query.setLimit(10);
        query.whereWithinKilometers("location", location, distance);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        List<Place> placesList = new ArrayList<>();
        List<ParseObject> listObject = query.find();
        for (ParseObject object: listObject) {
            placesList.add(DataParser.parsePlace(object));
        }

        return placesList;
    }


    public static List<Image> getImagesList(String placeId) throws ParseException {
        ParseObject placeOb = getObject(placeId);
        ParseRelation<ParseObject> relation = placeOb.getRelation("images");
        // generate a query based on that relation
        ParseQuery query = relation.getQuery();
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        ParseObject imageOb;
        ArrayList<Image> imagesList= new ArrayList<>();
        List<ParseObject> listObject = query.find();
        for (ParseObject object: listObject) {
            imagesList.add(DataParser.parseImage(object));
        }

        return imagesList;
    }

    public static ArrayList<String> getImageUrlList(String placeId) throws ParseException{

        ParseObject placeOb = getObject(placeId);
        ParseRelation<ParseObject> relation = placeOb.getRelation("images");
        // generate a query based on that relation
        ParseQuery query = relation.getQuery();
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        ArrayList<String> imageUrlList = new ArrayList<>();
        List<ParseObject> listObject = query.find();

        for (ParseObject object: listObject) {
            imageUrlList.add(object.getParseFile("img").getUrl());
        }
        return imageUrlList;
    }

    public static Image getThumbnail(String placeId) throws ParseException
    {
        ParseObject placeOb = getObject(placeId);
        ParseRelation<ParseObject> relation = placeOb.getRelation("images");
        // generate a query based on that relation
        ParseQuery query = relation.getQuery();
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        ParseObject imageOb;
        imageOb = query.getFirst();
        return DataParser.parseImage(imageOb);
    }
}
