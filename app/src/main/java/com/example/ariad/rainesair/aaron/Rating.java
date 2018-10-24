package com.example.ariad.rainesair.aaron;

/**
 * Created by AAron on 3/20/2017.
 */

public class Rating {
    private String userID;
    private double ratingNumber;
    private String userName;

    public Rating() {
    }

    public Rating(String userID, double ratingNumber, String userName) {
        this.userID = userID;
        this.ratingNumber = ratingNumber;
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public double getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(double ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
