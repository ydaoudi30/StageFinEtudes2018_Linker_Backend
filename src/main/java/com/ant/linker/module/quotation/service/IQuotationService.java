package com.ant.linker.module.quotation.service;

import java.util.List;

import com.ant.linker.data.entity.QuoteRequestInfo;
import com.ant.linker.data.entity.QuoteResponseInfo;
import com.ant.linker.module.shared.dto.cart.*;
import com.ant.linker.module.shared.dto.quotation.*;
import com.ant.linker.module.shared.dto.quotationDetail.*;

import com.ant.linker.module.shared.dto.security.UserSignUpDto;

public interface IQuotationService {
	public Float getTTTC(List<QuoteResponseInfo> listQuoteResponseInfo);

	public Long getQteTotal(List<QuoteRequestInfo> listQuoteRequestInfo);

	public Integer getNbArticle(List<QuoteRequestInfo> listQuoteRequestInfo);

	public Float getTHT(List<QuoteResponseInfo> listQuoteResponseInfo);

	public QuotationDetailDto getQuoteByRef(String reference, String email);

	public List<QuotationRequestListElement> loadQuotationList(UserSignUpDto commercialAccount);

	public QuotationDetailDto rtedit(QuotationDetailDto quotationDetailDto);

	public QuotationDetailDto toInProgress(QuotationDetailDto quotationDetailDto);

	public Float twoDigits(Float number);

	public void createResponse(QuotationDetailDto quotationDetailDto);

	public void editResponses(QuotationDetailDto quotationDetailDto);

	public void createEdit(QuotationDetailDto quotationDetailDto);

	public boolean quoteBelongsToCom(String reference, String email);
	
	public boolean quoteBelongsToCust(String reference, String email);

	public String cartToQuotation(CreateQuoteFromCommercialCartDto element);
	
	public String quoteRefBuilder(String commercial, String customer, Integer nbProduct);
	
	public List<QuotationDetailDto> getInPreparationQuotes(String customerEmail);
	
	public Integer getNewQuote(QuotationDetailDto quotationDetailDto);
	
	public List<QuotationDetailDto> removeProductFromQuoteManager(RemoveProductFromCartManager productId);
	
	public List<QuotationRequestListElement> loadCustomerQuotationList(UserSignUpDto commercialAccount);

}
