package com.model;

import java.util.ArrayList;
import java.util.List;

public class Image {
    private Long id;
    private String base64;
    private List<Tag> tags; // Changed to List<Tag>

    // Constructor
    public Image() {
        this.tags = new ArrayList<>();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public List<Tag> getTags() { // Return type changed to List<Tag>
        return tags;
    }

    public void setTags(List<Tag> tags) { // Parameter type matches the field type
        this.tags = tags;
    }

    // Method for adding a single tag
    public void addTag(Tag tag) { // Changed parameter type to Tag
        this.tags.add(tag);
    }
}
