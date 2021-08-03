package com.ant.linker.data.entity.event.type;

import java.io.Serializable;

import com.ant.linker.data.entity.Message;
import com.ant.linker.data.entity.QuotationRequest;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index
public class NewMessageQuoteEventContent extends QuoteEventContent implements Serializable {

	@Load
	private Ref<Message> message;
	
	public NewMessageQuoteEventContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewMessageQuoteEventContent(QuotationRequest quotation) {
		super(quotation);
		// TODO Auto-generated constructor stub
	}

	public NewMessageQuoteEventContent(QuotationRequest quotation, Message message) {
		super(quotation);
		this.message = Ref.create(message);
	}

	public Message getMessage() {
		return message.get();
	}

	public void setMessage(Message message) {
		this.message = Ref.create(message);
	}
	
	

}
