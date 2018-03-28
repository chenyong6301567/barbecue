package com.qiaoge.barbecue.biz.config.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
// 标注启动了缓存
@EnableCaching
@PropertySource("classpath:application.properties")
public class CacheConfiguration extends CachingConfigurerSupport {
	Logger log = LoggerFactory.getLogger(CacheConfiguration.class);

	/*
	 * ehcache 主要的管理器
	 */
	@Bean(name = "localCacheCacheManager")
	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
		log.debug("create EhCacheCacheManager");
		return new EhCacheCacheManager(bean.getObject());
	}

	/*
	 * 据shared与否的设置,Spring分别通过CacheManager.create()或new
	 * CacheManager()方式来创建一个ehcache基地.
	 */
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		log.debug("create ehCacheManagerFactoryBean");
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cacheManagerFactoryBean.setShared(true);
		return cacheManagerFactoryBean;
	}

	@Bean
	public RedisCacheManager redisCacheManager(RedisTemplate<String, String> redisTemplate) {
		log.debug("create redisCacheManager");
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("remote-item", 60L);
		cacheManager.setExpires(map);

		// Number of seconds before expiration. Defaults to unlimited (0)
		cacheManager.setDefaultExpiration(100L); // Sets the default expire time
													// (in seconds)
		return cacheManager;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		log.debug("create redisTemplate");
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(factory);
		return redisTemplate;
	}

	@Bean
	@Primary
	public CacheManager cacheManager(RedisCacheManager redisCacheManager, EhCacheCacheManager localCacheCacheManager) {
		MulCacheManager cacheManager = new MulCacheManager();
		cacheManager.setRemoteCacheManager(redisCacheManager);
		cacheManager.setLocalCacheManager(localCacheCacheManager);
		return cacheManager;
	}

	public static class MulCacheManager implements CacheManager {
		private CacheManager remoteCacheManager;
		private CacheManager localCacheManager;

		@Override
		public Cache getCache(String name) {
			if (name.startsWith("remote-")) {
				return remoteCacheManager.getCache(name);
			}
			return localCacheManager.getCache(name);
		}

		@Override
		public Collection<String> getCacheNames() {
			// new java.util.concurrent.ConcurrentSkipListSet<>();
			Collection<String> cacheNames = new HashSet<String>();
			if (null != localCacheManager) {
				cacheNames.addAll(localCacheManager.getCacheNames());
			}

			if (null != remoteCacheManager) {
				cacheNames.addAll(remoteCacheManager.getCacheNames());
			}
			return cacheNames;
		}

		public CacheManager getRemoteCacheManager() {
			return remoteCacheManager;
		}

		public void setRemoteCacheManager(CacheManager remoteCacheManager) {
			this.remoteCacheManager = remoteCacheManager;
		}

		public CacheManager getLocalCacheManager() {
			return localCacheManager;
		}

		public void setLocalCacheManager(CacheManager localCacheManager) {
			this.localCacheManager = localCacheManager;
		}
	}
}
