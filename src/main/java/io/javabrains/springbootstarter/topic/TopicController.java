package io.javabrains.springbootstarter.topic;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by skushwah on 6/1/17.
 */
@RestController
public class TopicController {
    //Controllers provide responses to URL hits

    @Autowired
    private TopicService topicService; //topicService is a singleton that Spring created. In order to ask for the
    //singleton that Spring created in the beginning, you need to use the Autowired tag (this is a dependency injection)

    //Provides a response when someone hits the localhost:8080/topics URL
    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @RequestMapping(value = "/simpl/{id}", method = RequestMethod.POST)
    public String cool(@PathVariable("id") String id) throws UnirestException {

        HttpResponse request = Unirest.post("https://textanalysis-text-summarization.p.mashape.com/text-summarizer")
                .header("X-Mashape-Authorization", "nwAwI2JPcnmshwcmFIskhiyChn5kp1H2MG0jsnwjVduJ03YAw9")
                .header("Content-Type", "application/json")
                .body("{\"url\":\"http://en.wikipedia.org/wiki/Automatic_summarization\",\"text\":\"\",\"sentnum\":8}")
                .asJson();
        return request.getBody().toString();
    }

    @RequestMapping("/topics/{foo}")
    public Topic getTopic(@PathVariable("foo") String id) { //foo in the URL is mapped to the variable id
        return topicService.getTopic(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics") //this method is called when there is a post request to localhost:8080/topics
    public void addTopic(@RequestBody Topic topic) { //take the payload of the Post request and convert that to a springbootstarter.topic object
        //Should use Postman application to make Post requests (REST Api calls)
        //POSTMAN reminders:
        //Go to Body and hit raw
        //Make the JSON object in the body
        //Set header as Content-Type -> application/json

        topicService.addTopic(topic);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
    public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {

        topicService.updateTopic(id, topic);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
    public void deleteTopic(@PathVariable String id) {

        topicService.deleteTopic(id);
    }



}
