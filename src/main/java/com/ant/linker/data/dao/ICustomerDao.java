package com.ant.linker.data.dao;

import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.User;

public interface ICustomerDao {

	public Customer loadCustomerByUser(User user);
	public Customer save(Customer customer);
}
