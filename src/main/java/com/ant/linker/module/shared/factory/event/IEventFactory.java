package com.ant.linker.module.shared.factory.event;

import java.util.List;
import java.util.stream.Collectors;

import com.ant.linker.data.entity.Event;
import com.ant.linker.data.entity.Message;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.event.type.EventType;
import com.ant.linker.data.entity.event.type.NewMessageQuoteEventContent;
import com.ant.linker.data.entity.event.type.NewQuoteEventContent;
import com.ant.linker.module.shared.dto.event.EventDto;

public interface IEventFactory {

	public NewMessageQuoteEventContent makeNewMessageContent(QuotationRequest quotation, Message message);
	
	public NewQuoteEventContent makeNewQuoteContent(QuotationRequest quotation);
	
	public List<EventDto> makeDtosFromEntities(List<Event> events);

	public EventDto makeDtoFromEntity(Event event);
	
	public Event makeEvent(EventType type);
}