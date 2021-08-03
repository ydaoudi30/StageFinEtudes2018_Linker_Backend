package com.ant.linker.module.quotation.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.dao.IBrand;
import com.ant.linker.data.dao.ICartDao;
import com.ant.linker.data.dao.ICommercial;
import com.ant.linker.data.dao.ICommercialCartDao;
import com.ant.linker.data.dao.ICustomerDao;
import com.ant.linker.data.dao.IDiscussionDao;
import com.ant.linker.data.dao.IGuestDao;
import com.ant.linker.data.dao.IMessageDao;
import com.ant.linker.data.dao.IProductDao;
import com.ant.linker.data.dao.IQuotationDao;
import com.ant.linker.data.dao.IQuotationRequestDao;
import com.ant.linker.data.dao.IQuoteRequestInfoDao;
import com.ant.linker.data.dao.IQuoteResponseInfoDao;
import com.ant.linker.data.dao.IUserDao;
import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.DelayUnit;
import com.ant.linker.data.entity.Discussion;
import com.ant.linker.data.entity.Message;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.Quotation;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.QuoteRequestInfo;
import com.ant.linker.data.entity.QuoteRequestStatus;
import com.ant.linker.data.entity.QuoteResponseInfo;
import com.ant.linker.module.cart.service.ICartService;
import com.ant.linker.module.commercial.service.IUserService;
import com.ant.linker.module.event.service.IEventService;
import com.ant.linker.module.file.service.IFileService;
import com.ant.linker.module.shared.dto.cart.CreateQuoteFromCommercialCartDto;
import com.ant.linker.module.shared.dto.cart.RemoveProductFromCartManager;
import com.ant.linker.module.shared.dto.product.ProductCreateDto;
import com.ant.linker.module.shared.dto.quotation.QuotationRequestListElement;
import com.ant.linker.module.shared.dto.quotationDetail.QuotationDetailDto;
import com.ant.linker.module.shared.dto.quotationDetail.QuoteElementDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;
import com.ant.linker.module.shared.factory.cart.ICartFactory;
import com.ant.linker.module.shared.mapper.DiscussionMessageMapper;
import com.ant.linker.module.shared.mapper.IQuotationDetailMapper;
import com.ant.linker.module.shared.mapper.IQuotationRequestMapper;

@Component
public class QuotationServiceImpl implements IQuotationService {
	@Autowired
	private IUserService userService;
	@Autowired
	private ICartService cartservice;
	@Autowired
	private ICommercial commercialDao;
	@Autowired
	private IQuotationRequestDao quotationRequestDao;
	@Autowired
	private IQuoteRequestInfoDao quoteRequestInfoDao;
	@Autowired
	private IQuoteResponseInfoDao quoteResponseInfoDao;
	@Autowired
	private IQuotationDao quotationDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private IGuestDao guestDao;
	@Autowired
	private ICartDao cartDao;
	@Autowired
	private ICommercialCartDao commercialCartDao;
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IBrand brandDao;
	@Autowired
	private IDiscussionDao discussionDao;
	@Autowired
	private IMessageDao messageDao;

	@Autowired
	private IFileService fileService;
	@Autowired
	private ICartService cartService;
	@Autowired
	private IQuotationRequestMapper iQuotationRequestMapper;
	@Autowired
	private IQuotationDetailMapper iQuotationDetailMapper;
	@Autowired
	private DiscussionMessageMapper dicussionMapper;

	@Autowired
	private IEventService eventService;
	
	@Autowired
	private ICartFactory cartFactory;
	
	@Override
	public Integer getNbArticle(List<QuoteRequestInfo> listQuoteRequestInfo) {
		Integer a = new Integer(listQuoteRequestInfo.size());
		return a;
	}

	@Override
	public Long getQteTotal(List<QuoteRequestInfo> listQuoteRequestInfo) {
		long quantity = 0;
		for (QuoteRequestInfo item : listQuoteRequestInfo) {
			quantity = item.getQuantity() + quantity;
		}
		Long a = new Long(quantity);
		return a;
	}

