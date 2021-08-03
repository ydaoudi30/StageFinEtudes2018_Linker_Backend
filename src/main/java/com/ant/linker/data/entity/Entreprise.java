package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index
public class Entreprise implements Serializable {
	@Id
	private Long id ;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String nomination;
	

	@Load
	private List<Ref<Brand>> listeBrand;

	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String nomination) {

		this.nomination = nomination;
		this.listeBrand = new ArrayList<>();

	}
	
	public void addBrand(Brand brand) {

		listeBrand.add(Ref.create(brand));

	}
	
	
	public void removeBrand(Brand brand){
		
		listeBrand.remove(Ref.create(brand));
		
	}
	
	
	
	
	
	

	public String getNomination() {
		return nomination;
	}

	public void setNomination(String nomination) {
		this.nomination = nomination;
	}

	public List<Brand> getListeBrand() {

		List<Brand> brands = new ArrayList<>();

		for (Ref<Brand> refBrand : listeBrand) {

			Brand brand = refBrand.get();

			brands.add(brand);

		}

		return brands;
	}

	public void setListeBrand(List<Brand> liste) {
		for (Brand brand : liste) {

			listeBrand.add(Ref.create(brand));
		}

	}

	@Override
	public String toString() {
		return "Entreprise [nomination=" + nomination + ", listeBrand=" + listeBrand + "]";
	}
}
