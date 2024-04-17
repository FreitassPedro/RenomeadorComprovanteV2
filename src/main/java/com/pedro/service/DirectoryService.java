package com.pedro.service;

import com.pedro.model.DirectoryModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryService {

    private DirectoryModel directoryModel = new DirectoryModel();


    public String pathToSaveRenameds = directoryModel.pathToSaveRenameds();
    public String pathMainFolder = directoryModel.pathMainFolderDesktop();


    public void createIfNotExist() {
        Path path = Paths.get(pathToSaveRenameds);

        if (!directoryExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println("Error creating directory: " + e.getMessage());
            }
        }
    }

    private static boolean directoryExists(Path path) {
        return Files.exists(path);
    }

}
