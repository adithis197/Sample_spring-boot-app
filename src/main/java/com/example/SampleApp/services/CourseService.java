package com.example.SampleApp.services;

import com.example.SampleApp.entities.Courses;

import java.util.List;

public interface CourseService {
// for coupling
    public List<Courses> getCourses();
    public Courses getCourse(Long courseId);
    public Courses addCourse(Courses course);

}
