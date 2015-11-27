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
     * Url of Image
     */
    private String mUrlImage;
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

    public String getUrlImage() {
        return mUrlImage;
    }

    public void setUrlImage(String mUrlImage) {
        this.mUrlImage = mUrlImage;
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
     * @param mUrlImage
     * @param mImageContain
     */
    public Image(String mImageId, String mUrlImage, byte[] mImageContain) {
        this.mImageId = mImageId;
        this.mUrlImage = mUrlImage;
        this.mImageContain = mImageContain;
    }

    /**
     * Constructor
     */
    public Image() {
    }
}
