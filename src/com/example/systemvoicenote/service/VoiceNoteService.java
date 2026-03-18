package com.example.systemvoicenote.service;

import com.example.systemvoicenote.model.VoiceNote;
import com.example.systemvoicenote.repository.UnitOfWork;

import java.util.Comparator;
import java.util.List;

public class VoiceNoteService {
    private final UnitOfWork uow;

    public VoiceNoteService(UnitOfWork uow) {
        this.uow = uow;
    }

    private String simulateRecording(int id) {
        String path = "recordings/note_" + id + ".wav";
        System.out.println("🎙 Запис голосу...");
        System.out.println("💾 Збережено файл: " + path);
        return path;
    }

    public VoiceNote create(String title, int userId, int categoryId) {
        // Автоматична тривалість запису
        System.out.print("Натисніть Enter, щоб почати запис...");
        new java.util.Scanner(System.in).nextLine();

        long start = System.currentTimeMillis();

        System.out.print("Натисніть Enter, щоб зупинити запис...");
        new java.util.Scanner(System.in).nextLine();

        int duration = (int) ((System.currentTimeMillis() - start) / 1000);
        if (duration < 1) duration = 1;

        int newId = uow.notes.getNextId();
        String filePath = simulateRecording(newId);

        VoiceNote note = new VoiceNote(newId, title, filePath, duration, userId, categoryId);
        uow.notes.add(note);
        uow.commit();

        System.out.println("\u001B[32m✔ Нотатку записано! Тривалість: " + duration + " сек\u001B[0m");
        return note;
    }

    public List<VoiceNote> getAll() {
        return uow.notes.getAll();
    }

    public List<VoiceNote> getMyNotes(int userId) {
        return uow.notes.find(n -> n.getUserId() == userId);
    }

    public List<VoiceNote> getByUserId(int userId) {
        return getMyNotes(userId);
    }

    public List<VoiceNote> getByCategoryId(int categoryId) {
        return uow.notes.find(n -> n.getCategoryId() == categoryId);
    }

    public List<VoiceNote> searchByTitle(String keyword) {
        String lower = keyword.toLowerCase();
        return uow.notes.find(n -> n.getTitle().toLowerCase().contains(lower));
    }

    public void delete(int id) {
        if (uow.notes.getById(id) != null) {
            uow.notes.remove(id);
            uow.commit();
            System.out.println("\u001B[32m✔ Нотатку видалено\u001B[0m");
        } else {
            System.out.println("\u001B[31mНотатку не знайдено\u001B[0m");
        }
    }

    // Редагування
    public boolean update(int id, String newTitle, int newCategoryId) {
        VoiceNote note = uow.notes.getById(id);
        if (note == null) return false;

        // Створюємо нову нотатку з оновленими даними (бо immutable полів немає)
        VoiceNote updated = new VoiceNote(
              note.getId(),
              newTitle,
              note.getFilePath(),
              note.getDuration(),
              note.getUserId(),
              newCategoryId
        );

        uow.notes.remove(id);
        uow.notes.add(updated);
        uow.commit();
        return true;
    }

    public List<VoiceNote> getSortedByDate() {
        return uow.notes.getAll().stream()
              .sorted(Comparator.comparing(VoiceNote::getCreatedAt).reversed())
              .toList();
    }

    public List<VoiceNote> getSortedByDuration() {
        return uow.notes.getAll().stream()
              .sorted(Comparator.comparingInt(VoiceNote::getDuration).reversed())
              .toList();
    }
}