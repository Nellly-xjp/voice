package com.example.systemvoicenote.forms;

import java.util.Scanner;

public class AddVoiceNoteForm {
    private final Scanner scanner;

    public AddVoiceNoteForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getTitle() {
        System.out.print("Назва: ");
        return scanner.nextLine();
    }

    public int getDuration() {
        while (true) {
            try {
                System.out.print("Тривалість (сек): ");
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("\u001B[31mВведіть число!\u001B[0m");
            }
        }
    }

    public int getUserId() {
        while (true) {
            try {
                System.out.print("User ID: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("\u001B[31mВведіть число!\u001B[0m");
            }
        }
    }

    public int getCategoryId() {
        while (true) {
            try {
                System.out.print("Category ID: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("\u001B[31mВведіть число!\u001B[0m");
            }
        }
    }
}