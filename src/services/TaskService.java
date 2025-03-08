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

    public static Task getTaskById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }
    public static void addTask(Task task) {
        if(task.getId() == 0) {
            task.setId(getMaxId()+1);
        }
        tasks.add(task);
        saveTasks();
    }

    public static void removeTask(Task task) {
        tasks.remove(task);
        saveTasks();
    }

    public static void saveTasks() {
        JsonHandler.writeTasksJson("tasks.json", tasks);
    }

    private static int getMaxId(){
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0);
    }
}
