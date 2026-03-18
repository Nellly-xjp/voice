package com.example.systemvoicenote.service;

import com.example.systemvoicenote.dto.UserStoreDto;
import com.example.systemvoicenote.model.User;
import com.example.systemvoicenote.repository.UnitOfWork;

import java.util.List;

public class UserService {
    private final UnitOfWork uow;

    public UserService(UnitOfWork uow) {
        this.uow = uow;
    }

    public User register(UserStoreDto dto) {
        boolean exists = uow.users.getAll().stream()
              .anyMatch(u -> u.getEmail().equalsIgnoreCase(dto.getEmail()));

        if (exists) {
            throw new IllegalArgumentException("Користувач з таким email вже існує");
        }

        int newId = uow.users.getNextId();                 // ← правильно
        User user = new User(newId, dto.getName(), dto.getEmail(), dto.getPassword());

        uow.users.add(user);                               // ← без id!
        uow.commit();

        return user;
    }

    public List<User> getAll() {
        return uow.users.getAll();
    }
}