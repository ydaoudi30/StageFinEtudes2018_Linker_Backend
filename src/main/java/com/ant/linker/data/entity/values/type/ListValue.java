package com.ant.linker.data.entity.values.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class ListValue extends AbstractValue implements Serializable {

	private List<String> values;

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
	public void addValue(String value) {
		this.values.add(value);
	}

	public ListValue(List<String> values) {
		super();
		this.values = values;
	}

	public ListValue() {
		super();
		this.values = new ArrayList<>();
	}
	
	
}
