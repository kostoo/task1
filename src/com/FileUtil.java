package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileUtil {

    public static boolean getLineCount(File file) throws IOException {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null & lines <= 10) {
                lines++;
            }
            return lines >= 11;
        }
    }

    public static List<File> getFilesInFolder(Path directory) throws IOException {
        return Files.walk(Paths.get(directory.toUri()))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
    }

    public static void copyFile(List<File> filesInFolder, Path pathToCopy) throws IOException {
        for (File file : Objects.requireNonNull(filesInFolder)) {
            if (FileUtil.getLineCount(file)) {
                Files.copy(file.toPath(), pathToCopy.resolve(file.getName()), REPLACE_EXISTING);
                System.out.printf("файл : %s скопирован\n", file.getName());
            }
        }
    }
}
