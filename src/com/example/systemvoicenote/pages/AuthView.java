package com.example.systemvoicenote.pages;

import com.example.systemvoicenote.forms.LoginForm;
import com.example.systemvoicenote.forms.SignUpForm;
import com.example.systemvoicenote.service.AuthService;
import com.example.systemvoicenote.service.UserService;

import java.util.Scanner;

public class AuthView {
    private final AuthService auth;
    private final UserService userService;
    private final Scanner scanner;

    public AuthView(AuthService auth, UserService userService, Scanner scanner) {
        this.auth = auth;
        this.userService = userService;
        this.scanner = scanner;
    }

    public void show() {
        while (!auth.isAuthenticated()) {
            System.out.println("\u001B[34m=== Авторизація ===\u001B[0m");
            System.out.println("1. Вхід");
            System.out.println("2. Реєстрація");

            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {
                    LoginForm form = new LoginForm(scanner);
                    auth.login(form.getEmail(), form.getPassword());
                    System.out.println("\u001B[32m✔ Вхід успішний. Ласкаво просимо " +
                          auth.getCurrentUser().getName() + "\u001B[0m");
                } else if (choice.equals("2")) {
                    SignUpForm form = new SignUpForm(scanner);
                    userService.register(form.collect());
                    System.out.println("\u001B[32m✔ Користувач успішно зареєстрований\u001B[0m");
                } else {
                    System.out.println("\u001B[31mПомилка вибору\u001B[0m");
                }
            } catch (Exception e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        }
    }
}