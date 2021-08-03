package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;


import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Quotation;
import com.ant.linker.data.entity.QuotationRequest;

import com.googlecode.objectify.Ref;

@Component
public class QuotationDao implements IQuotationDao {

	@Override
	public Quotation save(Quotation quotation) {
		ofy().save().entity(quotation).now();
		return quotation;
	}

	@Override
	public List<Quotation> loadAllQuotations() {
		List<Quotation> listQuotation = ofy().load().type(Quotation.class).list();
		return listQuotation;
	}

	@Override
	public Quotation loadQuotation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Quotation loadQuotationByQuotationRequest(QuotationRequest quotationRequest) {
		Quotation quotation = ofy().load().type(Quotation.class).filter("quotationRequest", Ref.create(quotationRequest)).first().now();
		return quotation;
	}

}
