package com.example.systemvoicenote.pages;

import com.example.systemvoicenote.model.VoiceNote;
import com.example.systemvoicenote.service.AuthService;
import com.example.systemvoicenote.service.VoiceNoteService;
import com.example.systemvoicenote.service.CategoryService;

import java.util.List;
import java.util.Scanner;

public class VoiceNotesView {
    private final VoiceNoteService service;
    private final CategoryService categoryService;
    private final AuthService authService;
    private final Scanner scanner;

    public VoiceNotesView(VoiceNoteService service, CategoryService categoryService, AuthService authService, Scanner scanner) {
        this.service = service;
        this.categoryService = categoryService;
        this.authService = authService;
        this.scanner = scanner;
    }

    public void show() {
        while (true) {
            System.out.println("\n\u001B[34m=== Голосові нотатки ===\u001B[0m");
            System.out.println("1. Показати всі");
            System.out.println("2. Мої нотатки");
            System.out.println("3. Додати нову нотатку");
            System.out.println("4. Пошук по назві");
            System.out.println("5. Сортувати за датою");
            System.out.println("6. Сортувати за тривалістю");
            System.out.println("7. Фільтр по категорії");
            System.out.println("8. Редагувати нотатку");
            System.out.println("9. Видалити нотатку");
            System.out.println("10. Назад");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> showNotes(service.getAll(), "Всі нотатки");
                    case "2" -> showNotes(service.getMyNotes(authService.getCurrentUser().getId()), "Мої нотатки");
                    case "3" -> addNote();
                    case "4" -> search();
                    case "5" -> showNotes(service.getSortedByDate(), "Відсортовані за датою (новіші зверху)");
                    case "6" -> showNotes(service.getSortedByDuration(), "Відсортовані за тривалістю (довші зверху)");
                    case "7" -> filterByCategory();
                    case "8" -> editNote();
                    case "9" -> deleteNote();
                    case "10" -> { return; }
                    default -> System.out.println("\u001B[31mНевірний вибір!\u001B[0m");
                }
            } catch (Exception e) {
                System.out.println("\u001B[31mПомилка: " + e.getMessage() + "\u001B[0m");
            }
        }
    }

    private void showNotes(List<VoiceNote> notes, String title) {
        System.out.println("\n\u001B[34m" + title + "\u001B[0m");
        if (notes.isEmpty()) {
            System.out.println("Нотаток поки немає.");
            return;
        }
        for (VoiceNote n : notes) {
            System.out.println("ID: " + n.getId() + " | " + n);
        }
    }

    private void addNote() {
        System.out.print("Назва нотатки: ");
        String title = scanner.nextLine().trim();
        if (title.isBlank()) {
            System.out.println("\u001B[31mНазва не може бути пустою!\u001B[0m");
            return;
        }

        System.out.print("ID категорії: ");
        int categoryId = Integer.parseInt(scanner.nextLine());

        int userId = authService.getCurrentUser().getId();

        service.create(title, userId, categoryId);
    }

    private void search() {
        System.out.print("Введіть частину назви: ");
        String keyword = scanner.nextLine();
        showNotes(service.searchByTitle(keyword), "Результати пошуку");
    }

    private void filterByCategory() {
        System.out.print("ID категорії: ");
        int catId = Integer.parseInt(scanner.nextLine());
        showNotes(service.getByCategoryId(catId), "Нотатки категорії");
    }

    private void deleteNote() {
        System.out.print("ID нотатки для видалення: ");
        int id = Integer.parseInt(scanner.nextLine());
        service.delete(id);
    }

    private void editNote() {
        System.out.print("ID нотатки для редагування: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Нова назва (або Enter, щоб залишити): ");
        String newTitle = scanner.nextLine().trim();

        System.out.print("Новий ID категорії (або Enter, щоб залишити): ");
        String catInput = scanner.nextLine().trim();

        int newCategoryId = catInput.isEmpty() ? -1 : Integer.parseInt(catInput);

        boolean success = service.update(id, newTitle.isEmpty() ? null : newTitle, newCategoryId);
        if (success) {
            System.out.println("\u001B[32m✔ Нотатку оновлено!\u001B[0m");
        } else {
            System.out.println("\u001B[31mНотатку не знайдено!\u001B[0m");
        }
    }
}