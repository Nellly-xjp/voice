package com.example.systemvoicenote.pages;

import com.example.systemvoicenote.model.Category;
import com.example.systemvoicenote.service.CategoryService;

import java.util.List;
import java.util.Scanner;

public class CategoriesView {
    private final CategoryService service;
    private final Scanner scanner;

    public CategoriesView(CategoryService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void show() {
        while (true) {
            System.out.println("\u001B[34m=== Категорії ===\u001B[0m");
            System.out.println("1. Показати всі");
            System.out.println("2. Додати");
            System.out.println("3. Назад");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showAll();
                case "2" -> add();
                case "3" -> { return; }
                default -> System.out.println("\u001B[31mПомилка\u001B[0m");
            }
        }
    }

    private void showAll() {
        List<Category> list = service.getAll();
        for (Category c : list) {
            System.out.println("ID: " + c.getId() + " | " + c.getName());
        }
    }

    private void add() {
        System.out.print("Назва категорії: ");
        String name = scanner.nextLine();
        service.create(name);
        System.out.println("\u001B[32m✔ Категорію створено\u001B[0m");
    }
}