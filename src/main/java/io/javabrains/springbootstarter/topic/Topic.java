package io.javabrains.springbootstarter.topic;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by skushwah on 6/1/17.
 */

@Entity
public class Topic {
    //Entity tag tells JPA database that this is an object class that can be put into database
    //with instance variables as columns of table

    //For a table, each object needs a primary key (unique identifies). In this case, its the id

    @Id
    private String id;

    private String name;
    private String description;

    public Topic() {

    }

    public Topic(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
