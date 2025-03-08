package models;

import models.enums.TaskType;

public class Task {
    private final int id;
    private int dayNum;
    private String title;
    private TaskType taskType;

    private String description;

    public Task(int id,int dayNum, String title, TaskType taskType, String description) {
        this.id = id;
        this.dayNum = dayNum;
        this.title = title;
        this.description = description;
        this.taskType = taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getType(){
        return taskType.getName();
    }
    public TaskType getTaskType() {
        return taskType;
    }

    public int getDayNum() {
        return dayNum;
    }
}
