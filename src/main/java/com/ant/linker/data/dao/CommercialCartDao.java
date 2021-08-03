package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.data.entity.CommercialCatalog;
import com.googlecode.objectify.Ref;

@Component
public class CommercialCartDao implements ICommercialCartDao {


	@Override
	public CommercialCart save(CommercialCart commercialCart) {
		// TODO Auto-generated method stub
		ofy().save().entity(commercialCart).now();
		return commercialCart;
	}

	@Override
	public List<CommercialCart> loadCommercialCartByCommercial(Commercial commercial){
		List<CommercialCart> commercialCarts = ofy().load().type(CommercialCart.class).filter("commercial", Ref.create(commercial)).list();
		return commercialCarts;
	}

	@Override
	public List<CommercialCart> loadCommercialCartByCart(Cart cart){
		List<CommercialCart> commercialCarts = ofy().load().type(CommercialCart.class).filter("cart", Ref.create(cart)).list();
		return commercialCarts;
	}
	
	@Override
	public void deleteCommercialCart(CommercialCart commercialCart) {

		ofy().delete().entity(commercialCart).now();

	}
	
	@Override
	public List<CommercialCart> loadCommercialCartByCommercialInCart(Cart cart, Commercial commercial){
		return ofy().load().type(CommercialCart.class).filter("cart", Ref.create(cart)).filter("commercial", Ref.create(commercial)).list();
	}
}
