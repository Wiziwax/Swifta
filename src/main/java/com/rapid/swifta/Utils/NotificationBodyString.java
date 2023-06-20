package com.rapid.swifta.Utils;


public class NotificationBodyString {
    private NotificationBodyString() {
        // Private constructor to prevent instantiation
    }

    public static String NOTIFICATION_BODY(Integer clientId, String jobDescription,
                                           Integer orderNumber, Integer merchantId){
        return String.format("YOU HAVE A JOB UPDATE FROM " + clientId + " to do "+ jobDescription +
                                            " with order number contact " + clientId + " for more information, Thank you");
    }
}
