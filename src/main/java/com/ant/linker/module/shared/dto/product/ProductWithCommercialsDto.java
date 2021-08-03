package com.ant.linker.module.shared.dto.product;

import java.util.List;

import com.ant.linker.module.shared.dto.commercial.CommercialInfoDto;

public class ProductWithCommercialsDto extends ProductCreateDto {

	private List<CommercialInfoDto> commercials;

	public ProductWithCommercialsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<CommercialInfoDto> getCommercials() {
		return commercials;
	}

	public void setCommercials(List<CommercialInfoDto> commercials) {
		this.commercials = commercials;
	}
}
