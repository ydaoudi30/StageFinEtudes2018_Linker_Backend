package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Discussion;
import com.ant.linker.data.entity.File;
import com.googlecode.objectify.Ref;

@Component
public class FileDao implements IFileDao {
	
	@Override
	public File save(File file) {
		ofy().save().entity(file).now();
		return file;
	}

	@Override
	public File loadFileByFileId(String fileId) {
		// TODO Auto-generated method stub
		File loadedFile = ofy().load().type(File.class).filter("fileId", fileId).first().now();
		return loadedFile;
	}
}
