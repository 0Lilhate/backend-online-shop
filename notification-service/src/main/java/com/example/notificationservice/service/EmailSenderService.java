package com.example.notificationservice.service;

import com.example.notificationservice.model.Order;
import com.example.notificationservice.model.PaymentObject;

public interface EmailSenderService {
    public void sendEmail(String toAddress, String subject, String toBody);
    public void sendEmailMessage(PaymentObject paymentObject);
}
