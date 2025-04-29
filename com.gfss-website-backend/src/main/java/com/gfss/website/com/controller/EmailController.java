package com.gfss.website.com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfss.website.com.VO.EmailRequestVO;
import com.gfss.website.com.service.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/submit")
@RequiredArgsConstructor
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/apply-job")
	public ResponseEntity<Map<String, Object>> applyJob(@ModelAttribute EmailRequestVO emailRequestVO) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        String message = emailService.sendApplicationEmail(emailRequestVO);
	        response.put("success", true);
	        response.put("message", message);
	        return ResponseEntity.ok(response);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        response.put("success", false);
	        response.put("message", "Failed to send application: " + e.getMessage());
	        return ResponseEntity.status(500).body(response);
	    }
	}

}
