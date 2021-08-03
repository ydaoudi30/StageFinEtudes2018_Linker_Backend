package com.ant.linker.module.product.importing;

import java.util.Map;

public interface ICSVReaderHelper {
	
	void loadFiles(Map<String, String> filePaths);

	void addFileToLoad(String key, String filePath);

	String[] getNextRecordOf(String key);
}
