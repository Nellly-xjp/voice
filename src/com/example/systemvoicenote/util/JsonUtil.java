package com.example.systemvoicenote.util;

import com.google.gson.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.List;

public class JsonUtil {

    // <-- замінили старий Gson на цей з TypeAdapter для LocalDateTime
    private static final Gson gson = new GsonBuilder()
          .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                new JsonPrimitive(src.toString()))  // конвертуємо LocalDateTime у String
          .setPrettyPrinting()
          .create();

    public static <T> void saveToFile(String fileName, List<T> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> loadFromFile(String fileName, Class<T[]> clazz) {
        try (FileReader reader = new FileReader(fileName)) {
            T[] arr = gson.fromJson(reader, clazz);
            return List.of(arr);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}