package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;


import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.QuoteRequestInfo;
import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.data.entity.QuotationRequest;

import com.googlecode.objectify.Ref;

@Component
public class QuoteRequestInfoDao implements IQuoteRequestInfoDao {

	@Override
	public QuoteRequestInfo save(QuoteRequestInfo quoteRequestInfo) {
		ofy().save().entity(quoteRequestInfo).now();
		return quoteRequestInfo;
	}

	@Override
	public List<QuoteRequestInfo> loadAllQuoteRequestInfo() {
		List<QuoteRequestInfo> listQuoteRequestInfo = ofy().load().type(QuoteRequestInfo.class).list();
		return listQuoteRequestInfo;
	}

	@Override
	public QuoteRequestInfo loadQuoteRequestInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<QuoteRequestInfo> loadQuoteRequestInfoByQuotationRequest(QuotationRequest quotationRequest) {
		List<QuoteRequestInfo> quoteRequestInfo = ofy().load().type(QuoteRequestInfo.class).filter("quotationRequest", Ref.create(quotationRequest)).list();
		return quoteRequestInfo;
	}
	
	@Override
	public QuoteRequestInfo loadQuoteRequestInfoByReference(String reference) {
		return ofy().load().type(QuoteRequestInfo.class).filter("reference =", reference).first().now();
	}
	
	@Override
	public void deleteQuoteRequestInfo(QuoteRequestInfo quoteRequestInfo) {

		ofy().delete().entity(quoteRequestInfo).now();

	}

}
