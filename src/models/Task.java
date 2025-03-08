package models;

import models.enums.TaskType;

public class Task {
    private final int id;
    private int dayNum;
    private String title;
    private String type;
    private String description;

    private String colorCode;

    private transient TaskType taskType;

    public Task(int id,int dayNum, String title, String type, String description) {
        this.id = id;
        this.dayNum = dayNum;
        this.title = title;
        this.type = type;
        this.description = description;
        this.taskType = TaskType.valueOf(type);
        this.colorCode = taskType.getColorCode();
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
        this.type = taskType.getName();
        this.colorCode = taskType.getColorCode();
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

    public TaskType getTaskType() {
        return taskType;
    }

    public String getType() {
        return type;
    }

    public int getDayNum() {
        return dayNum;
    }

    public String getColorCode() {
        return colorCode;
    }
}
