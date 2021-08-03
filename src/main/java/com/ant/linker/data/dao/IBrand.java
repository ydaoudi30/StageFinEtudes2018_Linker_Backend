package com.ant.linker.data.dao;

import com.ant.linker.data.entity.Brand;


public interface IBrand {
	public Brand save(Brand brand);
	public Brand loadBrandByLabel(String label);
	public Brand loadBrand();

}
