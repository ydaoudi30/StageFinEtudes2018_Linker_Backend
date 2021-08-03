package com.ant.linker.module.shared.dto.product;

public class MinMaxDto {
	
	private String unit;
	private String min;
	private String max;
	
	public MinMaxDto(String unit, String min, String max) {
		super();
		this.unit = unit;
		this.min = min;
		this.max = max;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	
	

}
