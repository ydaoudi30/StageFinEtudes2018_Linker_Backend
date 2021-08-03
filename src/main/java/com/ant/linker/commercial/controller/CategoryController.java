package com.ant.linker.commercial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.module.category.service.ICategoryService;
import com.ant.linker.module.shared.dto.product.CategoryDto;

@CrossOrigin()
@RestController
@RequestMapping("/cmr/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/load/ref/ascendents")
	public CategoryDto findCategory(@RequestParam() String refCategory) {
		return categoryService.loadAscendentOfCategory(refCategory);
	}
	
}
