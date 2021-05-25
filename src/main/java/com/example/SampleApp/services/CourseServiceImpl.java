package com.example.SampleApp.services;

import com.example.SampleApp.entities.Courses;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    List<Courses> list;

    public CourseServiceImpl() {
        list = new ArrayList<>();
        list.add(new Courses(145, "Java", "Basics of Java"));
        list.add(new Courses(12, "Spring boot framework", "Spring boot basics"));
    }

    @Override
    public List<Courses> getCourses() {
        return list;
    }
    public Courses getCourse(Long courseId) {
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
