package HomeSec;

import java.io.Serializable;

// Task class represents a task with a name and description
public class Task implements Serializable {
    private String name;
    private String description;

    // Constructor to initialize a Task object with name and description
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getter method to access the name of the task
    public String getName() {
        return name;
    }

    // Getter method to access the description of the task
    public String getDescription() {
        return description;
    }
}

