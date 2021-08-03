package com.ant.linker.jwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers(SecurityConstants.SIGN_UP_URL).permitAll()
			.antMatchers(SecurityConstants.ADMIN).permitAll()
			.antMatchers(SecurityConstants.PRODUCT_URL).permitAll()
			.antMatchers(SecurityConstants.CATALOG_IMPORT_API).permitAll()
			.antMatchers(SecurityConstants.CUSTOMER_API).permitAll()
			.antMatchers(SecurityConstants.COMMERCIAL_API).permitAll()
			.antMatchers(SecurityConstants.GUEST_API).permitAll()
			/*.antMatchers(SecurityConstants.COMMERCIAL_API).hasAuthority(UserRole.COMMERCIAL.getRole())
			.antMatchers(SecurityConstants.ADMIN_API).hasAuthority(UserRole.ADMIN.getRole())
			.antMatchers(SecurityConstants.CUSTOMER_API).hasAuthority(UserRole.CUSTOMER.getRole())*/
			.antMatchers(SecurityConstants.GET_CATEGORIES).permitAll()
			.antMatchers(SecurityConstants.GET_CUST_CATEGORIES).permitAll()
			.antMatchers(SecurityConstants.GET_NEW_PRODUCTS).permitAll()
			.antMatchers(SecurityConstants.GET_FILTER_PRODUCTS).permitAll()
			.antMatchers(SecurityConstants.GET_PRODUCTS_BYREF).permitAll()
			.antMatchers(SecurityConstants.GET_IMG_TEST).permitAll()
			.antMatchers(SecurityConstants.CRON_API).permitAll()
			.antMatchers(SecurityConstants.USER_SIGN_UP_API).permitAll()
			.antMatchers(SecurityConstants.ADMIN_CACHE).permitAll()
			.anyRequest().authenticated().and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
}
