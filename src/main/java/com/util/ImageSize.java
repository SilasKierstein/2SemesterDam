package com.util;
public enum ImageSize {
    THUMBNAIL(150, 150),
    MEDIUM(500, 500),
    FULL(1200, 1200);

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