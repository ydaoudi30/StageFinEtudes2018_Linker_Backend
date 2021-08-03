package com.ant.linker.module.shared.factory.cart;

import com.ant.linker.data.entity.Product;
import com.ant.linker.module.shared.dto.cart.AddFromGuestCartToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToMultipleCartDto;
import com.ant.linker.module.shared.dto.cart.CreateQuoteFromCommercialCartDto;

public interface ICartFactory {

	public AddToMultipleCartDto createGuestMergeMultipleCart(AddFromGuestCartToCartDto guestMergeCart);
	
	public AddToCartDto createGuestRemoveCart(AddFromGuestCartToCartDto guestMergeCart);

	public AddToCartDto createRemoveCartFromProduct(Product product, CreateQuoteFromCommercialCartDto quote);
}
