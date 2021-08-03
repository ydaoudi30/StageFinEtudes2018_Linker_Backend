package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.ant.linker.data.entity.values.type.AbstractValue;
import com.ant.linker.data.entity.values.type.CaractValueType;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index
public class Characteristic <T extends AbstractValue> implements Serializable {

	@Id
	private Long id;
	private String label;
	@Load
	private Ref<T> value;
	
	private List<Ref<Product>> listeProduct;
	@Load
	private Ref<Unit> unit;
	
	private CaractValueType valueType;
	
	// know if caracteristic is without unit
	private boolean textFormatOnly;
	

	public Long getIdCharacteristic() {
		return id;
	}

	public void setValue(T value) {
		this.value = Ref.create(value);
	}
	
	public T getValue() {
		return this.value.get();
	}
	
	public void addProduct(Product product) {

		listeProduct.add(Ref.create(product));

	}
		
	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public void removeProduct(Product Product){
		
		listeProduct.remove(Ref.create(Product));
		
	}
	
	public void setIdCharacteristic(Long idCharacteristic) {
		this.id = idCharacteristic;
	}

	public List<Product> getListeProduct() {
		List<Product> products = new ArrayList<>();

		for (Ref<Product> refProduct : listeProduct) {

			Product product = refProduct.get();

			products.add(product);

		}

		return products;
	}

	public void setListeProduct(List<Product> liste) {
		for (Product product : liste) {

			listeProduct.add(Ref.create(product));

		}
	}

	public Unit getUnit() {
		if(unit == null) return null;
		return unit.get();
	}

	public void setUnit(Unit unit) {

		this.unit = Ref.create(unit);

	}

	public CaractValueType getValueType() {
		return valueType;
	}

	public void setValueType(CaractValueType valueType) {
		this.valueType = valueType;
	}
	
	public boolean isTextFormatOnly() {
		return textFormatOnly;
	}

	public void setTextFormatOnly(boolean textFormatOnly) {
		this.textFormatOnly = textFormatOnly;
	}

	public Characteristic(String value, String label) {

		this.label = label;
		this.listeProduct = new ArrayList<>();
	}

	public Characteristic() {
		super();
		// TODO Auto-generated constructor stub
		this.listeProduct = new ArrayList<>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + (textFormatOnly ? 1231 : 1237);
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((valueType == null) ? 0 : valueType.hashCode());
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
		Characteristic other = (Characteristic) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (textFormatOnly != other.textFormatOnly)
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (valueType != other.valueType)
			return false;
		return true;
	}
	

}
