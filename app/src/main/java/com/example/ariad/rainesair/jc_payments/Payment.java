package com.example.ariad.rainesair.jc_payments;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 2/7/2017.
 */

public class Payment {

    private static String id;
    private static String status;
    private static String amount;
    private static String key;


    public Payment() {
    }

    public Payment(String id, String status, String amount, String key ) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.key = key;
            }

    public static String getAmount() {
        return amount;
    }

    public static void setAmount(String amount) {
        Payment.amount = amount;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Payment.id = id;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        Payment.key = key;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Payment.status = status;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("state", status);
        result.put("amount", amount);

        return result;
    }

}