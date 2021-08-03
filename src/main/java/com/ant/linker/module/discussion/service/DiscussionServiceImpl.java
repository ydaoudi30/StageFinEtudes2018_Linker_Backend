package com.ant.linker.module.discussion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.dao.IDiscussionDao;
import com.ant.linker.data.dao.IMessageDao;
import com.ant.linker.data.dao.IQuotationRequestDao;
import com.ant.linker.data.dao.IUserDao;
import com.ant.linker.data.entity.Discussion;
import com.ant.linker.data.entity.Message;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.User;
import com.ant.linker.module.event.service.IEventService;
import com.ant.linker.module.shared.dto.discussion.DiscussionDto;
import com.ant.linker.module.shared.mapper.DiscussionMessageMapper;

@Component
public class DiscussionServiceImpl implements IDiscussionService {

	@Autowired
	private IQuotationRequestDao quotationRequestDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IDiscussionDao discussionDao;
	@Autowired
	private IMessageDao messageDao;
	@Autowired
	private DiscussionMessageMapper discussionMessageMapper;
	@Autowired
	private IEventService eventService;

	@Override
	public DiscussionDto createAddMessage(DiscussionDto discussionDto) {

		QuotationRequest quotationRequest = quotationRequestDao.loadQuotationRequestByReference(discussionDto.getRefQuote());

		Discussion discussion = quotationRequest.getDiscussion();
		if (discussion != null) {

			User sender = userDao.load(discussionDto.getMessages().get(0).getSenderEmail());
			Message message = discussionMessageMapper.dtoToEntity(discussionDto.getMessages().get(0),sender);
			message.setDateAuto();
			messageDao.save(message);

			discussion.addMessage(message);
			discussionDao.save(discussion);
			
			// Event Notification
			eventService.createNewMessageQuoteEvent(quotationRequest, message);
			
			return discussionMessageMapper.entityToDto(discussion);
		}

		return discussionDto;
	}

}
