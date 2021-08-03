package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index

public class CommercialCatalog implements Serializable{
	
	@Id
	private Long id;
	
	private boolean inStock;
	@Load
	public Ref<Product> product;
	
	private Ref<Commercial> commercial;
	@Load
	private Date datePub;

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public Product getProduct() {
		return product.get();

	}

	public void setProduct(Product product) {
		this.product = Ref.create(product);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Commercial getCommercial() {
		return commercial.get();
	}

	public void setCommercial(Commercial commercial) {

		this.commercial = Ref.create(commercial);
	}

	public CommercialCatalog(boolean inStock) {

		this.inStock = inStock;
	}
	
	public Date getDatePub() {
		return datePub;
	}

	public void setDatePub(Date datePub) {
		this.datePub = datePub;
	}

	public CommercialCatalog() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CommercialCatalog [inStock=" + inStock + ", product=" + product + ", commercial=" + commercial + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commercial == null) ? 0 : commercial.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommercialCatalog other = (CommercialCatalog) obj;
		if (commercial == null) {
			if (other.commercial != null)
				return false;
		} else if (!commercial.equals(other.commercial))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
}
