package com.example.systemvoicenote.util;

import com.example.systemvoicenote.model.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    private static final Faker faker = new Faker();

    public static List<User> generateUsers(int count) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new User(
                  i,
                  faker.name().fullName(),
                  faker.internet().emailAddress(),
                  "SecurePass" + (i + 1)   // валідний пароль
            ));
        }
        return list;
    }

    public static List<Category> generateCategories() {
        return List.of(
              new Category(0, "Робота"),
              new Category(1, "Особисте"),
              new Category(2, "Навчання")
        );
    }

    public static List<VoiceNote> generateNotes(int count) {
        List<VoiceNote> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new VoiceNote(
                  i,
                  faker.book().title(),
                  "recordings/note_" + i + ".wav",
                  faker.number().numberBetween(15, 240),
                  faker.number().numberBetween(0, 4),
                  faker.number().numberBetween(0, 2)
            ));
        }
        return list;
    }
}