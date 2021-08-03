package com.ant.linker.module.shared.data.product;

import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.Product;

public class CommercialCatalogEntities {

	private Product product;

	private Commercial commercial;

	private CommercialCatalog commercialCatalog;
	
	

	public CommercialCatalogEntities(Product product, Commercial commercial,
			CommercialCatalog commercialCatalog) {
		super();
		this.product = product;
		this.commercial = commercial;
		this.commercialCatalog = commercialCatalog;
	}

	public CommercialCatalogEntities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Commercial getCommercial() {
		return commercial;
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}

	public CommercialCatalog getCommercialCatalog() {
		return commercialCatalog;
	}

	public void setCommercialCatalog(CommercialCatalog commercialCatalog) {
		this.commercialCatalog = commercialCatalog;
	}
	
}
