package com.example.notificationservice.service;

import com.example.notificationservice.common.AppConstants;
import com.example.notificationservice.model.Order;
import com.example.notificationservice.model.PaymentObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class EmailSenderServiceImpl implements EmailSenderService{

    public final JavaMailSender emailSender;
    private PaymentObject paymentObject;




    @Override
    public void sendEmail(String toAddress, String subject, String toBody) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(toBody);
        emailSender.send(simpleMailMessage);

    }

    @KafkaListener(topics = AppConstants.NOTIFICATION_TOPIC)
    public boolean sendEmailMessage(PaymentObject paymentObject){
        this.paymentObject = paymentObject;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(paymentObject.getEmailUser());
        simpleMailMessage.setSubject("Оплата товра");
        simpleMailMessage.setText("Ваш номер транзакции: " + paymentObject.getOrderNumber() +
                "\n" + "Сумма заказа: " + paymentObject.getAmountPayment() +
                "\n" + "Ваши товары: " + paymentObject.getClothOrders().toString() +
                "\n" + "Заказ прийдет на этот адресс: " + paymentObject.getAddress());
        emailSender.send(simpleMailMessage);
        return true;
    }
}
