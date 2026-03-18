package com.example.systemvoicenote;

import com.example.systemvoicenote.dto.UserStoreDto;
import com.example.systemvoicenote.repository.UnitOfWork;
import com.example.systemvoicenote.service.UserService;

public class Main {
    public static void main(String[] args) {
        UnitOfWork uow = new UnitOfWork();
        UserService userService = new UserService(uow);

        // --- Створюємо користувача через DTO ---
        UserStoreDto dto = new UserStoreDto("Марія Іванова", "maria@test.com", "abcd1234");
        userService.register(dto);

        // --- Збереження у JSON ---
        uow.commit();
        System.out.println("✔ Дані користувача збережено у файлі JSON");
    }
}