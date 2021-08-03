package com.ant.linker.commons.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.data.dao.ICartDao;
import com.ant.linker.data.dao.ICommercial;
import com.ant.linker.data.dao.ICommercialCartDao;
import com.ant.linker.data.dao.ICustomerDao;
import com.ant.linker.data.dao.IQuotationRequestDao;
import com.ant.linker.data.dao.IUserDao;
import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.DelayUnit;
import com.ant.linker.data.entity.Guest;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.QuoteRequestStatus;
import com.ant.linker.data.entity.User;
import com.ant.linker.data.entity.UserRole;
import com.ant.linker.module.commercial.service.IUserService;
import com.ant.linker.module.shared.dto.dropDownValues.DropDawnValuesDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;;

@CrossOrigin()
@RestController
@RequestMapping("/serve/dorpdown/values")
public class DropDownValuesController {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private ICommercial commercialDao;
	@Autowired
	private ICommercialCartDao comCartDao;
	@Autowired
	private ICartDao cartDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private IQuotationRequestDao quotationRequestDao;

	@PostMapping("/delays")
	public List<DropDawnValuesDto> delays(@RequestBody UserSignUpDto commercialDto) {
		DelayUnit[] values = DelayUnit.values();

		if (values == null) {
			return null;
		}

		List<DropDawnValuesDto> list = new ArrayList<>();

		for (DelayUnit item : values) {
			list.add(new DropDawnValuesDto(item.getDelay(), item.name()));
		}
		return list;
	}

	@PostMapping("/status")
	public List<DropDawnValuesDto> status(@RequestBody UserSignUpDto commercialDto) {
		QuoteRequestStatus[] values = QuoteRequestStatus.values();

		if (values == null) {
			return null;
		}

		List<DropDawnValuesDto> list = new ArrayList<>();

		for (QuoteRequestStatus item : values) {
			list.add(new DropDawnValuesDto(item.getStatus(), item.name()));
		}
		return list;
	}

	@PostMapping("/commercials")
	public List<DropDawnValuesDto> commercials(@RequestBody UserSignUpDto customerOrGuestDto){
		

		List<DropDawnValuesDto> list = new ArrayList<>();
		List<CommercialCart> comCart = new ArrayList<>();
		
		if (Arrays.asList(customerOrGuestDto.getRoles()).contains("GUEST")){
			Guest guest = userService.getGuestByAccountEmail(customerOrGuestDto.getEmail());
			comCart = comCartDao.loadCommercialCartByCart(cartDao.loadCartByGuest(guest));
		}
		else if (Arrays.asList(customerOrGuestDto.getRoles()).contains("CUSTOMER")){
			Customer customer = userService.getCustomerByAccountEmail(customerOrGuestDto.getEmail());
			comCart = comCartDao.loadCommercialCartByCart(cartDao.loadCartByCustomer(customer));
		}
		
		
		for(CommercialCart commercialCart : comCart){
			int x = 0;
			for (DropDawnValuesDto item : list) {
				if (commercialCart.getCommercial().getUserAccount().getFullName() == item.getValue()) {
					x = 1;
				}
			}
			if (x == 0) {
				list.add(new DropDawnValuesDto(commercialCart.getCommercial().getUserAccount().getEmail(),
						commercialCart.getCommercial().getUserAccount().getFullName()));
			}
		}

		return list;

	}
	
	@PostMapping("/GuestCartCommercials")
	public List<DropDawnValuesDto> guestCartCommercials(@RequestBody String guestEmail){
		

		List<DropDawnValuesDto> list = new ArrayList<>();
		List<CommercialCart> comCart = new ArrayList<>();
		
		User user = userDao.load(guestEmail);
		
		if(user.getRoles().contains(UserRole.GUEST)){
			Guest guest = userService.getGuestByAccountEmail(guestEmail);
			comCart = comCartDao.loadCommercialCartByCart(cartDao.loadCartByGuest(guest));
		}
		else if(user.getRoles().contains(UserRole.CUSTOMER)){
			Customer customer = userService.getCustomerByAccountEmail(guestEmail);
			comCart = comCartDao.loadCommercialCartByCart(cartDao.loadCartByCustomer(customer));
		}
		
		
		for(CommercialCart commercialCart : comCart){
			int x = 0;
			for(DropDawnValuesDto item : list){
				if(commercialCart.getCommercial().getUserAccount().getFullName() == item.getValue()){
					x = 1;
				}
			}
			if(x == 0){
				list.add(new DropDawnValuesDto(commercialCart.getCommercial().getUserAccount().getEmail(), commercialCart.getCommercial().getUserAccount().getFullName()));
			}
		}
		
		return list;
		
	}
	
	@PostMapping("/inPreparationQuotesCommercials")
	public List<DropDawnValuesDto> getInPreparationQuotesCommercials(@RequestBody UserSignUpDto custmerDto) {
		Customer customer = userService.getCustomerByAccountEmail(custmerDto.getEmail());
		List<QuotationRequest> quotes = quotationRequestDao.loadQuotationRequestsByCustomer(customer);
		List<DropDawnValuesDto> list = new ArrayList<>();

		for (QuotationRequest quote : quotes) {
			int x = 0;
			for (DropDawnValuesDto item : list) {
				if (quote.getVendor().getUserAccount().getFullName() == item.getValue()) {
					x = 1;
				}
			}
			if (x == 0 && quote.getEnumStatus() == QuoteRequestStatus.InPreparation) {
				list.add(new DropDawnValuesDto(quote.getVendor().getUserAccount().getEmail(),
						quote.getVendor().getUserAccount().getFullName()));
			}
		}

		return list;
	}


}