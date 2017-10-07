package io.javabrains.springbootstarter.topic;

import com.aylien.textapi.TextAPIException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aylien.textapi.TextAPIClient;
import com.aylien.textapi.parameters.*;
import com.aylien.textapi.responses.*;

import java.net.MalformedURLException;
import java.net.URL;
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

    @RequestMapping(value = "/simpl/url", method = RequestMethod.POST)
    public String cool(@RequestParam("link") String link) throws TextAPIException, MalformedURLException {

        TextAPIClient client = new TextAPIClient("17f4651a", "96c204d7f5cf1c91d23f64ac0fa55408");
        SentimentParams.Builder builder = SentimentParams.newBuilder();
        builder.setText("John is a very good football player");
        SummarizeParams.Builder builder1 = SummarizeParams.newBuilder();
        builder1.setUrl(new URL(link));
        builder1.setNumberOfSentences(5);
        Summarize summarize = client.summarize(builder1.build());

        //Sentiment sentiment = client.sentiment(builder.build());
        String x = "";
        for (int i = 0; i < summarize.getSentences().length; i++) {
            x = x + " " + summarize.getSentences()[i];
        }

        System.out.println(x);
        return x;
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
