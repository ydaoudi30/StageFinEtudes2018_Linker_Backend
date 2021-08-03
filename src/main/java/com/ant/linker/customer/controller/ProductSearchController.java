package com.ant.linker.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.module.caracteristic.service.ICaracteristicService;
import com.ant.linker.module.product.service.IProductService;
import com.ant.linker.module.shared.dto.product.DropDownReceivedCatDto;
import com.ant.linker.module.shared.dto.product.DropDownReceivedDto;
import com.ant.linker.module.shared.dto.product.ProductCardDto;
import com.ant.linker.module.shared.dto.product.SearchResultDto;
import com.ant.linker.module.shared.dto.product.ProductCreateDto;
import com.ant.linker.module.shared.dto.product.ProductSearchDto;
import com.ant.linker.module.shared.dto.product.ProductWithCommercialsDto;
import com.ant.linker.module.shared.dto.product.RangeReceivedCatDto;
import com.ant.linker.module.shared.dto.product.RangeReceivedDto;

@CrossOrigin()
@RestController
@RequestMapping("/cust/product/search")
public class ProductSearchController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICaracteristicService caracteristicService;
	
	
	@GetMapping("/latests")
	public List<ProductCardDto> searchNewProduct(){
		return productService.loadNewProducts();
	}
	
	@GetMapping("/bycategoryref")
	public SearchResultDto searchProductsByCategory(@RequestParam() String refCategory){
		return productService.loadProductsByCategory(refCategory);
	}
	@GetMapping("/bykeyword")
	public SearchResultDto searchProductsByKeyword(@RequestParam() String keyword) {
		return productService.searchProduct(keyword);
	}
	
	@PostMapping("/byRangeFilter")
	public SearchResultDto searchProductsByRangeFilter(@RequestBody  RangeReceivedDto range) {
		return caracteristicService.applyRangeFilter(range);
	}
	
	@PostMapping("/byDropDownFilter")
	public SearchResultDto searchProductsByDropDownFilter(@RequestBody  DropDownReceivedDto dropdown) {
		return caracteristicService.applyDropDownFilter(dropdown);
	}
	@PostMapping("/byRangeFilterCat")
	public SearchResultDto searchProductsByRangeFilterCat(@RequestBody  RangeReceivedCatDto range) {
		
		return caracteristicService.applyRangeFilterCat(range);
	}
	
	@PostMapping("/byDropDownFilterCat")
	public SearchResultDto searchProductsByDropDownFilterCat(@RequestBody  DropDownReceivedCatDto dropdown) {
		return caracteristicService.applyDropDownFilterCat(dropdown);
	}
	
	@PostMapping("/by-brand-and-model")
	/**
	 * In productSearchDto use only the brand and model fields
	 * @param productSearchDto
	 * @return
	 */
	public ProductWithCommercialsDto findProductByModelBrand(@RequestBody ProductSearchDto productSearchDto) {
		return productService.searchProductWithCommercialsByBrandModel(productSearchDto);
	}

}