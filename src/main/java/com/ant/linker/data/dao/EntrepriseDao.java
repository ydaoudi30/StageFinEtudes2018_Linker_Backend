package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Entreprise;
@Component
public class EntrepriseDao implements IEntreprise {

	@Override
	public Entreprise save(Entreprise manufacturer) {
		ofy().save().entity(manufacturer).now();
		return manufacturer;
	}

}
