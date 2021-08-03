package com.ant.linker.module.shared.dto.discussion;

import java.util.List;


public class DiscussionDto {
	
	private String refQuote;
	private String commercialEmail;
	private String commercialFullName;
	private String customerEmail;
	private String customerFullName;
	private List<MessageDto> messages;
	
	public DiscussionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiscussionDto(String refQuote, String commercialEmail, String commercialFullName, String customerEmail,
			String customerFullName, List<MessageDto> messages) {
		super();
		this.refQuote = refQuote;
		this.commercialEmail = commercialEmail;
		this.commercialFullName = commercialFullName;
		this.customerEmail = customerEmail;
		this.customerFullName = customerFullName;
		this.messages = messages;
	}

	public String getRefQuote() {
		return refQuote;
	}

	public void setRefQuote(String refQuote) {
		this.refQuote = refQuote;
	}

	public String getCommercialEmail() {
		return commercialEmail;
	}

	public void setCommercialEmail(String commercialEmail) {
		this.commercialEmail = commercialEmail;
	}

	public String getCommercialFullName() {
		return commercialFullName;
	}

	public void setCommercialFullName(String commercialFullName) {
		this.commercialFullName = commercialFullName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	public List<MessageDto> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDto> messages) {
		this.messages = messages;
	}
	
}
