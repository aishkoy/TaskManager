package services;

import models.Task;
import utils.JsonHandler;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private TaskService() {}
    private static final List<Task> tasks = JsonHandler.readTasksJson("tasks.json");

    public static List<Task> getTasks() {return tasks;}
    public static List<Task> getTasksByDay(int day) {
        List<Task> taskList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDayNum() == day) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    public static void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public static void removeTask(Task task) {
        tasks.remove(task);
        saveTasks();
    }

    public static void saveTasks() {
        JsonHandler.writeTasksJson("Tasks.json", tasks);
    }
}
