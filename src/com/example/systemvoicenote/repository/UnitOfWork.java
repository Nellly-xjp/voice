package com.example.systemvoicenote.repository;

import java.util.*;

public class UnitOfWork {
    public Repository<com.example.systemvoicenote.model.User> users = new Repository<>();
    public Repository<com.example.systemvoicenote.model.Category> categories = new Repository<>();
    public Repository<com.example.systemvoicenote.model.VoiceNote> notes = new Repository<>();

    public void commit() {
        com.example.systemvoicenote.util.JsonUtil.saveToFile("users.json", users.getAll());
        com.example.systemvoicenote.util.JsonUtil.saveToFile("categories.json", categories.getAll());
        com.example.systemvoicenote.util.JsonUtil.saveToFile("notes.json", notes.getAll());
    }
}