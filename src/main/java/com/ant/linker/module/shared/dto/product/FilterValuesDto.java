package com.ant.linker.module.shared.dto.product;

import org.json.JSONObject;

import com.ant.linker.data.entity.Unit;
import com.googlecode.objectify.Ref;

public class FilterValuesDto {
	
	
	private JSONObject value;
	private String unit;
	private String valueType;
	private boolean textFormatOnly;
	
	public FilterValuesDto(JSONObject value, String unit, String valueType, boolean textFormatOnly) {
		super();
		this.value = value;
		this.unit = unit;
		this.valueType = valueType;
		this.textFormatOnly = textFormatOnly;
	}

	public JSONObject getValue() {
		return value;
	}

	public void setValue(JSONObject value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public boolean isTextFormatOnly() {
		return textFormatOnly;
	}

	public void setTextFormatOnly(boolean textFormatOnly) {
		this.textFormatOnly = textFormatOnly;
	}
	
		
	

}
