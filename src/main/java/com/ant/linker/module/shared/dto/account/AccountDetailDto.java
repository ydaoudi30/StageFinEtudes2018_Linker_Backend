package com.ant.linker.module.shared.dto.account;

import com.ant.linker.module.shared.dto.security.UserSignUpDto;

public class AccountDetailDto {

	private UserSignUpDto credentials;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String company;

	public UserSignUpDto getCredentials() {
		return credentials;
	}

	public void setCredentials(UserSignUpDto credentials) {
		this.credentials = credentials;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
