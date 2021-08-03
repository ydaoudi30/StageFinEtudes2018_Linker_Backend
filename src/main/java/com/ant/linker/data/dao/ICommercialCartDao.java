package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCart;

public interface ICommercialCartDao {
	
	public CommercialCart save(CommercialCart commercialCart);
	public List<CommercialCart> loadCommercialCartByCommercial(Commercial commercial);
	public List<CommercialCart> loadCommercialCartByCart(Cart cart);
	public List<CommercialCart> loadCommercialCartByCommercialInCart(Cart cart, Commercial commercial);
	public void deleteCommercialCart(CommercialCart commercialCart);

}
