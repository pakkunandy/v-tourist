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

    private String mName;
    /**
     * Gender
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

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
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
     * @param mName
     */
    public User(String mUsername, String mEmail, String mPassword, String mName) {
        this.mUsername = mUsername;
        this.mEmail = mEmail;
        this.mPassword = mPassword;

        this.mName = mName;

    }

    /**
     * Constructor
     */
    public User() {
    }
}
