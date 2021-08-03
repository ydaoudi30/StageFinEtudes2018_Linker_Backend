package com.ant.linker.module.product.importing;

public interface IProductImporter {

	public void execute();
	public void initProducts();
	public boolean isProcessForCron();
	public void setProcessForCron(boolean processForCron);
}
