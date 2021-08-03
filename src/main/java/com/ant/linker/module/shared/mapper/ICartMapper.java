package com.ant.linker.module.shared.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.module.shared.dto.cart.CartDto;
import com.ant.linker.module.shared.dto.cart.CommercialCartDto;

@Mapper(componentModel = "spring", uses = IProductCreateMapper.class)
@Component
public abstract class ICartMapper {

	@Mappings({ 
		@Mapping(source = "customer.userAccount.email", target = "customer"),
		@Mapping(ignore = true, target = "guest"),
		@Mapping(source = "listCommercialCart", target = "listCommercialCartDto")})

	public abstract CartDto entityToDto(Cart cart);

	public abstract List<CommercialCartDto> listCommercialCartToListCommercialCartDto(List<CommercialCart> list);

	@Mappings({
		@Mapping(source = "commercial.userAccount.email", target = "commercial"),
		@Mapping(source = "listProduct", target = "listProduct", qualifiedByName = "ToProductCreateDto") })
	public abstract CommercialCartDto entityToDto(CommercialCart commercialCart);

}