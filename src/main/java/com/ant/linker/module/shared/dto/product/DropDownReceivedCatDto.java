package com.ant.linker.module.shared.dto.product;

public class DropDownReceivedCatDto {
	
	public String refCat;
	public String label;
	public String value;
	public DropDownReceivedCatDto(String refCat, String label, String value) {
		super();
		this.refCat = refCat;
		this.label = label;
		this.value = value;
	}
	public DropDownReceivedCatDto() {
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
