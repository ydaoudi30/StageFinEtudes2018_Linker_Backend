package com.ant.linker.module.commercial.service;

import java.util.ArrayList;
import java.util.List;

import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.Guest;
import com.ant.linker.data.entity.User;
import com.ant.linker.data.entity.UserRole;
import com.ant.linker.module.shared.dto.account.AccountDetailDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;

public interface IUserService {

	public Commercial getCommercialByAccountEmail(String email);
	public Customer getCustomerByAccountEmail(String email);
	public Guest getGuestByAccountEmail(String email);
	public UserSignUpDto createGuestAccount();
	public Integer deleteGuestAccount(String email);
	public void clearGuests();
	public void createCustomerUserAccount(AccountDetailDto customerAccount) throws Exception;
	public void createCommercialUserAccount(AccountDetailDto commercialAccount) throws Exception;
	public AccountDetailDto getAccountDetail(String email, UserRole userRole);
	public AccountDetailDto getAccountDetail(String email);
}
