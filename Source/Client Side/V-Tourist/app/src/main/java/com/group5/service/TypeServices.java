package com.group5.service;

import com.group5.model.Type;
import com.group5.parser.DataParser;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 27/11/2015.
 */

public class TypeServices {
    public static List<Type> getTypesList() throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Type");
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        List<Type> typeList = new ArrayList<>();
        List<ParseObject> listObject = query.find();
        for (ParseObject object: listObject
                ) {
            typeList.add(DataParser.parseType(object));
        }

        return typeList;
    }

    public static Type getType(String id) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Type");
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
        ParseObject object = new ParseObject("Type");
        object = query.get(id);

        return DataParser.parseType(object);
    }

}
