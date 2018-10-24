package com.example.ariad.rainesair;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ariad on 1/26/2017.
 */

public class PO {

    private String poNum;
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
    private String vendorName;
    private String vendorEmail;
    private String vendorPhone;
    private String orderDate;
    private String status;
    private String shipTo;
    private String comments;
    private String poID;


    public PO() {
    }

    public PO(String comments, String companyName, String condition, String creditCardFee, String description,
              String dgDocFee, String orderDate, String packingFee, String partNumber, String poNum,
              String price, String purchaseTotal, String quantity, String seriallNumber, String shippingCost,
              String status, String tagBy, String tagDate, String terms, String trace, String vendorEmail,
              String vendorName, String vendorPhone, String wireTransferFee, String shipTo) {
        this.comments = comments;
        this.companyName = companyName;
        this.condition = condition;
        this.creditCardFee = creditCardFee;
        this.description = description;
        this.dgDocFee = dgDocFee;
        this.orderDate = orderDate;
        this.packingFee = packingFee;
        this.partNumber = partNumber;
        this.poNum = poNum;
        this.price = price;
        this.purchaseTotal = purchaseTotal;
        this.quantity = quantity;
        this.seriallNumber = seriallNumber;
        this.shippingCost = shippingCost;
        this.status = status;
        this.tagBy = tagBy;
        this.tagDate = tagDate;
        this.terms = terms;
        this.trace = trace;
        this.vendorEmail = vendorEmail;
        this.vendorName = vendorName;
        this.vendorPhone = vendorPhone;
        this.wireTransferFee = wireTransferFee;
        this.shipTo = shipTo;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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

    public String getPoID() {
        return poID;
    }

    public void setPoID(String poID) {
        this.poID = poID;
    }

    public String getPoNum() {
        return poNum;
    }

    public void setPoNum(String poNum) {
        this.poNum = poNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public String getWireTransferFee() {
        return wireTransferFee;
    }

    public void setWireTransferFee(String wireTransferFee) {
        this.wireTransferFee = wireTransferFee;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("poNumber", poNum);
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
        result.put("vendorName", vendorName);
        result.put("vendorEmail", vendorEmail);
        result.put("vendorPhone", vendorPhone);
        result.put("orderDate", orderDate);
        result.put("status", status);
        result.put("shipTo", shipTo);
        result.put("comments", comments);

        return result;
    }
}
