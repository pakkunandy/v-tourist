package com.group5.service;

import com.group5.controller.GlobalVariable;
import com.group5.model.Bookmark;
import com.group5.model.Place;
import com.group5.model.Rating;
import com.group5.parser.DataParser;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 30/11/2015.
 */
public class BookmarkServices {
    public static Bookmark getBookmark(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bookmark");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject object;
        object = query.get(id);

        return DataParser.parseBookmark(object);
    }

    public static String inBookmark() throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bookmark");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.whereEqualTo("place", PlaceServices.getObject(GlobalVariable.idGlobalPlaceCurrent));
        ParseObject rs = null;
        rs = query.getFirst();
        if(rs != null)
            return rs.getObjectId();
        else
            return "#";
    }

    public static List<Bookmark> getBookmarkList() throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bookmark");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        List<Bookmark> bookmarkList = new ArrayList<>();
        try {
            List<ParseObject> listObject = query.find();
            for (ParseObject bookmark : listObject) {
                Bookmark bmitem = DataParser.parseBookmark(bookmark);

                ParseQuery<ParseObject> queryPlace = ParseQuery.getQuery("Place");
                queryPlace.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
                ParseObject place = queryPlace.get(bmitem.getPlace().getPlaceId());
                if (place != null)
                {
                    ParseRelation<ParseObject> relation = place.getRelation("images");
                    ParseQuery query2 = relation.getQuery();
                    query2.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
                    try {
                        ParseObject imageObject = query2.getFirst();
                        ParseFile imageFile = imageObject.getParseFile("img");
                        bmitem.getPlace().firstImageURL = imageFile.getUrl();
                    } catch (Exception ex) {}
                }

                bookmarkList.add(bmitem);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return bookmarkList;
    }

    public static boolean editBookmark(Bookmark bookmark) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bookmark");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject object = new ParseObject("Rating");
        object = query.get(bookmark.getId());
        object.put("place", bookmark.getPlace().getPlaceId());
        object.save();
        return true;
    }

    public static boolean deleteBookmark(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bookmark");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject object = query.get(id);
        object.delete();
        return true;
    }

    public static boolean createBookmark(String placeID) throws ParseException {
        ParseObject object = new ParseObject("Bookmark");
        object.put("user", ParseUser.getCurrentUser());
        object.put("place", PlaceServices.getObject(placeID));
        object.save();
        return true;
    }

}