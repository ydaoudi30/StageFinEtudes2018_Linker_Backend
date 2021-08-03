package com.ant.linker.module.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ant.linker.data.entity.File;
import com.ant.linker.module.shared.factory.IFileFactory;

@Service
public class FileServiceImpl implements IFileService {
	
	@Value("${host.url}")
	private String hostUrl;
	
	@Value("${image.server.host.url}")
	private String imgServerHostUrl;

	@Autowired
	private IFileFactory fileFactory;

	@Override
	public String getFileUrl(File file) {
		if (file.getFileId() == null || file.getFileId().isEmpty())
			return "assets/images/box.png";

		StringBuilder stBuilder = new StringBuilder();
		stBuilder.append(imgServerHostUrl).append(file.getFileId());
		return stBuilder.toString();
	}

}
