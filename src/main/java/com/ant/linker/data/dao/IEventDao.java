package com.ant.linker.data.dao;

import com.ant.linker.data.entity.Event;
import com.ant.linker.data.entity.event.type.AbstractEventContent;

public interface IEventDao {

	public Event saveEvent(Event event);
	
	public AbstractEventContent saveContent(AbstractEventContent abstractEventContent);
	
}
