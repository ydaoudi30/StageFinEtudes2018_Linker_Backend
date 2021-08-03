package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Discussion;
import com.ant.linker.data.entity.QuotationRequest;
import com.googlecode.objectify.Ref;

@Component
public class DiscussionDao implements IDiscussionDao {

	@Override
	public Discussion save(Discussion discussion) {
		ofy().save().entity(discussion).now();
		return discussion;
	}

	@Override
	public Discussion loadDiscussion() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Discussion loadDiscussionByQuotationRequest(QuotationRequest quotationRequest){
		Discussion discussion = ofy().load().type(Discussion.class).filter("quotationRequest", Ref.create(quotationRequest)).first().now();
		return discussion;
	}
	
	@Override
	public void deleteDiscussion(Discussion discussion) {

		ofy().delete().entity(discussion).now();

	}
	
}
