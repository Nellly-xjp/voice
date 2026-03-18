package com.example.systemvoicenote.dto;

import java.util.Objects;

public final class UserStoreDto {
    private final String name;
    private final String email;
    private final String password;

    public UserStoreDto(String name, String email, String password) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Ім'я обов'язкове");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Некоректний email");
        if (password == null || password.length() < 4) throw new IllegalArgumentException("Пароль мінімум 4 символи");
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}