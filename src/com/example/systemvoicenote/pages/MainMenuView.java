package com.example.systemvoicenote.pages;

import com.example.systemvoicenote.service.AuthService;

import java.util.Scanner;

public class MainMenuView {
    private final VoiceNotesView notesView;
    private final CategoriesView categoriesView;
    private final UsersView usersView;
    private final AuthService authService;
    private final Scanner scanner;

    public MainMenuView(VoiceNotesView notesView, CategoriesView categoriesView, UsersView usersView,
          AuthService authService, Scanner scanner) {
        this.notesView = notesView;
        this.categoriesView = categoriesView;
        this.usersView = usersView;
        this.authService = authService;
        this.scanner = scanner;
    }

    public void show() {
        while (true) {
            System.out.println("\n\u001B[34m=== Voice Notes System ===\u001B[0m");
            System.out.println("Поточний користувач: " + authService.getCurrentUser().getName());
            System.out.println("1. Голосові нотатки");
            System.out.println("2. Категорії");
            System.out.println("3. Користувачі");
            System.out.println("4. Вийти з акаунту");
            System.out.println("5. Вийти з програми");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> notesView.show();
                case "2" -> categoriesView.show();
                case "3" -> usersView.show();
                case "4" -> {
                    authService.logout();
                    return;
                }
                case "5" -> System.exit(0);
                default -> System.out.println("\u001B[31mНевірний вибір!\u001B[0m");
            }
        }
    }
}