package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.FileUtil.copyFile;
import static com.FileUtil.getFilesInFolder;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("введите исходную папку \n");
            Path inputPath = Paths.get(bufferedReader.readLine());

            System.out.print("введите папку копирования\n");
            Path pathToCopy = Paths.get(bufferedReader.readLine());;

            bufferedReader.close();

            List<File> filesInFolder = getFilesInFolder(inputPath);

            copyFile(filesInFolder, pathToCopy);

        } catch (NoSuchFileException e) {
            System.out.printf("Проблема с файлом %s\n", e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
