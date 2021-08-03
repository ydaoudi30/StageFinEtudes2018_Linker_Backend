package com.ant.linker.module.product.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.ant.linker.cache.CachePaginatorHelper;
import com.ant.linker.cache.config.CacheConfig;
import com.ant.linker.data.dao.IAbstractCategoryDao;
import com.ant.linker.data.dao.IBrand;
import com.ant.linker.data.dao.ICommercial;
import com.ant.linker.data.dao.ICommercialCatalogDao;
import com.ant.linker.data.dao.IFileDao;
import com.ant.linker.data.dao.IProductDao;
import com.ant.linker.data.entity.Brand;
import com.ant.linker.data.entity.Characteristic;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.File;
import com.ant.linker.data.entity.FinalCategory;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.values.type.AbstractValue;
import com.ant.linker.module.caracteristic.service.ICaracteristicService;
import com.ant.linker.module.commercial.service.IUserService;
import com.ant.linker.module.shared.data.product.CommercialCatalogEntities;
import com.ant.linker.module.shared.dto.product.CommercialProductDto;
import com.ant.linker.module.shared.dto.product.ProductCardDto;
import com.ant.linker.module.shared.dto.product.ProductCreateDto;
import com.ant.linker.module.shared.dto.product.ProductFiltersDto;
import com.ant.linker.module.shared.dto.product.ProductListElement;
import com.ant.linker.module.shared.dto.product.ProductSearchDto;
import com.ant.linker.module.shared.dto.product.ProductSearchResponseDto;
import com.ant.linker.module.shared.dto.product.ProductWithCommercialsDto;
import com.ant.linker.module.shared.dto.product.SearchResultDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;
import com.ant.linker.module.shared.factory.IFileFactory;
import com.ant.linker.module.shared.factory.IProductFactory;
import com.ant.linker.module.shared.mapper.ICharacteristicMapper;
import com.ant.linker.module.shared.mapper.IProductCreateMapper;
import com.ant.linker.module.shared.mapper.IProductMapper;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao productDao;
	@Autowired
	private IAbstractCategoryDao abstractCategoryDao;

	@Autowired
	private ICommercial commercialDao;
	@Autowired
	private IBrand brandDao;
	@Autowired
	private ICommercialCatalogDao commercialCatalogDao;
	@Autowired
	private IUserService userService;
	@Autowired
	ICharacteristicMapper iCharacteristicMapper;
	@Autowired
	private IProductMapper iProductMapper;

	@Autowired
	private IProductCreateMapper iProductCreateMapper;

	@Autowired
	private IFileDao fileDao;

	@Autowired
	private ICaracteristicService caracteristicService;
	
	@Autowired
	private IProductFactory iProductFactory;
	
	@Autowired
	private CachePaginatorHelper cache;
	
	@Autowired
	private IFileFactory fileFactory;

	public List<ProductListElement> loadProductList(UserSignUpDto commercialAccount) {
		Commercial commercial = userService.getCommercialByAccountEmail(commercialAccount.getEmail());
		return iProductMapper.entitiesToDtos(productDao.loadAllProductsByCommercial(commercial));

	}

	@Override
	public void deleteCommercialCatalog(CommercialProductDto commercialProductDao) {

		Brand brand = brandDao.loadBrandByLabel(commercialProductDao.getBrand());

		Product product = productDao.loadProductByBrandModel(brand, commercialProductDao.getModel());

		Commercial commercial = userService.getCommercialByAccountEmail(commercialProductDao.getCommercialEmail());

		CommercialCatalog commercialCatalog = commercialCatalogDao.findCommercialCatalogBy(product, commercial);

		product.removeCommercialCatalog(commercialCatalog);
		productDao.save(product);

		commercial.removeCommercialCatalog(commercialCatalog);
		commercialDao.saveCommercial(commercial);

		commercialCatalogDao.deleteCommercialCatalog(commercialCatalog);

	}

	@Override
	public ProductSearchResponseDto searchProduct(ProductSearchDto productSearchDto) {

		ProductSearchResponseDto productSearchResponseDto = new ProductSearchResponseDto();
		String brandLabel = productSearchDto.getBrand().toUpperCase();
		String model = productSearchDto.getModel().toUpperCase();
		String commercialEmail = productSearchDto.getCommercialEmail();

		Brand brand = brandDao.loadBrandByLabel(brandLabel);
		CommercialCatalogEntities commercialCatalogEntities = loadCommercialCatalogEntities(brand, model,
				commercialEmail);

		if (brand == null) {
			productSearchResponseDto.setBrandExist(false);
		} else {
			Product product = commercialCatalogEntities.getProduct();

			if (product == null) {
				productSearchResponseDto.setProductExist(false);
			} else {

				if (commercialCatalogEntities.getCommercialCatalog() != null) {
					productSearchResponseDto.setInCatalog(true);
				} else {
					productSearchResponseDto.setInCatalog(false);
				}

				productSearchResponseDto.setProductExist(true);
			}

			productSearchResponseDto.setBrandExist(true);
		}
		return productSearchResponseDto;
	}

	public CommercialCatalogEntities loadCommercialCatalogEntities(Brand brand, String modelParam, String emailParam) {

		if (brand != null) {
			Product product = productDao.loadProductByBrandModel(brand, modelParam);
			Commercial commercial = userService.getCommercialByAccountEmail(emailParam);
			CommercialCatalog commercialCatalog = null;
			if (product != null && commercial != null) {
				commercialCatalog = commercialCatalogDao.findCommercialCatalogBy(product, commercial);
			}
			return new CommercialCatalogEntities(product, commercial, commercialCatalog);
		}

		return new CommercialCatalogEntities();
	}

	@Override
	/**
	 * createProductOnly param : is for create product without any relation with
	 * commercial
	 */
	public void createProduct(ProductCreateDto productCreate, boolean createProductOnly, boolean mergeProduct) {
		Commercial commercial = null;
		CommercialCatalog commercialCatalog = null;
		if (!createProductOnly) {
			commercialCatalog = new CommercialCatalog();
			commercial = userService.getCommercialByAccountEmail(productCreate.getCommercialEmail());
		}

		String brandLabel = productCreate.getBrandLabel().toUpperCase();

		Brand brand = brandDao.loadBrandByLabel(brandLabel);

		if (brand == null) {
			brand = iProductCreateMapper.labelToBrand(brandLabel);
			brandDao.save(brand);
		}

		String refCategory = productCreate.getRefCategory();
		FinalCategory finalCategory = abstractCategoryDao.loadFinalCategoryByref(refCategory);

		Product product = iProductCreateMapper.dtoToEntities(productCreate, brand, finalCategory);
		product.setDatePub(new Date());
		product.makeSearchField();
		product.setModel(product.getModel().toUpperCase());
		product.setLabel(product.getLabel().toUpperCase());

		if (productCreate.getImages() == null || productCreate.getImages().isEmpty()) {
			File image = new File();
			fileDao.save(image);
			product.addImage(image);
		} else {
			for (String fileId : productCreate.getImages()){
				File image = fileDao.loadFileByFileId(fileId);
				if(image != null) {
					product.addImage(image);
				} else {
					File fileEntity = fileFactory.constructFileEntity(fileId, fileId, MediaType.IMAGE_JPEG_VALUE);
					fileEntity = fileDao.save(fileEntity);
					product.addImage(fileEntity);
				}
			}
		}
		
		if(mergeProduct) {
			product = (Product) productDao.updateWithMerge(product).clone();
		} else {
			productDao.save(product);
		}

		if (finalCategory != null) {
			finalCategory.addProduct(product);
			abstractCategoryDao.saveCategory(finalCategory);
		}

		List<Characteristic<AbstractValue>> createCaracteristic = caracteristicService.createCaracteristic(productCreate.getCharacteristics(), product);
		product.setListeCharacteristic(createCaracteristic);
		if(mergeProduct) {
			product = (Product) productDao.updateWithMerge(product).clone();
		} else {
			productDao.save(product);
		}

		if (!createProductOnly) {
			commercialCatalog.setCommercial(commercial);
			commercialCatalog.setProduct(product);
			commercialCatalogDao.save(commercialCatalog);

			product.addCommercialCatalog(commercialCatalog);
			commercial.addCommercialCatalog(commercialCatalog);

			if(mergeProduct) {
				product = (Product) productDao.updateWithMerge(product).clone();
			} else {
				productDao.save(product);
			}
			commercialDao.saveCommercial(commercial);
		}
		
		cache.loadToCache(CacheConfig.ALL_PRODUCTS_KEY, product);

	}

	@Override
	public ProductCreateDto searchProductByBrandModel(ProductSearchDto productSearchDto) {
		Product product = loadProduct(productSearchDto);
        return iProductCreateMapper.entiteToDto(product);
	}
	
	@Override
	public ProductWithCommercialsDto searchProductWithCommercialsByBrandModel(ProductSearchDto productSearchDto) {
		Product product = loadProduct(productSearchDto);
        return iProductCreateMapper.entiteWithCommercialsToDto(product);
	}
	
	private Product loadProduct(ProductSearchDto productSearchDto) {
		String brandLabel = productSearchDto.getBrand();
		String model = productSearchDto.getModel();
		Brand brand = brandDao.loadBrandByLabel(brandLabel);
        Product product = productDao.loadProductByBrandModel(brand, model);
		return product;
	}
	
	@Override
	public List<ProductCardDto> loadNewProducts() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -8);
		Date dateLimit = cal.getTime();

		List<Product> filteredProducts = productDao.filterProductsByDate(dateLimit);
		List<ProductCardDto> newProducts = iProductMapper.entitiesToCardDtos(filteredProducts);
		return newProducts;
	}

	@Override
	public SearchResultDto loadProductsByCategory(String refCategory) {

		FinalCategory finalCategory = abstractCategoryDao.loadFinalCategoryByref(refCategory);
		List<Product> listeProduct = finalCategory.getListeProduct();
		List<ProductCardDto> productsCards = iProductMapper.entitiesToCardDtos(listeProduct);
		ProductFiltersDto allFilters = caracteristicService.getFilters(listeProduct);
		
		SearchResultDto searchResult = iProductFactory.makeSearchResultDto(allFilters, productsCards);
		return searchResult;
	}

	@Override
	public SearchResultDto searchProduct(String keyword) {
		// load products from cache
		List<Product> listeProducts = (List<Product>) cache.retrievFromCache(CacheConfig.ALL_PRODUCTS_KEY);
		if(listeProducts == null || listeProducts.isEmpty()) {
			// load products from datastore
			listeProducts = productDao.loadAllProducts();
			// load products to cache
			cache.loadListToCache(CacheConfig.ALL_PRODUCTS_KEY, listeProducts);
		}

		keyword = keyword.trim().replaceAll("\\s", " ");
		String[] subkeywords = keyword.split(" ");
		List<String> subKeyWordsArray = Arrays.asList(subkeywords);
		List<String> processedSubKeys = new ArrayList<>(subKeyWordsArray);
		for(int i = 1; i < subKeyWordsArray.size() && subKeyWordsArray.size() > 1; i++) {
			processedSubKeys.add(subKeyWordsArray.get(i-1) + " " + subKeyWordsArray.get(i));
		}

		List<Product> result = listeProducts.stream().filter(product -> DoesProductContainsKeyword(product, processedSubKeys)).sorted( (product1, product2) -> {
			return product2.getSearchScore() - product1.getSearchScore();
		}).collect(Collectors.toList());

		List<ProductCardDto> productsCards = iProductMapper.entitiesToCardDtos(result);
		
		ProductFiltersDto allFilters = caracteristicService.getFilters(result);
		
		SearchResultDto searchResult = iProductFactory.makeSearchResultDto(allFilters, productsCards);

		return searchResult;
	}

	@Override
	public SearchResultDto searchProductAfterFilter(String keyword, List<Product> listeProduct) {

		keyword = keyword.trim().replaceAll(" +", " ");
		String[] subkeywords = keyword.split(" ");
		List<String> subKeyWordsArray = Arrays.asList(subkeywords);

		List<Product> result = listeProduct.stream().filter(product -> DoesProductContainsKeyword(product, subKeyWordsArray)).sorted( (product1, product2) -> {
			return product1.getSearchScore() - product2.getSearchScore();
		}).collect(Collectors.toList());

		List<ProductCardDto> productsCards = iProductMapper.entitiesToCardDtos(result);
		
		ProductFiltersDto allFilters = caracteristicService.getFilters(result);
		
		SearchResultDto searchResult = iProductFactory.makeSearchResultDto(allFilters, productsCards);

		return searchResult;
	}
	
	@Override
	public SearchResultDto searchProductAfterFilterCat(List<Product> listeProduct) {


		List<ProductCardDto> productsCards = iProductMapper.entitiesToCardDtos(listeProduct);
		
		ProductFiltersDto allFilters = caracteristicService.getFilters(listeProduct);
		
		SearchResultDto searchResult = iProductFactory.makeSearchResultDto(allFilters, productsCards);

		return searchResult;
	}
	
	
	@Override
	public boolean DoesProductContainsKeyword(Product product, List<String> subkeys) {
		boolean[] result = new boolean[1];
		result[0] = false;
		if(product.getModel().compareTo("AB 152") == 0) {
			System.out.println("");
		}
		for(String subKey : subkeys) {
			if (product.getSearchField() != null && product.getSearchField().toUpperCase().contains(subKey.toUpperCase())) {
				result[0] = true;
				product.setSearchScore(product.getSearchScore() + 1);
			}
		}
		return result[0];
	}

	@Override
	public Integer publishProductToCustommer(ProductSearchDto productSearchDto) {
		Integer result = -1;
		String brandLabel = productSearchDto.getBrand();
		String model = productSearchDto.getModel();
		Brand brand = brandDao.loadBrandByLabel(brandLabel);
		String commercialEmail = productSearchDto.getCommercialEmail();
        
		CommercialCatalogEntities commercialCatalogEntities = this.loadCommercialCatalogEntities(brand, model, commercialEmail);
		if(commercialCatalogEntities.getCommercialCatalog() == null) {
			CommercialCatalog commercialCatalog = new CommercialCatalog();
			
			Commercial commercial = commercialCatalogEntities.getCommercial();
			Product product = commercialCatalogEntities.getProduct();
			
			commercialCatalog.setCommercial(commercial);
			commercialCatalog.setProduct(product);
			commercialCatalog.setDatePub(new Date());
			
			commercialCatalogDao.save(commercialCatalog);
			
			commercial.addCommercialCatalog(commercialCatalog);
			product.addCommercialCatalog(commercialCatalog);
			commercialDao.saveCommercial(commercial);
			productDao.save(product);
			result = 1;
		}
		
		return result;
	}

}
