package com.company;

import java.util.Date;

public class TaskObject {
    // define the properties
    private int id;
    private String username;
    private String task;
    private Date deadline;
    private boolean acomplishment;

    //constructor

    public TaskObject() {
    }

    public TaskObject(int id, String username, String task, Date deadline, boolean acomplishment) {
        this.id = id;
        this.username = username;
        this.task = task;
        this.deadline = deadline;
        this.acomplishment = acomplishment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isAcomplishment() {
        return acomplishment;
    }

    public void setAcomplishment(boolean acomplishment) {
        this.acomplishment = acomplishment;
    }

    @Override public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", task='" + task + '\'' +
                ", deadline=" + deadline +
                ", acomplishment=" + acomplishment +
                '}';
    }


}