	@Override
	public Float getTTTC(List<QuoteResponseInfo> listQuoteResponseInfo) {
		Float TTTC = new Float(0);
		for (QuoteResponseInfo item : listQuoteResponseInfo) {

			TTTC = (item.getPrice() * item.getQuoteRequestInfo().getQuantity()) + TTTC;
		}
		return twoDigits(TTTC);
	}

	public Float getTHT(List<QuoteResponseInfo> listQuoteResponseInfo) {
		Float THT = new Float(0);
		for (QuoteResponseInfo item : listQuoteResponseInfo) {
			THT = (item.getPriceHT() * item.getQuoteRequestInfo().getQuantity()) + THT;
		}

		return twoDigits(THT);
	};

	public QuotationDetailDto getQuoteByRef(String ref, String email) {
		if (quoteBelongsToCom(ref, email) || quoteBelongsToCust(ref, email)) {
			QuotationRequest quotation = quotationRequestDao.loadQuotationRequestByReference(ref);
			QuotationDetailDto quotationDto = iQuotationDetailMapper.entityToDto(quotation, DelayUnit.values(),
					QuoteRequestStatus.values(), Boolean.TRUE);
			return quotationDto;
		} else {
			return iQuotationDetailMapper.entityToDto(Boolean.FALSE);
		}
	}

	public List<QuotationRequestListElement> loadQuotationList(UserSignUpDto commercialAccount) {
		Commercial commercial = userService.getCommercialByAccountEmail(commercialAccount.getEmail());
		if (commercial != null) {

			List<QuotationRequest> listeQuotationRequest = new ArrayList<>();
			for (QuotationRequest quote : commercial.getListeQuotationRequest()) {
				if (quote != null) {
					listeQuotationRequest.add(quote);
				}
			}
			return iQuotationRequestMapper.entitiesToDtos(listeQuotationRequest);
		} else {
			List<QuotationRequestListElement> listeQuotationRequest = new ArrayList<>();
			return listeQuotationRequest;
		}

	}

	public QuotationDetailDto rtedit(QuotationDetailDto detail) {
		Float tht = new Float(0);
		if (detail != null) {

			for (QuoteElementDto item : detail.getQuoteElementList()) {
				if (item.getResponse().getUnitPriceHT() != null && item.getResponse().getDiscount() != null) {

					tht = new Float(tht + item.getQteAsked() * item.getResponse().getUnitPriceHT()
							* (1 - (item.getResponse().getDiscount() * new Float(0.01))));
					item.getResponse().setUnitPriceDiscounted(twoDigits(item.getResponse().getUnitPriceHT()
							* (1 - (item.getResponse().getDiscount() * new Float(0.01)))));
					item.getResponse().setTotalUnitPriceDiscounted(twoDigits(item.getResponse().getUnitPriceHT()
							* (1 - (item.getResponse().getDiscount() * new Float(0.01))) * item.getQteAsked()));
				}
			}

			detail.setTHT(twoDigits(tht));
			Float x = new Float(tht * 1.2);
			detail.setTTTC(twoDigits(x));
		}
		return detail;

	}

	public Float twoDigits(Float number) {
		return new Float(Math.round(number * 100.0) / 100.0);
	}

	public QuotationDetailDto toInProgress(QuotationDetailDto detail) {
		QuotationRequest quotationRequest = quotationRequestDao.loadQuotationRequestByReference(detail.getReference());
		if (quotationRequest.getEnumStatus() == QuoteRequestStatus.New) {
			quotationRequest.setStatus(QuoteRequestStatus.InProgress);
			quotationRequestDao.save(quotationRequest);
			detail.setStatus(QuoteRequestStatus.InProgress.getStatus());
		}

		return detail;
	}

