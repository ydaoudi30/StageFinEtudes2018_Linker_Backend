package com.ant.linker.module.shared.dto.product;

public class RangeReceivedCatDto {
public String refCat;
public String label;
public String min;
public String max;
public String unit;
public RangeReceivedCatDto(String refCat, String label, String min, String max, String unit) {
	super();
	this.refCat = refCat;
	this.label = label;
	this.min = min;
	this.max = max;
	this.unit = unit;
}
public RangeReceivedCatDto() {
	super();
	// TODO Auto-generated constructor stub
}
public String getRefCat() {
	return refCat;
}
public void setRefCat(String refCat) {
	this.refCat = refCat;
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