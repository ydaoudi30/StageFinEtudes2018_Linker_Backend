package com.ant.linker.commons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.data.entity.UserRole;
import com.ant.linker.module.commercial.service.IUserService;
import com.ant.linker.module.shared.dto.account.AccountDetailDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;

@RestController
@RequestMapping("/account")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/commercial")
	public void signUpCommercial(@RequestBody AccountDetailDto account) throws Exception {
		userService.createCommercialUserAccount(account);
	}
	
	@PostMapping("/customer")
	public void signUpCustomer(@RequestBody AccountDetailDto account) throws Exception {
		userService.createCustomerUserAccount(account);
	}
	
	@PostMapping("/commercial/get-detail")
	public AccountDetailDto getCommercialDetails(@RequestBody UserSignUpDto user) {
		return userService.getAccountDetail(user.getEmail(), UserRole.COMMERCIAL);
	}
	
	@PostMapping("/customer/get-detail")
	public AccountDetailDto getCustomerDetails(@RequestBody UserSignUpDto user) {
		return userService.getAccountDetail(user.getEmail(), UserRole.CUSTOMER);
	}
	
	@PostMapping("/user/get-detail")
	public AccountDetailDto getUserDetails(@RequestBody UserSignUpDto user) {
		return userService.getAccountDetail(user.getEmail());
	}
}