package com.ant.linker.module.shared.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Discussion;
import com.ant.linker.data.entity.Message;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.QuoteResponseInfo;
import com.ant.linker.data.entity.User;
import com.ant.linker.module.discussion.service.IDiscussionService;
import com.ant.linker.module.quotation.service.IQuotationService;
import com.ant.linker.module.shared.dto.discussion.DiscussionDto;
import com.ant.linker.module.shared.dto.discussion.MessageDto;

@Mapper(componentModel = "spring")

@Component
public abstract class DiscussionMessageMapper {
	@Autowired
	private IDiscussionService discussionService;

	@Mappings({ @Mapping(source = "message", target = "message"),
			@Mapping(source = "sender.email", target = "senderEmail"),
			@Mapping(source = "sender.fullName", target = "senderFullName"),
			@Mapping(source = "dateMessage", target = "date") })

	public abstract MessageDto entityToDto(Message message);

	@Mappings({ @Mapping(source = "quotationRequest.reference", target = "refQuote"),
			@Mapping(source = "quotationRequest.vendor.userAccount.fullName", target = "commercialFullName"),
			@Mapping(source = "quotationRequest.vendor.userAccount.email", target = "commercialEmail"),
			@Mapping(source = "quotationRequest.claimer.userAccount.fullName", target = "customerFullName"),
			@Mapping(source = "quotationRequest.claimer.userAccount.email", target = "customerEmail"),
			@Mapping(source = "quotationRequest.discussion.listMessage", target = "messages") })

	@Named("Discussion")
	public abstract DiscussionDto entityToDto(Discussion discussion);

	public abstract List<MessageDto> entitiesToDtos(List<Message> listMessage);

	@Mappings({ @Mapping(source = "messageDto.message", target = "message"),
			@Mapping(source = "sender", target = "sender") })
	public abstract Message dtoToEntity(MessageDto messageDto, User sender);

	public abstract List<Message> dtosToEntities(List<MessageDto> listMessageDto);

	@Mappings({ @Mapping(source = "refQuote", target = "quotationRequest.reference"),
			@Mapping(source = "commercialFullName", target = "quotationRequest.vendor.userAccount.fullName"),
			@Mapping(source = "commercialEmail", target = "quotationRequest.vendor.userAccount.email"),
			@Mapping(source = "customerFullName", target = "quotationRequest.claimer.userAccount.fullName"),
			@Mapping(source = "customerEmail", target = "quotationRequest.claimer.userAccount.email"),
			@Mapping(source = "messages", target = "quotationRequest.discussion.listMessage") })
	public abstract Discussion dtoToEntity(DiscussionDto discussionDto);

}