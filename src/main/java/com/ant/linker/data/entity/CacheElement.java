package com.ant.linker.data.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class CacheElement {

	@Id
	private Long cacheElmtId;
	private Long numberOfPages = 0L;
	private String cacheKey;
	private Long cachePageSize;
	private boolean activatePagination;
	
	public Long getCacheElmtId() {
		return cacheElmtId;
	}
	public void setCacheElmtId(Long cacheElmtId) {
		this.cacheElmtId = cacheElmtId;
	}
	public Long getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(Long numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public String getCacheKey() {
		return cacheKey;
	}
	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
	public Long getCachePageSize() {
		return cachePageSize;
	}
	public void setCachePageSize(Long cachePageSize) {
		this.cachePageSize = cachePageSize;
	}
	public boolean isActivatePagination() {
		return activatePagination;
	}
	public void setActivatePagination(boolean activatePagination) {
		this.activatePagination = activatePagination;
	}
}
