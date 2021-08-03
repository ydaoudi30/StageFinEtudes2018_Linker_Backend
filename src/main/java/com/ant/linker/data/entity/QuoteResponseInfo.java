package com.ant.linker.data.entity;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

@Entity
@Index

public class QuoteResponseInfo implements Serializable {
	@Id
	private Long idQuoteResponseInfo;
	@Load
	private Float unitPrice;
	@Load
	private Integer discount;
	@Ignore
	private String reference;
	@Load
	private Ref<QuoteRequestInfo> quoteRequestInfo;

	public QuoteResponseInfo() {
		super();
	}

	public QuoteResponseInfo(Float unitPrice, Integer discount) {
		this.unitPrice = unitPrice;
		this.discount = discount;
	}

	public Long getIdQuoteResponseInfo() {
		return idQuoteResponseInfo;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public Integer getDiscount() {
		return this.discount;
	}

	public String getReference() {
		return this.reference;
	}

	public float getPrice() {
		if (this.unitPrice != null) {
			return new Float((this.getUnitPrice() * (1 - ((float) this.getDiscount() / 100))) * (float) 1.2);
		} else {
			return 0F;
		}
	}

	public float getPriceHT() {
		if (this.unitPrice != null) {
			return new Float(this.getUnitPrice() * (1 - ((float) this.getDiscount() / 100)));
		} else {
			return 0F;
		}

	}

	public QuoteRequestInfo getQuoteRequestInfo() {
		return quoteRequestInfo.get();
	}

	public Float getTotal() {
		return new Float(this.getPriceHT() * getQuoteRequestInfo().getQuantity());
	}

	public void setIdQuoteResponseInfo(Long Id) {
		idQuoteResponseInfo = Id;
	}

	public void setUnitPrice(Float price) {
		unitPrice = price;
	}

	public void setDiscount(Integer rebate) {
		discount = rebate;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setQuoteRequestInfo(QuoteRequestInfo quote) {
		quoteRequestInfo = Ref.create(quote);
	}

}