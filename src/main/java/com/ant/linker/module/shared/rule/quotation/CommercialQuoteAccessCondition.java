package com.ant.linker.module.shared.rule.quotation;

import com.ant.linker.module.shared.rule.pattern.ICondition;

public class CommercialQuoteAccessCondition implements ICondition {
	
	private String commercialEmail;
	
	private String quoteRef;
	
	public CommercialQuoteAccessCondition(String commercialEmail, String quoteRef) {
		super();
		this.commercialEmail = commercialEmail;
		this.quoteRef = quoteRef;
	}

	@Override
	public boolean isSatisfied() {
		// calling service to have information about commercial and quote
		return false;
	}

}