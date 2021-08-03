package com.ant.linker.module.shared.dto.discussion;

import java.util.Date;

public class MessageDto {
	
	private String message;
	private String senderEmail;
	private String senderFullName;
	private Date date;
	
	
	public MessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MessageDto(String message, String senderEmail, String senderFullName, Date date) {
		super();
		this.message = message;
		this.senderEmail = senderEmail;
		this.senderFullName = senderFullName;
		this.date = date;
		
	}



	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getSenderEmail() {
		return senderEmail;
	}


	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}


	public String getSenderFullName() {
		return senderFullName;
	}


	public void setSenderFullName(String senderFullName) {
		this.senderFullName = senderFullName;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	
}
