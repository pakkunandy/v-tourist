package com.group5.service;

import com.group5.model.Rating;
import com.group5.parser.DataParser;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 27/11/2015.
 */

public class RatingServices {
    public static Rating getRating(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Rating");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject object = new ParseObject("Rating");
        object = query.get(id);

        return DataParser.parseRating(object);
    }

    public static List<Rating> getRatingList(String placeId) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Rating");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.whereEqualTo("place", placeId);
        List<Rating> ratingList = new ArrayList<>();
        List<ParseObject> listObject = query.find();
        for (ParseObject object: listObject
                ) {
            ratingList.add(DataParser.parseRating(object));
        }

        return ratingList;

    }

    public static boolean editRating(Rating rating) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Rating");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject object = new ParseObject("Rating");
        object = query.get(rating.getId());
        object.put("user", rating.getUserRate().getId());
        object.put("point", rating.getScore());
        object.put("comment", rating.getComment());
        object.save();
        return true;
    }

    public static boolean deleteRating(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Rating");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject object = new ParseObject("Rating");
        object = query.get(id);
        object.delete();
        return true;
    }

    public static boolean createRating(Rating rating) throws ParseException {
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setWriteAccess(ParseUser.getCurrentUser(), true);
        ParseObject object = new ParseObject("Rating");
        object.setACL(acl);
        object.put("user", rating.getUserRate().getId());
        object.put("point", rating.getScore());
        object.put("comment", rating.getComment());
        object.put("place", rating.getPlaceId());
        object.save();
        return true;
    }


}
