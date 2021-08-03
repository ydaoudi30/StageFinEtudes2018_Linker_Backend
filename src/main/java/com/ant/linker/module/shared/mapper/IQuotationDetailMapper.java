package com.ant.linker.module.shared.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.dao.IQuoteResponseInfoDao;
import com.ant.linker.data.entity.DelayUnit;
import com.ant.linker.data.entity.File;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.Quotation;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.QuoteRequestInfo;
import com.ant.linker.data.entity.QuoteRequestStatus;
import com.ant.linker.data.entity.QuoteResponseInfo;
import com.ant.linker.module.file.service.IFileService;
import com.ant.linker.module.quotation.service.IQuotationService;
import com.ant.linker.module.shared.dto.quotationDetail.ProductDetailDto;
import com.ant.linker.module.shared.dto.quotationDetail.QuotationDetailDto;
import com.ant.linker.module.shared.dto.quotationDetail.QuoteElementDto;

@Mapper(componentModel = "spring", uses = {DiscussionMessageMapper.class, ICharacteristicMapper.class})

@Component
public abstract class IQuotationDetailMapper {

	@Autowired
	private IQuotationService quotationService;
	@Autowired
	private IQuoteResponseInfoDao quoteResponseInfoDao;

	@Mappings({ @Mapping(source = "quotationRequest.reference", target = "reference"),
			@Mapping(source = "quotationRequest.claimer.userAccount.fullName", target = "customer"),
			@Mapping(source = "quotationRequest.vendor.userAccount.fullName", target = "commercial"),
			@Mapping(source = "quotationRequest.stringStatus", target = "status"),
			@Mapping(source = "quotationRequest.quotation.deliveryDate", target = "delay"),
			@Mapping(source = "quotationRequest.quotation.deliveryPeriod", target = "type"),
			@Mapping(source = "quotationRequest.listQuoteRequestInfo", target = "quoteElementList"),
			@Mapping(source = "quotationRequest.quotation.listQuoteResponseInfo", target = "TTTC", qualifiedByName = "TTTC"),
			@Mapping(source = "quotationRequest.quotation.listQuoteResponseInfo", target = "THT", qualifiedByName = "THT"),
			@Mapping(source = "quotationChecked", target = "quotationChecked"),
			@Mapping(source = "delayPeriods", target = "periods"),
			@Mapping(source = "statusList", target = "listStatus"),
			@Mapping(source = "quotationRequest.discussion", target = "discussion", qualifiedByName = "Discussion") })
	public abstract QuotationDetailDto entityToDto(QuotationRequest quotationRequest, DelayUnit[] delayPeriods, QuoteRequestStatus[] statusList,
			Boolean quotationChecked);

	@Mappings({ @Mapping(source = "quotationChecked", target = "quotationChecked") })
	public abstract QuotationDetailDto entityToDto(Boolean quotationChecked);

	@Named("TTTC")
	public Float b(List<QuoteResponseInfo> list) {
		return quotationService.getTTTC(list);
	}

	@Named("THT")
	public Float a(List<QuoteResponseInfo> list) {
		return quotationService.getTHT(list);
	}

	public abstract List<QuoteElementDto> listQuoteRequestInfoToQuoteElementList(List<QuoteRequestInfo> list);

	/*
	 * public List<QuoteElementDto>
	 * listQuoteRequestInfoToQuoteElementList(List<QuoteRequestInfo> list){
	 * return quotationService.getListElements(list); }
	 */

	@Mappings({ @Mapping(source = "product.model", target = "product.model"),
			@Mapping(source = "product.label", target = "product.wording"),
			@Mapping(source = "product.brand.manufacturer.nomination", target = "product.manufacturer"),
			@Mapping(source = "product.brand.label", target = "product.brand"),
			@Mapping(source = "product.firstImage", target = "product.image", qualifiedByName = "Image"),
			@Mapping(source = "product.listeCharacteristic", target = "product.characteristics"),
			@Mapping(source = "quantity", target = "qteAsked"),
			@Mapping(source = "quoteResponseInfo.unitPrice", target = "response.unitPriceHT"),
			@Mapping(source = "quoteResponseInfo.discount", target = "response.discount"),
			@Mapping(source = "quoteResponseInfo.priceHT", target = "response.unitPriceDiscounted"),
			@Mapping(source = "quoteResponseInfo.total", target = "response.totalUnitPriceDiscounted"),

	})
	public abstract QuoteElementDto entityToDto(QuoteRequestInfo quoteRequestInfo);

