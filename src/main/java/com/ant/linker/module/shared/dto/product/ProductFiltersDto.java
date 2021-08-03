package com.ant.linker.module.shared.dto.product;

import java.util.ArrayList;
import java.util.List;

public class ProductFiltersDto {
	private List<FilterRangeDto> rangevalues;
	private List<FilterDropDownDto> dropdownvalues;
	
	public ProductFiltersDto(List<FilterRangeDto> rangevalues, List<FilterDropDownDto> dropdownvalues) {
		super();
		this.rangevalues = rangevalues;
		this.dropdownvalues = dropdownvalues;
	}
	
	public List<FilterRangeDto> getRangevalues() {
		return rangevalues;
	}
	public void setRangevalues(List<FilterRangeDto> rangevalues) {
		this.rangevalues = rangevalues;
	}
	public List<FilterDropDownDto> getDropdownvalues() {
		return dropdownvalues;
	}
	public void setDropdownvalues(List<FilterDropDownDto> dropdownvalues) {
		this.dropdownvalues = dropdownvalues;
	}
	
	

}
