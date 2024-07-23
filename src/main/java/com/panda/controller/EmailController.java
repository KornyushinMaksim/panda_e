package com.panda.controller;

import com.panda.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/emails")
public class EmailController {

    private final EmailService emailService;


    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        emailService.sendSimpleMessage(to, subject, text);
        return "Email sent successfully";
    }
//    @PostMapping("/send-email")
//    public void sendEmail() {
//        emailService.sendEmail();
//    }
}
