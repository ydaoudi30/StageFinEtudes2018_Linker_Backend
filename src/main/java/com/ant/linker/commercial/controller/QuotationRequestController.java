package com.ant.linker.commercial.controller;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.data.dao.IBrand;
import com.ant.linker.data.dao.IEntreprise;
import com.ant.linker.data.dao.IFileDao;
import com.ant.linker.data.dao.IProductDao;
import com.ant.linker.data.entity.*;
import com.ant.linker.module.commercial.service.IUserService;
import com.ant.linker.module.quotation.service.IQuotationService;
import com.ant.linker.module.discussion.service.IDiscussionService;
import com.ant.linker.module.event.service.IEventService;
import com.ant.linker.module.shared.dto.quotation.QuotationRequestListElement;
import com.ant.linker.module.shared.dto.quotationDetail.QuotationDetailDto;
import com.ant.linker.module.shared.dto.quotationDetail.QuoteElementDto;
import com.ant.linker.module.shared.dto.quotationDetail.QuoteRequestBodyDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;
import com.ant.linker.module.shared.dto.discussion.*; 

@CrossOrigin()
@RestController
@RequestMapping("/cmr/quotationRequest")

public class QuotationRequestController {
	@Autowired
	private IUserService userService ;
	
	@Autowired
	private IQuotationService quotationRequestService;
	
	@Autowired
	private IDiscussionService discussionService;
	
	@Autowired
	private IEventService eventService;
	
	@PostMapping("/commercial/list")
	public List<QuotationRequestListElement> getList(@RequestBody UserSignUpDto commercialDto) {
		return quotationRequestService.loadQuotationList(commercialDto);
	}
 
	@PostMapping("/commercial/quote/detail")
	public QuotationDetailDto getQuote(@RequestBody QuoteRequestBodyDto quoteRequestBodyDto){
		return quotationRequestService.getQuoteByRef(quoteRequestBodyDto.getReference(), quoteRequestBodyDto.getEmail());
	}
	
	@PostMapping("/commercial/quote/detail/bind")
	public QuotationDetailDto sendEditedQuote(@RequestBody QuotationDetailDto quotationDetailDto){
		return quotationRequestService.rtedit(quotationDetailDto);	}
	
	@PostMapping("/commercial/quote/detail/status/progress")
	public QuotationDetailDto progress(@RequestBody QuotationDetailDto quotationDetailDto){
		return quotationRequestService.toInProgress(quotationDetailDto);
	}
	
	@PostMapping("/commercial/quote/detail/responseManager")
	public QuotationDetailDto responseManager(@RequestBody QuotationDetailDto quotationDetailDto){
		for(QuoteElementDto item : quotationDetailDto.getQuoteElementList()){
			item.setQuotationRef(item.getProduct().getBrand() + "_" + item.getProduct().getModel() + "_" + quotationDetailDto.getReference());
		}
		quotationRequestService.createEdit(quotationDetailDto);
		return quotationDetailDto;
	}
	@PostMapping("/commercial/quote/detail/addMessage")
	public DiscussionDto createMessage(@RequestBody DiscussionDto discussionDto){
		return discussionService.createAddMessage(discussionDto);
	}
	

	
	
