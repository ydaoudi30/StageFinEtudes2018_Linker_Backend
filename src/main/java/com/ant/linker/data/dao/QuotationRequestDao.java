package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;


import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.Customer;

import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.QuoteRequestInfo;
import com.googlecode.objectify.Ref;

@Component
public class QuotationRequestDao implements IQuotationRequestDao {

	@Override
	public QuotationRequest save(QuotationRequest quotationRequest) {
		ofy().save().entity(quotationRequest).now();
		return quotationRequest;
	}

	@Override
	public List<QuotationRequest> loadAllQuotationRequests() {
		List<QuotationRequest> listQuoteRequest = ofy().load().type(QuotationRequest.class).list();
		return listQuoteRequest;
	}

	@Override
	public QuotationRequest loadQuotationRequest() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<QuotationRequest> loadQuotationRequestsByCommercial(Commercial commercial) {
		List<QuotationRequest> quotationRequests = ofy().load().type(QuotationRequest.class).filter("commercial", Ref.create(commercial)).list();
		return quotationRequests;
	}
	
	@Override
	public List<QuotationRequest> loadQuotationRequestsByCustomer(Customer claimer) {
		List<QuotationRequest> quotationRequests = ofy().load().type(QuotationRequest.class).filter("claimer", Ref.create(claimer)).list();
		return quotationRequests;
	}
	
	@Override
	public QuotationRequest loadQuotationRequestByReference(String reference) {
		return ofy().load().type(QuotationRequest.class).filter("reference =", reference).first().now();
	}
	
	@Override
	public void deleteQuotationRequest(QuotationRequest quotationRequest) {

		ofy().delete().entity(quotationRequest).now();

	}

	

}
