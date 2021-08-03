package com.ant.linker.module.shared.dto.product;

public class RangeReceivedDto {
public String keyword;
public String label;
public String min;
public String max;
public String unit;
public RangeReceivedDto() {
	super();
	// TODO Auto-generated constructor stub
}
public RangeReceivedDto(String keyword, String label, String min, String max, String unit) {
	super();
	this.keyword = keyword;
	this.label = label;
	this.min = min;
	this.max = max;
	this.unit = unit;
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
public String getMin() {
	return min;
}
public void setMin(String min) {
	this.min = min;
}
public String getMax() {
	return max;
}
public void setMax(String max) {
	this.max = max;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}


	
}
