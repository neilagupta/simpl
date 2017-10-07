package io.javabrains.springbootstarter.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skushwah on 6/1/17.
 */

@Service
public class CourseService {
    //Is a singleton and is instiantiated once when the TomCat server is run, and runs throughout the duration of the TomCat server

    @Autowired
    private CourseRepository courseRepository; //see comment in TopicController for explanation of Autowired


    public List<Course> getAllCourses(String topicId) {
        List<Course> courses = new ArrayList<>();
        courseRepository.findByTopicId(topicId);
        return courses;
    }

    public Course getCourse(String id) {
        return courseRepository.findOne(id);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }


    public void updateCourse(Course course) {
        courseRepository.save(course); //smart save operation updates if it sees course already exsists
    }

    public void deleteCourse(String id) {
        courseRepository.delete(id);
    }
}
