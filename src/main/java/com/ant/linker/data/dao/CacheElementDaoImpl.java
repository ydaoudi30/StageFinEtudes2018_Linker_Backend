package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.CacheElement;

@Component
public class CacheElementDaoImpl implements ICacheElementDao {

	@Override
	public CacheElement save(CacheElement cacheElement) {
		ofy().save().entity(cacheElement).now();
		return cacheElement;
	}

	@Override
	public List<CacheElement> loadAllCacheElements() {
		return ofy().load().type(CacheElement.class).list();
	}

	@Override
	public CacheElement loadCacheElementByKey(String key) {
		CacheElement cacheElement = ofy().load().type(CacheElement.class).filter("cacheKey", key).first().now();
		return cacheElement;
	}

	@Override
	public void deleteCacheElement(CacheElement cacheElement) {
		ofy().delete().entity(cacheElement).now();
	}

}
