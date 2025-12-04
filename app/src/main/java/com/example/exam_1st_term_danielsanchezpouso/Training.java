package com.example.exam_1st_term_danielsanchezpouso;

import java.io.Serializable;

public class Training implements Serializable {
    private String title;
    private String description;
    private int imageResId;

    public Training(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getImageResId() { return imageResId; }
}