package com.buildingon.cardano.boc;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
public class CacheConfiguration {
	private static final Logger log = LoggerFactory.getLogger(CacheConfiguration.class);
	
    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager("allprojects", "latestprojects");
    }

	@CacheEvict(allEntries = true, cacheNames = { "allprojects" })
	@Scheduled(fixedDelay = 120000)
	public void cacheEvict() {
		log.info("Cache allprojects evicted");
	}
	
	
	@CacheEvict(allEntries = true, cacheNames = { "latestprojects" })
	@Scheduled(fixedDelay = 240000)
	public void cacheEvict2() {
		log.info("Cache latestprojects evicted ");
	}
	
	@Scheduled(cron = "0 40 8 * * ?")
	public void restartTimer() {
		log.info("Restarting");
		BocApplication.restart();
	}
	

	
}
