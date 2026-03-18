package com.example.systemvoicenote.forms;

import com.example.systemvoicenote.dto.UserStoreDto;

import java.util.Scanner;

public class SignUpForm {
    private final Scanner scanner;

    public SignUpForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public UserStoreDto collect() {
        System.out.print("Ім'я: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        return new UserStoreDto(name, email, password);
    }
}