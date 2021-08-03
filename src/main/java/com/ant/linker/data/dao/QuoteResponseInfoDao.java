package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;


import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.QuoteResponseInfo;
import com.ant.linker.data.entity.Quotation;
import com.ant.linker.data.entity.QuoteRequestInfo;
import com.googlecode.objectify.Ref;

@Component
public class QuoteResponseInfoDao implements IQuoteResponseInfoDao {

	@Override
	public QuoteResponseInfo save(QuoteResponseInfo quoteResponseInfo) {
		ofy().save().entity(quoteResponseInfo).now();
		return quoteResponseInfo;
	}

	@Override
	public List<QuoteResponseInfo> loadAllQuoteResponseInfo() {
		List<QuoteResponseInfo> listQuoteResponseInfo = ofy().load().type(QuoteResponseInfo.class).list();
		return listQuoteResponseInfo;
	}

	@Override
	public QuoteResponseInfo loadQuoteResponseInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<QuoteResponseInfo> loadQuoteResponseInfoByQuotation(Quotation quotation) {
		List<QuoteResponseInfo> quoteResponseInfo = ofy().load().type(QuoteResponseInfo.class).filter("quotation", Ref.create(quotation)).list();
		return quoteResponseInfo;
	}
	
	@Override
	public QuoteResponseInfo loadQuoteResponseInfoByReference(String reference){
		return ofy().load().type(QuoteResponseInfo.class).filter("reference =", reference).first().now();
	};

}
