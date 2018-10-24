package com.example.ariad.rainesair;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ariad on 1/22/2017.
 */

public class Inventory {

    private String partNumber;
    private String seriallNumber;
    private String description;
    private String condition;
    private String trace;
    private String tagBy;
    private String tagDate;
    private String cost;
    private String quantity;
    private String totalInvested;
    private String listPrice;
    private String comments;
    private String inventoryID;


    public Inventory() {
    }


    public Inventory(String comments, String condition, String cost, String description, String inventoryID,
                     String listPrice, String partNumber, String quantity, String seriallNumber,
                     String tagBy, String tagDate, String totalInvested, String trace) {

        this.comments = comments;
        this.condition = condition;
        this.cost = cost;
        this.description = description;
        this.inventoryID = inventoryID;
        this.listPrice = listPrice;
        this.partNumber = partNumber;
        this.quantity = quantity;
        this.seriallNumber = seriallNumber;
        this.tagBy = tagBy;
        this.tagDate = tagDate;
        this.totalInvested = totalInvested;
        this.trace = trace;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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

    public String getTotalInvested() {
        return totalInvested;
    }

    public void setTotalInvested(String totalInvested) {
        this.totalInvested = totalInvested;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
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
        result.put("cost", cost);
        result.put("quantity", quantity);
        result.put("totalInvested", totalInvested);
        result.put("listPrice", listPrice);
        result.put("comments", comments);


        return result;
    }
}
