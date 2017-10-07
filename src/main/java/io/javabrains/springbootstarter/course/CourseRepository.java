package io.javabrains.springbootstarter.course;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by skushwah on 6/1/17.
 */

//Connects to database
public interface CourseRepository extends CrudRepository<Course, String> {
    //extending CrudRepository gives you the default methods of updating, deleting, and creating entities
    //Generic types -> Type of entity, type of id for entity

    //need to add extra methods if your doing interesting stuff like adding multiple things

    public List<Course> findByTopicId(String topicId); //since the name follows a particular format, Spring is smart
    //and automatically creates this method for you. Used to find course by topic




}
