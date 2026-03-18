package com.example.systemvoicenote.service;

import com.example.systemvoicenote.model.Category;
import com.example.systemvoicenote.repository.UnitOfWork;

import java.util.List;

public class CategoryService {
    private final UnitOfWork uow;

    public CategoryService(UnitOfWork uow) {
        this.uow = uow;
    }

    public Category create(String name) {
        int newId = uow.categories.getAll().size() + 1;
        Category c = new Category(newId, name);
        uow.categories.add(c.getId(), c);
        return c;
    }

    public List<Category> getAll() {
        return uow.categories.getAll();
    }
}