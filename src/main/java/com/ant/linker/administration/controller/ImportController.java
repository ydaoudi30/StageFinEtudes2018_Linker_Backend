package com.ant.linker.administration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.data.dao.IImportConfigDao;
import com.ant.linker.data.entity.ImportConfig;
import com.ant.linker.data.entity.general.GeneralStatus;
import com.ant.linker.data.entity.general.ImportType;
import com.ant.linker.module.product.importing.ICategoryImporter;
import com.ant.linker.module.product.importing.IProductImporter;
import com.ant.linker.module.product.importing.ProductImporterImpl;

@RestController
@RequestMapping("/cmr/import")
public class ImportController {
	
	@Autowired
	private ICategoryImporter categoryImporter;
	
	@Autowired
	private IProductImporter productImporter;
	
	@Autowired
	private IImportConfigDao configDao;

	@GetMapping("/catalog")
	public void importCatalog() {
		categoryImporter.execute();
	}
	
	@GetMapping("/init/catalog")
	public void initCatalog() {
		categoryImporter.initCatalog();
	}
	
	@GetMapping("/products")
	public void importProducts() {
		productImporter.setProcessForCron(false);
		productImporter.execute();
	}
	
	@GetMapping("/init/Products")
	public void initProducts() {
		productImporter.initProducts();
	}
	
	@GetMapping("/cronjob/products")
	public void importProductsFromCronJob() {
		productImporter.setProcessForCron(true);
		productImporter.execute();
	}
	
	@GetMapping("/config/init")
	public void initImportConfig() {
		for(int i = 0 ; i < 9; i++) {
			ImportConfig configProduct = new ImportConfig();
			configProduct.setFilePath(ImportType.PRODUCT.getFilePrefix() + i + ".csv");
			configProduct.setImportType(ImportType.PRODUCT);
			configProduct.setIndex(i);
			configProduct.setStatus(GeneralStatus.TO_PROCESS);
			configDao.save(configProduct);
			
			ImportConfig configCaracteristic = new ImportConfig();
			configCaracteristic.setFilePath(ImportType.CARACTERISTIC.getFilePrefix() + i + ".csv");
			configCaracteristic.setImportType(ImportType.CARACTERISTIC);
			configCaracteristic.setIndex(i);
			configCaracteristic.setStatus(GeneralStatus.TO_PROCESS);
			configDao.save(configCaracteristic);
		}
	}
	
}
