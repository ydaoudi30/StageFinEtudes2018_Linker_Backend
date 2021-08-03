package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.Unit;

@Component
public class UnitDao implements IUnitDao {

	@Override
	public Unit loadUnitByLabel(String labelUnit) {
		return ofy().load().type(Unit.class).filter("labelUnit =", labelUnit).first().now();
		
	}

	@Override
	public Unit save(Unit unit) {
		ofy().save().entity(unit).now();
		return unit;
	}

	@Override
	public List<Unit> loadListUnit() {
		List<Unit> listUnit =	 ofy().load().type(Unit.class).list();
		return listUnit;
		
	}
	
	
}
