package com.ant.linker.module.shared.rule.quotation;

import  com.ant.linker.module.shared.rule.pattern.IRule;

import static com.ant.linker.module.shared.rule.pattern.ConditionsNames.COMMERCIAL_ACCESS_COND;

public class CommercialQuoteAccessRule extends IRule{	

	public CommercialQuoteAccessRule(String commercialEmail, String quoteRef) {
		super();
		
		CommercialQuoteAccessCondition commercialQuoteAccessCondition = new CommercialQuoteAccessCondition(commercialEmail, quoteRef);
		addCondition(COMMERCIAL_ACCESS_COND, commercialQuoteAccessCondition);
	}

	@Override
	public boolean isValid() throws Exception {
		return isConditionSatisfied(COMMERCIAL_ACCESS_COND);
	}

}