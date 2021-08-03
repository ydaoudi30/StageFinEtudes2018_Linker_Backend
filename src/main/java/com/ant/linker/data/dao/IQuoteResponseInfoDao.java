package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.Quotation;
import com.ant.linker.data.entity.QuoteResponseInfo;




public interface IQuoteResponseInfoDao {
	
	public QuoteResponseInfo save(QuoteResponseInfo quoteResponseInfo);
	public QuoteResponseInfo loadQuoteResponseInfo();
	public List<QuoteResponseInfo> loadAllQuoteResponseInfo();
	public List<QuoteResponseInfo> loadQuoteResponseInfoByQuotation(Quotation quotation);
	public QuoteResponseInfo loadQuoteResponseInfoByReference(String reference);
	
}
