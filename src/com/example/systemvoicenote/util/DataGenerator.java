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
                  "1234"
            ));
        }
        return list;
    }

    public static List<Category> generateCategories() {
        return List.of(
              new Category(1, "Робота"),
              new Category(2, "Особисте"),
              new Category(3, "Навчання")
        );
    }

    public static List<VoiceNote> generateNotes(int count, int maxUserId, int maxCategoryId) {
        List<VoiceNote> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new VoiceNote(
                  i,
                  faker.book().title(),
                  "audio" + i + ".wav",
                  faker.number().numberBetween(10, 300),
                  faker.number().numberBetween(0, maxUserId),
                  faker.number().numberBetween(1, maxCategoryId)
            ));
        }
        return list;
    }
}