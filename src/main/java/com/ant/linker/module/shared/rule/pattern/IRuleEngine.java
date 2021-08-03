package com.ant.linker.module.shared.rule.pattern;

import java.util.ArrayList;
import java.util.List;

public abstract class IRuleEngine {
	
	protected List<IRule> rules;
	
	public IRuleEngine() {
		this.rules = new ArrayList<>();
	}

	public List<IRule> getRules() {
		return rules;
	}

	public void setRules(List<IRule> rules) {
		this.rules = rules;
	}
}
