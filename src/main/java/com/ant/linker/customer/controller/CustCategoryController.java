package com.ant.linker.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.module.category.service.ICategoryService;
import com.ant.linker.module.shared.dto.product.CategoryDto;

@RestController
@RequestMapping("/cust/category")
public class CustCategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/list")
	public List<CategoryDto> findCategory() {
		return categoryService.loadListeCategory();
	}
}