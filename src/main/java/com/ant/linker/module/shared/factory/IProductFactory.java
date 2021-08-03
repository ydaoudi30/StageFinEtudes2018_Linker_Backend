package com.ant.linker.module.shared.factory;

import java.util.List;

import com.ant.linker.module.shared.dto.product.ProductCardDto;
import com.ant.linker.module.shared.dto.product.ProductFiltersDto;
import com.ant.linker.module.shared.dto.product.SearchResultDto;


public interface IProductFactory {
	
	public SearchResultDto makeSearchResultDto(ProductFiltersDto allFilters, List<ProductCardDto> productlist);

}
