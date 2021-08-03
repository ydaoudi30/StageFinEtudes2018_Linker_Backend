package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.CacheElement;

public interface ICacheElementDao {
	public CacheElement save(CacheElement cacheElement);
	public List<CacheElement> loadAllCacheElements();
	public CacheElement loadCacheElementByKey(String key);
	public void deleteCacheElement(CacheElement cacheElement);
}
