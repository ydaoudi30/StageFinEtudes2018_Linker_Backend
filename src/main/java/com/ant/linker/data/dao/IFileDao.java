package com.ant.linker.data.dao;

import com.ant.linker.data.entity.File;

public interface IFileDao {
	public File save(File file);
	
	public File loadFileByFileId(String fileId);
}
