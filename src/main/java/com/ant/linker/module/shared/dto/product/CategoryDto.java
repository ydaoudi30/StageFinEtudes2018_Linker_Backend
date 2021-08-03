package com.ant.linker.module.shared.dto.product;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
	private String label;
	private boolean finalCategory;
	private List<CategoryDto> childs;
	private String refCategory ;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<CategoryDto> getChilds() {
		return childs;
	}

	public void setChilds(List<CategoryDto> childs) {
		this.childs = childs;
	}

	public CategoryDto(String label, boolean isFinal, List<CategoryDto> childs) {
		super();
		this.label = label;
		this.finalCategory = isFinal;
		this.childs = childs;
	}

	public CategoryDto() {
		super();
		this.childs = new ArrayList<>();
	}

	public boolean getFinalCategory() {
		return finalCategory;
	}

	public void setFinalCategory(boolean finalCategory) {
		this.finalCategory = finalCategory;
	}

	public String getRefCategory() {
		return refCategory;
	}

	public void setRefCategory(String refCategory) {
		this.refCategory = refCategory;
	}

	public void addCategoryDto(CategoryDto child){
		this.childs.add(child);
	}

	@Override
	public String toString() {
		return "CategoryDto [finalCategory=" + finalCategory + ", childs=" + childs + ", refCategory=" + refCategory
				+ "]";
	}
	
}
