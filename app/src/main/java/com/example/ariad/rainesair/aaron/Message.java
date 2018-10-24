package com.example.ariad.rainesair.aaron;

import java.io.Serializable;
import com.example.ariad.rainesair.Users;

/**
 * Created by AAron on 1/15/2017.
 */

public class Message implements Serializable{
    private String subject;
    private String messageText;
    private Users sender;
    private String senderName;
    private String dbKey;

    public String getSenderName() {
        return senderName;
    }



    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    //private Users recipient; //get rid of this?
    private String senderUserID;
    //private boolean isRead;

    public Message(){

    }

    public Message(String subject, String messageText, Users recipient, Users sender) {
        this.subject = subject;
        this.messageText = messageText;
        //this.recipient = recipient;
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    /*
    public Users getRecipient() {
        return recipient;
    }

    public void setRecipient(Users recipient) {
        this.recipient = recipient;
    }
    */

    public String getSenderUserID() {
        return senderUserID;
    }

    public void setSenderUserID(String senderUserID) {
        this.senderUserID = senderUserID;
    }

    public String getDbKey() {
        return dbKey;
    }

    public void setDbKey(String dbKey) {
        this.dbKey = dbKey;
    }
}
