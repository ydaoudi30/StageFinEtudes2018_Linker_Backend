package com.ant.linker.data.dao;

import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.Product;


public interface ICommercialCatalogDao {

	public CommercialCatalog findCommercialCatalogBy(Product product, Commercial commercial);
	
	public void deleteCommercialCatalog(CommercialCatalog commercCatalog);

	public CommercialCatalog save(CommercialCatalog commercialCatalog);
	public CommercialCatalog findCommercialCatalogByProduct(Product product);


}
