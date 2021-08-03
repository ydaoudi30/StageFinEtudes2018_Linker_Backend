package com.ant.linker.module.category.service;

import java.util.List;

import com.ant.linker.data.entity.AbstractCategory;
import com.ant.linker.module.shared.dto.product.CategoryDto;

public interface ICategoryService {
	public List<CategoryDto>  loadListeCategory ();
	public AbstractCategory saveCategory(AbstractCategory category);
	public CategoryDto loadAscendentOfCategory(String refCategory);
}
