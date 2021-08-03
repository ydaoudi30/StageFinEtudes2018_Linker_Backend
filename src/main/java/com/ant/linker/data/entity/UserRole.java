package com.ant.linker.data.entity;

import java.io.Serializable;

public enum UserRole implements Serializable {

	ADMIN("ADMIN"), COMMERCIAL("COMMERCIAL"), CUSTOMER("CUSTOMER"), GUEST("GUEST");

	public String role = "";

	UserRole(String role){
	    this.role = role;
	  }

	public String getRole() {
		return role;
	}
	
}
