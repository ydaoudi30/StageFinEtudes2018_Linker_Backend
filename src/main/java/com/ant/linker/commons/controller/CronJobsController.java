package com.ant.linker.commons.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.data.dao.ICartDao;
import com.ant.linker.data.dao.ICommercial;
import com.ant.linker.data.dao.ICommercialCartDao;
import com.ant.linker.data.dao.ICustomerDao;
import com.ant.linker.data.dao.IQuotationRequestDao;
import com.ant.linker.data.dao.IUserDao;
import com.ant.linker.module.commercial.service.IUserService;;

@CrossOrigin()
@RestController
@RequestMapping("/cronJobs")
public class CronJobsController{
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

	
	@GetMapping("/clearGuests")
	public void clearGuests(){
		System.out.println("cron job for clearing guests...");
		userService.clearGuests();
	}

	
}