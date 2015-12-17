/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flightmaster.configuration;

import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 *
 * @author yan
 */
@Configuration
@EnableCaching
public class CacheConfig {
    
    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cm){
       return new EhCacheCacheManager(cm);
    }
    
    @Bean
    public EhCacheManagerFactoryBean ehcache(){
      EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
      ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
      return ehCacheManagerFactoryBean;
    }
}
