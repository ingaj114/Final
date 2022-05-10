package com.company;

import java.util.Date;

public class ToDoObject {
    private int id;
    private String item;
    private Date accomplish;
    private String notes;

    public ToDoObject(int id, String item, Date accomplish, String notes) {
        this.id = id;
        this.item = item;
        this.accomplish = accomplish;
        this.notes = notes;
    }
    public ToDoObject() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Date getAccomplish() {
        return accomplish;
    }

    public void setAccomplish(Date accomplish) {
        this.accomplish = accomplish;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
