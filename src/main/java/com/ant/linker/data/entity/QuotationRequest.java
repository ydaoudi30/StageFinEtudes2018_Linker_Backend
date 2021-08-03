package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

@Entity
@Index

public class QuotationRequest implements Serializable {

	@Id
	private Long idQuotRequest;
	@Load
	private String reference;
	@Load
	private List<Ref<QuoteRequestInfo>> listQuoteRequestInfo;
	@Load
	private Ref<Quotation> quotation;
	@Load
	private Ref<Customer> claimer;
	@Load
	private Ref<Commercial> vendor;
	@Load
	private Ref<Discussion> discussion;
	@Load
	private Date dateRequest;
	
	private QuoteRequestStatus status;
	
	public QuotationRequest() {
		super();
		this.listQuoteRequestInfo = new ArrayList<>();
	}
	
	public QuotationRequest(String reference, Date dateRequest) {
		this.reference = reference;
		this.dateRequest = dateRequest;
		this.listQuoteRequestInfo = new ArrayList<>();
	}


	public String getStatus(){
		return this.status.name();
	}
	
	public QuoteRequestStatus getEnumStatus(){
		return this.status;
	}
	
	public String getStringStatus(){
		return this.status.getStatus();
	}
	
	public Long getIdQuoteRequest() {
		return this.idQuotRequest;
	}
	
	public String getReference() {
		return this.reference;
	}
	
	public Quotation getQuotation() {
		if(this.quotation == null){
			return null;
		}
		return quotation.get();
	}
	
	public List<QuoteRequestInfo> getListQuoteRequestInfo() {
		List<QuoteRequestInfo> quotations = new ArrayList<>();
		for (Ref<QuoteRequestInfo> refquote : listQuoteRequestInfo) {
			QuoteRequestInfo quote = refquote.get();
			quotations.add(quote);
		}
		return quotations;
	}
	
	public Customer getClaimer() {

		return claimer.get();
	}
	
	public Commercial getVendor() {

		return vendor.get();
	}
	
	public Discussion getDiscussion(){
		return discussion.get();
	}
	
	public Date getDateRequest(){
		return this.dateRequest;
	}
	
	public void setIdQuoteRequest(Long idQuotRequest) {
		this.idQuotRequest = idQuotRequest;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public void setListQuoteRequestInfo(List<QuoteRequestInfo> liste) {
		for (QuoteRequestInfo QuoteRequestInfo : liste) {
			listQuoteRequestInfo.add(Ref.create(QuoteRequestInfo));
		}
	
	}
	
	public void setClaimer(Customer claimer) {
		this.claimer = Ref.create(claimer);
	}
	
	public void setVendor(Commercial vendor) {
		this.vendor = Ref.create(vendor);
	}
	
	public void addQuoteRequestInfo(QuoteRequestInfo quote) {
		listQuoteRequestInfo.add(Ref.create(quote));
	}
	
	public void removeQuoteRequestInfo(QuoteRequestInfo quote) {
		listQuoteRequestInfo.remove(Ref.create(quote));
	}
	
	public void setQuotation(Quotation quote) {
		this.quotation = Ref.create(quote);
	}
	
	public void setDiscussion(Discussion conversation){
		discussion = Ref.create(conversation);
	}
	
	public void setDateRequest(Date dateRequest){
		this.dateRequest = dateRequest;
	}

	public void setStatus(QuoteRequestStatus status) {
		this.status = status;
		
	}
	
	
}