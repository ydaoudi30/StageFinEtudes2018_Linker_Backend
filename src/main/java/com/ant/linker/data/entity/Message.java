package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

@Entity
@Index

public class Message implements Serializable{
	@Id
	private Long idMessage;
	@Load
	private String message;
	@Load
	private Ref<User> sender;
	@Load
	private Date dateMessage;
	
	public Message() {
		super();
	}
	
	public Message(String message, Date dateMessage) {
		this.message = message;
		this.dateMessage = dateMessage;
		
	}
	
	public Long getidMessage(){
		return this.idMessage;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	
	public Date getDateMessage(){
		return this.dateMessage;
	}
	
	public User getSender() {

		return sender.get();
	}

	
	public void setidMessage(Long idMessage){
		this.idMessage = idMessage;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public void setDateMessage(){
		this.dateMessage = new Date();
	}
	
	public void setDateAuto(){
		this.dateMessage = new Date();
	}
	
	public void setSender(User sender) {
		this.sender = Ref.create(sender);
	}
	

}
	
	
