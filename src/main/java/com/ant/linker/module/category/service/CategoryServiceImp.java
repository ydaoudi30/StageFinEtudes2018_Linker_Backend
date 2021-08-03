package com.ant.linker.module.category.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.linker.cache.CachePaginatorHelper;
import com.ant.linker.cache.config.CacheConfig;
import com.ant.linker.data.dao.IAbstractCategoryDao;
import com.ant.linker.data.entity.AbstractCategory;
import com.ant.linker.data.entity.FinalCategory;
import com.ant.linker.data.entity.NodeCategory;
import com.ant.linker.module.shared.dto.product.CategoryDto;
import com.ant.linker.module.shared.mapper.CategoryMapper;

@Service
public class CategoryServiceImp implements ICategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private IAbstractCategoryDao abstractCategoryDao;

	@Autowired
	private CachePaginatorHelper cacheData;
	
	@Override
	public List<CategoryDto> loadListeCategory() {
		//load catalog from cache
		List<AbstractCategory> generalCatalog = (List<AbstractCategory>) cacheData.retrievFromCache(CacheConfig.GENERAL_CATALOG_KEY);
		if(generalCatalog == null || generalCatalog.isEmpty()) {
			//load catalog from datastore
			generalCatalog = abstractCategoryDao.loadFromGeneralCatalog();
			//load catalog to cache
			cacheData.loadToCache(CacheConfig.GENERAL_CATALOG_KEY, generalCatalog);
		}
		return categoryMapper.entitiesToDtos(generalCatalog);
	}

	@Override
	public AbstractCategory saveCategory(AbstractCategory category) {
		return abstractCategoryDao.saveCategory(category);
	}

	@Override
	public CategoryDto loadAscendentOfCategory(String refCategory) {
		// TODO Auto-generated method stub
		FinalCategory finalCategory = abstractCategoryDao.loadFinalCategoryByref(refCategory);
		NodeCategory level2 = abstractCategoryDao.loadNodeCategoryByChildRef(finalCategory);
		level2.setListeAbstractCategory(Arrays.asList(finalCategory));
		NodeCategory level1 = abstractCategoryDao.loadNodeCategoryByChildRef(level2);
		level1.setListeAbstractCategory(Arrays.asList(level2));
		return categoryMapper.entityToDto(level1);
	}

}
