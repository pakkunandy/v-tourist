package com.group5.model;

/**
 * Created by HarleTracker on 12/8/2015.
 */
public class FavoriteItem {
    public String idBookmarkRecord;
    public String placeID;
    public String Name;
    public int imgPlace;
    public String Location;
    public String Description;

    public FavoriteItem(String idBookmark, String placeid, String name, int imgPlace, String location, String description) {
        this.idBookmarkRecord = idBookmark;
        this.placeID = placeid;
        this.Name = name;
        this.imgPlace = imgPlace;
        this.Location = location;
        this.Description = description;
    }
}
