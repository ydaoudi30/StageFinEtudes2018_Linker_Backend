package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.data.entity.Guest;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.User;
import com.googlecode.objectify.Ref;

@Component
public class GuestDao implements IGuestDao {

	@Override
	public Guest loadGuestByUser(User user) {
		return ofy().load().type(Guest.class).filter("userAccount =", Ref.create(user)).first().now();
	}

	@Override
	public Guest saveGuest(Guest guest) {
		// TODO Auto-generated method stub
		ofy().save().entity(guest).now();
		return guest;
	}
	
	
	@Override
	public void deleteGuest(Guest guest) {

		ofy().delete().entity(guest).now();

	}
	
	@Override
	public List<Guest> loadOutDatedGuests(Date date) {
		List<Guest> listGuest = ofy().load().type(Guest.class).filter("creationDate <", date).list();;
		return listGuest;
	}
	

}
