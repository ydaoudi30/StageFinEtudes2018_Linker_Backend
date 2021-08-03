package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.ImportConfig;
import com.ant.linker.data.entity.general.GeneralStatus;
import com.ant.linker.data.entity.general.ImportType;

@Component
public class ImportConfigDao implements IImportConfigDao {

	@Override
	public ImportConfig save(ImportConfig config) {
		ofy().save().entity(config);
		return config;
	}

	@Override
	public List<ImportConfig> loadByStatus(GeneralStatus status) {
		List<ImportConfig> configList = ofy().load().type(ImportConfig.class).filter("status", status).list();
		return configList;
	}

	@Override
	public List<ImportConfig> loadByTypeAndStatus(ImportType type, GeneralStatus status) {
		List<ImportConfig> configList = ofy().load().type(ImportConfig.class).filter("status", status).filter("importType", type).list();
		return configList;
	}

	@Override
	public List<ImportConfig> loadAll() {
		List<ImportConfig> configList = ofy().load().type(ImportConfig.class).list();
		return configList;
	}

	@Override
	public List<ImportConfig> loadByIndexAndStatus(Integer index, GeneralStatus status) {
		List<ImportConfig> configList = ofy().load().type(ImportConfig.class).filter("status", status).filter("index", index).list();
		return configList;
	}

	@Override
	public ImportConfig loadFirstByTypeAndStatus(ImportType type, GeneralStatus status) {
		return ofy().load().type(ImportConfig.class).filter("status", status).filter("importType", type).first().now();
	}

}
