package com.ant.linker.module.shared.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Characteristic;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.File;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.QuoteRequestInfo;
import com.ant.linker.data.entity.values.type.AbstractValue;
import com.ant.linker.module.file.service.IFileService;
import com.ant.linker.module.shared.dto.product.ProductCardDto;
import com.ant.linker.module.shared.dto.product.ProductListElement;
import com.google.common.collect.Lists;


@Mapper(componentModel = "spring")
@Component
public abstract class IProductMapper {
	
	@Autowired
	private IFileService fileService;

	public abstract List<ProductListElement> entitiesToDtos(List<Product> products);

	@Mappings({ @Mapping(source = "model", target = "model"), 
		@Mapping(source = "label", target = "label"),
			@Mapping(source = "description", target = "description"), 
			@Mapping(source = "datePub", target = "datePub"),
			@Mapping(source = "brand.label", target = "mark"),
			@Mapping(source = "brand.manufacturer.nomination", target = "manufacturer"),
			@Mapping(source = "firstImage", target = "image")
			
	})

	public abstract ProductListElement entityToDto(Product product);
	
	public String fileToUrl(File file){
		return fileService.getFileUrl(file);
	}
	
	public abstract List<ProductCardDto> entitiesToCardDtos(List<Product> products);

	@Mappings({ @Mapping(source= "idProduct", target="id"),
		@Mapping(source = "model", target = "model"), 
		@Mapping(source = "label", target = "label"),
			@Mapping(source = "brand.label", target = "brand"),
			@Mapping(source = "firstImage", target = "thumbnail")
	})
	public abstract ProductCardDto entityToCardDto(Product product);
	
	@Mappings({})
	public abstract Product mapEntityToEntity(Product product);
	
	public Product mergeEntityToEntity(Product refProduct, Product productToMerge) {
		
		Set<Characteristic<AbstractValue>> refCaracteristics = new HashSet<Characteristic<AbstractValue>>(refProduct.getListeCharacteristic());
		Set<Characteristic<AbstractValue>> caractsToMerge = new HashSet<Characteristic<AbstractValue>>(productToMerge.getListeCharacteristic());
		refCaracteristics.addAll(caractsToMerge);
		refProduct.setListeCharacteristic(Lists.newArrayList(refCaracteristics));
		
		Set<File> refImages = new HashSet<File>(refProduct.getListeImages());
		Set<File> imagesToMerge = new HashSet<File>(productToMerge.getListeImages());
		refImages.addAll(imagesToMerge);
		refProduct.setListImages(Lists.newArrayList(refImages));
		
		Set<CommercialCatalog> refCatalog = new HashSet<CommercialCatalog>(refProduct.getlistCommercialCatalog());
		Set<CommercialCatalog> catalogToMerge = new HashSet<CommercialCatalog>(productToMerge.getlistCommercialCatalog());
		refCatalog.addAll(catalogToMerge);
		refProduct.setListCommercialCatalog(Lists.newArrayList(refCatalog));
		
		Set<QuoteRequestInfo> refQuotes = new HashSet<QuoteRequestInfo>(refProduct.getQuoteRequestInfo());
		Set<QuoteRequestInfo> quotesToMerge = new HashSet<QuoteRequestInfo>(productToMerge.getQuoteRequestInfo());
		refQuotes.addAll(quotesToMerge);
		refProduct.setListQuoteRequestInfo(Lists.newArrayList(refQuotes));
		
		refProduct.setLabel(productToMerge.getLabel());
		refProduct.setDescription(productToMerge.getDescription());
		refProduct.makeSearchField();
		
		return refProduct;
	}
}