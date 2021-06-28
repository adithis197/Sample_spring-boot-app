package com.example.SampleApp;

import io.lettuce.core.RedisClient;
import jdk.nashorn.internal.runtime.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@SpringBootApplication
@EnableCaching
@RestController
public class SampleAppApplication<list> {
	@GetMapping("/message")
	public String getMessage(){
		return "Hello universe!";
	}





	/*private static JedisPool jedisPool;

	private void RedisClients(String ip, int port) {
		try {
			if (jedisPool == null) {
				jedisPool = new JedisPool(new URI("http://" + "localhost" + ":" + 6379));
			}
		} catch (URISyntaxException e) {

		}
	}

	private static volatile RedisClient instance = null;

	public static RedisClient getInstance(String ip, final int port) {
		if (instance == null) {
			synchronized (RedisClient.class) {

				//if (instance == null) instance = new RedisClients("localhost", 6379);
			}
		}
		return instance;
	}


	public Set keys() {
		try (Jedis jedis = jedisPool.getResource()) {
			return jedis.keys("*");
		} catch (Exception ex) {

		}
		return new Set() {
			@Override
			public int size() {
				return 0;
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public boolean contains(Object o) {
				return false;
			}

			@Override
			public Iterator iterator() {
				return null;
			}

			@Override
			public Object[] toArray() {
				return new Object[0];
			}

			@Override
			public Object[] toArray(Object[] a) {
				return new Object[0];
			}

			@Override
			public boolean add(Object o) {
				return false;
			}

			@Override
			public boolean remove(Object o) {
				return false;
			}

			@Override
			public boolean containsAll(Collection c) {
				return false;
			}

			@Override
			public boolean addAll(Collection c) {
				return false;
			}

			@Override
			public boolean retainAll(Collection c) {
				return false;
			}

			@Override
			public boolean removeAll(Collection c) {
				return false;
			}

			@Override
			public void clear() {

			}
		};
	}
	Set set = keys();
		/*@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	RedisTemplate<Long, Courses> redisTemplate() {
		RedisTemplate<Long, Courses> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(SampleAppApplication.class, args);
	}

}
