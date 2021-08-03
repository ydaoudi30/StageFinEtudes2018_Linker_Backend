package com.ant.linker.module.shared.factory;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.GeneralCatalog;

@Component
public class GeneralCatalogFactory implements IGeneralCatalogFactory {


	@Override
	public GeneralCatalog getInstance() {
		GeneralCatalog generalCatalog = new GeneralCatalog();
		return generalCatalog;
	}

}
