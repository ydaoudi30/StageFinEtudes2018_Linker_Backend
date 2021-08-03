package com.ant.linker.data.entity;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index
public class ModificationRequest implements Serializable{

	@Id
	private int idProduit;

	@Load
	private Ref<Commercial> commercial;

	@Load
	private Ref<Product> newProductInfo;

	@Load
	private Ref<Product> oldProductInfo;

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public Commercial getCommercial() {
		return commercial.get();
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = Ref.create(commercial);
	}

	public Product getNewProductInfo() {
		return newProductInfo.get();

	}

	public void setNewProductInfo(Product newProductInfo) {
		this.newProductInfo = Ref.create(newProductInfo);
	}

	public Product getOldProductInfo() {
		return oldProductInfo.get();

	}

	public void setOldProductInfo(Product oldProductInfo) {
		this.oldProductInfo = Ref.create(oldProductInfo);
	}

	public ModificationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ModificationRequest [idProduit=" + idProduit + ", commercial=" + commercial + ", newProductInfo="
				+ newProductInfo + ", oldProductInfo=" + oldProductInfo + "]";
	}

}
