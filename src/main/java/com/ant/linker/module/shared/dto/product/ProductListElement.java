package com.ant.linker.module.shared.dto.product;

import java.util.Date;

public class ProductListElement {

	private String	image;
	private String	model;
	private String	label;
	private String	mark;
	private String	manufacturer;
	private String	description;
	private Date	datePub;

	public ProductListElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductListElement(String image, String model, String label, String mark, String manufacturer,
			String description, Date datePub) {
		super();
		this.image = image;
		this.model = model;
		this.label = label;
		this.mark = mark;
		this.manufacturer = manufacturer;
		this.description = description;
		this.datePub = datePub;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatePub() {
		return datePub;
	}

	public void setDatePub(Date datePub) {
		this.datePub = datePub;
	}
}
