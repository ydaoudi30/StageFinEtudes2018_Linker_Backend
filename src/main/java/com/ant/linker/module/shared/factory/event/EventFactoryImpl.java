package com.ant.linker.module.shared.factory.event;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.ant.linker.data.entity.Event;
import com.ant.linker.data.entity.Message;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.User;
import com.ant.linker.data.entity.event.type.EventType;
import com.ant.linker.data.entity.event.type.NewMessageQuoteEventContent;
import com.ant.linker.data.entity.event.type.NewQuoteEventContent;
import com.ant.linker.module.shared.dto.event.EventDto;

@Service
public class EventFactoryImpl implements IEventFactory {

	@Override
	public NewMessageQuoteEventContent makeNewMessageContent(QuotationRequest quotation, Message message) {
		NewMessageQuoteEventContent messageContent = new NewMessageQuoteEventContent(quotation, message);
		return messageContent;
	}

	@Override
	public NewQuoteEventContent makeNewQuoteContent(QuotationRequest quotation) {
		NewQuoteEventContent quoteContent = new NewQuoteEventContent(quotation);
		return quoteContent;
	}
	
	@Override
	public Event makeEvent(EventType type) {
		Event<?> event = null;
		switch(type) {
			case NEW_MESSAGE_QUOTE_EVENT : {
				event = new Event<NewMessageQuoteEventContent>();
				break;
			}
			case NEW_QUOTE_EVENT : {
				event = new Event<NewQuoteEventContent>();
				break;
			}
		}
		
		event.setEventType(type);
		event.setDate(new Date());
		return event;
	}
	
	@Override
	public List<EventDto> makeDtosFromEntities(List<Event> events) {
		List<EventDto> dtoEvents = events.stream().map(event -> makeDtoFromEntity(event)).collect(Collectors.toList());
		return dtoEvents;
	}

	@Override
	public EventDto makeDtoFromEntity(Event event) {

		EventDto eventDto = new EventDto();
		eventDto.setDate(event.getDate());
		eventDto.setEventType(event.getEventType().getEventType());

		JSONObject content = new JSONObject();
		switch (event.getEventType()) {
			case NEW_MESSAGE_QUOTE_EVENT: {
				
				NewMessageQuoteEventContent messageContent = (NewMessageQuoteEventContent) event.getContent();
				String reference = messageContent.getQuotation().getReference();
				String message = messageContent.getMessage().getMessage();
				User sender = messageContent.getMessage().getSender();
				
				content.put("message", message);
				content.put("senderFullName", sender.getFullName());
				content.put("senderEmail", sender.getEmail());
				content.put("quote", reference);
			}
				break;
			case NEW_QUOTE_EVENT:{
				NewQuoteEventContent quoteContent = (NewQuoteEventContent) event.getContent();
				String reference = quoteContent.getQuotation().getReference();
				User claimer = quoteContent.getQuotation().getClaimer().getUserAccount();
				
				content.put("claimerFullName", claimer.getFullName());
				content.put("claimerEmail", claimer.getEmail());
				content.put("quote", reference);
			}
				break;
		}

		eventDto.setContent(content);
		
		return eventDto;
	}

}
