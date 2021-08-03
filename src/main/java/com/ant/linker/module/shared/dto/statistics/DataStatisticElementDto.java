package com.ant.linker.module.shared.dto.statistics;

public class DataStatisticElementDto {

	private String dataLabel;
	private Integer dataValue;
	
	public DataStatisticElementDto(String dataLabel, Integer dataValue) {
		super();
		this.dataLabel = dataLabel;
		this.dataValue = dataValue;
	}
	public String getDataLabel() {
		return dataLabel;
	}
	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}
	public Integer getDataValue() {
		return dataValue;
	}
	public void setDataValue(Integer dataValue) {
		this.dataValue = dataValue;
	}
	
	
}
