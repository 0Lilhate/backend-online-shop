package com.example.notificationservice;

import com.example.notificationservice.service.EmailSenderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
//        ApplicationContext context = SpringApplication.run(NotificationServiceApplication.class);
//        EmailSenderService emailSenderService = context.getBean(EmailSenderService.class);
//
//        for (int i = 0; i < 100; i++){
//            emailSenderService.sendEmail("nastyaknyazknyaz@mail.ru", "Test", "Харош бухать");
//        }



    }

}
