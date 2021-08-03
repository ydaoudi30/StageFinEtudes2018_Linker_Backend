package com.ant.linker.data.dao;

import java.util.Date;
import java.util.List;

import com.ant.linker.data.entity.Brand;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.QuotationRequest;

public interface IProductDao {
	public Product save(Product product);

	public Product loadProduct();
	

	public List<Product> loadAllProducts();

	public List<Product> loadAllProductsByCommercial(Commercial commercial);
	public List<Product> loadAllProductsByQuotationRequest(QuotationRequest quotationRequest);
	
	public Product loadProductByBrandModel(Brand brand, String model);

	public List<Product> testProductsByModelLabel();
		
	
	public List<Product> loadProductsByBrand(Brand brand);
	
	public List<Product> loadProductsByLabel(String label);
	
	public List<Product> filterProductsByDate(Date datePub);

	public Product updateWithMerge(Product productToMerge);
	
	
}