package com.example.ariad.rainesair;

import android.net.Uri;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ariad on 1/10/2017.
 */

public class Users  implements Serializable {


    private String firstName;
    private String lastName;
    private String country;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String cell;
    private String workNumber;
    private String extension;
    private String email;
    private String profilePic = "defaultprofile"; //somehow I keep getting null exceptions unless it has this default name

    private double rating = 0;

    public Users() {
    }

    public Users(String address, String cell, String city, String country, String email, String extension, String firstName, String lastName, String state,
                 String workNumber, String zipCode, String profilePic) {
        this.address = address;
        this.cell = cell;
        this.city = city;
        this.country = country;
        this.email = email;
        this.extension = extension;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.workNumber = workNumber;
        this.zipCode = zipCode;
        this.profilePic = profilePic;

    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("country", country);
        result.put("address", address);
        result.put("city", city);
        result.put("state", state);
        result.put("zipCode", zipCode);
        result.put("cell", cell);
        result.put("workNumber", workNumber);
        result.put("extension", extension);
        result.put("email", email);
        result.put("profilePic", profilePic);

        return result;
    }
}