	@GetMapping("/default/quote1")
	public void defaultUp() {
		
		Commercial commercial = userService.getCommercialByAccountEmail("agent@gmail.com");
		
		if(commercial != null){
			
			Customer customer = userService.getCustomerByAccountEmail("user.cust@gmail.com");
			
			if(customer != null){
				
				Product product1 = ofy().load().type(Product.class).filter("model", "A01IA89").first().now();
				Product product2 = ofy().load().type(Product.class).filter("model", "A01I009").first().now();
			
				
				
				QuotationRequest quotationRequest = new QuotationRequest("Q011", new Date(2017, 10, 19));
				quotationRequest.setClaimer(customer);
				quotationRequest.setVendor(commercial);
				quotationRequest.setStatus(QuoteRequestStatus.New);
				ofy().save().entity(quotationRequest).now();
			
				
				QuoteRequestInfo quoteRequestInfo1 = new QuoteRequestInfo(12L);
				ofy().save().entity(quoteRequestInfo1).now();
				quoteRequestInfo1.setProduct(product1);
				
				
				QuoteRequestInfo quoteRequestInfo2 = new QuoteRequestInfo(6L);
				ofy().save().entity(quoteRequestInfo2).now();
				quoteRequestInfo2.setProduct(product2);
				
				
				
				quotationRequest.addQuoteRequestInfo(quoteRequestInfo1);
				quotationRequest.addQuoteRequestInfo(quoteRequestInfo2);
				
				
				ofy().save().entity(quotationRequest).now();
				
				quoteRequestInfo1.setQuotationRequest(quotationRequest);
				quoteRequestInfo2.setQuotationRequest(quotationRequest);
				quoteRequestInfo1.setReference();
				quoteRequestInfo2.setReference();
				
				ofy().save().entity(quoteRequestInfo1).now();
				ofy().save().entity(quoteRequestInfo2).now();
				
				
				
				/*Quotation quotation = new Quotation();
				quotation.setQuotationRequest(quotationRequest);
				ofy().save().entity(quotation).now();
				
				quotationRequest.setQuotation(quotation);
				ofy().save().entity(quotationRequest).now();
				
				QuoteResponseInfo quoteResponseInfo1 = new QuoteResponseInfo(500F,12);
				QuoteResponseInfo quoteResponseInfo2 = new QuoteResponseInfo(558F,9);
				
				quoteResponseInfo1.setQuoteRequestInfo(quoteRequestInfo1);
				quoteResponseInfo2.setQuoteRequestInfo(quoteRequestInfo2);
				ofy().save().entity(quoteResponseInfo1).now();
				ofy().save().entity(quoteResponseInfo2).now();
				
				quoteRequestInfo1.setQuoteResponseInfo(quoteResponseInfo1);
				quoteRequestInfo2.setQuoteResponseInfo(quoteResponseInfo2);
				ofy().save().entity(quoteRequestInfo1).now();
				ofy().save().entity(quoteRequestInfo2).now();
				
				quotation.addQuoteResponceInfo(quoteResponseInfo1);
				quotation.addQuoteResponceInfo(quoteResponseInfo2);
				quotation.setDiscounted();
				quotation.setDelay(DelayUnit.Day);
				quotation.setDeliveryDate(3);
				quotation.setDeliveryPeriod();
				ofy().save().entity(quotation).now();*/
				
				product1.addQuoteRequestInfo(quoteRequestInfo1);
				product2.addQuoteRequestInfo(quoteRequestInfo2);
				
				ofy().save().entity(product1).now();
				ofy().save().entity(product2).now();
				
				
				
				commercial.addQuotationRequest(quotationRequest);
				ofy().save().entity(commercial).now();
				
				customer.addQuotationRequest(quotationRequest);
				ofy().save().entity(customer).now();
				
				/* Event Test */
				eventService.createNewQuoteEvent(quotationRequest);
				
				/*Discussion Test*/
				
				Discussion discussion = new Discussion();
				discussion.setQuotationRequest(quotationRequest);
				ofy().save().entity(discussion).now();
				
				quotationRequest.setDiscussion(discussion);
				ofy().save().entity(quotationRequest).now();
				
				
				Message message1 = new Message();
				message1.setMessage("Hello my friend");
				message1.setDateMessage();
				message1.setSender(commercial.getUserAccount());
				ofy().save().entity(message1).now();
				
				Message message2 = new Message();
				message2.setMessage("sup Buddy?");
				message2.setDateMessage();
				message2.setSender(customer.getUserAccount());
				ofy().save().entity(message2).now();
				
				List<Message> listMessage = new ArrayList<>();
				listMessage.add(message1);
				listMessage.add(message2);
				discussion.setListMessage(listMessage);
				ofy().save().entity(discussion).now();
				
				eventService.createNewMessageQuoteEvent(quotationRequest, message2);
			}
		}
		
	}
	
