package com.example.SampleApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisShardInfo;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
@RestController
public class SampleAppApplication<list> {
	@GetMapping("/message")
	public String getMessage(){
		return "Hello universe!";
	}
	public static void main(String[] args) {
		SpringApplication.run(SampleAppApplication.class, args);
	}

}
