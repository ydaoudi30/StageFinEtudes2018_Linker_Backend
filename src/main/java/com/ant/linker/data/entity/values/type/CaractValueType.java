package com.ant.linker.data.entity.values.type;

import java.io.Serializable;

public enum CaractValueType implements Serializable{

	SIMPLE_VALUE("SIMPLE_VALUE"),
	MIN_MAX_VALUE("MIN_MAX_VALUE"),
	LIST_VALUE("LIST_VALUE");
	
	private String valueType;

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	private CaractValueType(String valueType) {
		this.valueType = valueType;
	}
	
}
