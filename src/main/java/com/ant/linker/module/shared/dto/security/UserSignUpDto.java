package com.ant.linker.module.shared.dto.security;

public class UserSignUpDto {
	
	private String email;
	private String password;
	private String[] roles;
	
	public UserSignUpDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserSignUpDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public UserSignUpDto(String email, String password, String[] roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
}