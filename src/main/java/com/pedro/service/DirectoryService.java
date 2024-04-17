package com.pedro.service;

import com.pedro.model.DirectoryModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryService {

    private DirectoryModel directoryModel = new DirectoryModel();


    public String pathToSaveRenameds = directoryModel.pathToSaveRenameds();
    public String pathMainFolder = directoryModel.pathMainFolderDesktop();


    private boolean createIfNotExist() {
        Path path = Paths.get(pathToSaveRenameds);
        File directoryInDesktop = new File(pathToSaveRenameds);
        if (!directoryInDesktop.exists()) {
            try {
                Files.createDirectories(path);
                System.out.println("Repositorio Criado! " + path);
                return false;
            } catch (IOException e) {
                System.err.println("Error creating directory: " + e.getMessage());
                return false;
            }
        }
        return true;
    }


    public boolean directoryExists() {
          return createIfNotExist();
    }

}
