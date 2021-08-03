package com.ant.linker.administration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ant.linker.cache.config.CacheConfig;
import com.ant.linker.data.dao.ICacheElementDao;
import com.ant.linker.data.entity.CacheElement;

@RestController
@RequestMapping("/adm/cache")
public class CacheConfigController {

	@Autowired
	private ICacheElementDao cacheConfigDao;
	
	@GetMapping("/initConfig")
	public void initConfig() {
		CacheElement productConfig = new CacheElement();
		productConfig.setActivatePagination(true);
		productConfig.setCacheKey(CacheConfig.ALL_PRODUCTS_KEY);
		productConfig.setCachePageSize(50L);
		cacheConfigDao.save(productConfig);
		
		CacheElement catalogConfig = new CacheElement();
		catalogConfig.setActivatePagination(false);
		catalogConfig.setCacheKey(CacheConfig.GENERAL_CATALOG_KEY);
		cacheConfigDao.save(catalogConfig);
	}
}
