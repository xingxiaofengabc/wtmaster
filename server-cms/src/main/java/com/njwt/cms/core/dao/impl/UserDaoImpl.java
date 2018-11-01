package com.njwt.cms.core.dao.impl;

import com.njwt.cms.core.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;


    /**
     * 在SpringBoot启动的环境中，会默认的创建两个RedisTemplate类型的对象。
     * 1 - RedisTemplate<Object, Object>
     * 2 - StringRedisTemplate<String, String>
     * RedisTemplate对象是由
     *     org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.RedisConfiguration
     *     创建的。
     * 如果在工程中，使用SpringBoot默认提供的RedisTemplate对象，会有错误结果：
     *     key数据，有类似乱码的前缀。 value数据无法录入到redis中。
     *     原因是：SpringBoot默认提供的RedisTemplate并没有设置keySerializer和valueSerializer。
     *     解决问题：
     *        通过Configuration来自定义初始化一个需要使用的RedisTemplate对象即可。
     *        @see com.njwt.cms.core.config.MyRedisConfiguration
     */
    @Repository
    public class UserDaoImpl implements UserDao {

        @Autowired
        private RedisTemplate<String, Object> redisTemplate;

        @Override
        public void set(String key, String value) {
            this.redisTemplate.opsForValue().set(key, value);
        }

        @Override
        public void set(String key, Object value) {
            this.redisTemplate.opsForValue().set(key, value);
        }

        @Override
        public Object get(String key) {
            return this.redisTemplate.opsForValue().get(key);
        }

        @Override
        public long ttl(String key) {
            return this.redisTemplate.getExpire(key);
        }

        @Override
        public void expire(String key, long times, TimeUnit timeUnit) {
            this.redisTemplate.expire(key, times, timeUnit);
        }

    }

