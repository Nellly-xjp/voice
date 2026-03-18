package com.example.systemvoicenote.pages;

import com.example.systemvoicenote.forms.AddUserForm;
import com.example.systemvoicenote.service.UserService;
import com.example.systemvoicenote.dto.UserStoreDto;
import com.example.systemvoicenote.model.User;

import java.util.List;
import java.util.Scanner;

public class UsersView {
    private final UserService userService;
    private final Scanner scanner;

    public UsersView(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public void show() {
        while (true) {
            System.out.println("\u001B[34m=== Користувачі ===\u001B[0m");
            System.out.println("1. Показати всіх");
            System.out.println("2. Додати");
            System.out.println("3. Назад");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showAll();
                case "2" -> addUser();
                case "3" -> { return; }
                default -> System.out.println("\u001B[31mПомилка\u001B[0m");
            }
        }
    }

    private void showAll() {
        List<User> users = userService.getAll();
        for (User u : users) {
            System.out.println("ID: " + u.getId() + " | " + u);
        }
    }

    private void addUser() {
        try {
            AddUserForm form = new AddUserForm(scanner);
            UserStoreDto dto = form.collect();
            userService.register(dto);
            System.out.println("\u001B[32m✔ Користувач створений\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }
}