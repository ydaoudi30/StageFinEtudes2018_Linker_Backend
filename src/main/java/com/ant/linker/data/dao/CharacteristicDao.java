package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Characteristic;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.values.type.AbstractValue;
@Component
public class CharacteristicDao implements ICharacteristicDao {

	@Override
	public Characteristic<?> loadCharacteristicByLabel(String labelCarac) {
		
		return ofy().load().type(Characteristic.class).filter("labelCarac =", labelCarac).first().now();
		
	}

	@Override
	public Characteristic<?> save(Characteristic<?> characteristic) {
		// TODO Auto-generated method stub
		ofy().save().entity(characteristic).now();
		return characteristic;
	}

	@Override
	public AbstractValue saveAbstractValue(AbstractValue value) {
		ofy().save().entity(value).now();
		return value;
	}
}