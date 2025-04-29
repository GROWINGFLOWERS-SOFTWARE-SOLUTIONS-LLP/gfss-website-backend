package com.gfss.website.com.service.impl;


import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gfss.website.com.VO.EmailRequestVO;
import com.gfss.website.com.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSenderImpl mailSender;

     // Use your sender email

    public EmailServiceImpl(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public String sendApplicationEmail(EmailRequestVO emailRequestVO) throws MessagingException {
    	
    	String fromEmail = emailRequestVO.getEmail();
    	
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromEmail);

        // Send to HR/Recruiter email (can also make this dynamic)
        helper.setTo("contact-us@growing-flowers-software-solution.com");

        helper.setSubject("New Job Application: " + emailRequestVO.getPosition());

        String content = "New job application received:\n\n"
                + "Name: " + emailRequestVO.getName() + "\n"
                + "Email: " + emailRequestVO.getEmail() + "\n"
                + "Phone: " + emailRequestVO.getPhone() + "\n"
                + "Position Applied For: " + emailRequestVO.getPosition() + "\n\n"
                + "Resume is attached.";

        helper.setText(content);

        MultipartFile attachment = emailRequestVO.getAttachment();
        if (attachment != null && !attachment.isEmpty()) {
            helper.addAttachment(
                    attachment.getOriginalFilename(),
                    (InputStreamSource) attachment::getInputStream
            );
        }

        mailSender.send(mimeMessage);

        return "Application submitted successfully for " + emailRequestVO.getPosition() + "this postion.";
    }

}
