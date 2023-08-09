package HomeSec;

import java.io.*;
import java.util.List;

public class TaskStorage {

    public static void saveTasks(List<ApplicationLogic.Task> tasks, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ApplicationLogic.Task> loadTasks(String filename) {
        List<ApplicationLogic.Task> tasks = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<ApplicationLogic.Task>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
