package com.example.systemvoicenote.repository;

import com.example.systemvoicenote.model.Identifiable;
import com.example.systemvoicenote.util.JsonUtil;

import java.util.*;
import java.util.function.Predicate;

public class Repository<T extends Identifiable> {
    private final Map<Integer, T> items = new HashMap<>();
    private final String fileName;
    private final Class<T[]> clazz;

    public Repository(String fileName, Class<T[]> clazz) {
        this.fileName = fileName;
        this.clazz = clazz;
        load();
    }

    public void add(T item) {
        items.put(item.getId(), item);
    }

    public void remove(int id) {
        items.remove(id);
    }

    public T getById(int id) {
        return items.get(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(items.values());
    }

    public List<T> find(Predicate<T> predicate) {
        return items.values().stream().filter(predicate).toList();
    }

    public void save() {
        JsonUtil.saveToFile(fileName, getAll());
    }

    private void load() {
        List<T> list = JsonUtil.loadFromFile(fileName, clazz);
        items.clear();
        for (T item : list) {
            items.put(item.getId(), item);
        }
    }

    public int getNextId() {
        return items.keySet().stream().mapToInt(i -> i).max().orElse(0) + 1;
    }
}