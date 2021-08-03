package com.ant.linker.jwt.security.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.UserRole;

@Component
public class UserAuthoritiesHelper implements IUserAuthoritiesHelper{
	
	public UserAuthoritiesHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<GrantedAuthority> buildUserAuthorities(List<UserRole> userRoles){
		String[] authorities = new String[50];
		buildUserAuthority(userRoles, authorities);
		return buildUserAuthority(authorities);
	}

	public void buildUserAuthority(List<UserRole> userRoles, String[] authorities) {
		int size = userRoles.size() > 50 ? 50 : userRoles.size();
		for(int i = 0 ; i < size ; i++){
			authorities[i] = userRoles.get(i).getRole();
		}
	}
	
	public Set<GrantedAuthority> buildUserAuthority(String[] authorities) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (String userRole : authorities) {
			if(userRole == null) break;
			setAuths.add(new SimpleGrantedAuthority(userRole));
		}

		Set<GrantedAuthority> Result = new HashSet<GrantedAuthority>(setAuths);

		return Result;
	}

	@Override
	public Map<String, Set<GrantedAuthority>> buildUserAuthority(String tokenSubject) {
		
		Map<String, Set<GrantedAuthority>> userAuths = new HashMap<>();
		Set<GrantedAuthority> autorities = new HashSet<>();
		JSONObject tokeSubJson = new JSONObject(tokenSubject);
		
		String user = tokeSubJson.getString("user");
		JSONArray roleArray = tokeSubJson.getJSONArray("roles");
		roleArray.forEach(item -> {
		    String authority = (String) item;
		    autorities.add(new SimpleGrantedAuthority(authority));
		});
		
		userAuths.put(user, autorities);
		return userAuths;
	}
	
	@Override
	public String[] authoritiesToString(Collection<GrantedAuthority> roles) {
		String[] strRoles = new String[roles.size()];
		for(int i = 0; i < roles.size(); i++){
			strRoles[i] = new ArrayList<GrantedAuthority>(roles).get(i).getAuthority();
		}
		return strRoles;
	}
}
