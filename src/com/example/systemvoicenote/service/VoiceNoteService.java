package com.example.systemvoicenote.service;

import com.example.systemvoicenote.model.VoiceNote;
import com.example.systemvoicenote.repository.UnitOfWork;

import java.util.List;

public class VoiceNoteService {
    private final UnitOfWork uow;

    public VoiceNoteService(UnitOfWork uow) {
        this.uow = uow;
    }

    public VoiceNote create(String title, String filePath, int duration, int userId, int categoryId) {
        int newId = uow.notes.getAll().size();
        VoiceNote note = new VoiceNote(newId, title, filePath, duration, userId, categoryId);
        uow.notes.add(note.getId(), note);
        return note;
    }

    public List<VoiceNote> getByUserId(int userId) {
        return uow.notes.find(n -> n.getUserId() == userId);
    }

    public List<VoiceNote> getByCategoryId(int categoryId) {
        return uow.notes.find(n -> n.getCategoryId() == categoryId);
    }

    public void delete(int noteId) {
        uow.notes.remove(noteId);
    }
}