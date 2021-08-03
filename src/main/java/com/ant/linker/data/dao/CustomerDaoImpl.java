package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.User;
import com.googlecode.objectify.Ref;

@Component
public class CustomerDaoImpl implements ICustomerDao {

	@Override
	public Customer loadCustomerByUser(User user) {
		return ofy().load().type(Customer.class).filter("userAccount =", Ref.create(user)).first().now();
	}
	
	@Override
	public Customer save(Customer customer){
		ofy().save().entity(customer).now();
		return customer;
	}

}
