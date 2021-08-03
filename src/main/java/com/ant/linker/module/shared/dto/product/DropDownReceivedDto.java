package com.ant.linker.module.shared.dto.product;

public class DropDownReceivedDto {

public String keyword;
public String label;
public String value;
public DropDownReceivedDto(String keyword, String label, String value) {
	super();
	this.keyword = keyword;
	this.label = label;
	this.value = value;
}
public DropDownReceivedDto() {
	super();
	// TODO Auto-generated constructor stub
}
public String getKeyword() {
	return keyword;
}
public void setKeyword(String keyword) {
	this.keyword = keyword;
}
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}


	
}
