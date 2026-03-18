package com.example.systemvoicenote.model;

public class Category implements Identifiable {
    private int id;
    private String name;

    public Category(int id, String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Категорія не може бути пустою");
        this.id = id;
        this.name = name;
    }

    @Override public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}