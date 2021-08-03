package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.Unit;

public interface IUnitDao {
	
	public Unit loadUnitByLabel(String labelUnit);

	public Unit save(Unit unit);

	List<Unit> loadListUnit();

}
