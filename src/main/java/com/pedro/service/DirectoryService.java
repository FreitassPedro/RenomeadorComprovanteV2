package com.pedro.service;

import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryService {

    public static String pathFilesInDesktop() {
        FileSystemView path = FileSystemView.getFileSystemView();
        return path.getHomeDirectory().getPath() + "\\Comprovantes\\Renomeados\\";
    }

    public void createIfNotExist() {
        Path path = Paths.get(pathFilesInDesktop());

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
