package com.ant.linker.module.shared.factory.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Product;
import com.ant.linker.module.shared.dto.cart.AddFromGuestCartToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToMultipleCartDto;
import com.ant.linker.module.shared.dto.cart.CreateQuoteFromCommercialCartDto;

@Component
public class CartFactory implements ICartFactory {

	@Override
	public AddToMultipleCartDto createGuestMergeMultipleCart(AddFromGuestCartToCartDto guestMergeCart) {
		List<String> commercials = new ArrayList<String>();
		commercials.add(guestMergeCart.getCommercialEmail());
		AddToMultipleCartDto addToCartDto = new AddToMultipleCartDto(guestMergeCart.getBrand(),
				guestMergeCart.getModel(), guestMergeCart.getCustomerEmail(), commercials);
		return addToCartDto;
	}

	@Override
	public AddToCartDto createGuestRemoveCart(AddFromGuestCartToCartDto guestMergeCart) {
		AddToCartDto removePoductFromGuestCart = new AddToCartDto(guestMergeCart.getBrand(), guestMergeCart.getModel(),
				guestMergeCart.getGuestEmail(), guestMergeCart.getCommercialEmail());
		return removePoductFromGuestCart;
	}

	@Override
	public AddToCartDto createRemoveCartFromProduct(Product product, CreateQuoteFromCommercialCartDto quote) {
		AddToCartDto removeCart = new AddToCartDto(product.getBrand().getLabel(), product.getModel(),
				quote.getCustomerEmail(), quote.getCommercialCart().getCommercial());
		return removeCart;
	}

}
