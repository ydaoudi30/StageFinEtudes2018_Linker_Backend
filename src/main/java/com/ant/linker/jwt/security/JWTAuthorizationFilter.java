package com.ant.linker.jwt.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ant.linker.jwt.security.helper.IUserAuthoritiesHelper;
import com.ant.linker.jwt.security.helper.UserAuthoritiesHelper;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private IUserAuthoritiesHelper userAuthoritiesHelper;
	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
		this.userAuthoritiesHelper = new UserAuthoritiesHelper();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		String header = req.getHeader(SecurityConstants.HEADER_STRING);
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {
			// parse the token.
			String subject = Jwts.parser()
					.setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			
			Map<String, Set<GrantedAuthority>> buildUserAuthority = userAuthoritiesHelper.buildUserAuthority(subject);
			
			if (subject != null) {
				String user = new ArrayList<>(buildUserAuthority.entrySet()).get(0).getKey();
				Set<GrantedAuthority> authorities = new ArrayList<>(buildUserAuthority.entrySet()).get(0).getValue();
				return new UsernamePasswordAuthenticationToken(user, null, authorities);
			}
			return null;
		}
		return null;
	}

	public IUserAuthoritiesHelper getUserAuthoritiesHelper() {
		return userAuthoritiesHelper;
	}

	public void setUserAuthoritiesHelper(IUserAuthoritiesHelper userAuthoritiesHelper) {
		this.userAuthoritiesHelper = userAuthoritiesHelper;
	}
}