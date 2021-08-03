package com.ant.linker.data.entity.event.type;

import java.io.Serializable;

import com.ant.linker.data.entity.QuotationRequest;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
public class QuoteEventContent extends AbstractEventContent implements Serializable{

	@Load
	private Ref<QuotationRequest> quotation;
	

	public QuoteEventContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuoteEventContent(QuotationRequest quotation) {
		super();
		this.quotation = Ref.create(quotation);
	}

	public QuotationRequest getQuotation() {
		if(quotation == null) return null;
		return quotation.get();
	}

	public void setQuotation(QuotationRequest quotation) {
		this.quotation = Ref.create(quotation);
	}
	
}
