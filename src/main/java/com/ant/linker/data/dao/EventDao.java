package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Event;
import com.ant.linker.data.entity.event.type.AbstractEventContent;

@Component
public class EventDao implements IEventDao {

	@Override
	public Event saveEvent(Event event) {
		
		ofy().save().entity(event).now();
		return event;
		
	}

	@Override
	public AbstractEventContent saveContent(AbstractEventContent abstractEventContent) {
		ofy().save().entity(abstractEventContent).now();
		return abstractEventContent;
	}

}
