package com.ant.linker.data.entity.general;

public enum ImportType {

	PRODUCT("products_"), CARACTERISTIC("characts_"), CATALOG("categories_");
	
	private String filePrefix;

	ImportType(String filePrefix) {
		this.filePrefix = filePrefix;
	}

	public String getFilePrefix() {
		return filePrefix;
	}

	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}
	
}
