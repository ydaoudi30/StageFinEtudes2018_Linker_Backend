package com.ant.linker.module.shared.dto.quotationDetail;

import java.util.List;

import com.ant.linker.module.shared.dto.discussion.DiscussionDto;

import java.util.ArrayList;

public class QuotationDetailDto {

	private String	reference;
	private String commercial;
	private String	customer;
	private String	status;
	private Integer	delay;
	private String  type;
	private Float TTTC;
	private Float THT;
	private DiscussionDto discussion;
	private boolean quotationChecked;
	private List<QuoteElementDto> quoteElementList;
	private List<String> periods;
	private List<String> listStatus;
	
	
	
	public QuotationDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuotationDetailDto(String reference,String commercial, String customer, String status, Integer delay, String type, Float TTTC, Float THT, DiscussionDto discussion) {
		super();
		this.reference = reference;
		this.commercial = commercial;
		this.customer = customer;
		this.status = status;
		this.delay = delay;
		this.type = type;
		this.TTTC = TTTC;
		this.THT = THT;
		this.discussion = discussion;
		this.quoteElementList = new ArrayList<>();
		this.periods = new ArrayList<>();
		this.listStatus = new ArrayList<>();
		
		
	}

	
		
	public String getCommercial() {
		return commercial;
	}

	public void setCommercial(String commercial) {
		this.commercial = commercial;
	}

	public DiscussionDto getDiscussion() {
		return discussion;
	}

	public void setDiscussion(DiscussionDto discussion) {
		this.discussion = discussion;
	}

	public void setQuotationChecked(boolean quotationChecked) {
		this.quotationChecked = quotationChecked;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String ref) {
		this.reference = ref;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDelay() {
		return this.delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public Float getTTTC(){
		return this.TTTC;
	}
	
	public void setTTTC(Float TTTC){
		this.TTTC = TTTC;
	}
	
	public Float getTHT(){
		return this.THT;
	}
	
	public void setTHT(Float THT){
		this.THT = THT;
	}
	
	public List<QuoteElementDto> getQuoteElementList(){
		return this.quoteElementList;
	}
	
	public void setQuoteElementList(List<QuoteElementDto> list){
		this.quoteElementList = list;
	}

	public void addQuoteElement(QuoteElementDto element){
		this.quoteElementList.add(element);
	}
	
	public void removeQuoteElement(QuoteElementDto element){
		this.quoteElementList.remove(element);
	}
	
	public List<String> getPeriods(){
		return this.periods;
	}
	
	public void setPeriods(List<String> list){
		this.periods = list;
	}

	public Boolean getQuotationChecked() {
		return quotationChecked;
	}

	public void setQuotationChecked(Boolean quotationChecked) {
		this.quotationChecked = quotationChecked;
	}
	
	public List<String> getListStatus(){
		return this.listStatus;
	}
	
	public void setListStatus(List<String> list){
		this.listStatus = list;
	}
}
