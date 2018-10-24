package com.example.ariad.rainesair;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ariad on 1/19/2017.
 */

public class Parts {

    private String partNumber;
    private String seriallNumber;
    private String description;
    private String condition;
    private String trace;
    private String tagBy;
    private String tagDate;
    private String comments;
    private String partID;
    private String publisherId;
    private String publisherFirst;
    private String publisherLast;
    private String publisherCell;
    private String publisherEmail;
    private String partPic;


    public Parts() {
    }


    public Parts(String comments, String condition, String description, String partNumber, String seriallNumber,
                 String tagBy, String tagDate, String trace, String partPic ) {
        this.comments = comments;
        this.condition = condition;
        this.description = description;
        this.partNumber = partNumber;
        this.seriallNumber = seriallNumber;
        this.tagBy = tagBy;
        this.tagDate = tagDate;
        this.trace = trace;
        this.partPic = partPic;

    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getSeriallNumber() {
        return seriallNumber;
    }

    public void setSeriallNumber(String seriallNumber) {
        this.seriallNumber = seriallNumber;
    }

    public String getTagBy() {
        return tagBy;
    }

    public void setTagBy(String tagBy) {
        this.tagBy = tagBy;
    }

    public String getTagDate() {
        return tagDate;
    }

    public void setTagDate(String tagDate) {
        this.tagDate = tagDate;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getPublisherCell() {
        return publisherCell;
    }

    public void setPublisherCell(String publisherCell) {
        this.publisherCell = publisherCell;
    }

    public String getPublisherEmail() {
        return publisherEmail;
    }

    public void setPublisherEmail(String publisherEmail) {
        this.publisherEmail = publisherEmail;
    }

    public String getPublisherFirst() {
        return publisherFirst;
    }

    public void setPublisherFirst(String publisherFirst) {
        this.publisherFirst = publisherFirst;
    }

    public String getPublisherLast() {
        return publisherLast;
    }

    public void setPublisherLast(String publisherLast) {
        this.publisherLast = publisherLast;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getPartPic() {
        return partPic;
    }

    public void setPartPic(String partPic) {
        this.partPic = partPic;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("partNumber", partNumber);
        result.put("seriallNumber", seriallNumber);
        result.put("description", description);
        result.put("condition", condition);
        result.put("trace", trace);
        result.put("tagBy", tagBy);
        result.put("tagDate", tagDate);
        result.put("comments", comments);
        result.put("publisherId", publisherId);
        result.put("publisherFirst", publisherFirst);
        result.put("publisherLast", publisherLast);
        result.put("publisherCell", publisherCell);
        result.put("publisherEmail", publisherEmail);
        result.put("partPic", partPic);
      return result;
    }
}
