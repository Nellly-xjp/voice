package com.example.systemvoicenote.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VoiceNote {
    private int id;
    private String title;
    private String filePath;
    private LocalDateTime createdAt;
    private int duration;
    private int userId;
    private int categoryId;

    public VoiceNote(int id, String title, String filePath, int duration, int userId, int categoryId) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Назва не може бути пустою");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Тривалість має бути > 0");
        }

        this.id = id;
        this.title = title;
        this.filePath = filePath;
        this.duration = duration;
        this.userId = userId;
        this.categoryId = categoryId;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getFilePath() { return filePath; }
    public int getDuration() { return duration; }
    public int getUserId() { return userId; }
    public int getCategoryId() { return categoryId; }

    // Додаємо геттер, який повертає дату як String
    public String getCreatedAt() {
        return createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return title + " (" + getCreatedAt() + ", тривалість: " + duration + " с)";
    }
}