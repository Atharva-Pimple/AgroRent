package com.majorproj.agrorent.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
	
	private JavaMailSender mailSender;

	@Override
	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("atharvapimple30@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
		
	}

}
