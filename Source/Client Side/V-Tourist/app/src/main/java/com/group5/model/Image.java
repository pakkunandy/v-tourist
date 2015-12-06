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
    private byte[] mImageContain;

    /**
     * Get/Set Properties
     */
    public String getImageId() {
        return mImageId;
    }

    public void setImageId(String mImageId) {
        this.mImageId = mImageId;
    }

    public byte[] getImageContain() {
        return mImageContain;
    }

    public void setImageContain(byte[] mImageContain) {
        this.mImageContain = mImageContain;
    }

    /**
     * Constructor
     * @param mImageId
     * @param mImageContain
     */
    public Image(String mImageId, byte[] mImageContain) {
        this.mImageId = mImageId;
        this.mImageContain = mImageContain;
    }

    /**
     * Constructor
     */
    public Image() {
    }
}
