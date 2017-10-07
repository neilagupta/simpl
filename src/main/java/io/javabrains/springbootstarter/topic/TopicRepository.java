package io.javabrains.springbootstarter.topic;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by skushwah on 6/1/17.
 */

//Connects to database
public interface TopicRepository extends CrudRepository<Topic, String> {
    //extending CrudRepository gives you the default methods of updating, deleting, and creating entities
    //Generic types -> Type of entity, type of id for entity

    //need to add extra methods if your doing interesting stuff like adding multiple things




}
