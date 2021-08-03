package com.ant.linker.module.shared.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.AbstractCategory;
import com.ant.linker.data.entity.FinalCategory;
import com.ant.linker.data.entity.NodeCategory;
import com.ant.linker.module.category.service.ICategoryService;
import com.ant.linker.module.shared.dto.product.CategoryDto;

@Mapper(componentModel = "spring")

@Component
public abstract class CategoryMapper {
	
	@Autowired
	private ICategoryService categoryService;

	// Dao To Dto
	public List<CategoryDto> entitiesToDtos(List<AbstractCategory> abstractCategories) {
		if (abstractCategories == null) {
			return null;
		}

		List<CategoryDto> list = new ArrayList<CategoryDto>(abstractCategories.size());
		for (AbstractCategory abstractCategory : abstractCategories) {
			if (abstractCategory.isFinal()) {
				list.add(entityToDto((FinalCategory) abstractCategory));
			} else {
				list.add(entityToDto((NodeCategory) abstractCategory));
			}
		}

		return list;
	}

	@Mappings({ 
		@Mapping(source = "label", target = "label"), 
		@Mapping(source = "final", target = "finalCategory"),
		@Mapping(source = "ref", target = "refCategory") 
	})
	public abstract CategoryDto entityToDto(FinalCategory finalCategory);
	
	@Mappings({ 
			@Mapping(source = "label", target = "label"), 
			@Mapping(source = "final", target = "finalCategory"),
			@Mapping(source = "listeAbstractCategory", target = "childs"),
			@Mapping(source = "ref", target = "refCategory")
	})
	public abstract CategoryDto entityToDto(NodeCategory nodeCategory);

	
	// Dto to Dao
	public List<AbstractCategory> dtosToEntities(List<CategoryDto> categories) {
		if (categories == null) {
			return null;
		}

		List<AbstractCategory> list = new ArrayList<AbstractCategory>(categories.size());
		for (CategoryDto categoryDto : categories) {
			if (categoryDto.getFinalCategory()) {
				FinalCategory dtoToFinalEntity = dtoToFinalEntity(categoryDto);
				AbstractCategory savedFinalCategory = categoryService.saveCategory(dtoToFinalEntity);
				list.add(savedFinalCategory);
			} else {
				NodeCategory dtoToNodeEntity = dtoToNodeEntity(categoryDto);
				AbstractCategory savedNodeCategory = categoryService.saveCategory(dtoToNodeEntity);
				list.add(savedNodeCategory);
			}
		}

		return list;
	}

	@Mappings({
		@Mapping(source = "label", target = "label"),
		@Mapping(source = "refCategory", target = "ref")
	})
	public abstract FinalCategory dtoToFinalEntity(CategoryDto category);
	
	@Mappings({
		@Mapping(source = "label", target = "label"),
		@Mapping(source = "refCategory", target = "ref"),
		@Mapping(source = "childs", target = "listeAbstractCategory")
	})
	public abstract NodeCategory dtoToNodeEntity(CategoryDto category);

}
