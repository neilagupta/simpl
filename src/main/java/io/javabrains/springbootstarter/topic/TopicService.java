package io.javabrains.springbootstarter.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by skushwah on 6/1/17.
 */

@Service
public class TopicService {
    //Is a singleton and is instiantiated once when the TomCat server is run, and runs throughout the duration of the TomCat server

    @Autowired
    private TopicRepository topicRepository; //see comment in TopicController for explanation of Autowired


    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add); //lambda expression for iterating over iterable (topicRepo.findAll()) and adding
        //each element to the arraylist
        return topics;
    }

    public Topic getTopic(String id) {
        return topicRepository.findOne(id);
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }


    public void updateTopic(String id, Topic topic) {
        topicRepository.save(topic); //smart save operation updates if it sees topic already exsists
    }

    public void deleteTopic(String id) {
        topicRepository.delete(id);
    }
}
