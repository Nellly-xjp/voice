package com.example.systemvoicenote.service;

import com.example.systemvoicenote.model.User;
import com.example.systemvoicenote.repository.UnitOfWork;

public class AuthService {
    private final UnitOfWork uow;
    private User currentUser;

    public AuthService(UnitOfWork uow) {
        this.uow = uow;
    }

    public User login(String email, String password) {
        for (User u : uow.users.getAll()) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                currentUser = u;
                return u;
            }
        }
        throw new IllegalArgumentException("Невірний email або пароль");
    }

    public void logout() {
        currentUser = null;
        System.out.println("\u001B[32mВи вийшли з акаунту.\u001B[0m");
    }

    public boolean isAuthenticated() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        if (currentUser == null) throw new IllegalStateException("Користувач не авторизований");
        return currentUser;
    }
}