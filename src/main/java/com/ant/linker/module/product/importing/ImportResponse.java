package com.ant.linker.module.product.importing;

import java.util.ArrayList;
import java.util.List;

import com.ant.linker.data.entity.general.ErrorCause;
import com.ant.linker.data.entity.general.GeneralStatus;
import com.ant.linker.data.entity.general.ImportType;

public class ImportResponse {

	private GeneralStatus status;
	private List<ErrorCause> causes = new ArrayList<>();
	
	public GeneralStatus getStatus() {
		return status;
	}

	public void setStatus(GeneralStatus status) {
		this.status = status;
	}
	public List<ErrorCause> getCauses() {
		return causes;
	}

	public void setCauses(List<ErrorCause> causes) {
		this.causes = causes;
	}

	public void addCause(ErrorCause cause) {
		causes.add(cause);
	}
	
	public void removeCause(ErrorCause cause) {
		causes.remove(cause);
	}
	
	public ImportResponse(GeneralStatus status, ErrorCause firstCause) {
		super();
		this.status = status;
		this.causes.add(firstCause);
	}
	
	public ImportResponse(GeneralStatus status) {
		super();
		this.status = status;
	}

	public ImportResponse() {
		super();
	}
	
}
