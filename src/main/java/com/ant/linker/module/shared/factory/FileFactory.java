package com.ant.linker.module.shared.factory;

import org.springframework.stereotype.Service;

import com.ant.linker.data.entity.File;

@Service
public class FileFactory implements IFileFactory {

	@Override
	public File constructFileEntity(String fileId, String fileName, String fileType){
		File fileEntity = new File();
		fileEntity.setFileId(fileId);
		fileEntity.setFileName(fileName);
		fileEntity.setFileType(fileType);
		return fileEntity;
	}
}
