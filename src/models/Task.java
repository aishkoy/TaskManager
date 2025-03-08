package models;

import models.enums.TaskType;

public class Task {
    private int id;
    private int dayNum;
    private String title;
    private TaskType taskType;

    private String description;

    public Task(int dayNum, String title, TaskType taskType, String description) {
        this.dayNum = dayNum;
        this.title = title;
        this.description = description;
        this.taskType = taskType;
    }
    public Task(int id,int dayNum, String title, TaskType taskType, String description) {
        this(dayNum, title, taskType, description);
        this.id = id;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }
    public int getDayNum() {
        return dayNum;
    }
}
