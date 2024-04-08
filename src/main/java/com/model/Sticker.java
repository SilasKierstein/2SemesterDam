package com.model;

public class Sticker {
    private Long id;
    private String base64;
    private String type; //

    // Constructors
    public Sticker() {}

    public Sticker(String type) {
        this.type = type;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBase64() { return base64; }
    public void setBase64(String base64) { this.base64 = base64; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
