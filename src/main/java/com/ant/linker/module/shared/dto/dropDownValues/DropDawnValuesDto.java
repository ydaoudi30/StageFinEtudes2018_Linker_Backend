package com.ant.linker.module.shared.dto.dropDownValues;

public class DropDawnValuesDto {
	
	private String label;
	private String value;

	public DropDawnValuesDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DropDawnValuesDto(String label ,String value) {
		super();
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	

}
