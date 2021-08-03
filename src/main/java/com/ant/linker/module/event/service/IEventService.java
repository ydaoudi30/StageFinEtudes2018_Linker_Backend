package com.ant.linker.module.event.service;

import java.util.List;

import com.ant.linker.data.entity.Message;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.module.shared.dto.event.EventDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;

public interface IEventService {

	public void createNewQuoteEvent(QuotationRequest quotation);

	public void createNewMessageQuoteEvent(QuotationRequest quotation, Message message);
	
	public List<EventDto> loadEvents(UserSignUpDto commercial);
}
