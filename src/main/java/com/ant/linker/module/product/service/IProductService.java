package com.ant.linker.module.product.service;

import java.util.List;

import com.ant.linker.data.entity.Brand;
import com.ant.linker.data.entity.Product;
import com.ant.linker.module.shared.data.product.CommercialCatalogEntities;
import com.ant.linker.module.shared.dto.product.CommercialProductDto;
import com.ant.linker.module.shared.dto.product.ProductCardDto;
import com.ant.linker.module.shared.dto.product.ProductCreateDto;
import com.ant.linker.module.shared.dto.product.ProductListElement;
import com.ant.linker.module.shared.dto.product.ProductSearchDto;
import com.ant.linker.module.shared.dto.product.ProductSearchResponseDto;
import com.ant.linker.module.shared.dto.product.SearchResultDto;
import com.ant.linker.module.shared.dto.product.ProductWithCommercialsDto;
import com.ant.linker.module.shared.dto.product.UnitDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;

public interface IProductService {
	
	public List<ProductListElement> loadProductList(UserSignUpDto commercialAccount);
	public void deleteCommercialCatalog(CommercialProductDto commercialProductDao) ;
	public ProductSearchResponseDto searchProduct(ProductSearchDto  productSearchDto );
	public CommercialCatalogEntities loadCommercialCatalogEntities(Brand brand, String modelParam, String emailParam);
	public void createProduct (ProductCreateDto productCreate, boolean importingMode, boolean mergeProduct);
	public List<ProductCardDto> loadNewProducts();
	public SearchResultDto loadProductsByCategory(String refCategory);
	public SearchResultDto searchProduct(String keyword);
	public ProductCreateDto searchProductByBrandModel(ProductSearchDto  productSearchDto );
	public ProductWithCommercialsDto searchProductWithCommercialsByBrandModel(ProductSearchDto productSearchDto);
	public Integer publishProductToCustommer(ProductSearchDto  productSearchDto);
	public boolean DoesProductContainsKeyword(Product product, List<String> subkeys);
	public SearchResultDto searchProductAfterFilter(String keyword, List<Product> listeProduct);
	public SearchResultDto searchProductAfterFilterCat(List<Product> listeProduct);
	
}
