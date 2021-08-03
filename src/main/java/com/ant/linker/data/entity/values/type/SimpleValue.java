package com.ant.linker.data.entity.values.type;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class SimpleValue extends AbstractValue implements Serializable {


	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SimpleValue(String value) {
		super();
		this.value = value;
	}

	public SimpleValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
