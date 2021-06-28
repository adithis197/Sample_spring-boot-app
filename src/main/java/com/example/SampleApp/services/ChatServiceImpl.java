package com.example.SampleApp.services;

import com.example.SampleApp.entities.Chat;
import com.example.SampleApp.entities.Courses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory. annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Service
@CacheConfig(cacheNames = {"Chat"})
public class ChatServiceImpl implements ChatService {

    List<Chat> queue;

    public ChatServiceImpl() {

        queue = new ArrayList<>();
        int i;
        for(i=1;i<=8000;i++){
            queue.add(new Chat("cust",i%20 ,"abc@gmail.com", "TV145", i+20, "Sony-TV","SONY","Display: 46 and Wifi connectivity for browsing", i));
        }
    }}
    //private static final Logger LOG = LoggerFactory.getLogger(ChatServiceImpl.class);




    //public List<Chat> getChats() {
       // return queue;
    //}
    /*@Cacheable(value = "chat", key = "{#chatId,#agentId}")
    @RequestMapping(value = "/{chatId}", method = RequestMethod.GET)
    public Chat getChat(Long chatId, Long agentId, String emailId, String domain) {
        LOG.info("Getting chat info for chatId - {}, agentId - {} ", chatId,agentId);
        Chat c = null;
        for (Chat chat : queue) {
            if (chat.getChatId() == chatId && chat.getAgentId() == agentId && chat.getEmailId() == emailId) {
                c = chat;
                break;
            }
        }
        return c;
    }


}*/

