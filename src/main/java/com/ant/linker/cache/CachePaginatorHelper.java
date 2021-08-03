package com.ant.linker.cache;

import java.util.List;

public interface CachePaginatorHelper{


	public void loadToCache(String key, Object value);
	
	public Object retrievFromCache(String key);
	
	public void mergeInList(String key, List<Object> valueToMerge);

	void loadListToCache(String key, List<?> values);
}
