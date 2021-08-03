package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.ImportConfig;
import com.ant.linker.data.entity.general.GeneralStatus;
import com.ant.linker.data.entity.general.ImportType;


public interface IImportConfigDao {
	public ImportConfig save(ImportConfig config);
	public List<ImportConfig> loadByStatus(GeneralStatus status);
	public List<ImportConfig> loadByTypeAndStatus(ImportType type, GeneralStatus status);
	public ImportConfig loadFirstByTypeAndStatus(ImportType type, GeneralStatus status);
	public List<ImportConfig> loadByIndexAndStatus(Integer index, GeneralStatus status);
	public List<ImportConfig> loadAll();
}