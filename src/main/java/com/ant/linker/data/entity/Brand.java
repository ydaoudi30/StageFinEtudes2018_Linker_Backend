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

public class Brand implements Serializable {
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String label;
	@Load
	private Ref<Entreprise> manufacturer;
	@Load
	private List<Ref<Product>> listeProduct;
    
	

	

	public Entreprise getManufacturer() {
		if(manufacturer == null){
			return null;
		}
		return manufacturer.get();
	}

	public void setManufacturer(Entreprise manufacturer) {
		this.manufacturer = Ref.create(manufacturer);
	}

	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addProduct(Product product) {

		listeProduct.add(Ref.create(product));

	}
	
	
	public void removeProduct(Product Product){
		
		listeProduct.remove(Ref.create(Product));
		
	}
	
	
	
	
	
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Product> getListeProduct() {

		List<Product> products = new ArrayList<>();

		for (Ref<Product> refPro : listeProduct) {

			Product product = refPro.get();

			products.add(product);

		}
		return products;

	}

	public void setListeProduct(List<Product> liste) {
		for (Product product : liste) {

			listeProduct.add(Ref.create(product));
		}
	}

	public Brand(String label) {

		this.label = label;

		this.listeProduct = new ArrayList<>();

	}

	@Override
	public String toString() {
		return "Brand [label=" + label + ", entreprise=" + manufacturer + ", listeProduct=" + listeProduct + "]";
	}

}