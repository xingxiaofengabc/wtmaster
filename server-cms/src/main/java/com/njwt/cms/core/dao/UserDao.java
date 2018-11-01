package com.njwt.cms.core.dao;

import java.util.concurrent.TimeUnit;

public interface UserDao {void set(String key, String value);
    void set(String key, Object value);
    Object get(String key);
    long ttl(String key);
    void expire(String key, long times, TimeUnit timeUnit);

}
