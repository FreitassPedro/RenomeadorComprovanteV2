package com.pedro.config;

import com.pedro.controller.ComprovanteController;
import com.pedro.controller.DirectoryController;
import com.pedro.controller.FileController;

public class ConfigurationProgram {
    private DirectoryController directoryController;
    private FileController fileController;

    public void setDirectoryController(DirectoryController directoryController) {
        this.directoryController = directoryController;
    }

    public void setFileController(FileController fileController) {
        this.fileController = fileController;
    }

    public void startProgram() {
        directoryController.createDirectory();
        fileController.prepareFilesToRename();
    }
}

