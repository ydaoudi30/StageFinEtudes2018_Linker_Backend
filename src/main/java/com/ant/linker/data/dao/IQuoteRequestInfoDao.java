package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.QuoteRequestInfo;



public interface IQuoteRequestInfoDao {
	
	public QuoteRequestInfo save(QuoteRequestInfo quoteRequestInfo);
	public QuoteRequestInfo loadQuoteRequestInfo();
	public List<QuoteRequestInfo> loadAllQuoteRequestInfo();
	public List<QuoteRequestInfo> loadQuoteRequestInfoByQuotationRequest(QuotationRequest quotationRequest);
	public QuoteRequestInfo loadQuoteRequestInfoByReference(String reference);
	public void deleteQuoteRequestInfo(QuoteRequestInfo quoteRequestInfo);

}
