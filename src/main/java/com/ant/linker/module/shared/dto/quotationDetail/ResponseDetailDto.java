package com.ant.linker.module.shared.dto.quotationDetail;


public class ResponseDetailDto {

	private Float	unitPriceHT;
	private Integer	discount;
	private Float	unitPriceDiscounted;
	private Float 	totalUnitPriceDiscounted;
	
	
	public ResponseDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseDetailDto(Float response, Integer discount, Float price, Float totalUnitPriceDiscounted) {
		super();
		this.unitPriceHT = response;
		this.discount = discount;
		this.unitPriceDiscounted = price;
		this.totalUnitPriceDiscounted = totalUnitPriceDiscounted;
		
	}
	
	
	public Float getUnitPriceHT(){
		return this.unitPriceHT;
	}
	
	public Integer getDiscount(){
		return this.discount;
	}
	
	public Float getUnitPriceDiscounted(){
		return this.unitPriceDiscounted;
	}
	
	public Float getTotalUnitPriceDiscounted(){
		return this.totalUnitPriceDiscounted;
	}
	
	
	public void setUnitPriceHT(Float response){
		this.unitPriceHT = response;
	}
	
	public void setDiscount(Integer discount){
		this.discount = discount;
	}
	
	public void setUnitPriceDiscounted(Float price){
		this.unitPriceDiscounted = price;
	}
	
	public void setTotalUnitPriceDiscounted(Float a){
		this.totalUnitPriceDiscounted = a;
	}
}