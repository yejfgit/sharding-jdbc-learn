package com.netease.okr.redis;
/*package com.netease.okr.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


public class RedisClient {

	@Autowired
	private StringRedisTemplate redisTemplate;// redis操作模板

	public void put(String key, String value) {
		if (key == null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().put(key, key, value);

	}

	public void put(String key, Object value) {
		if (key == null || "".equals(key)) {
			return;
		}
		redisTemplate.opsForHash().put(key, key, JSON.toJSON(value));

	}

	public <T> T get(String key, Class<T> className) {
		Object obj = redisTemplate.opsForHash().get(key, key);
		if (obj == null) {
			return null;
		}
		return  JSON.parseObject("" + obj, className);
	}

	public String get(String key) {
		Object obj = redisTemplate.opsForHash().get(key, key);
		if (obj == null) {
			return null;
		} else {
			return String.valueOf(obj);
		}
	}

}
*/