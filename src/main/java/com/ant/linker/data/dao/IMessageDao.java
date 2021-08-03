package com.ant.linker.data.dao;


import com.ant.linker.data.entity.Discussion;
import com.ant.linker.data.entity.Message;

public interface IMessageDao {
	public Message save(Message message);
	public Message loadMessage();
	public Discussion loadDiscussionByMessage(Message message);
	public void deleteMessage(Message message);
	
}
