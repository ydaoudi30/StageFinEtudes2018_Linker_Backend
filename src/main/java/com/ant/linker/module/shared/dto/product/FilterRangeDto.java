package com.ant.linker.module.shared.dto.product;

import java.util.List;

import com.ant.linker.data.entity.Unit;

public class FilterRangeDto {
	
	private String label;
	private List<MinMaxDto> value;
	
	public FilterRangeDto(String label, List<MinMaxDto> value) {
		super();
		this.label = label;
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<MinMaxDto> getValue() {
		return value;
	}
	public void setValue(List<MinMaxDto> value) {
		this.value = value;
	}
	
	
	
	

}
