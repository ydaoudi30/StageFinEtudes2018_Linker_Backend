package com.ant.linker.commercial.controller;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.data.dao.IBrand;
import com.ant.linker.data.dao.IEntreprise;
import com.ant.linker.data.dao.IFileDao;
import com.ant.linker.data.dao.IProductDao;
import com.ant.linker.data.entity.Brand;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.Entreprise;
import com.ant.linker.data.entity.File;
import com.ant.linker.data.entity.Product;
import com.ant.linker.module.caracteristic.service.ICaracteristicService;
import com.ant.linker.module.category.service.ICategoryService;
import com.ant.linker.module.commercial.service.IUserService;
import com.ant.linker.module.product.service.IProductService;
import com.ant.linker.module.shared.dto.product.CategoryDto;
import com.ant.linker.module.shared.dto.product.CommercialProductDto;
import com.ant.linker.module.shared.dto.product.ProductCreateDto;
import com.ant.linker.module.shared.dto.product.ProductListElement;
import com.ant.linker.module.shared.dto.product.ProductSearchDto;
import com.ant.linker.module.shared.dto.product.ProductSearchResponseDto;
import com.ant.linker.module.shared.dto.product.UnitDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;

@CrossOrigin()
@RestController
@RequestMapping("/cmr/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	@Autowired
	private IBrand brandDao;
	@Autowired
	private ICaracteristicService caracteristicService;
	@Autowired
	private IEntreprise manufacturerDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IFileDao fileDao;
	@Autowired
	private IUserService userService;
	@Autowired
	private ICategoryService categoryService;
	
	@PostMapping("/commercial/list")
	public List<ProductListElement> getList(@RequestBody UserSignUpDto commercialDto) {
		return productService.loadProductList(commercialDto);
	}

	@PostMapping("/search")
	public ProductSearchResponseDto findProduct(@RequestBody ProductSearchDto productSearchDto) {
		return productService.searchProduct(productSearchDto);
	}
	
	@PostMapping("/commercial/delete")
	public boolean deleteCommercialProduct(@RequestBody CommercialProductDto commercialProductDto) {

		try {
			productService.deleteCommercialCatalog(commercialProductDto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GetMapping("/default")
	public void defaultUp() {
		Product product = new Product("Moteur", "A01IA89", new Date(), "ceci est moteur");

		File file = new File();
		file = fileDao.save(file);
		Entreprise manufacturer = new Entreprise();
		manufacturer.setNomination("Blackmere");
		manufacturer = manufacturerDao.save(manufacturer);

		Brand brand = new Brand();
		brand.setManufacturer(manufacturer);
		brand.setLabel("Motors");

		brand = brandDao.save(brand);
		product.setBrand(brand);
		product.addImage(file);

		productDao.save(product);

		Commercial commercial = userService.getCommercialByAccountEmail("agent@gmail.com");

		if (commercial != null) {
			CommercialCatalog commercialCatalog = new CommercialCatalog();
			ofy().save().entity(commercialCatalog).now();

			commercialCatalog.setCommercial(commercial);
			commercialCatalog.setProduct(product);

			product.addCommercialCatalog(commercialCatalog);
			commercial.addCommercialCatalog(commercialCatalog);

			productDao.save(product);
			ofy().save().entity(commercial).now();
			ofy().save().entity(commercialCatalog).now();
		}
	}

	@GetMapping("/category-list")
	public List<CategoryDto> findCategory() {
		return categoryService.loadListeCategory();
	}
	
	
	@GetMapping("/unit-list")
	public List<UnitDto> findUnit() {
		return caracteristicService.loadListUnit();
	}

	@PostMapping("/create-product")
	public void createProduct(@RequestBody ProductCreateDto productCreate) {
		 productService.createProduct(productCreate, false, false);
	}

	@PostMapping("/create-unit")
	public UnitDto createUnit(@RequestBody UnitDto unitDto) {
		return caracteristicService.createUnit(unitDto);
	}

	@PostMapping("/searchProduct")
	public ProductCreateDto findProductByModelBrand(@RequestBody ProductSearchDto productSearchDto) {
		return productService.searchProductByBrandModel(productSearchDto);
	}
	
	@PostMapping("/publish")
	public Integer publishProduct(@RequestBody ProductSearchDto productSearchDto) {
		return productService.publishProductToCustommer(productSearchDto);
	}

}

