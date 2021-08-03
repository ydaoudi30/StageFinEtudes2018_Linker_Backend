package com.ant.linker.configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;


@Configuration
public class AppEngineConfig {

    @Bean
    @ConditionalOnMissingBean
    public DatastoreService datastoreService() {
        return DatastoreServiceFactory.getDatastoreService();
    }
    
    
    

}