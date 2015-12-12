package com.group5.model;

/**
 * Created by Duy on 11/10/2015.
 */
public class Image {
    /**
     * Image Id
     */
    private String mImageId;
    /**
     * Byte Array Image
     */
    private String mImageUrl;

    /**
     * Get/Set Properties
     */
    public String getImageId() {
        return mImageId;
    }

    public void setImageId(String mImageId) {
        this.mImageId = mImageId;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    /**
     * Constructor
     * @param mImageId
     * @param mImageUrl
     */
    public Image(String mImageId, String mImageUrl) {
        this.mImageId = mImageId;
        this.mImageUrl = mImageUrl;
    }

    /**
     * Constructor
     */
    public Image() {
    }
}