	public List<String> listOfPeriodsValues(DelayUnit[] values) {
		if (values == null) {
			return null;
		}
		List<String> list = new ArrayList<>();
		for (DelayUnit item : values) {
			list.add(item.name());
		}
		return list;
	}

	@Autowired
	private IFileService fileService;

	@Named("Image")
	public String fileToUrl(File file) {
		return fileService.getFileUrl(file);
	}

	@Mappings({ @Mapping(source = "quoteElementList", target = "listQuoteResponseInfo"),
			@Mapping(source = "delay", target = "deliveryDate"), @Mapping(source = "type", target = "delay") })

	public abstract Quotation dtoToEntity(QuotationDetailDto quotationDetailDto);

	@IterableMapping(qualifiedByName = "mappSaved")
	public abstract List<QuoteResponseInfo> QuoteElementListTolistQuoteResponseInfo(List<QuoteElementDto> list);

	@Mappings({ @Mapping(source = "quotationRef", target = "reference"),
			@Mapping(source = "response.unitPriceHT", target = "unitPrice"),
			@Mapping(source = "response.discount", target = "discount")

	})
	public abstract QuoteResponseInfo mappQuoteResponse(QuoteElementDto quoteElement);

	@Named("mappSaved")
	public QuoteResponseInfo mappSavedQuoteResponse(QuoteElementDto quoteElement) {
		QuoteResponseInfo responseQuote = mappQuoteResponse(quoteElement);
		quoteResponseInfoDao.save(responseQuote);
		return responseQuote;
	}

	public DelayUnit stringToDelayUnit(String delay) {
		switch (delay) {
		case "Day":
			return DelayUnit.Day;
		case "Month":
			return DelayUnit.Month;
		case "Week":
			return DelayUnit.Week;

		}
		throw new IllegalArgumentException();
	}

	public QuoteRequestStatus stringToQuoteRequestStatus(String status) throws IllegalArgumentException {
		
		QuoteRequestStatus quoteRequestStatus = QuoteRequestStatus.valueOf(status);
		
		if (quoteRequestStatus == null) {
			throw new IllegalArgumentException();
		}
		
		return quoteRequestStatus;
	}

	@Mappings({ @Mapping(source = "quotationRef", target = "quoteResponseInfo.reference"),
			@Mapping(source = "product.model", target = "product.model"),
			@Mapping(source = "product.manufacturer", target = "product.brand.manufacturer.nomination"),
			@Mapping(source = "product.wording", target = "product.label"),
			@Mapping(source = "product.brand", target = "product.brand.label"),
			@Mapping(source = "qteAsked", target = "quantity"),
			@Mapping(source = "response.unitPriceHT", target = "quoteResponseInfo.unitPrice"),
			@Mapping(source = "response.discount", target = "quoteResponseInfo.discount")

	})
	public abstract QuoteRequestInfo dtoToEntity(QuoteElementDto quoteElementDto);

	public abstract List<QuoteRequestInfo> dtosToEntities(List<QuoteElementDto> quoteElementDtos);

	@Mappings({ @Mapping(source = "model", target = "model"), @Mapping(source = "label", target = "wording"),
			@Mapping(source = "brand.manufacturer.nomination", target = "manufacturer"),
			@Mapping(source = "brand.label", target = "brand"), @Mapping(source = "firstImage", target = "image") })

	public abstract ProductDetailDto entityToDto(Product product);

	public String fileToUrl2(File file) {
		return fileService.getFileUrl(file);
	}

}