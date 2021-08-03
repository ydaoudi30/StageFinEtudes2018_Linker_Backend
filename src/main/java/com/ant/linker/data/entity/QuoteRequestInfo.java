package com.ant.linker.data.entity;

import java.io.Serializable;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

@Entity
@Index

public class QuoteRequestInfo implements Serializable {

	@Id
	private Long idQuoteRequestInfo;
		
	@Load
	private String reference;
	
	@Load
	private Ref<QuotationRequest> quotationRequest; 
	@Load
	private Ref<Product> product;
	@Load
	private Ref<QuoteResponseInfo> quoteResponseInfo;
	@Load
	private Long quantity;
	
	public QuoteRequestInfo(){
		super();
	}
	
	public QuoteRequestInfo(Long quantity){
		this.quantity = quantity;
	}
	
	public Long getidQuoteRequestInfo(){
		return this.idQuoteRequestInfo;
	}
	
	public String getReference(){
		return this.reference;
	}
	
	public Long getQuantity(){
		return this.quantity;
	}
	
	public Product getProduct() {

		return product.get();
	}
	
	public QuotationRequest getQuotationRequest(){
		return quotationRequest.get();
	}
	
	public QuoteResponseInfo getQuoteResponseInfo(){
		if(this.quoteResponseInfo != null){
			return quoteResponseInfo.get();
		}
		return null;
	}
	
	public void setQuoteResponseInfo(QuoteResponseInfo response){
		quoteResponseInfo = Ref.create(response);
	}
	
	
	public void setidQuotRequestInfo(Long idQuotRequestInfo){
		this.idQuoteRequestInfo = idQuotRequestInfo;
	}
	
	public void setQuantity(Long quantity){
		this.quantity = quantity; 
	}
	
	public void setProduct(Product product) {
		this.product = Ref.create(product);
	}
	
	public void setQuotationRequest(QuotationRequest quote) {
		this.quotationRequest = Ref.create(quote);
	}
	
	public void setReference(){
		this.reference = getProduct().getBrand().getLabel() + "_" + getProduct().getModel() + "_" + getQuotationRequest().getReference();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((quotationRequest == null) ? 0 : quotationRequest.hashCode());
		result = prime * result + ((quoteResponseInfo == null) ? 0 : quoteResponseInfo.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuoteRequestInfo other = (QuoteRequestInfo) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (quotationRequest == null) {
			if (other.quotationRequest != null)
				return false;
		} else if (!quotationRequest.equals(other.quotationRequest))
			return false;
		if (quoteResponseInfo == null) {
			if (other.quoteResponseInfo != null)
				return false;
		} else if (!quoteResponseInfo.equals(other.quoteResponseInfo))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}
	
}