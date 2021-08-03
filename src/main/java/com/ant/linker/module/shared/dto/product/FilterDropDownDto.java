package com.ant.linker.module.shared.dto.product;

import java.util.List;

public class FilterDropDownDto {
	
	private String label;
	private List<String> textOnlyList;
	
	
	
	public FilterDropDownDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterDropDownDto(String label, List<String> textOnlyList) {
		super();
		this.label = label;
		this.textOnlyList = textOnlyList;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<String> getTextOnlyList() {
		return textOnlyList;
	}
	public void setTextOnlyList(List<String> textOnlyList) {
		this.textOnlyList = textOnlyList;
	}

	public void addValue(String valueOfList) {
		this.textOnlyList.add(valueOfList);
		
	}
	
}
