package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;


import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Discussion;
import com.ant.linker.data.entity.Message;

import com.googlecode.objectify.Ref;

@Component
public class MessageDao implements IMessageDao {

	@Override
	public Message save(Message message) {
		ofy().save().entity(message).now();
		return message;
	}

	@Override
	public Message loadMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Discussion loadDiscussionByMessage(Message message){
		Discussion discussion = ofy().load().type(Discussion.class).order("-__key__").filter("message", Ref.create(message)).first().now();
		return discussion;
	}
	
	@Override
	public void deleteMessage(Message message) {

		ofy().delete().entity(message).now();

	}
	
}
