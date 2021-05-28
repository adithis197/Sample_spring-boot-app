package com.example.SampleApp.services;

import com.example.SampleApp.entities.Courses;

import java.util.List;
import java.util.Map;

public interface CourseService {
    public List<Courses> getCourses();
  public Courses getCourse(Long courseId);
  public Courses addCourse(Courses course);


}
