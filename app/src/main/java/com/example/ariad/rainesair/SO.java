package com.example.ariad.rainesair;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ariad on 2/16/2017.
 */

public class SO {

    private String soNum;
    private String companyName;
    private String partNumber;
    private String seriallNumber;
    private String description;
    private String condition;
    private String trace;
    private String tagBy;
    private String tagDate;
    private String price;
    private String quantity;
    private String terms;
    private String creditCardFee;
    private String wireTransferFee;
    private String dgDocFee;
    private String packingFee;
    private String shippingCost;
    private String purchaseTotal;
    private String purchaserName;
    private String purchaserEmail;
    private String purchaserPhone;
    private String salesDate;
    private String status;
    private String shipTo;
    private String comments;


    public SO() {
    }

    public SO(String comments, String companyName, String condition, String creditCardFee, String description,
              String dgDocFee, String packingFee, String partNumber, String price, String purchaserEmail,
              String purchaserName, String purchaserPhone, String purchaseTotal, String quantity, String salesDate,
              String seriallNumber, String shippingCost, String shipTo, String soNum, String status,
              String tagBy, String tagDate, String terms, String trace, String wireTransferFee) {
        this.comments = comments;
        this.companyName = companyName;
        this.condition = condition;
        this.creditCardFee = creditCardFee;
        this.description = description;
        this.dgDocFee = dgDocFee;
        this.packingFee = packingFee;
        this.partNumber = partNumber;
        this.price = price;
        this.purchaserEmail = purchaserEmail;
        this.purchaserName = purchaserName;
        this.purchaserPhone = purchaserPhone;
        this.purchaseTotal = purchaseTotal;
        this.quantity = quantity;
        this.salesDate = salesDate;
        this.seriallNumber = seriallNumber;
        this.shippingCost = shippingCost;
        this.shipTo = shipTo;
        this.soNum = soNum;
        this.status = status;
        this.tagBy = tagBy;
        this.tagDate = tagDate;
        this.terms = terms;
        this.trace = trace;
        this.wireTransferFee = wireTransferFee;
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

    public String getCreditCardFee() {
        return creditCardFee;
    }

    public void setCreditCardFee(String creditCardFee) {
        this.creditCardFee = creditCardFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDgDocFee() {
        return dgDocFee;
    }

    public void setDgDocFee(String dgDocFee) {
        this.dgDocFee = dgDocFee;
    }

    public String getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(String packingFee) {
        this.packingFee = packingFee;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPurchaserEmail() {
        return purchaserEmail;
    }

    public void setPurchaserEmail(String purchaserEmail) {
        this.purchaserEmail = purchaserEmail;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getPurchaserPhone() {
        return purchaserPhone;
    }

    public void setPurchaserPhone(String purchaserPhone) {
        this.purchaserPhone = purchaserPhone;
    }

    public String getPurchaseTotal() {
        return purchaseTotal;
    }

    public void setPurchaseTotal(String purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public String getSeriallNumber() {
        return seriallNumber;
    }

    public void setSeriallNumber(String seriallNumber) {
        this.seriallNumber = seriallNumber;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getSoNum() {
        return soNum;
    }

    public void setSoNum(String soNum) {
        this.soNum = soNum;
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

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getWireTransferFee() {
        return wireTransferFee;
    }

    public void setWireTransferFee(String wireTransferFee) {
        this.wireTransferFee = wireTransferFee;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("soNumber", soNum);
        result.put("companyName", companyName);
        result.put("partNumber", partNumber);
        result.put("seriallNumber", seriallNumber);
        result.put("description", description);
        result.put("condition", condition);
        result.put("trace", trace);
        result.put("tagBy", tagBy);
        result.put("tagDate", tagDate);
        result.put("price", price);
        result.put("quantity", quantity);
        result.put("terms", terms);
        result.put("creditCardFee", creditCardFee);
        result.put("wireTransferFee", wireTransferFee);
        result.put("dgDocFee", dgDocFee);
        result.put("packingFee", packingFee);
        result.put("shippingCost", shippingCost);
        result.put("purchaseTotal", purchaseTotal);
        result.put("purchaserName", purchaserName);
        result.put("purchaserEmail", purchaserEmail);
        result.put("purchaserPhone", purchaserPhone);
        result.put("salesDate", salesDate);
        result.put("status", status);
        result.put("shipTo", shipTo);
        result.put("comments", comments);


        return result;
    }
}
