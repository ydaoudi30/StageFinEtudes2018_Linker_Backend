package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.Guest;

public interface ICartDao {

	public Cart save(Cart cart);
	public List<Cart> loadCarts();
	public Cart loadCartByGuest(Guest guest);
	public Cart loadCartByCustomer(Customer customer);
	public List<Cart> loadCartByCommercial(Commercial commercial);
	public void deleteCart(Cart cart);
	
}
