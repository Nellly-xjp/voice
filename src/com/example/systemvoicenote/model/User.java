package com.example.systemvoicenote.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email, String password) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім'я не може бути пустим");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Некоректний email");
        }
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Пароль має бути мінімум 4 символи");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}