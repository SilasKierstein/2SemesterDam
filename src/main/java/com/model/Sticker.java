package com.model;

import com.util.StickerType;

public class Sticker {
    private Long id;
    private String base64;
    private StickerType type; //

    // Constructors
    public Sticker() {}

    public Sticker(StickerType type) {
        this.type = type;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBase64() { return base64; }
    public void setBase64(String base64) { this.base64 = base64; }
    public StickerType getType() { return type; }
    public void setType(StickerType type) { this.type = type; }
}
