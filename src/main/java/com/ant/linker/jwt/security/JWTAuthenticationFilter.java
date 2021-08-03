package com.ant.linker.jwt.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ant.linker.data.entity.UserRole;
import com.ant.linker.jwt.security.helper.IUserAuthoritiesHelper;
import com.ant.linker.jwt.security.helper.UserAuthoritiesHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	private IUserAuthoritiesHelper userAuthoritiesHelper;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		this.userAuthoritiesHelper = new UserAuthoritiesHelper();
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			com.ant.linker.data.entity.User creds = new ObjectMapper().readValue(req.getInputStream(), com.ant.linker.data.entity.User.class);
			
			if(creds.getRoles().contains(UserRole.GUEST)){
				creds.setPassword(SecurityConstants.SECRET);
			}
			
			return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					creds.getEmail(), 
					creds.getPassword(), 
					new ArrayList<>()
				)
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		Map<String, Object> subjectMap = new HashMap<>();
		Collection<GrantedAuthority> authorities = ((User) auth.getPrincipal()).getAuthorities();
		
		subjectMap.put("user", ((User) auth.getPrincipal()).getUsername());
		subjectMap.put("roles", this.userAuthoritiesHelper.authoritiesToString(authorities));
		JSONObject subject = new JSONObject(subjectMap);
		
		String token = Jwts.builder()
				.setSubject(subject.toString())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
				.compact();

		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("Access-Control-Expose-Headers", SecurityConstants.HEADER_STRING);
	}

}
