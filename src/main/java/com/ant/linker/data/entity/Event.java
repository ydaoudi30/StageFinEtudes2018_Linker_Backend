package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.Date;

import com.ant.linker.data.entity.event.type.AbstractEventContent;
import com.ant.linker.data.entity.event.type.EventType;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class Event <T extends AbstractEventContent> implements Serializable {

	@Id
	private Long id;
	private Date date;
	private Ref<T> content;
	private EventType eventType;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(Date date, T content) {
		super();
		this.date = date;
		this.content = Ref.create(content);
	}

	public Long getId() {
		return id;
	}

	public T getContent() {
		if(content == null) return null;
		return content.get();
	}

	public void setContent(T content) {
		this.content = Ref.create(content);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
}
