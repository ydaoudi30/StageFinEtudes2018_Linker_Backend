package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;


import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.Guest;
import com.googlecode.objectify.Ref;

@Component
public class CartDao implements ICartDao {
	
	@Override
	public Cart save(Cart cart) {
		ofy().save().entity(cart).now();
		return cart;
	}
	
	@Override
	public List<Cart> loadCarts(){
		List<Cart> listCart = ofy().load().type(Cart.class).list();
		return listCart;
	}
	
	@Override
	public Cart loadCartByGuest(Guest guest){
		return ofy().load().type(Cart.class).filter("guest", Ref.create(guest)).first().now();
	}
	
	@Override
	public Cart loadCartByCustomer(Customer customer){
		return ofy().load().type(Cart.class).filter("customer", Ref.create(customer)).first().now();
	}
	
	@Override
	public List<Cart> loadCartByCommercial(Commercial commercial){
		List<Cart> carts = ofy().load().type(Cart.class).filter("commercial", Ref.create(commercial)).list();
		return carts;
	}
	
	@Override
	public void deleteCart(Cart cart) {

		ofy().delete().entity(cart).now();

	}
	
	
}