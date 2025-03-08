package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private static final String DIRECTORY = "data/";

    private JsonHandler(){}


    private static <T> List<T> readJson(String fileName, Type type) {
        try {
            Path path = getPath(fileName);
            String json = Files.readString(path);

            return GSON.fromJson(json, type);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return List.of();
        }
    }
    private static <T> void writeJson(String fileName, List<T> data, Type type) {
        try {
            Path path = Paths.get(DIRECTORY + fileName);
            String newJson = GSON.toJson(data, type);
            Files.write(path, newJson.getBytes());
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }


    private static Path getPath(String fileName) throws FileNotFoundException {
        File file = new File(DIRECTORY + fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Такого файла не существует!");
        }
        return file.toPath();
    }
}

