package com.ant.linker.module.shared.rule.product;

import com.ant.linker.module.shared.rule.pattern.ICondition;

public class StockCondition implements ICondition {
	
	private boolean isOnStock;

	public StockCondition(boolean isOnStock) {
		super();
		this.isOnStock = isOnStock;
	}

	@Override
	public boolean isSatisfied() {
		// TODO Auto-generated method stub
		return isOnStock;
	}
	
}
