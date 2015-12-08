package com.group5.controller;

/**
 * Class Contain Description of Comment for a Place
 */
public class CommentItem {
    public String userName;
    public int imgAvatar;
    public String comment;
    public float rating;

    public CommentItem(String userName, int imgAvatar, String comment, float rating) {
        this.userName = userName;
        this.imgAvatar = imgAvatar;
        this.comment = comment;
        this.rating = rating;
    }
}
