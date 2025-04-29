package com.gfss.website.com.service;
import com.gfss.website.com.VO.EmailRequestVO;

import jakarta.mail.MessagingException;

public interface EmailService {
	String sendApplicationEmail(EmailRequestVO emailRequestVO) throws MessagingException;
}
