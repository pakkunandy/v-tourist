package com.group5.model;

/**
 * Type of Place
 */
public class Type {
    /**
     * Id of Type
     */
    private String mTypeId;

    /**
     * Name of Type
     */
    private String mName;

    /**
     * Description of Type
     */
    private String mDescription;

    /**
     * Get/Set Properties
     */
    public String getTypeId() {
        return mTypeId;
    }

    public void setTypeId(String mTypeId) {
        this.mTypeId = mTypeId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    /**
     * Constructor of Type
     * @param mTypeId : Type id
     * @param mName : Type Name
     * @param mDescription: Description Type
     */
    public Type(String mTypeId, String mName, String mDescription) {
        this.mTypeId = mTypeId;
        this.mName = mName;
        this.mDescription = mDescription;
    }
}
