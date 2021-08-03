package com.ant.linker.data.entity.values.type;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class MinMaxValue extends AbstractValue implements Serializable {

	private String minValue;
	
	private String maxValue;

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public MinMaxValue(String minValue, String maxValue) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public MinMaxValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
