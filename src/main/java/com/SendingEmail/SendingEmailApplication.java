package com.SendingEmail;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SendingEmailApplication {
	
	@Autowired
    private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(SendingEmailApplication.class, args);
	}
	
	
// Mail Server Properties
//	The interfaces and classes for Java mail support in the Spring framework are organized as follows:
//
//		
//		4.SimpleMailMessage class: used to create a simple mail message including the from, to, cc, subject and text fields
	void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("aashitagrover28@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
	
//	
//	MimeMessageHelper class: helper class for the creation of MIME messages. It offers support for images, typical mail attachments 
//	and text content in an HTML layout
	
	
//	Multipurpose Internet Mail Extensions (MIME) is an Internet standard that extends the format of email messages to
//	support text in character sets other than ASCII,
//	as well as attachments of audio, video, images, and application programs.
	void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo("aashitagrover28@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

		// hard coded a file path
        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Rohit\\Desktop\\error.jpg"));

        helper.addAttachment("error.jpg", file);

        javaMailSender.send(msg);

    }
	
	
	
}
