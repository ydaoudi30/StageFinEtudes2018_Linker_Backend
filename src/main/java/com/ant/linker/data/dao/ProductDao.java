package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Brand;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.QuoteRequestInfo;
import com.ant.linker.module.shared.mapper.IProductMapper;
import com.googlecode.objectify.Ref;

@Component
public class ProductDao implements IProductDao {

	@Autowired
	private IProductMapper mapper;
	
	@Override
	public Product save(Product product) {
		Product foundProduct = loadProductByBrandModel(product.getBrand(), product.getModel());
		if(foundProduct != null) {
			product.setIdProduct(foundProduct.getIdProduct());
			ofy().save().entity(product).now();
		} else {
			ofy().save().entity(product).now();
		}
		return product;
	}
	
	@Override
	public Product updateWithMerge(Product productToMerge) {
		Product foundProduct = loadProductByBrandModel(productToMerge.getBrand(), productToMerge.getModel());
		if(foundProduct != null) {
			System.out.println("Product duplicated : " + productToMerge.getModel() + "  ###  " + productToMerge.getBrand());
			foundProduct = mapper.mergeEntityToEntity(foundProduct, productToMerge);
			foundProduct.setIdProduct(foundProduct.getIdProduct());
			ofy().save().entity(foundProduct).now();
			return foundProduct;
		} else {
			ofy().save().entity(productToMerge).now();
			return productToMerge;
		}
	}

	@Override
	public List<Product> loadAllProducts() {
		List<Product> listProduct = ofy().load().type(Product.class).list();
		return listProduct;
	}

	@Override
	public Product loadProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> loadAllProductsByCommercial(Commercial commercial) {
		if(commercial == null){
			return new ArrayList<>();
		}
		List<CommercialCatalog> commercialCatalogs = ofy().load().type(CommercialCatalog.class)
				.filter("commercial", Ref.create(commercial)).list();
		return commercialCatalogs.stream().map(CommercialCatalog::getProduct).collect(Collectors.toList());
	}
	
	@Override
	public List<Product> loadAllProductsByQuotationRequest(QuotationRequest quotationRequest){
		List<QuoteRequestInfo> quoteRequestInfos = ofy().load().type(QuoteRequestInfo.class).filter("quotationRequest", Ref.create(quotationRequest)).list();
		return quoteRequestInfos.stream().map(QuoteRequestInfo::getProduct).collect(Collectors.toList());
	}

	@Override
	public Product loadProductByBrandModel(Brand brand, String model) {

		return ofy().load().type(Product.class).filter("brand =", Ref.create(brand)).filter("model =", model).first().now();
				

	}
	
	@Override
	public  List<Product> testProductsByModelLabel() {
		// TODO Auto-generated method stub
		return ofy().load().type(Product.class).filter("label =", "dateur").list();	
	}
	
	@Override
	public List<Product> loadProductsByBrand(Brand brand) {
		// TODO Auto-generated method stub
		return ofy().load().type(Product.class).filter("brand =", Ref.create(brand)).list();
	}

	@Override
	public List<Product> loadProductsByLabel(String label) {
		// TODO Auto-generated method stub
		return ofy().load().type(Product.class).filter("label =", label).list();
	}

	@Override
	public List<Product> filterProductsByDate(Date date) {
		// TODO Auto-generated method stub
		return ofy().load().type(Product.class).limit(16).order("-datePub").filter("datePub >", date).list();
	}
	
	
	
}