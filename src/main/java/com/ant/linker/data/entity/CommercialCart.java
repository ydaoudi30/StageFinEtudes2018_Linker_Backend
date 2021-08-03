package com.ant.linker.data.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index

public class CommercialCart implements Serializable {
	
	@Id
	private Long id;
	@Load
	private List<Ref<Product>> listProduct;
	@Load
	private Ref<Cart> cart;
	@Load
	private Ref<Commercial> commercial;
	
	
	public CommercialCart() {
		super();
		this.listProduct = new ArrayList<>();
	} 
	
	public List<Product> getListProduct() {
		List<Product> products = new ArrayList<>();
		for (Ref<Product> prod : listProduct) {
			Product product = prod.get();
			products.add(product);
		}
		return products;
	}
	
	public void setListProduct(List<Product> liste) {
		for (Product product : liste) {
			listProduct.add(Ref.create(product));
		}
	
	}
	
	public void addProduct(Product product) {
		listProduct.add(Ref.create(product));
	}
	
	public void removeProduct(Product product) {
		listProduct.remove(Ref.create(product));
	}
	public Cart getCart() {
		return cart.get();
	}


	public void setCart(Cart cart) {
		this.cart = Ref.create(cart);
	}

	public Commercial getCommercial() {

		return commercial.get();
	}
	public void setCommercial(Commercial vendor) {
		this.commercial = Ref.create(vendor);
	}
	
}
