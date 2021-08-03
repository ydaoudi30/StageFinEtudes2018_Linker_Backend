package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.Quotation;
import com.ant.linker.data.entity.QuotationRequest;

public interface IQuotationDao {
	public Quotation save(Quotation quotation);
	public Quotation loadQuotation();
	public List<Quotation> loadAllQuotations();
	public Quotation loadQuotationByQuotationRequest(QuotationRequest quotationRequest);

}
