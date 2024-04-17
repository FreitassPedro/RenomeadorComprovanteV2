package com.pedro.controller;

import com.pedro.service.DirectoryService;

public class DirectoryController {

    private DirectoryService directoryService = new DirectoryService();


    public DirectoryController () {}


    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    public String getPathFiles() {
        return directoryService.pathMainFolder;
    }

    public boolean createDirectory() {
       return directoryService.directoryExists();
    }
}