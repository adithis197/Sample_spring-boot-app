package com.example.SampleApp.controller;
import com.example.SampleApp.entities.Chat;
import com.example.SampleApp.entities.Courses;
import com.example.SampleApp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
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
    @GetMapping("/courses/{courseId}/{duration}")
    public Courses getCourse(@PathVariable String courseId, @PathVariable String duration){
        return  this.courseService.getCourse(Long.parseLong(courseId),Long.parseLong(duration));
    }
    private static JedisPool jedisPool;
    static {
        try {
            jedisPool = new JedisPool(new URI("http://" + "localhost" + ":" + 6379));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Set keys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("*");
        }
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

    @GetMapping("/chat/{chatId}/{agentId}/{emailId}")
    public Chat getChat(@PathVariable String chatId, @PathVariable String agentId, @PathVariable String emailId){
        String domain = getDomain(emailId);
        int flag = checkIfDomainExists(domain)? 1:0;
        return  this.chatService.getChat(Long.parseLong(chatId),Long.parseLong(agentId), emailId, domain, flag);

    }

    @PostMapping("/courses")
    public Courses addCourse(@RequestBody Courses course){
        return this.courseService.addCourse(course);

    }}

