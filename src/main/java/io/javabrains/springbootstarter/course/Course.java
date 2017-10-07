package io.javabrains.springbootstarter.course;

import io.javabrains.springbootstarter.topic.Topic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by skushwah on 6/1/17.
 */

@Entity
public class Course {
    //Note, there is a Course table, but it is also part of the topic table (each topic has courses)
    //Need entity relationships

    @Id
    private String id;

    private String name;
    private String description;

    @ManyToOne
    private Topic topic; //Annotation for entity relationships. Indicates that many courses can map to one topic

    public Course() {

    }

    public Course(String id, String name, String description, String topicId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topic = new Topic(topicId, "", ""); //the topicId is the identifier used in comparison
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
