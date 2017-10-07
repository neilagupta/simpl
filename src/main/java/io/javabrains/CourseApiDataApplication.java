package io.javabrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApiDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseApiDataApplication.class, args);
	}
}

/*
Application Flow

Course is an entity (object)
TopicController listens for requests at certain URLs.
When it sees a request, it is instantiated and it calls TopicService to fulfill that request.
TopicService is a singleton class created at runtime, and is always there to fulfill requests.
TopicService calls CourseRepository to get the information from the database and return it (TopicRepo is also a singleton)

Call localhost:8080/topics with a get request to see all topics (should initally be empty, and then you can add topics)


 */
