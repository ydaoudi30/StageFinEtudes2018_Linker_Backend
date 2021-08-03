package com.ant.linker.module.shared.dto.quotationDetail;


public class QuoteElementDto {

	private ProductDetailDto product;
	private ResponseDetailDto response;
	private Long qteAsked;
	private String quotationRef;
	
	
	public QuoteElementDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuoteElementDto(ProductDetailDto product, ResponseDetailDto response, Long qteAsked, String quotationRef) {
		super();
		this.product = product;
		this.response = response;
		this.qteAsked = qteAsked;
		this.quotationRef = quotationRef;
		
	}
	
	public ProductDetailDto getProduct(){
		return this.product;
	}
	
	public ResponseDetailDto getResponse(){
		return this.response;
	}
	
	public Long getQteAsked(){
		return this.qteAsked;
	}
	public String getQuotationRef(){
		return this.quotationRef;
	}
	
	public void setProduct(ProductDetailDto product){
		this.product = product;
	}
	
	public void setResponse(ResponseDetailDto response){
		this.response = response;
	}
	public void setQteAsked(Long qteAsked){
		this.qteAsked = qteAsked;
	}
	public void setQuotationRef(String ref){
		this.quotationRef = ref;
	}
	
}