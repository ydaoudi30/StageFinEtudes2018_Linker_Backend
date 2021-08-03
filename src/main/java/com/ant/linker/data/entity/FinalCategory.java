package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Index
@Entity
public class FinalCategory extends AbstractCategory implements Serializable {

	private List<Ref<Product>> listeProduct;

	public void addProduct(Product product) {

		listeProduct.add(Ref.create(product));

	}

	public void removeProduct(Product Product) {

		listeProduct.remove(Ref.create(Product));

	}

	public List<Product> getListeProduct() {
		List<Product> products = new ArrayList<>();

		for (Ref<Product> refProduct : listeProduct) {

			Product product = refProduct.get();
			if(product != null) {
				products.add(product);
			}
		}
		return products;
	}

	public void setListeProduct(List<Product> liste) {
		for (Product product : liste) {

			listeProduct.add(Ref.create(product));

		}

	}

	public FinalCategory() {
		super();
		// TODO Auto-generated constructor stub
		this.listeProduct = new ArrayList<>();
	}

	public FinalCategory(List<Product> listeProduct) {
		for (Product product : listeProduct) {
			this.listeProduct.add(Ref.create(product));
		}
	}

	@Override
	public boolean isFinal() {
		return true;
	}
	
}
