package com.pedro.controller;

import com.pedro.service.FileService;

import java.util.ArrayList;
import java.util.List;

public class FileController {
    private FileService fileService = new FileService();
    public FileController() {}

    public List<String> renamedList = new ArrayList<>();

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    public void prepareFilesToRename() {
        fileService.getFile(false);
    }

    public List<String> startRenameWithData() {
       return fileService.getFile(true);
    }


}
