//
package HomeSec;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationLogic {
    private List<Event> events;
    private List<Task> tasks;
    private List<Group> groups;
    private List<User> users;

    // File name to store tasks data
    private String tasksFileName = "tasks.dat";

    public ApplicationLogic() {
        events = new ArrayList<>();
        tasks = new ArrayList<>();
        groups = new ArrayList<>();
        users = new ArrayList<>();

        // Load tasks from file when ApplicationLogic is created
        loadTasksFromFile();
    }

    public void createEvent(String name) {
        events.add(new Event(name));
    }

    public void createTask(String name, String description) {
        tasks.add(new Task(name, description));
        saveTasksToFile(); // Save tasks to file after adding
    }

    public void createGroup(String name) {
        groups.add(new Group(name));
    }

    public void createUser(String name) {
        users.add(new User(name));
    }

    // Method to save tasks to a file using object serialization
    void saveTasksToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(tasksFileName))) {
            outputStream.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        public void setupShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveTasksToFile(); // Save tasks to file
        }));
    }

    // Method to load tasks from a file using object deserialization
    void loadTasksFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(tasksFileName))) {
            tasks = (List<Task>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // No saved tasks or error in loading, continue with empty tasks list
        }
    }

    class Event {
        private String name;
        private List<User> attendees;

        public Event(String name) {
            this.name = name;
            attendees = new ArrayList<>();
        }

        public String getName() {
            return name;
        }
    }

    class Task {
        private String name;
        private String description; // Added description field

        public Task(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }

    class Group {
        private String name;
        private List<User> members;

        public Group(String name) {
            this.name = name;
            members = new ArrayList<>();
        }

        public String getName() {
            return name;
        }
    }

    class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void updateTasks(List<Task> updatedTasks) {
        tasks = updatedTasks;
    }

    public void updateEvents(List<Event> updatedEvents) {
        events = updatedEvents;
    }
}
