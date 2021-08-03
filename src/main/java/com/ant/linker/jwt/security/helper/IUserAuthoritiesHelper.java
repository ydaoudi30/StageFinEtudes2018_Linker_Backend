package com.ant.linker.jwt.security.helper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.ant.linker.data.entity.UserRole;

public interface IUserAuthoritiesHelper {
	
	public Map<String, Set<GrantedAuthority>> buildUserAuthority(String tokenSubject);
	
	public Set<GrantedAuthority> buildUserAuthorities(List<UserRole> userRoles);

	public void buildUserAuthority(List<UserRole> userRoles, String[] authorities);
	
	public Set<GrantedAuthority> buildUserAuthority(String[] authorities);

	public String[] authoritiesToString(Collection<GrantedAuthority> authorities);

}
