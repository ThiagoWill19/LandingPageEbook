package com.thiagowill.landingpage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
	
	@Autowired private JavaMailSender mailSender;
	
	public boolean sendEmail(String adress) {
		
		SimpleMailMessage message = new SimpleMailMessage();
	    message.setSubject("E-Book Gratuito InovaTec");
        message.setText("Aqui está seu Ebook");
        message.setTo(adress);
        message.setFrom("thiagopompeu19@gmail.com");
        
        try {
            mailSender.send(message);
            
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return false;
        }
        
        return true;
	}
}
