package com.example.systemvoicenote.service;

import com.example.systemvoicenote.dto.UserStoreDto;
import com.example.systemvoicenote.dto.UserUpdateDto;
import com.example.systemvoicenote.model.User;
import com.example.systemvoicenote.repository.UnitOfWork;

public class UserService {
    private final UnitOfWork uow;

    public UserService(UnitOfWork uow) {
        this.uow = uow;
    }

    // Реєстрація користувача
    public User register(UserStoreDto dto) {
        int newId = uow.users.getAll().size();
        User user = new User(newId, dto.getName(), dto.getEmail(), dto.getPassword());
        uow.users.add(user.getId(), user);
        sendWelcomeEmail(user);
        return user;
    }

    // Оновлення даних користувача
    public User update(int userId, UserUpdateDto dto) {
        User user = uow.users.getById(userId);
        if (user == null) throw new IllegalArgumentException("Користувач не знайдений");
        String name = dto.getName() != null ? dto.getName() : user.getName();
        String email = dto.getEmail() != null ? dto.getEmail() : user.getEmail();
        String password = dto.getPassword() != null ? dto.getPassword() : user.getEmail();
        User updated = new User(user.getId(), name, email, password);
        uow.users.add(updated.getId(), updated);
        return updated;
    }

    // Імітація надсилання email
    private void sendWelcomeEmail(User user) {
        System.out.println("📧 Відправка email: Вітаємо, " + user.getName() + " (" + user.getEmail() + ")");
    }
}