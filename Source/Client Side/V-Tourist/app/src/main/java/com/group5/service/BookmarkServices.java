package com.group5.service;

import com.group5.model.Bookmark;
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

    public static List<Bookmark> getBookmarkList(String placeId) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Bookmark");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        List<Bookmark> bookmarkList = new ArrayList<>();
        List<ParseObject> listObject = query.find();
        for (ParseObject object: listObject ) {
            bookmarkList.add(DataParser.parseBookmark(object));
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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Rating");
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        ParseObject object = new ParseObject("Rating");
        object = query.get(id);
        object.delete();
        return true;
    }

    public static boolean createBookmark(Bookmark bookmark) throws ParseException {
        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(false);
        acl.setWriteAccess(ParseUser.getCurrentUser(), true);
        ParseObject object = new ParseObject("Rating");
        object.setACL(acl);
        object.put("user", UserServices.getCurrentUser().getId());
        object.put("place", bookmark.getPlace().getPlaceId());
        object.save();
        return true;
    }

}
