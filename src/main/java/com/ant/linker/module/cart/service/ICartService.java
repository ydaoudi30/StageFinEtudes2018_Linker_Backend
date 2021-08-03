package com.ant.linker.module.cart.service;

import java.util.List;

import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.module.shared.dto.cart.AddFromGuestCartToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToMultipleCartDto;
import com.ant.linker.module.shared.dto.cart.CartDto;
import com.ant.linker.module.shared.dto.cart.EmailsForMergeDto;

public interface ICartService {
	public Integer nbArticle(String customerOrGuestEmail);
	public CartDto sendCartByUser(String email);
	public CartDto removeProductFromCart(AddToCartDto productToRemove);
	public CartDto addProductFromCartGuest(AddFromGuestCartToCartDto productToAdd) throws Exception;
	public CommercialCart findComCartByEmail(Cart cart, String commercialEmail);
	public Integer mergeCarts(EmailsForMergeDto infos)  throws Exception;
	Integer addProductToCart(AddToMultipleCartDto addToCartDto) throws Exception;
}
