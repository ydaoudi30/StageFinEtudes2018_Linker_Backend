package com.ant.linker.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.module.cart.service.ICartService;
import com.ant.linker.module.commercial.service.IUserService;
import com.ant.linker.module.discussion.service.IDiscussionService;
import com.ant.linker.module.quotation.service.IQuotationService;
import com.ant.linker.module.shared.dto.cart.AddFromGuestCartToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToCartDto;
import com.ant.linker.module.shared.dto.cart.AddToMultipleCartDto;
import com.ant.linker.module.shared.dto.cart.CartDto;
import com.ant.linker.module.shared.dto.cart.CreateQuoteFromCommercialCartDto;
import com.ant.linker.module.shared.dto.cart.EmailsForMergeDto;
import com.ant.linker.module.shared.dto.cart.RemoveProductFromCartManager;
import com.ant.linker.module.shared.dto.discussion.DiscussionDto;
import com.ant.linker.module.shared.dto.quotation.QuotationRequestListElement;
import com.ant.linker.module.shared.dto.quotationDetail.QuotationDetailDto;
import com.ant.linker.module.shared.dto.quotationDetail.QuoteRequestBodyDto;
import com.ant.linker.module.shared.dto.security.UserSignUpDto;

@CrossOrigin()
@RestController
@RequestMapping("/cust/cart")
public class CartController {
	@Autowired
	private ICartService cartService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IQuotationService quotationRequestService;
	@Autowired
	private IDiscussionService discussionService;

	@PostMapping("/customer/cart")
	@ExceptionHandler({ Exception.class })
	public Integer addToCart(@RequestBody AddToMultipleCartDto multipleCartDto) throws Exception {
		return cartService.addProductToCart(multipleCartDto);
	}
	
	@PostMapping("/customer/cart/nbProduct")
	public Integer numberProductInCart(@RequestBody String customerOrGuestEmail){
		return cartService.nbArticle(customerOrGuestEmail);
	}
	
	@GetMapping("/guest")
	public UserSignUpDto createGuest(){
		return userService.createGuestAccount();
	}
	
	@PostMapping("/customer/cart/display")
	public CartDto getcart(@RequestBody String email){
		return cartService.sendCartByUser(email);
	}
	
	@PostMapping("/customer/cart/removeItem")
	public CartDto removeProduct(@RequestBody AddToCartDto product){
		return cartService.removeProductFromCart(product);
	}
	
	@PostMapping("/customer/cart/newQuote")
	public String createQuote(@RequestBody CreateQuoteFromCommercialCartDto element){
		return quotationRequestService.cartToQuotation(element);
	}
	
	@PostMapping("/customer/quote/inPreparationQuotes")
	public List<QuotationDetailDto> getInPreparationQuotes(@RequestBody String customerEmail){
		return quotationRequestService.getInPreparationQuotes(customerEmail);
	}
	
	@PostMapping("/customer/quote/generateNewQuote")
	public Integer generateNewQuote(@RequestBody QuotationDetailDto quotationDetailDto){
		return quotationRequestService.getNewQuote(quotationDetailDto);
	}
	
	@PostMapping("/customer/quote/removeProduct")
	public List<QuotationDetailDto> deleteProductFromQuoteManager(@RequestBody RemoveProductFromCartManager productId){
		return quotationRequestService.removeProductFromQuoteManager(productId);
	}
	
	@PostMapping("/customer/quotationsList")
	public List<QuotationRequestListElement> getList(@RequestBody UserSignUpDto customerDto) {
		return quotationRequestService.loadCustomerQuotationList(customerDto);
	}
	
	@PostMapping("/customer/quotation/detail")
	public QuotationDetailDto getQuotationDetail(@RequestBody QuoteRequestBodyDto quoteRequestBodyDto) {
		return quotationRequestService.getQuoteByRef(quoteRequestBodyDto.getReference(), quoteRequestBodyDto.getEmail());
	}
	
	@PostMapping("/customer/quotation/detail/addMessage")
	public DiscussionDto createMessage(@RequestBody DiscussionDto discussionDto){
		return discussionService.createAddMessage(discussionDto);
	}
	
	@PostMapping("/customer/cart/mergeOneProduct")
	public CartDto mergeOne(@RequestBody AddFromGuestCartToCartDto product) throws Exception{
		return cartService.addProductFromCartGuest(product);
	}
	
	@PostMapping("/customer/cart/mergeAllProducts")
	public Integer mergeAll(@RequestBody EmailsForMergeDto infos) throws Exception{
		return cartService.mergeCarts(infos);
	}
	
	@PostMapping("/guest/merge/deleteGuest")
	public Integer deleteGuest(@RequestBody String guestEmail){
		return userService.deleteGuestAccount(guestEmail);
	}
	
	
}