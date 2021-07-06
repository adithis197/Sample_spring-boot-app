package com.example.SampleApp.services;
import com.example.SampleApp.entities.Chat;
import com.example.SampleApp.entities.Courses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory. annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.JedisShardInfo;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CacheManager cacheManager;
    List<Courses> list;
    List<Chat> queueChat;

    public CourseServiceImpl() {
        list = new ArrayList<>();
        int i;
        for(i=1;i<=8000;i++){
            list.add(new Courses(i,i+3, "Random java course", "Basics of Java plus spring framework. Build applications in Java"));
        }
        queueChat = new ArrayList<>();
        int j;
        for(j=1;j<=8000;j++){
            queueChat.add(new Chat("cust"+j,j%20 ,"abc@gmail.com", "TV145", j+20, "Sony-TV","SONY","Display: 46 and Wifi connectivity for browsing", j));
        }

    }
    private static final Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);

    public List<Courses> getCourses() {
        return list;
    }
    @Cacheable(value = "courses", key = "{#courseId}")
    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
    public Courses getCourse(Long courseId) {
        LOG.info("Getting course info for id {}", courseId);
        Courses c = null;
        Collection<String> caches = cacheManager.getCacheNames();
        System.out.println(caches);
        for (Courses course : list) {
            if (course.getId() == courseId) {
                c = course;
                break;
            }
        }
        return c;
    }
    @Cacheable(cacheNames = "chats", key = "{#chatId,#agentId,#domain}", condition = "#check == 1" )
    @RequestMapping(value = "/{chatId}", method = RequestMethod.GET)
    public Chat getChat(Long chatId, Long agentId, String emailId, String domain, int check) {
        LOG.info("Getting chat info for chatId - {}, agentId - {} ", chatId,agentId);
        Chat c = null;
        for (Chat chat : queueChat) {
            if (chat.getChatId() == chatId && chat.getAgentId() == agentId) {
                c = chat;
                break;
            }
        }
        return c;
    }

    @CachePut(cacheNames = "chats", key = "{#chatId,#agentId,#domain}", unless = "#cond == 0")
    @RequestMapping(value = "/{chatId}", method = RequestMethod.PATCH)
    public Chat updateChat(Long chatId, Long agentId, String emailId, String domain, int cond, String productName){
        LOG.info("Updating chat info for chatId - {}, agentId - {} ", chatId,agentId);
        Chat c = null;
        for (Chat chat : queueChat) {
            if (chat.getChatId() == chatId && chat.getAgentId() == agentId) {
                chat.setProductName(productName);
                c = chat;
                break;
            }
        }
        return c;
    }



    @Override
    public Courses addCourse(Courses course) {
        list.add(course);
        return course;
    }
        //cacheManager.getCache("courses").evict("3,6"); this can be used if we want to evict a particular key
        //cacheManager.getCache("courses").clear(); this will evict all keys

}
