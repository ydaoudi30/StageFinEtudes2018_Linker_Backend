package com.ant.linker.module.product.importing;

public enum TransformImageStatus {
	
	TO_PROCESS("TO_PROCESS"),
	DONE("DONE"),
	ERROR("ERROR")
	;

	private String status;

	private TransformImageStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
