package com.ant.linker.module.shared.dto.product;

import java.util.List;

public class SearchResultDto {
	
	ProductFiltersDto filterlist;
	List<ProductCardDto> productlist;
	
	public SearchResultDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchResultDto(ProductFiltersDto filterlist, List<ProductCardDto> productlist) {
		super();
		this.filterlist = filterlist;
		this.productlist = productlist;
	}
	
	public ProductFiltersDto getFilterlist() {
		return filterlist;
	}
	public void setFilterlist(ProductFiltersDto filterlist) {
		this.filterlist = filterlist;
	}
	public List<ProductCardDto> getProductlist() {
		return productlist;
	}
	public void setProductlist(List<ProductCardDto> productlist) {
		this.productlist = productlist;
	}

	
}
