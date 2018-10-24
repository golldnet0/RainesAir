package com.example.ariad.rainesair;

/**
 * Created by ariad on 1/22/2017.
 */

public class ContactMe {


    private String firstName;
    private String lastName;
    private String cell;
    private String email;
    private String subject;
    private String comments;
    private String contactID;

    public ContactMe() {
    }


    public ContactMe(String cell, String comments, String contactID, String email, String firstName, String lastName, String subject) {
        this.cell = cell;
        this.comments = comments;
        this.contactID = contactID;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }


    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
