package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

@Entity
@Index

public class Quotation implements Serializable{

	@Id
	private Long idQuotation;
	@Load
	private Ref<QuotationRequest> quotationRequest;
	@Load
	private List<Ref<QuoteResponseInfo>> listQuoteResponseInfo;
	@Load
	private Boolean discounted;
	@Load
	private int deliveryDate;
	@Load
	private String deliveryPeriod;
	
	private DelayUnit delay;

	public Quotation() {
		super();
		this.listQuoteResponseInfo = new ArrayList<>();
	}

	public String getPeriod() {
		return Integer.toString(this.getDeliveryDate()) + " " + this.getDeliveryPeriod();
	}

	public Long getIdQuotation() {
		return this.idQuotation;

	}

	public String getDeliveryPeriod() {
		return this.deliveryPeriod;
	}

	public int getDeliveryDate() {
		return this.deliveryDate;
	}

	public String getDelay() {
		return this.delay.name();
	}

	public QuotationRequest getQuotationRequest() {
		return quotationRequest.get();
	}

	public List<QuoteResponseInfo> getListQuoteResponseInfo() {
		List<QuoteResponseInfo> responses = new ArrayList<>();
		if (this.listQuoteResponseInfo == null) {
			return responses;
		} else {
			for (Ref<QuoteResponseInfo> refresp : listQuoteResponseInfo) {
				QuoteResponseInfo quote = refresp.get();
				responses.add(quote);
			}
			return responses;
		}
	}

	public Boolean getDiscounted() {
		return this.discounted;
	}

	public void setidQuotation(Long idQuotation) {
		this.idQuotation = idQuotation;
	}

	public void setQuotationRequest(QuotationRequest quote) {
		this.quotationRequest = Ref.create(quote);
	}

	public void setlistQuoteResponseInfo(List<QuoteResponseInfo> liste) {
		for (QuoteResponseInfo QuoteResponseInfo : liste) {
			listQuoteResponseInfo.add(Ref.create(QuoteResponseInfo));
		}
	}

	public void addQuoteResponceInfo(QuoteResponseInfo quote) {
		listQuoteResponseInfo.add(Ref.create(quote));
	}

	public void removeQuoteResponseInfo(QuoteResponseInfo quote) {
		listQuoteResponseInfo.remove(Ref.create(quote));
	}

	public void setDiscounted() {

		int a = 0;
		for (QuoteResponseInfo item : getListQuoteResponseInfo()) {
			if(item.getDiscount() == null){
				a=0;
			}
			else{
				a = a + item.getDiscount();
			}
			
		}

		if (a == 0) {
			this.discounted = false;
		} else {
			this.discounted = true;
		}
	}

	public void setDelay(DelayUnit delay) {
		this.delay = delay;
		this.setDeliveryPeriod();
	}

	public void setDeliveryPeriod() {
		this.deliveryPeriod = this.delay.name();
	}

	public void setDeliveryDate(int number) {
		this.deliveryDate = number;
	}

}