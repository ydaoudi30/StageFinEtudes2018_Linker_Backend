package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

@Entity
@Index

public class Discussion implements Serializable {
	
	@Id
	private Long idDiscussion;
	@Load
	private Ref<QuotationRequest> quotationRequest;
	@Load
	private List<Ref<Message>> listMessage;
	
	
	public Discussion(){
		super();
		this.listMessage = new ArrayList<>();
	}
	
	public Long getIdDiscussion(){
		return this.idDiscussion;
	}
	
	public QuotationRequest getQuotationRequest(){
		return quotationRequest.get();
	}
	
	public List<Message> getListMessage() {
		List<Message> messages = new ArrayList<>();

		for (Ref<Message> refMessage : listMessage) {

			Message message = refMessage.get();

			messages.add(message);

		}

		return messages;
	} 
	
	public void setIdDiscussion(Long id){
		this.idDiscussion = id;
	}
	
	public void setQuotationRequest(QuotationRequest quote){
		quotationRequest = Ref.create(quote);
	}
	
	public void setListMessage(List<Message> liste) {
		for (Message message : liste) {

			listMessage.add(Ref.create(message));

		}
	}
	
	public void addMessage(Message message) {

		listMessage.add(Ref.create(message));

	}
	
public void removeMessage(Message message){
		
		listMessage.remove(Ref.create(message));
		
	}

}