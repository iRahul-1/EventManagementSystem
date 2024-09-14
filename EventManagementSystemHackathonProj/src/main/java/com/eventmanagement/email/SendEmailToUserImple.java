package com.eventmanagement.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class SendEmailToUserImple implements ISendEmailToUser{
	@Autowired
	private JavaMailSender sender ;
	@Value("${spring.mail.username}")
	private String fromEmail;
	public String mailUser(String msg,String Emails) throws Exception{
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(fromEmail);
		helper.setCc(Emails);
		helper.setSubject("Open it");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("thankyou.jpg", new ClassPathResource("thankyou.jpg"));
		sender.send(message);
		return "mail sent";
		
	}
}
