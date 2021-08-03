package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.Product;
import com.googlecode.objectify.Ref;



@Component
public class CommercialCatalogDao implements ICommercialCatalogDao {

	@Override
	public void deleteCommercialCatalog(CommercialCatalog commercialCatalog) {

		ofy().delete().entity(commercialCatalog).now();

	}

	@Override
	public CommercialCatalog findCommercialCatalogBy(Product product, Commercial commercial) {
		 
		return ofy().load().type(CommercialCatalog.class).filter("product", Ref.create(product)).filter("commercial", Ref.create(commercial)).first().now();
	
		 
	}
	
	@Override
	public CommercialCatalog findCommercialCatalogByProduct(Product product) {
		 
		return ofy().load().type(CommercialCatalog.class).filter("product", Ref.create(product)).first().now();
	
		 
	}
	
	
	@Override
	public CommercialCatalog save(CommercialCatalog commercialCatalog) {
		ofy().save().entity(commercialCatalog).now();
		return commercialCatalog;
	
	}
}
