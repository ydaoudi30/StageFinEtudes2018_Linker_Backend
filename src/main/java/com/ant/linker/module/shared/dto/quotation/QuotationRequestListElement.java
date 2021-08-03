package com.ant.linker.module.shared.dto.quotation;

import java.util.Date;

public class QuotationRequestListElement {

	private String	reference;
	private String	customer;
	private Integer	nbArticle;
	private Long	qteTotal;
	private Date	dateRequest;
	private Boolean	discount;
	private Float	TTTC;
	private Integer delay;
	private String type;
	private String status;
	
	
	public QuotationRequestListElement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuotationRequestListElement(String reference, String customer, Integer nbArticle, Long qteTotal, Date dateRequest,
			Boolean discount, Float TTTC, Integer delay, String type, String status) {
		super();
		this.reference = reference;
		this.customer = customer;
		this.nbArticle = nbArticle;
		this.qteTotal = qteTotal;
		this.dateRequest = dateRequest;
		this.discount = discount;
		this.TTTC = TTTC;
		this.delay = delay;
		this.type = type;
		this.status = status;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Integer getNbArticle() {
		return this.nbArticle;
	}

	public void setNbArticle(Integer nb) {
		this.nbArticle = nb;
	}

	public Long getQteTotal() {
		return this.qteTotal;
	}

	public void setQteTotal(Long qte) {
		this.qteTotal = qte;
	}

	public Date getDateRequest() {
		return this.dateRequest;
	}

	public void setDateRequest(Date date) {
		this.dateRequest = date;
	}

	public Boolean getDiscount() {
		return this.discount;
	}

	public void setDiscount(Boolean disc) {
		this.discount = disc;
	}

	public Float getTTTC() {
		return this.TTTC;
	}

	public void setTTTC(Float tttc) {
		this.TTTC = tttc;
	}
	public Integer getDelay() {
		return this.delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
