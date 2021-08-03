package com.ant.linker.data.entity;

import com.ant.linker.data.entity.general.GeneralStatus;
import com.ant.linker.data.entity.general.ImportType;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index
public class ImportConfig {

	@Id
	private Long importConfigId;
	private String filePath;
	private Integer index;	
	@Load
	private GeneralStatus status;
	@Load
	private ImportType importType;
	
	public Long getImportConfigId() {
		return importConfigId;
	}
	public void setImportConfigId(Long importConfigId) {
		this.importConfigId = importConfigId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public GeneralStatus getStatus() {
		return status;
	}
	public void setStatus(GeneralStatus status) {
		this.status = status;
	}
	
	public ImportType getImportType() {
		return importType;
	}
	public void setImportType(ImportType importType) {
		this.importType = importType;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public ImportConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
