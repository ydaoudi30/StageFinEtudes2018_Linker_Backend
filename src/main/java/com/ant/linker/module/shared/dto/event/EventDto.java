package com.ant.linker.module.shared.dto.event;

import java.util.Date;
import java.util.Map;

import org.json.JSONObject;

public class EventDto {
	
	private Date date;
	private JSONObject content;
	private String eventType;
	
	public EventDto(Date date, JSONObject content, String eventType) {
		super();
		this.date = date;
		this.content = content;
		this.eventType = eventType;
	}
	public EventDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Map getContent() {
		return content.toMap();
	}
	public void setContent(JSONObject content) {
		this.content = content;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
}
