package com.example.SampleApp.services;

import com.example.SampleApp.entities.Chat;
import com.example.SampleApp.entities.Courses;

import java.util.List;
import java.util.Map;

public interface CourseService {
    public List<Courses> getCourses();
   public Courses getCourse(Long courseId, Long duration);
  public Courses addCourse(Courses course);
    public Chat getChat(Long chatId, Long agentId, String emailId, String domain, int flag);



}