	public void createResponse(QuotationDetailDto quotationDetailDto) {
		Quotation quotation = iQuotationDetailMapper.dtoToEntity(quotationDetailDto);
		quotationDao.save(quotation);
		;
		QuotationRequest quotationRequest = quotationRequestDao
				.loadQuotationRequestByReference(quotationDetailDto.getReference());
		quotation.setQuotationRequest(quotationRequest);
		quotationDao.save(quotation);

		quotationRequest.setQuotation(quotation);
		quotationRequestDao.save(quotationRequest);

		for (QuoteResponseInfo response : quotation.getListQuoteResponseInfo()) {

			QuoteRequestInfo request = quoteRequestInfoDao.loadQuoteRequestInfoByReference(response.getReference());

			response.setQuoteRequestInfo(request);
			quoteResponseInfoDao.save(response);
			request.setQuoteResponseInfo(response);
			quoteRequestInfoDao.save(request);
		}

		quotation.setDiscounted();
		quotationDao.save(quotation);
		quotationRequest.setStatus(QuoteRequestStatus.Answered);
		quotationRequestDao.save(quotationRequest);

	};

	public void editResponses(QuotationDetailDto quotationDetailDto) {
		QuotationRequest quotationRequest = quotationRequestDao
				.loadQuotationRequestByReference(quotationDetailDto.getReference());
		Quotation quotation = quotationDao.loadQuotationByQuotationRequest(quotationRequest);
		quotation.setDeliveryDate(quotationDetailDto.getDelay());
		quotation.setDelay(DelayUnit.valueOf(quotationDetailDto.getType()));
		quotationDao.save(quotation);

		for (QuoteResponseInfo response : quotation.getListQuoteResponseInfo()) {

			for (QuoteElementDto element : quotationDetailDto.getQuoteElementList()) {

				if (response.getQuoteRequestInfo().getReference().equals(element.getQuotationRef())) {

					response.setDiscount(element.getResponse().getDiscount());
					response.setUnitPrice(element.getResponse().getUnitPriceHT());
					quoteResponseInfoDao.save(response);
				}
			}

		}
		quotation.setDiscounted();
		quotationDao.save(quotation);
		if (quotationRequest.getEnumStatus() == QuoteRequestStatus.Answered) {
			quotationRequest.setStatus(QuoteRequestStatus.Rectified);
			quotationRequestDao.save(quotationRequest);
		}

	}

	public void createEdit(QuotationDetailDto quotationDetailDto) {
		if (quotationDetailDto.getStatus().equals(QuoteRequestStatus.New.getStatus())) {
			createResponse(quotationDetailDto);
		} else if (quotationDetailDto.getStatus().equals(QuoteRequestStatus.Rectified.getStatus())
				|| quotationDetailDto.getStatus().equals(QuoteRequestStatus.Answered.getStatus())
				|| quotationDetailDto.getStatus().equals(QuoteRequestStatus.InProgress.getStatus())) {
			editResponses(quotationDetailDto);
		}
	}

