package com.group5.parser;

import com.group5.model.City;
import com.group5.model.District;
import com.group5.model.Place;
import com.group5.model.Rating;
import com.group5.model.Type;
import com.group5.model.Ward;
import com.parse.ParseObject;

/**
 * Created by DELL on 12/11/2015.
 */
public class DataParser {
    public static City parseCity(ParseObject object)
    {
        City city = new City();
        city.setCityId(object.getObjectId());
        city.setName(object.getString("name"));
        return city;
    }

    public static District parseDistrict(ParseObject object)
    {
        District district = new District();
        district.setDistrictId(object.getObjectId());
        district.setDistrictName(object.getString("name"));
        //district.setCity(ParseDemo.);
        return district;
    }

    public static Ward parseWard(ParseObject object)
    {
        Ward ward = new Ward();
        ward.setWardId(object.getObjectId());
        ward.setWardName(object.getString("name"));
        //ward.setCity(ParseDemo.);
        return ward;
    }

    public static Type parseType(ParseObject object)
    {
        return null;
    }

    public static Place parsePlace(ParseObject object)
    {
        return null;
    }

    public static Rating parseRating(ParseObject object)
    {
        return null;
    }


}

