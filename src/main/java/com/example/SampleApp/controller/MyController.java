package com.example.SampleApp.controller;
import com.example.SampleApp.entities.Chat;
import com.example.SampleApp.entities.Courses;
import com.example.SampleApp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
public class MyController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseService chatService;
    @GetMapping("/home")
    public String home(){
        return "This is home";
    }

    @GetMapping("/courses")
    public List<Courses> getCourses() {
        return this.courseService.getCourses();

    }
    private static JedisPool jedisPool;
    static {
        try {
            jedisPool = new JedisPool(new URI("http://" + "127.0.0.1" + ":" + 7000));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/courses/{courseId}")
    public Courses getCourse(@PathVariable String courseId){
        return  this.courseService.getCourse(Long.parseLong(courseId));
    }



    public Set keys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("*");
        }
    }


    public int checkIfCached(String key){
        Jedis jedis = jedisPool.getResource();
        int flag = 1;
        if(jedis.get(key) == null){
            flag = 0;
        }
        return flag;
    }

    public String getDomain(String emailId){
        String domain = emailId.substring(emailId.indexOf('@')+1); // domain is the substring of the email Id after '@' character
        return domain;
    }
    public boolean checkIfDomainExists(String domain){
        boolean flag = false;
        Set set = keys();  // this set contains all the keys in the cache
        String key;
        for( Object entry : set){ // for each key in the cache
            key = entry.toString();
            flag = key.endsWith(domain);
            if(flag){
                break;
            }
        }
        return flag ;
    }
    public int checkDomain(String domain){
        if(domain.equals("gmail.com")){
            return 1;
        }
        return 0;
    }

    @GetMapping("/chat/{chatId}/{agentId}/{emailId}")
    public Chat getChat(@PathVariable String chatId, @PathVariable String agentId, @PathVariable String emailId){
        Jedis jedis = jedisPool.getResource();
        String domain = getDomain(emailId);
        //jedis.set("chats::"+chatId+","+agentId+"{"+domain+"}","this is the value");
        //int flag = checkIfDomainExists(domain)? 1:0;
        int check = checkDomain(domain);
        domain = "{"+domain+"}";
        return  this.chatService.getChat(Long.parseLong(chatId),Long.parseLong(agentId), emailId, domain, check);
    }

    @PatchMapping("/chat/{chatId}/{agentId}/{emailId}/{productName}")  // where productName is to be updated for the key composed of the other three attributes
    public Chat updateChat(@PathVariable String chatId, @PathVariable String agentId, @PathVariable String emailId, @PathVariable String productName){
        String domain = getDomain(emailId);
        int flag = checkDomain(domain);
        int cond1 = 1;
        domain = "{"+domain+"}";
        //int cond = checkIfCached("chats::"+chatId+","+agentId+","+domain);
        /*if(flag == 1 && cond == 0){
            cond1 = 0;
        }*/
        return  this.chatService.updateChat(Long.parseLong(chatId),Long.parseLong(agentId), emailId, domain, cond1, productName);
    }

    @PostMapping("/courses")
    public Courses addCourse(@RequestBody Courses course){
        return this.courseService.addCourse(course);

    }}

