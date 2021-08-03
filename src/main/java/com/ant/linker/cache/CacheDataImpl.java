package com.ant.linker.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import org.springframework.stereotype.Service;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;

@Service
public class CacheDataImpl implements CacheData {

	private Cache cache;

	public CacheDataImpl() {
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = cacheFactory.createCache(Collections.emptyMap());
		} catch (CacheException e) {
			// ...
		}
	}

	public void loadToCache(String key, Object value) {
		// Put the value into the cache.
		cache.put(key, value);
	}
	
	public void mergeInList(String key, List<Object> valueToMerge) {
		cache.merge(key, valueToMerge, (oldList, newList) -> {
			return Stream.concat(((Collection<Object>) oldList).stream(), ((Collection<Object>) newList).stream()).collect(Collectors.toList());
		});
	}

	public Object retrievFromCache(String key) {
		// Get the value from the cache.
		return (Object) cache.get(key);

	}

	public void configureExpirationTime(int nmbreOfHours) {
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			Map<Object, Object> properties = new HashMap<>();
			properties.put(GCacheFactory.EXPIRATION_DELTA, TimeUnit.HOURS.toSeconds(nmbreOfHours));
			cache = cacheFactory.createCache(properties);
		} catch (CacheException e) {
			// ...
		}
	}

	public void configureSetPolicy() {
		Map<Object, Object> properties = new HashMap<>();
		properties.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
	}

}
