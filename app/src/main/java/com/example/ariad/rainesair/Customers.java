package com.example.ariad.rainesair;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ariad on 1/10/2017.
 */

public class Customers {


    private String companyName;
    private String country;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String contactPersonName;
    private String contactPersonCell;
    private String workNumber;
    private String extension;
    private String email;
    private String fax;
    private String comments;
    private String customerID;

    public Customers() {
    }


    public Customers(String address, String city, String comments, String companyName, String contactPersonCell,
                     String contactPersonName, String country, String email, String extension, String fax,
                     String state, String workNumber, String zipCode) {


        this.address = address;
        this.city = city;
        this.comments = comments;
        this.companyName = companyName;
        this.contactPersonCell = contactPersonCell;
        this.contactPersonName = contactPersonName;
        this.country = country;
        this.email = email;
        this.extension = extension;
        this.fax = fax;
        this.state = state;
        this.workNumber = workNumber;
        this.zipCode = zipCode;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPersonCell() {
        return contactPersonCell;
    }

    public void setContactPersonCell(String contactPersonCell) {
        this.contactPersonCell = contactPersonCell;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("companyName", companyName);
        result.put("country", country);
        result.put("address", address);
        result.put("city", city);
        result.put("state", state);
        result.put("zipCode", zipCode);
        result.put("contactPersonName", contactPersonName);
        result.put("contactPersonCell", contactPersonCell);
        result.put("workNumber", workNumber);
        result.put("extension", extension);
        result.put("email", email);
        result.put("fax", fax);
        result.put("comments", comments);
        return result;
    }
}
