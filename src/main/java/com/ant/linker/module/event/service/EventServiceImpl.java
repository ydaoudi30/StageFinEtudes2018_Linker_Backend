package com.ant.linker.module.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.linker.data.dao.IEventDao;
import com.ant.linker.data.dao.IUserDao;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.Event;
import com.ant.linker.data.entity.Message;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.User;
import com.ant.linker.data.entity.event.type.EventType;
import com.ant.linker.data.entity.event.type.NewMessageQuoteEventContent;
import com.ant.linker.data.entity.event.type.NewQuoteEventContent;
import com.ant.linker.module.shared.dto.event.EventDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;
import com.ant.linker.module.shared.factory.event.IEventFactory;

@Service
public class EventServiceImpl implements IEventService {

	@Autowired
	private IEventFactory eventFactory;

	@Autowired
	private IEventDao eventDao;

	@Autowired
	private IUserDao userDao;

	@Override
	public void createNewQuoteEvent(QuotationRequest quotation) {
		NewQuoteEventContent newQuoteContent = eventFactory.makeNewQuoteContent(quotation);
		eventDao.saveContent(newQuoteContent);
		
		Event newQuoteEvent = eventFactory.makeEvent(EventType.NEW_QUOTE_EVENT);
		newQuoteEvent.setContent(newQuoteContent);
		eventDao.saveEvent(newQuoteEvent);

		Commercial vendor = quotation.getVendor();
		User userAccount = vendor.getUserAccount();
		userAccount.addEvent(newQuoteEvent);
		userDao.save(userAccount);
	}

	@Override
	public void createNewMessageQuoteEvent(QuotationRequest quotation, Message message) {
		NewMessageQuoteEventContent newMessageContent = eventFactory.makeNewMessageContent(quotation, message);
		eventDao.saveContent(newMessageContent);
		
		Event newMessageEvent = eventFactory.makeEvent(EventType.NEW_MESSAGE_QUOTE_EVENT);
		newMessageEvent.setContent(newMessageContent);
		eventDao.saveEvent(newMessageEvent);

		User sender = message.getSender();
		User claimer = quotation.getClaimer().getUserAccount();
		User vendor = quotation.getVendor().getUserAccount();

		if (sender.getEmail().compareTo(claimer.getEmail()) == 0) {
			vendor.addEvent(newMessageEvent);
			userDao.save(vendor);
		} else if (sender.getEmail().compareTo(vendor.getEmail()) == 0) {
			claimer.addEvent(newMessageEvent);
			userDao.save(claimer);
		}

	}

	@Override
	public List<EventDto> loadEvents(UserSignUpDto commercial) {
		User user = userDao.load(commercial.getEmail());
		List<Event> events = user.getEvents();
		if (events != null) {
			events.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
			return eventFactory.makeDtosFromEntities(events);
		}
		return null;
	}

}
