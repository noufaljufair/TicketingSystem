package com.TicketingSystem.security;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {
     private final StringRedisTemplate stringRedisTemplate;
     private ValueOperations valueOperations;

    public RedisUtils(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
        valueOperations = this.stringRedisTemplate.opsForValue();
    }

    public void save(String key, Date date){
        long duration = getRemainingTimeLeft(date);
        System.out.println(new Date().toString());
        valueOperations.set(key, new Date().toString(),duration, TimeUnit.MILLISECONDS);
    }

    public boolean hasKey(String key){
        return valueOperations.getOperations().hasKey(key);
    }
    private long getRemainingTimeLeft(Date date) {
        return Math.abs(date.getTime() - (new Date()).getTime());
    }

}
