package com.ant.linker.data.entity.event.type;

import java.io.Serializable;

import com.ant.linker.data.entity.QuotationRequest;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class NewQuoteEventContent extends QuoteEventContent implements Serializable{
	
	public NewQuoteEventContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewQuoteEventContent(QuotationRequest quotation) {
		super(quotation);
		// TODO Auto-generated constructor stub
	}

}
