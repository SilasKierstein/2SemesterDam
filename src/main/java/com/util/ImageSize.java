package com.util;
public enum ImageSize {
    THUMBNAIL(128, 128),
    MEDIUM(512, 512),
    FULL(1024, 1024);

    private final int width;
    private final int height;

    ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}