package com.example.systemvoicenote;

import com.example.systemvoicenote.pages.*;
import com.example.systemvoicenote.repository.UnitOfWork;
import com.example.systemvoicenote.service.*;
import com.example.systemvoicenote.util.DataGenerator;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UnitOfWork uow = new UnitOfWork();

        // Створюємо папку recordings
        new File("recordings").mkdirs();

        // Демо-дані
        if (uow.users.getAll().isEmpty()) {
            System.out.println("\u001B[33mГенеруємо демо-дані...\u001B[0m");
            DataGenerator.generateUsers(5).forEach(uow.users::add);
            DataGenerator.generateCategories().forEach(uow.categories::add);
            DataGenerator.generateNotes(8).forEach(uow.notes::add);
            uow.commit();
            System.out.println("\u001B[32mДемо-дані створено!\u001B[0m");
            System.out.println("Приклад логіну: admin@example.com / SecurePass1\n");
        }

        UserService userService = new UserService(uow);
        CategoryService categoryService = new CategoryService(uow);
        VoiceNoteService voiceNoteService = new VoiceNoteService(uow);
        AuthService authService = new AuthService(uow);

        AuthView authView = new AuthView(authService, userService, scanner);
        authView.show();

        // Оновлені views
        VoiceNotesView notesView = new VoiceNotesView(voiceNoteService, categoryService, authService, scanner);
        CategoriesView categoriesView = new CategoriesView(categoryService, scanner);
        UsersView usersView = new UsersView(userService, scanner);

        MainMenuView mainMenu = new MainMenuView(notesView, categoriesView, usersView, authService, scanner);
        mainMenu.show();

        System.out.println("\u001B[32mДо побачення!\u001B[0m");
    }
}