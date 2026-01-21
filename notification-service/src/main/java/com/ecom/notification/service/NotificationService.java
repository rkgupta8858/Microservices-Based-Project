package com.ecom.notification.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics = "ORDER_CREATED", groupId = "notification-group")
    public void handleOrderCreated(String message) {
        System.out.println("📧 Sending Email for: " + message);
        System.out.println("📲 Sending SMS for: " + message);
    }

    @KafkaListener(topics = "PAYMENT_SUCCESS", groupId = "notification-group")
    public void handlePaymentSuccess(String message) {
        System.out.println("✅ Payment Success Notification: " + message);
        System.out.println("📧 Email sent to customer for payment success");
    }
}
