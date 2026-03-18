package com.example.systemvoicenote.dto;

public final class UserUpdateDto {
    private final String name;
    private final String email;
    private final String password;

    public UserUpdateDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}