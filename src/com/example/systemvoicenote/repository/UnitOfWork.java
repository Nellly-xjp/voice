package com.example.systemvoicenote.repository;

import com.example.systemvoicenote.model.Category;
import com.example.systemvoicenote.model.User;
import com.example.systemvoicenote.model.VoiceNote;

public class UnitOfWork {
    public final Repository<User> users;
    public final Repository<Category> categories;
    public final Repository<VoiceNote> notes;

    public UnitOfWork() {
        users = new Repository<>("users.json", User[].class);
        categories = new Repository<>("categories.json", Category[].class);
        notes = new Repository<>("notes.json", VoiceNote[].class);
    }

    public void commit() {
        users.save();
        categories.save();
        notes.save();
    }
}