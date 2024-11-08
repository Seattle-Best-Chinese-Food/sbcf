package com.example.backstage.entity;

public class Item {
    private int id;
    private String name;
    private String description;
    private boolean published;
    public Item() {
    }

    public Item(int id, String name, String description, boolean published) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.published = published;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
