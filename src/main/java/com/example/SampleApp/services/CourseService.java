package com.example.SampleApp.services;

import com.example.SampleApp.entities.Chat;
import com.example.SampleApp.entities.Courses;

import java.util.List;

public interface CourseService {
    public List<Courses> getCourses();
    public Courses getCourse(Long courseId);
    public Courses addCourse(Courses course);
    public Chat getChat(Long chatId, Long agentId, String emailId, String domain, int flag);
    public Chat updateChat(Long chatId, Long agentId, String emailId, String domain, int cond, String productName);

}
