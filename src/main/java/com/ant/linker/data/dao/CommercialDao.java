package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Commercial;

import com.ant.linker.data.entity.*;
import com.googlecode.objectify.Ref;

@Component
public class CommercialDao implements ICommercial {

	@Override
	public Commercial loadCommercialByUser(User user) {
		return ofy().load().type(Commercial.class).filter("userAccount =", Ref.create(user)).first().now();
	}

	@Override
	public Commercial saveCommercial(Commercial commercial) {
		// TODO Auto-generated method stub
		ofy().save().entity(commercial).now();
		return commercial;
	}
	

	

}
