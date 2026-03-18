package com.example.systemvoicenote.model;

public class User implements Identifiable {
    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email, String password) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Ім'я не може бути пустим");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Некоректний email");
        if (password == null || password.length() < 8) throw new IllegalArgumentException("Пароль має бути мінімум 8 символів");
        if (!password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*"))
            throw new IllegalArgumentException("Пароль має містити букви та цифри");

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}