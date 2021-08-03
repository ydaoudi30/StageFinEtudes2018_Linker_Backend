package com.ant.linker.data.dao;


import com.ant.linker.data.entity.Discussion;
import com.ant.linker.data.entity.QuotationRequest;


public interface IDiscussionDao {
	public Discussion save(Discussion quotation);
	public Discussion loadDiscussion();
	public Discussion loadDiscussionByQuotationRequest(QuotationRequest quotationRequest);
	public void deleteDiscussion(Discussion discussion);
	
}
