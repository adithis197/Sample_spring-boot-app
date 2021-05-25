package com.example.SampleApp.controller;

import com.example.SampleApp.entities.Courses;
import com.example.SampleApp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private CourseService courseService;
    @GetMapping("/home")
    public String home(){
        return "This is home";
    }

    //Get courses list
    @GetMapping("/courses")
    public List<Courses> getCourses() {
        return this.courseService.getCourses();

    }
    @GetMapping("/courses/{courseId}")
    public Courses getCourse(@PathVariable String courseId){
        return  this.courseService.getCourse(Long.parseLong(courseId));
    }
    @PostMapping("/courses")
    public Courses addCourse(@RequestBody Courses course){
        return this.courseService.addCourse(course);

    }
}
