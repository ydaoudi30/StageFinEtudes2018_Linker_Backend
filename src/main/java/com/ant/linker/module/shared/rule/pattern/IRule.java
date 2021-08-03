package com.ant.linker.module.shared.rule.pattern;

import java.util.HashMap;
import java.util.Map;

public abstract class IRule {

	protected Map<String, ICondition> conditions;

	public abstract boolean isValid() throws Exception;

	public IRule() {
		this.conditions = new HashMap<>();
	}

	public void addCondition(String conditionKey, ICondition condition) {
		this.conditions.put(conditionKey, condition);
	}

	public Map<String, ICondition> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, ICondition> conditions) {
		this.conditions = conditions;
	}
	
	public boolean isConditionSatisfied(String conditionKey) throws Exception{
		if(this.conditions.get(conditionKey) == null){
			throw new Exception("Condition with key : " + conditionKey + " not exist !!!");
		}
		return this.conditions.get(conditionKey).isSatisfied();
	}

}
