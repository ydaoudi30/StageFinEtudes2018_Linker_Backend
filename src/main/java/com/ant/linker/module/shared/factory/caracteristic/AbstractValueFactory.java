package com.ant.linker.module.shared.factory.caracteristic;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.ant.linker.data.entity.values.type.AbstractValue;
import com.ant.linker.data.entity.values.type.CaractValueType;
import com.ant.linker.data.entity.values.type.ListValue;
import com.ant.linker.data.entity.values.type.MinMaxValue;
import com.ant.linker.data.entity.values.type.SimpleValue;
import com.ant.linker.module.shared.factory.IAbstractValueFactory;

@Service
public class AbstractValueFactory implements IAbstractValueFactory {

	@Override
	public AbstractValue makeValueFromJson(JSONObject value, String valueType) {

		CaractValueType caractValType = CaractValueType.valueOf(valueType);

		switch (caractValType) {
			case MIN_MAX_VALUE: {
				String min = value.getString("min");
				String max = value.getString("max");
				return new MinMaxValue(min, max);
			}
			case LIST_VALUE: {
				ListValue listValue = new ListValue();
				JSONArray list = value.getJSONArray("list");
				for (int i = 0; i < list.length(); i++) {
					JSONObject row = list.getJSONObject(i);
					listValue.addValue(row.getString("value"));
				}
				return listValue;
			}
			case SIMPLE_VALUE: {
				String simpleValue = value.getString("value");
				return new SimpleValue(simpleValue);
			}
			default: {
				
			}
		}
		return null;
	}

	@Override
	public JSONObject makeJsonFromValue(AbstractValue value, CaractValueType caractValType) {

		JSONObject jsonValue = new JSONObject();

		switch (caractValType) {
			case MIN_MAX_VALUE: {
				String minValue = ((MinMaxValue) value).getMinValue();
				String maxValue = ((MinMaxValue) value).getMaxValue();
				jsonValue.put("min", minValue);
				jsonValue.put("max", maxValue);
				return jsonValue;
			}
			case LIST_VALUE: {
				JSONArray jsonListValue = new JSONArray();
				List<String> listValue = ((ListValue) value).getValues();
				for(String valueOfList : listValue){
					JSONObject valueJson = new JSONObject();
					valueJson.put("value", valueOfList);

					jsonListValue.put(valueJson);
				}
				
				jsonValue.put("list", jsonListValue);
				return jsonValue;
			}
			case SIMPLE_VALUE: {
				String simpleValue = ((SimpleValue) value).getValue();
				jsonValue.put("value", simpleValue);
				return jsonValue;
			}
			default: {
				
			}
		}
		return null;
	}
}
