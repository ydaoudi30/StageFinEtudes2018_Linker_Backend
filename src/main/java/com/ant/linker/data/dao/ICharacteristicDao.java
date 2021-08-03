package com.ant.linker.data.dao;


import com.ant.linker.data.entity.Characteristic;
import com.ant.linker.data.entity.values.type.AbstractValue;


public interface ICharacteristicDao {

	public Characteristic<?> loadCharacteristicByLabel(String labelChar);
	public Characteristic<?> save (Characteristic<?> characteristic);
	
	public AbstractValue saveAbstractValue(AbstractValue value);
	
}
