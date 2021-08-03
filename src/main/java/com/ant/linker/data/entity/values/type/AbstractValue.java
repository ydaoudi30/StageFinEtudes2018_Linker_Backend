package com.ant.linker.data.entity.values.type;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class AbstractValue implements Serializable{

	@Id
	private Long idValue;
	
}