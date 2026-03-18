package com.example.systemvoicenote.forms;

import java.util.Scanner;

public class LoginForm {
    private final Scanner scanner;

    public LoginForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getEmail() {
        System.out.print("Email: ");
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.print("Пароль: ");
        return scanner.nextLine();
    }
}