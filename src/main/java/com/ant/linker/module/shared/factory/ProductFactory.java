package com.ant.linker.module.shared.factory;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ant.linker.module.shared.dto.product.ProductCardDto;
import com.ant.linker.module.shared.dto.product.ProductFiltersDto;
import com.ant.linker.module.shared.dto.product.SearchResultDto;

@Service
public class ProductFactory implements IProductFactory {
	
	public SearchResultDto makeSearchResultDto(ProductFiltersDto filterlist, List<ProductCardDto> productlist) {
		SearchResultDto searchResult = new SearchResultDto(filterlist, productlist);
		return searchResult;
	}

	

}
