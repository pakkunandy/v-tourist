package com.group5.model;

/**
 * Rating of Place
 */
public class Rating {
    /**
     * User Rate
     */
    private User mUserRate;
    /**
     * Score
     */
    private float mScore;
    /**
     * Comment
     */
    private String mComment;

    /**
     * Get/Set properties
     */
    public User getUserRate() {
        return mUserRate;
    }

    public void setUserRate(User mUserRate) {
        this.mUserRate = mUserRate;
    }

    public float getScore() {
        return mScore;
    }

    public void setScore(float mScore) {
        this.mScore = mScore;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String mComment) {
        this.mComment = mComment;
    }

    /**
     * Constructor
     * @param mUserRate
     * @param mScore
     * @param mComment
     */
    public Rating(User mUserRate, float mScore, String mComment) {
        this.mUserRate = mUserRate;
        this.mScore = mScore;
        this.mComment = mComment;
    }

    /**
     * Constructor
     */
    public Rating() {
    }
}
