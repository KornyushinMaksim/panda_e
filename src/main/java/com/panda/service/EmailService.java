package com.panda.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Transactional
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

//    private final JavaMailSender javaMailSender;
//
//    @Value("${spring.mail.username}")
//    private String senderEmail;
////    @Value("${sender.text}")
////    private String senderText;
//
//    public void sendEmail() {
//            SimpleMailMessage massage = new SimpleMailMessage();
//            massage.setFrom(senderEmail);
//            massage.setTo("maksimkornyushin88@gmail.com");
//            massage.setSubject("тема письма - название заявки");
//            massage.setText("здесь будет текст письма из textArea изи формы отправки");
//            javaMailSender.send(massage);
//            System.out.println("письмо отправлено");
//    }
}
