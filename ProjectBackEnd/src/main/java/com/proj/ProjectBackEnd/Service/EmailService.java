package com.proj.ProjectBackEnd.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

    public String sendSimpleEmail(String toEmail,String body, String subject) {
    	
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("kvrreddy1997@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        return "Mail Send...";
    }

}