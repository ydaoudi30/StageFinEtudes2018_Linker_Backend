package com.ant.linker.module.shared.rule.product;

import com.ant.linker.module.shared.rule.pattern.IRuleEngine;

public abstract class IProductRuleEngine extends IRuleEngine {

	public abstract boolean isProductOnStock() throws Exception;
}