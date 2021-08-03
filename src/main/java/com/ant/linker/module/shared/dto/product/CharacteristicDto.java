package com.ant.linker.module.shared.dto.product;

import java.util.Map;

import org.json.JSONObject;

public class CharacteristicDto {
	
 	private JSONObject value;
	private String unit;
	private String label;
	private String valueType;
	private boolean textFormatOnly;
	
	public Map getValue() {
		return value.toMap();
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
