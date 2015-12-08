package com.group5.controller;

/**
 * Class Contain Description of Comment for a Place
 */
public class CommentItem {
    public int imgAvatar;
    public String comment;
    public float rating;

    public CommentItem(int imgAvatar, String comment, float rating) {
        this.imgAvatar = imgAvatar;
        this.comment = comment;
        this.rating = rating;
    }
}
