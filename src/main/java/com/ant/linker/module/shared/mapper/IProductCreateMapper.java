package com.ant.linker.module.shared.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Brand;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.File;
import com.ant.linker.data.entity.FinalCategory;
import com.ant.linker.data.entity.Product;
import com.ant.linker.module.file.service.IFileService;
import com.ant.linker.module.shared.dto.commercial.CommercialInfoDto;
import com.ant.linker.module.shared.dto.product.ProductCreateDto;
import com.ant.linker.module.shared.dto.product.ProductWithCommercialsDto;

@Mapper(componentModel = "spring", uses=ICharacteristicMapper.class)
@Component
public abstract class IProductCreateMapper {
	
	@Autowired
	private IFileService fileService;

	@Mappings({ @Mapping(source = "model", target = "model"),
		@Mapping(source = "label", target = "label"),
		@Mapping(source = "description", target = "description"),
		@Mapping(source = "brand", target = "brandLabel"),
		@Mapping(source = "refCategory", target = "refCategory"),
		@Mapping(source = "images", target = "images")
	})
	public abstract ProductCreateDto toProductDto(String model, String label, String description, 
			String refCategory, String brand, List<String> images);
	
	@Mappings({ @Mapping(source = "productDto.model", target = "model"),
		@Mapping(source = "productDto.label", target = "label"),
		@Mapping(source = "productDto.description", target = "description"),
		@Mapping(source = "brand", target = "brand"),
		@Mapping(source = "category", target = "finalCategory")
	})
	public abstract Product dtoToEntities(ProductCreateDto productDto, Brand brand, FinalCategory category);
	

	@Named("ToProductCreateDto")
	@Mappings({ @Mapping(source = "model", target = "model"),
		@Mapping(source = "label", target = "label"),
		@Mapping(source = "description", target = "description"),
		@Mapping(source = "brand.manufacturer.nomination", target = "manufacturer"),
		@Mapping(source = "listeImages", target = "images"),
		@Mapping(source = "brand.label", target = "brandLabel"),
		@Mapping(source = "finalCategory.ref", target = "refCategory"),
		@Mapping(source = "listeCharacteristic", target = "characteristics")})
	public abstract ProductCreateDto entiteToDto(Product product);
	
	@Named("ToProductWithCommercialsDto")
	@Mappings({ @Mapping(source = "model", target = "model"),
		@Mapping(source = "label", target = "label"),
		@Mapping(source = "description", target = "description"),
		@Mapping(source = "brand.manufacturer.nomination", target = "manufacturer"),
		@Mapping(source = "listeImages", target = "images"),
		@Mapping(source = "brand.label", target = "brandLabel"),
		@Mapping(source = "finalCategory.ref", target = "refCategory"),
		@Mapping(source = "listeCharacteristic", target = "characteristics"),
		@Mapping(source = "listCommercialCatalog", target = "commercials")})
	public abstract ProductWithCommercialsDto entiteWithCommercialsToDto(Product product);
	
	@Mappings({ @Mapping(source = "commercial.userAccount.email", target = "email"),
		@Mapping(source = "commercial.userAccount.fullName", target = "fullName"),
		@Mapping(source = "commercial.userAccount.company", target = "company")})
	public abstract CommercialInfoDto entiteToDto(CommercialCatalog commercialCatalog);
	
	public String fileToUrl(File file){
		return fileService.getFileUrl(file);
	}
	
	public abstract List<String> fileToUrl(List<File> file);

	@Mapping(source = "brandlabel", target = "label")
	public abstract Brand labelToBrand(String brandlabel);
	

}