	@GetMapping("/default/quote2")
	public void defaultUp3() {
		
		Commercial commercial = userService.getCommercialByAccountEmail("agent@gmail.com");
		
		if(commercial != null){
			
			Customer customer = userService.getCustomerByAccountEmail("omar.cust@gmail.com");
			
			if(customer != null){
				
				Product product1 = ofy().load().type(Product.class).filter("model", "A01IA89").first().now();
				Product product2 = ofy().load().type(Product.class).filter("model", "A01I009").first().now();
			
				
				
				QuotationRequest quotationRequest = new QuotationRequest("Q010", new Date(2018, 6, 12));
				quotationRequest.setClaimer(customer);
				quotationRequest.setVendor(commercial);
				quotationRequest.setStatus(QuoteRequestStatus.New);
				ofy().save().entity(quotationRequest).now();
			
				
				QuoteRequestInfo quoteRequestInfo1 = new QuoteRequestInfo(6L);
				ofy().save().entity(quoteRequestInfo1).now();
				quoteRequestInfo1.setProduct(product1);
				
				
				QuoteRequestInfo quoteRequestInfo2 = new QuoteRequestInfo(5L);
				ofy().save().entity(quoteRequestInfo2).now();
				quoteRequestInfo2.setProduct(product2);
				
				
				
				quotationRequest.addQuoteRequestInfo(quoteRequestInfo1);
				quotationRequest.addQuoteRequestInfo(quoteRequestInfo2);
				
				
				ofy().save().entity(quotationRequest).now();
				
				quoteRequestInfo1.setQuotationRequest(quotationRequest);
				quoteRequestInfo2.setQuotationRequest(quotationRequest);
				quoteRequestInfo1.setReference();
				quoteRequestInfo2.setReference();
				
				ofy().save().entity(quoteRequestInfo1).now();
				ofy().save().entity(quoteRequestInfo2).now();
				
				
				
				/*Quotation quotation = new Quotation();
				quotation.setQuotationRequest(quotationRequest);
				ofy().save().entity(quotation).now();
				
				quotationRequest.setQuotation(quotation);
				ofy().save().entity(quotationRequest).now();
				
				QuoteResponseInfo quoteResponseInfo1 = new QuoteResponseInfo(500F,12);
				QuoteResponseInfo quoteResponseInfo2 = new QuoteResponseInfo(558F,9);
				
				quoteResponseInfo1.setQuoteRequestInfo(quoteRequestInfo1);
				quoteResponseInfo2.setQuoteRequestInfo(quoteRequestInfo2);
				ofy().save().entity(quoteResponseInfo1).now();
				ofy().save().entity(quoteResponseInfo2).now();
				
				quoteRequestInfo1.setQuoteResponseInfo(quoteResponseInfo1);
				quoteRequestInfo2.setQuoteResponseInfo(quoteResponseInfo2);
				ofy().save().entity(quoteRequestInfo1).now();
				ofy().save().entity(quoteRequestInfo2).now();
				
				quotation.addQuoteResponceInfo(quoteResponseInfo1);
				quotation.addQuoteResponceInfo(quoteResponseInfo2);
				quotation.setDiscounted();
				quotation.setDelay(DelayUnit.Day);
				quotation.setDeliveryDate(3);
				quotation.setDeliveryPeriod();
				ofy().save().entity(quotation).now();*/
				
				product1.addQuoteRequestInfo(quoteRequestInfo1);
				product2.addQuoteRequestInfo(quoteRequestInfo2);
				
				ofy().save().entity(product1).now();
				ofy().save().entity(product2).now();
				
				
				
				commercial.addQuotationRequest(quotationRequest);
				ofy().save().entity(commercial).now();
				
				customer.addQuotationRequest(quotationRequest);
				ofy().save().entity(customer).now();
				
		
			}
		}
		
	}
	
	
	@Autowired
	private IBrand brandDao;
	
	@Autowired
	private IEntreprise manufacturerDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IFileDao fileDao;
	
	@GetMapping("/default/product1")
	public void defaultUp1() {
		Product product = new Product("Moteur", "A01IA89", new Date(), "ceci est moteur");
		
		File file = new File();
		file = fileDao.save(file);
		Entreprise manufacturer =new Entreprise ();
		manufacturer.setNomination("Blackmere");
		manufacturer = manufacturerDao.save(manufacturer);
		
		Brand brand = new Brand();
		brand.setManufacturer(manufacturer);
		brand.setLabel("Motors");
	
		brand = brandDao.save(brand);
		product.setBrand(brand);
		product.addImage(file);
		
		productDao.save(product);
		
		Commercial commercial = userService.getCommercialByAccountEmail("agent@gmail.com");
		
		if(commercial != null){		
			CommercialCatalog commercialCatalog = new CommercialCatalog();
			ofy().save().entity(commercialCatalog).now();
			
			commercialCatalog.setCommercial(commercial);
			commercialCatalog.setProduct(product);
			
			product.addCommercialCatalog(commercialCatalog);
			commercial.addCommercialCatalog(commercialCatalog);
	
			
			productDao.save(product);
			ofy().save().entity(commercial).now();
			ofy().save().entity(commercialCatalog).now();
		}
	}
	
	@GetMapping("/default/product2")
	public void defaultUp2() {
		Product product = new Product("Moteur AS", "A01I009", new Date(), "ceci est moteur asynchrone");
		
		File file = new File();
		file = fileDao.save(file);
		
		Brand brand = ofy().load().type(Brand.class).filter("label", "Motors").first().now();
		product.setBrand(brand);
		product.addImage(file);
		
		productDao.save(product);
		
		Commercial commercial = userService.getCommercialByAccountEmail("agent@gmail.com");
		
		if(commercial != null){
			CommercialCatalog commercialCatalog = new CommercialCatalog();
			ofy().save().entity(commercialCatalog).now();
			
			commercialCatalog.setCommercial(commercial);
			commercialCatalog.setProduct(product);
			
			product.addCommercialCatalog(commercialCatalog);
			commercial.addCommercialCatalog(commercialCatalog);
	
			
			productDao.save(product);
			ofy().save().entity(commercial).now();
			ofy().save().entity(commercialCatalog).now();
		}
	}

	
}
