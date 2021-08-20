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
        return new ConcurrentMapCacheManager("allprojects", "latestprojects", "recentlyupdated", "featuredprojects");
    }

	@CacheEvict(allEntries = true, cacheNames = { "allprojects" })
	@Scheduled(fixedDelay = 1240000)
	public void cacheEvict() {
		log.info("Cache allprojects evicted");
	}
	
	
	@CacheEvict(allEntries = true, cacheNames = { "latestprojects" })
	@Scheduled(fixedDelay = 3200000)
	public void cacheEvict2() {
		log.info("Cache latestprojects evicted ");
	}
	
	@CacheEvict(allEntries = true, cacheNames = { "recentlyupdated"})
	@Scheduled(fixedDelay = 3600000)
	public void cacheEvict3() {
		log.info("Cache recentlyupdated evicted ");
	}
	
	@CacheEvict(allEntries = true, cacheNames = { "featuredprojects" })
	@Scheduled(fixedDelay = 2600000)
	public void cacheEvict4() {
		log.info("Cache featuredprojects evicted ");
	}
	
	
	
	
	@Scheduled(cron = "0 43 9 * * ?")
	public void restartTimer() {
		log.info("Restarting");
		BocApplication.restart();
	}
	

	
}
