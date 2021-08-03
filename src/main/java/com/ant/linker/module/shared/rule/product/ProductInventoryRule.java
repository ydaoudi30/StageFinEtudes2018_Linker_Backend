package com.ant.linker.module.shared.rule.product;

import java.util.ArrayList;

import com.ant.linker.module.shared.rule.pattern.IRule;

public class ProductInventoryRule extends IRule {
	
	public ProductInventoryRule() {
		StockCondition stockCondition = new StockCondition(true);
		this.conditions.put("StockCondition", stockCondition);
	}

	@Override
	public boolean isValid() {
		return true;
	}

}
