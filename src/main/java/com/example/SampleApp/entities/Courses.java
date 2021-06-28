package com.example.SampleApp.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
//@Entity
public class Courses implements Serializable {

 @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", duration='" + duration + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private long id;
    private long duration;
    private String title; // certain titles caching
    private String description;

    public Courses(long id, long duration, String title, String description) {
        this.id = id;
        this.duration = duration;
        this.title = title;
        this.description = description;
    }

    public Courses(){
        super();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }



}
