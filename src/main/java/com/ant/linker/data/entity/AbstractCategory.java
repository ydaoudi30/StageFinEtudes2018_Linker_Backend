
package com.ant.linker.data.entity;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public abstract class AbstractCategory implements Serializable {
	@Id
	Long idAbstr;
	private String ref ;
	private String label;

	public Long getIdAbstr() {
		return idAbstr;
	}

	public void setIdAbstr(Long idAbstr) {
		this.idAbstr = idAbstr;
	}

	

	public AbstractCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public AbstractCategory(String label) {
		super();
		this.label = label;
	}

	public abstract boolean isFinal();

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

}
