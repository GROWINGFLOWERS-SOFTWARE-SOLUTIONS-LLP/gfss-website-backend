package com.gfss.website.com.VO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EmailRequestVO {

	private String name;
    private String email;
    private String phone;
    private String position;
    private MultipartFile attachment;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public MultipartFile getAttachment() {
		return attachment;
	}
	public void setAttachment(MultipartFile attachment) {
		this.attachment = attachment;
	}
    
    
    
}
