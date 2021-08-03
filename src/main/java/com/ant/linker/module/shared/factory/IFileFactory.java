package com.ant.linker.module.shared.factory;

import com.ant.linker.data.entity.File;

public interface IFileFactory {

	public File constructFileEntity(String fileId, String fileName, String fileType);
}
