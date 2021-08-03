package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Brand;
import com.ant.linker.data.entity.GeneralCatalog;
import com.ant.linker.data.entity.Product;


@Component
public class BrandDao implements IBrand {

	@Override
	public Brand save(Brand brand) {
		ofy().save().entity(brand).now();
		return brand;
	}

	@Override
	public Brand loadBrandByLabel(String label) {
		// TODO Auto-generated method stub
		return ofy().load().type(Brand.class).filter("label =", label).first().now();
	}

	@Override
	public Brand loadBrand() {
		// TODO Auto-generated method stub
		return ofy().load().type(Brand.class).first().now();
	}

}
