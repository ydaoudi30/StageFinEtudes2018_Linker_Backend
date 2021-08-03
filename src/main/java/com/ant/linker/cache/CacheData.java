package com.ant.linker.cache;

import java.util.List;

public interface CacheData {
	
	public void loadToCache(String key, Object value);
	public Object retrievFromCache(String key);
	public void configureExpirationTime(int nmbreOfHours);
	public void configureSetPolicy() ;
	public void mergeInList(String key, List<Object> valueToMerge);

}
