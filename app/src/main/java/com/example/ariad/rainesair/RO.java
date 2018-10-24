package com.example.ariad.rainesair;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ariad on 3/4/2017.
 */

public class RO {

    private String roNum;
    private String companyName;
    private String partNumber;
    private String seriallNumber;
    private String description;
    private String condition;
    private String trace;
    private String tagBy;
    private String tagDate;
    private String quantity;
    private String laborCost;
    private String partsCost;
    private String totalCost;
    private String workRequested;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String repairDate;
    private String terms;
    private String status;
    private String shipTo;
    private String comments;

    public RO() {
    }

    public RO(String comments, String companyName, String condition, String customerEmail, String customerName,
              String customerPhone, String description, String laborCost, String repairDate, String partNumber,
              String partsCost, String quantity, String roNum, String seriallNumber, String shipTo, String status,
              String tagBy, String tagDate, String terms, String totalCost, String trace, String workRequested) {
        this.comments = comments;
        this.companyName = companyName;
        this.condition = condition;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.description = description;
        this.laborCost = laborCost;
        this.repairDate = repairDate;
        this.partNumber = partNumber;
        this.partsCost = partsCost;
        this.quantity = quantity;
        this.roNum = roNum;
        this.seriallNumber = seriallNumber;
        this.shipTo = shipTo;
        this.status = status;
        this.tagBy = tagBy;
        this.tagDate = tagDate;
        this.terms = terms;
        this.totalCost = totalCost;
        this.trace = trace;
        this.workRequested = workRequested;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(String laborCost) {
        this.laborCost = laborCost;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(String partsCost) {
        this.partsCost = partsCost;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRoNum() {
        return roNum;
    }

    public void setRoNum(String roNum) {
        this.roNum = roNum;
    }

    public String getSeriallNumber() {
        return seriallNumber;
    }

    public void setSeriallNumber(String seriallNumber) {
        this.seriallNumber = seriallNumber;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getWorkRequested() {
        return workRequested;
    }

    public void setWorkRequested(String workRequested) {
        this.workRequested = workRequested;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("roNumber", roNum);
        result.put("companyName", companyName);
        result.put("partNumber", partNumber);
        result.put("seriallNumber", seriallNumber);
        result.put("description", description);
        result.put("condition", condition);
        result.put("trace", trace);
        result.put("tagBy", tagBy);
        result.put("tagDate", tagDate);
        result.put("quantity", quantity);
        result.put("laborCost", laborCost);
        result.put("partsCost", partsCost);
        result.put("totalCost", totalCost);
        result.put("workRequested", workRequested);
        result.put("customerName", customerName);
        result.put("customerEmail", customerEmail);
        result.put("customerPhone", customerPhone);
        result.put("repairDate", repairDate);
        result.put("terms", terms);
        result.put("status", status);
        result.put("shipTo", shipTo);
        result.put("comments", comments);

        return result;
    }
}