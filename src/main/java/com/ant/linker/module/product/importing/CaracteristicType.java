package com.ant.linker.module.product.importing;

public enum CaracteristicType {

	SIMPLE("SIMPLE"), LIST("LIST"), MIN_MAX("MIN_MAX");

	private String type;

	private CaracteristicType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
