package com.ant.linker.module.shared.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.module.quotation.service.IQuotationService;

import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.QuoteRequestInfo;
import com.ant.linker.data.entity.QuoteResponseInfo;

import com.ant.linker.module.shared.dto.quotation.QuotationRequestListElement;

@Mapper(componentModel = "spring")

@Component
public abstract class IQuotationRequestMapper {
	
	@Autowired
	private IQuotationService quotationService;

	
	public abstract List<QuotationRequestListElement> entitiesToDtos(List<QuotationRequest> quotationRequests);

	@Mappings({ @Mapping(source = "reference", target = "reference"), 
		@Mapping(source = "claimer.userAccount.fullName", target = "customer"),
			@Mapping(source = "dateRequest", target = "dateRequest"), 
			@Mapping(source = "quotation.discounted", target = "discount"),
			@Mapping(source = "listQuoteRequestInfo", target = "nbArticle", qualifiedByName={ "Methods", "NbArticle" }),
			@Mapping(source = "listQuoteRequestInfo", target = "qteTotal",  qualifiedByName={ "Methods", "Quantity" }),
			@Mapping(source = "quotation.listQuoteResponseInfo", target = "TTTC",qualifiedByName={ "Methods", "TTC" }),
			@Mapping(source = "stringStatus", target = "status"),
			@Mapping(source = "quotation.deliveryDate", target = "delay"),
			@Mapping(source = "quotation.deliveryPeriod", target = "type")
			

	})

	public abstract QuotationRequestListElement entityToDto(QuotationRequest quotationRequest);
	
		@Named("NbArticle")
		public Integer listToNumber(List<QuoteRequestInfo> list){
			return quotationService.getNbArticle(list);
		}

		@Named("Quantity")
		public Long listToQte(List<QuoteRequestInfo> list){
			return quotationService.getQteTotal(list);
		}
		@Named("TTC")
		public Float listTotttc(List<QuoteResponseInfo> list){
			return quotationService.getTTTC(list);
		}
	   
}