package io.javabrains.springbootstarter.course;

import io.javabrains.springbootstarter.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skushwah on 6/1/17.
 */
@RestController
public class CourseController {
    //Controllers provide responses to URL hits

    @Autowired
    private CourseService courseService; //topicService is a singleton that Spring created. In order to ask for the
    //singleton that Spring created in the beginning, you need to use the Autowired tag (this is a dependency injection)

    //Provides a response when someone hits the localhost:8080/topics URL
    @RequestMapping("/topics/{id}/courses")
    public List<Course> getAllCourses(@PathVariable("id") String id) {
        return courseService.getAllCourses(id);
    }

    @RequestMapping("/topics/{topicId}/courses/{foo}")
    public Course getCourse(@PathVariable("foo") String id) { //foo in the URL is mapped to the variable id
        return courseService.getCourse(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses") //this method is called when there is a post request to localhost:8080/topics
    public void addCourses(@RequestBody Course course, @PathVariable("topicId") String topicId) { //take the payload of the Post request and convert that to a springbootstarter.course object
        //Should use Postman application to make Post requests (REST Api calls)
        //POSTMAN reminders:
        //Go to Body and hit raw
        //Make the JSON object in the body
        //Set header as Content-Type -> application/json
        course.setTopic(new Topic(topicId, "", "")); //this allows us to get courses specific to a topic
        courseService.addCourse(course);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{id}")
    public void updateCourse(@RequestBody Course course, @PathVariable String id, @PathVariable String topicId) {
        course.setTopic(new Topic(topicId, "", ""));
        courseService.updateCourse(course);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{courseId}")
    public void deleteCourse(@PathVariable String courseId) {

        courseService.deleteCourse(courseId);
    }



}
