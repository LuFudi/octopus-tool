package com.octopus.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RedisUtils {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 使用spring-boot-starter-data-redis依赖后自动配置，继承RedisTemplate，KV皆为String类型
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     *对key执行自增操作
     * @param key
     * @param delta
     * @return
     */
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

}
