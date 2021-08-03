package com.ant.linker.module.shared.factory;

import org.json.JSONObject;

import com.ant.linker.data.entity.values.type.AbstractValue;
import com.ant.linker.data.entity.values.type.CaractValueType;

public interface IAbstractValueFactory {

	public AbstractValue makeValueFromJson(JSONObject value, String valueType);
	
	public JSONObject makeJsonFromValue(AbstractValue value, CaractValueType caractValType);
}
