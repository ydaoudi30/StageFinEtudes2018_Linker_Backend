package com.ant.linker.module.shared.dto.product;

public class ProductSearchResponseDto {

	private Boolean productExist;
	private Boolean inCatalog;
	private Boolean brandExist;
	private Boolean unitExist;
	
	//private ProductDetailDto productDetails;

	public ProductSearchResponseDto(Boolean inCatalog) {
		super();
		this.inCatalog = inCatalog;
	}


	

	public ProductSearchResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean getProductExist() {
		return productExist;
	}

	public void setProductExist(Boolean productExist) {
		this.productExist = productExist;
	}

	public Boolean getInCatalog() {
		return inCatalog;
	}

	public void setInCatalog(Boolean inCatalog) {
		this.inCatalog = inCatalog;
	}

	public Boolean getBrandExist() {
		return brandExist;
	}

	public void setBrandExist(Boolean brandExist) {
		this.brandExist = brandExist;
	}




	public Boolean getUnitExist() {
		return unitExist;
	}




	public void setUnitExist(Boolean unitExist) {
		this.unitExist = unitExist;
	}
}
