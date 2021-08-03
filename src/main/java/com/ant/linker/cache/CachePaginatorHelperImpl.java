package com.ant.linker.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.dao.ICacheElementDao;
import com.ant.linker.data.entity.CacheElement;

@Component
public class CachePaginatorHelperImpl implements CachePaginatorHelper {

	@Autowired
	private CacheData cacheData;

	@Autowired
	private ICacheElementDao cacheElementDao;

	@Override
	public void loadToCache(String key, Object value) {
		CacheElement cacheConfig = cacheElementDao.loadCacheElementByKey(key);

		if (!cacheConfig.isActivatePagination()) {
			// case when no pagination required
			cacheData.loadToCache(key, value);
		} else {
			addOneValueToCache(key, value, cacheConfig);
		}
	}
	
	@Override
	public void loadListToCache(String key, List<?> values) {
		CacheElement cacheConfig = cacheElementDao.loadCacheElementByKey(key);

		if (!cacheConfig.isActivatePagination()) {
			// case when no pagination required
			cacheData.loadToCache(key, values);
		} else {
			values.forEach(value -> {
				addOneValueToCache(key, value, cacheConfig);
			});
			
		}
	}

	private void addOneValueToCache(String key, Object value, CacheElement cacheConfig) {
		
		Long cachePageSize = cacheConfig.getCachePageSize();
		Long numberOfPages = cacheConfig.getNumberOfPages();
		
		// Construct key of page and retrieve the list value
		String ConstructedKey = key.concat("_").concat(String.valueOf(numberOfPages - 1));
		Object cacheValue = cacheData.retrievFromCache(ConstructedKey);

		if (cacheValue == null) {
			createNewPageInCahce(key, value, numberOfPages, cacheConfig);
		}

		if (cacheValue instanceof List) {
			List cacheValueList = (List) cacheValue;
			if (cacheValueList.size() + 1 > cachePageSize) {
				createNewPageInCahce(key, value, numberOfPages, cacheConfig);
			} else {
				// add value to list and update cache
				cacheValueList.add(value);
				cacheData.loadToCache(ConstructedKey, cacheValueList);
			}
		}
	}

	private void createNewPageInCahce(String key, Object value, Long numberOfPages, CacheElement cacheConfig) {
		// create new Page in cache
		String nextConstructedKey = key.concat("_").concat(String.valueOf(numberOfPages));
		List nextPageValue = new ArrayList<Object>();
		nextPageValue.add(value);

		// add new page to cache
		cacheData.loadToCache(nextConstructedKey, nextPageValue);

		// update configuration
		cacheConfig.setNumberOfPages(numberOfPages + 1);
		cacheElementDao.save(cacheConfig);
	}

	@Override
	public Object retrievFromCache(String key) {
		CacheElement cacheConfig = cacheElementDao.loadCacheElementByKey(key);
		if (!cacheConfig.isActivatePagination()) {
			// case when no pagination required
			return cacheData.retrievFromCache(key);
		} else {
			List<Object> mergeList = new ArrayList<Object>();
			Long numberOfPages = cacheConfig.getNumberOfPages();
			for(int a = 0; a < numberOfPages; a++) {
				String retrieveConstructedKey = key.concat("_").concat(String.valueOf(a));
				Object cacheValue = cacheData.retrievFromCache(retrieveConstructedKey);
				if (cacheValue instanceof List) {
					List cacheValueList = (List) cacheValue;
					mergeList = merge(mergeList, cacheValueList);
				}
			}
			return mergeList;
		}
	}
	
	// Generic function to join	 two lists in Java
	public static<T> List<T> merge(List<T> list1, List<T> list2) {
		return Stream.of(list1, list2)
					   .flatMap(x -> x.stream())
					   .collect(Collectors.toList());
	}

	@Override
	public void mergeInList(String key, List<Object> valueToMerge) {
		cacheData.mergeInList(key, valueToMerge);
	}

}
