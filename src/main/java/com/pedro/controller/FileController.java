package com.pedro.controller;

import com.pedro.service.FileService;

public class FileController {
    private FileService fileService = new FileService();
    public FileController() {}

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    public int prepareFilesToRename() {
        return fileService.prepareFileToRename();
    }
}
