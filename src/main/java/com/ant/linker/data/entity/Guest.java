package com.ant.linker.data.entity;


import java.io.Serializable;
import java.util.Date;


import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index

public class Guest implements Serializable{
	
	@Id
	private Long id;
	@Load
	private Ref<User> userAccount;
	@Load
	private Ref<Cart> cart;
	@Load
	private Date creationDate;
	
	
	public Guest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public User getUserAccount() {
		return userAccount.get();
	}


	public void setUserAccount(User userAccount) {
		this.userAccount = Ref.create(userAccount);
	}
	
	public Cart getCart() {
		return cart.get();
	}


	public void setCart(Cart cart) {
		this.cart = Ref.create(cart);
	}
	
	

}
