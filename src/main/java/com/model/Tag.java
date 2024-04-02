package com.model;
public class Tag {
    private Long id; // Will be set by the database (SERIAL)
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // Getters, setters, and constructors
}