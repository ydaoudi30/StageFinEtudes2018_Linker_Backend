package com.ant.linker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan("com.ant")
public class AntLinkerBackApplication extends SpringBootServletInitializer {
	
	@Autowired
	private EntitiesRegistrer entitiesRegistrer;

	public static void main(String[] args) {
		SpringApplication.run(AntLinkerBackApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AntLinkerBackApplication.class);
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public EntitiesRegistrer entitiesRegistrer() {
		EntitiesRegistrer entitiesRegistrer = new EntitiesRegistrer();
		entitiesRegistrer.registrEntities();
		
        return entitiesRegistrer;
    }
}