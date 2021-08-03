package com.ant.linker.data.entity;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Unit implements Serializable {
	@Id
	private Long idUnit;
	private String descriptionUnit;
	private String labelUnit;
	private Boolean isValide;

	public Long getIdUnit() {
		return idUnit;
	}

	public void setIdUnit(Long idUnit) {
		this.idUnit = idUnit;
	}

	public String getDescriptionUnit() {
		return descriptionUnit;
	}

	@Override
	public String toString() {
		return "Unit [idUnit=" + idUnit + ", description=" + descriptionUnit + ", label=" + labelUnit + ", isValide=" + isValide
				+ "]";
	}

	public Unit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Unit(String descriptionUnit, String labelUnit, Boolean isValide) {
		super();
		this.descriptionUnit = descriptionUnit;
		this.labelUnit = labelUnit;
		this.isValide = isValide;
	}

	public void setDescriptionUnit(String descriptionUnit) {
		this.descriptionUnit = descriptionUnit;
	}

	public String getLabelUnit() {
		return labelUnit;
	}

	public void setLabelUnit(String labelUnit) {
		this.labelUnit = labelUnit;
	}

	public Boolean getIsValide() {
		return isValide;
	}

	public void setIsValide(Boolean isValide) {
		this.isValide = isValide;
	}

}
