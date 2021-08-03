package com.ant.linker.module.shared.dto.product;

public class UnitDto {
	private Boolean unitExist;
	private String labelUnit;
	private String descriptionUnit;
	public UnitDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UnitDto(Boolean unitExist, String labelUnit, String descriptionUnit) {
		super();
		this.unitExist = unitExist;
		this.labelUnit = labelUnit;
		this.descriptionUnit = descriptionUnit;
	}
	public Boolean getUnitExist() {
		return unitExist;
	}
	public void setUnitExist(Boolean unitExist) {
		this.unitExist = unitExist;
	}
	public String getLabelUnit() {
		return labelUnit;
	}
	public void setLabelUnit(String labelUnit) {
		this.labelUnit = labelUnit;
	}
	public String getDescriptionUnit() {
		return descriptionUnit;
	}
	public void setDescriptionUnit(String descriptionUnit) {
		this.descriptionUnit = descriptionUnit;
	}
}

