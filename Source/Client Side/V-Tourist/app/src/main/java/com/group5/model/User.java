package com.group5.model;

import java.util.List;

/**
 * User Information
 */
public class User {
    /**
     * Id
     */
    private String mId;
    /**
     * Username
     */
    private String mUsername;
    /**
     * Email of User
     */
    private String mEmail;
    /**
     * Password of User (password was hashed)
     */
    private String mPassword;
    /**
     * Facebook ID
     */
    private String mFacebookId;
    /**
     * Facebook Token
     */
    private String mFacebookToken;
    /**
     * Name of User
     */
    private String mName;
    /**
     * Gender
     */
    private boolean mGender;
    /**
     * Type
     */
    private String mType;
    /**
     * List of Bookmark Place of User
     */
    private List<Place> mListBookmarkPlace;

    /**
     * Get/Set Properties
     */
    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getFacebookId() {
        return mFacebookId;
    }

    public void setFacebookId(String mFacebookId) {
        this.mFacebookId = mFacebookId;
    }

    public String getFacebookToken() {
        return mFacebookToken;
    }

    public void setFacebookToken(String mFacebookToken) {
        this.mFacebookToken = mFacebookToken;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public boolean ismGender() {
        return mGender;
    }

    public void setGender(boolean mGender) {
        this.mGender = mGender;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public List<Place> getListBookmarkPlace() {
        return mListBookmarkPlace;
    }

    public void setListBookmarkPlace(List<Place> mListBookmarkPlace) {
        this.mListBookmarkPlace = mListBookmarkPlace;
    }

    /**
     * Constructor
     * @param mUsername
     * @param mEmail
     * @param mPassword
     * @param mFacebookId
     * @param mFacebookToken
     * @param mName
     * @param mGender
     * @param mType
     */
    public User(String mUsername, String mEmail, String mPassword, String mFacebookId, String mFacebookToken, String mName, boolean mGender, String mType) {
        this.mUsername = mUsername;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mFacebookId = mFacebookId;
        this.mFacebookToken = mFacebookToken;
        this.mName = mName;
        this.mGender = mGender;
        this.mType = mType;
    }

    /**
     * Constructor
     */
    public User() {
    }
}
