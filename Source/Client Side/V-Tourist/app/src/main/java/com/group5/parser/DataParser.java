package com.group5.parser;

import com.group5.model.Bookmark;
import com.group5.model.City;
import com.group5.model.District;
import com.group5.model.Image;
import com.group5.model.Place;
import com.group5.model.Rating;
import com.group5.model.Type;
import com.group5.model.User;
import com.group5.model.Ward;
import com.group5.service.CityServices;
import com.group5.service.DistrictServices;
import com.group5.service.PlaceServices;
import com.group5.service.RatingServices;
import com.group5.service.TypeServices;
import com.group5.service.UserServices;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by DELL on 12/11/2015.
 */
public class DataParser {
    public static City parseCity(ParseObject object)
    {
        City city = new City();
        city.setCityId(object.getObjectId());
        city.setName(object.getString("name"));
        ParseFile file = object.getParseFile("Picture");
        city.setPicture(file.getUrl());
        return city;
    }

    public static District parseDistrict(ParseObject object) throws ParseException {
        District district = new District();
        district.setDistrictId(object.getObjectId());
        district.setDistrictName(object.getString("name"));
        district.setCity(CityServices.getCity(object.getString("city")));
        return district;
    }

    public static Ward parseWard(ParseObject object) throws ParseException {
        Ward ward = new Ward();
        ward.setWardId(object.getObjectId());
        ward.setWardName(object.getString("name"));
        ward.setDistrict(DistrictServices.getDistrict(object.getString("district")));
        return ward;
    }

    public static Type parseType(ParseObject object)
    {
        Type type = new Type();
        type.setName(object.getString("name"));
        type.setTypeId(object.getObjectId());
        type.setDescription(object.getString("description"));
        return type;
    }

    public static Place parsePlace(ParseObject object) throws ParseException {
        Place place = new Place();
        place.setAddress(object.getString("address"));
        ParseGeoPoint location = object.getParseGeoPoint("location");
        place.setLatitude(location.getLatitude());
        place.setLongitude(location.getLongitude());
        place.setLongDescription(object.getString("longDescription"));
        place.setPlaceDescription(object.getString("description"));
        place.setPlaceId(object.getObjectId());
        place.setPlaceName(object.getString("name"));
        //place.setType(TypeServices.getType(object.getString("type")));
        return place;
    }

    public static Rating parseRating(ParseObject object) throws ParseException {
        Rating rating = new Rating();
        rating.setPlaceId(object.getParseObject("place").fetchIfNeeded().getObjectId());
        rating.setComment(object.getString("comment"));
        rating.setScore(object.getInt("point"));
        rating.setUserRate(DataParser.parseUser(object.getParseUser("user").fetchIfNeeded()));
        return rating;
    }
    public static Bookmark parseBookmark(ParseObject object) throws ParseException {
        Bookmark bookmark = new Bookmark();
        bookmark.setPlace(parsePlace(object.getParseObject("place").fetchIfNeeded()));
        bookmark.setId(object.getObjectId());
        return bookmark;
    }
    public static Image parseImage(ParseObject object) throws ParseException {
        Image image = new Image();
        image.setImageId(object.getObjectId());
        ParseFile file = object.getParseFile("img");
        image.setImageUrl(file.getUrl());
        return image;
    }
    public static User parseUser(ParseUser object)
    {
        User user = new User();
        user.setName(object.getUsername());
        user.setEmail(object.getEmail());
        user.setId(object.getObjectId());
        return user;
    }


}