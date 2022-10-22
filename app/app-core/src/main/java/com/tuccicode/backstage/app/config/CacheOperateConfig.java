package com.tuccicode.backstage.app.config;

import com.tuccicode.backstage.domain.core.cache.CacheOperate;
import com.tuccicode.backstage.domain.core.cache.RedisTemplateCacheOperate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author tucci.lee
 */
@Configuration
public class CacheOperateConfig {

    private final RedisTemplate redisTemplate;

    public CacheOperateConfig(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public CacheOperate cacheOperate() {
        return new RedisTemplateCacheOperate(redisTemplate);
    }

}
