package com.example.tugasakhir.response;

import com.example.tugasakhir.models.Book;
import com.example.tugasakhir.models.Chapter;

import java.util.List;

public class ChapterResponse {
    private int status;
    private String message;
    private List<Chapter> chapters;

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
}