	public boolean quoteBelongsToCom(String reference, String email) {
		QuotationRequest quotationRequest = quotationRequestDao.loadQuotationRequestByReference(reference);
		if (quotationRequest != null) {
			if (quotationRequest.getVendor().getUserAccount().getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean quoteBelongsToCust(String reference, String email){
		QuotationRequest quotationRequest = quotationRequestDao.loadQuotationRequestByReference(reference);
		if (quotationRequest != null) {
			if (quotationRequest.getClaimer().getUserAccount().getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String cartToQuotation(CreateQuoteFromCommercialCartDto element) {

		String quoteRef = quoteRefBuilder(element.getCommercialCart().getCommercial(), element.getCustomerEmail(),
				element.getCommercialCart().getListProduct().size());

		int x = 0;
		int y = 0;

		Commercial commercial = userService.getCommercialByAccountEmail(element.getCommercialCart().getCommercial());
		if (commercial != null) {

			Customer customer = userService.getCustomerByAccountEmail(element.getCustomerEmail());

			if (customer != null) {

				List<QuotationRequest> quoteList = quotationRequestDao.loadQuotationRequestsByCustomer(customer);
				for (QuotationRequest quote : quoteList) {
					if (quote.getVendor().getUserAccount().getEmail().equals(element.getCommercialCart().getCommercial())
							&& quote.getEnumStatus().equals(QuoteRequestStatus.InPreparation)) {
						
						for (ProductCreateDto item : element.getCommercialCart().getListProduct()) {

							Product product = productDao.loadProductByBrandModel(
									brandDao.loadBrandByLabel(item.getBrandLabel()), item.getModel());
							for (QuoteRequestInfo request : quote.getListQuoteRequestInfo()) {
								if (product.equals(request.getProduct())) {
									y = 1;
									cartService.removeProductFromCart(cartFactory.createRemoveCartFromProduct(product, element));
									continue;
								}
							}
							if (y == 0) {
								QuoteRequestInfo quoteRequestInfo = new QuoteRequestInfo(0L);
								quoteRequestInfoDao.save(quoteRequestInfo);

								quoteRequestInfo.setProduct(product);
								quoteRequestInfoDao.save(quoteRequestInfo);

								quote.addQuoteRequestInfo(quoteRequestInfo);
								quotationRequestDao.save(quote);

								quoteRequestInfo.setQuotationRequest(quote);
								quoteRequestInfo.setReference();
								quoteRequestInfoDao.save(quoteRequestInfo);

								product.addQuoteRequestInfo(quoteRequestInfo);
								productDao.save(product);

								Cart cart = cartDao.loadCartByCustomer(customer);
								CommercialCart comCart = cartservice.findComCartByEmail(cart,
										element.getCommercialCart().getCommercial());

								cart.removeCommercialCart(comCart);
								cartDao.save(cart);
								commercialCartDao.deleteCommercialCart(comCart);
							}
						}

						x = 1;
					}
				}

				if (x == 0) {

					QuotationRequest quotationRequest = new QuotationRequest(quoteRef, new Date());
					quotationRequest.setClaimer(customer);
					quotationRequest.setVendor(commercial);
					quotationRequest.setStatus(QuoteRequestStatus.InPreparation);
					ofy().save().entity(quotationRequest).now();

					for (ProductCreateDto item : element.getCommercialCart().getListProduct()) {
						QuoteRequestInfo quoteRequestInfo = new QuoteRequestInfo(0L);
						quoteRequestInfoDao.save(quoteRequestInfo);
						Product product = productDao.loadProductByBrandModel(
								brandDao.loadBrandByLabel(item.getBrandLabel()), item.getModel());

						quoteRequestInfo.setProduct(product);
						quoteRequestInfoDao.save(quoteRequestInfo);

						quotationRequest.addQuoteRequestInfo(quoteRequestInfo);
						quotationRequestDao.save(quotationRequest);

						quoteRequestInfo.setQuotationRequest(quotationRequest);
						quoteRequestInfo.setReference();
						quoteRequestInfoDao.save(quoteRequestInfo);

						product.addQuoteRequestInfo(quoteRequestInfo);
						productDao.save(product);

					}

					commercial.addQuotationRequest(quotationRequest);
					commercialDao.saveCommercial(commercial);

					customer.addQuotationRequest(quotationRequest);
					customerDao.save(customer);

					Discussion discussion = new Discussion();
					discussion.setQuotationRequest(quotationRequest);
					discussionDao.save(discussion);

					quotationRequest.setDiscussion(discussion);
					quotationRequestDao.save(quotationRequest);

					Cart cart = cartDao.loadCartByCustomer(customer);
					CommercialCart comCart = cartservice.findComCartByEmail(cart,
							element.getCommercialCart().getCommercial());

					cart.removeCommercialCart(comCart);
					cartDao.save(cart);
					commercialCartDao.deleteCommercialCart(comCart);
					
				}

			}
		}

		return quoteRef;

	}

	@Override
	public String quoteRefBuilder(String commercial, String customer, Integer nbProduct) {

		String twoOfEach = commercial.length() < 2 ? commercial
				: commercial.substring(0, 2).concat(customer.length() < 2 ? customer : customer.substring(0, 2));
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String date = String.valueOf(timestamp.getTime());
		String quoteRef = twoOfEach.concat(date).concat(String.valueOf(nbProduct));

		return quoteRef;

	}

	@Override
	public List<QuotationDetailDto> getInPreparationQuotes(String customerEmail) {

		Customer customer = userService.getCustomerByAccountEmail(customerEmail);

		List<QuotationDetailDto> list = new ArrayList<>();

		for (QuotationRequest quote : quotationRequestDao.loadQuotationRequestsByCustomer(customer)) {
			if (quote.getEnumStatus() == QuoteRequestStatus.InPreparation) {
				list.add(iQuotationDetailMapper.entityToDto(quote, DelayUnit.values(), QuoteRequestStatus.values(),
						Boolean.TRUE));
			}
		}

		return list;

	}

	@Override
	public Integer getNewQuote(QuotationDetailDto quotationDetailDto) {

		if (quotationDetailDto != null) {

			QuotationRequest quotationRequest = quotationRequestDao
					.loadQuotationRequestByReference(quotationDetailDto.getReference());
			for (QuoteRequestInfo request : quotationRequest.getListQuoteRequestInfo()) {
				for (QuoteElementDto element : quotationDetailDto.getQuoteElementList()) {
					if (request.getProduct().getModel().equals(element.getProduct().getModel())) {
						request.setQuantity(new Long(element.getQteAsked()));
						quoteRequestInfoDao.save(request);
						quotationRequestDao.save(quotationRequest);
					}
				}
			}

			quotationRequest.setStatus(QuoteRequestStatus.New);
			quotationRequestDao.save(quotationRequest);
			
			// Event Notification
			eventService.createNewQuoteEvent(quotationRequest);
			
			return 1;

		} else {
			return 0;
		}

	}

	@Override
	public List<QuotationDetailDto> removeProductFromQuoteManager(RemoveProductFromCartManager productId) {

		QuotationRequest quotationRequest = quotationRequestDao
				.loadQuotationRequestByReference(productId.getQuoteRef());
		Product product = productDao.loadProductByBrandModel(brandDao.loadBrandByLabel(productId.getBrand()),
				productId.getModel());
		Customer customer = userService.getCustomerByAccountEmail(productId.getCustomer());
		Commercial commercial = userService.getCommercialByAccountEmail(productId.getCommercial());

		for (QuoteRequestInfo request : quoteRequestInfoDao.loadQuoteRequestInfoByQuotationRequest(quotationRequest)) {
			if (request.getProduct().getModel().equals(productId.getModel())
					&& request.getProduct().getBrand().getLabel().equals(productId.getBrand())) {
				quotationRequest.removeQuoteRequestInfo(request);
				quotationRequestDao.save(quotationRequest);
				product.removeQuoteRequestInfo(request);
				quoteRequestInfoDao.deleteQuoteRequestInfo(request);
				if (quotationRequest.getListQuoteRequestInfo().size() == 0) {
					customer.removeQuotationrequest(quotationRequest);
					customerDao.save(customer);

					commercial.removeQuotationrequest(quotationRequest);
					commercialDao.saveCommercial(commercial);
					
					for(Message message : quotationRequest.getDiscussion().getListMessage()){
						messageDao.deleteMessage(message);
					}
					
					discussionDao.deleteDiscussion(quotationRequest.getDiscussion());

					quotationRequestDao.deleteQuotationRequest(quotationRequest);
				}

			}
		}

		return getInPreparationQuotes(productId.getCustomer());
	}

	@Override
	public List<QuotationRequestListElement> loadCustomerQuotationList(UserSignUpDto commercialAccount) {
		Customer customer = userService.getCustomerByAccountEmail(commercialAccount.getEmail());
		if (customer != null) {

			List<QuotationRequest> listeQuotationRequest = new ArrayList<>();
			for (QuotationRequest quote : customer.getListeQuotationRequest()) {
				if (quote != null) {
					listeQuotationRequest.add(quote);
				}
			}
			return iQuotationRequestMapper.entitiesToDtos(listeQuotationRequest);
		} else {
			List<QuotationRequestListElement> listeQuotationRequest = new ArrayList<>();
			return listeQuotationRequest;
		}

	}
}
