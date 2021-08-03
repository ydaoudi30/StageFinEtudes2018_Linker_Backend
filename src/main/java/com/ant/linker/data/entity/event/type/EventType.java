package com.ant.linker.data.entity.event.type;

import java.io.Serializable;

public enum EventType implements Serializable {
	
	NEW_MESSAGE_QUOTE_EVENT("NEW_MESSAGE_QUOTE_EVENT"),
	NEW_QUOTE_EVENT("NEW_QUOTE_EVENT")
	;

	private String eventType;

	private EventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	
}
