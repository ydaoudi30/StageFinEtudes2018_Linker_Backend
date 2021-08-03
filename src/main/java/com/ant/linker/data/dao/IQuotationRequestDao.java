package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.Customer;


public interface IQuotationRequestDao {
	
	public QuotationRequest save(QuotationRequest quotationRequest);
	public QuotationRequest loadQuotationRequest();
	public List<QuotationRequest> loadAllQuotationRequests();
	public QuotationRequest loadQuotationRequestByReference(String reference);
	public List<QuotationRequest> loadQuotationRequestsByCommercial(Commercial commercial);
	public List<QuotationRequest> loadQuotationRequestsByCustomer(Customer claimer);
	public void deleteQuotationRequest(QuotationRequest quotationRequest);
}
