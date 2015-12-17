package com.group5.service;

import com.group5.model.User;
import com.group5.parser.DataParser;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by DELL on 27/11/2015.
 */
public class UserServices {
    public static User getCurrentUser()
    {
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null)
            return DataParser.parseUser(user);
        else return null;
    }

    public static User getByUsername(String username) throws ParseException {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", username);
        ParseUser user;
        user = query.getFirst();
        if (user != null)
            return DataParser.parseUser(user);
        else return null;
    }

    public static User getById(String id) throws ParseException {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        ParseUser user;
        user = query.get(id);
        if (user != null)
            return DataParser.parseUser(user);
        else return null;
    }

    public static ParseObject getOBject(String id) throws ParseException {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        ParseUser user;
        user = query.get(id);
        if (user != null)
            return user;
        else return null;
    }
}
