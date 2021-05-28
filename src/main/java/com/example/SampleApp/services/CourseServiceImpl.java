package com.example.SampleApp.services;

import com.example.SampleApp.entities.Courses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = {"Courses"})
public class CourseServiceImpl implements CourseService {

    List<Courses> list;

    public CourseServiceImpl(RedisTemplate<Long,Courses> redisTemplate) {
       
        list = new ArrayList<>();
        list.add(new Courses(145, "Java", "Basics of Java"));
        list.add(new Courses(12, "Spring boot framework", "Spring boot basics"));
    }
    private static final Logger LOG = LoggerFactory.getLogger(CourseServiceImpl.class);




    public List<Courses> getCourses() {
        return list;
    }
    @Cacheable(value = "courses", key = "#courseId")
    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
    public Courses getCourse(Long courseId) {
        LOG.info("Getting course info for id {}", courseId);
        Courses c = null;
        for (Courses course : list) {
            if (course.getId() == courseId) {
                c = course;
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

}
