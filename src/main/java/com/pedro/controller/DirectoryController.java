package com.pedro.controller;

import com.pedro.service.DirectoryService;

public class DirectoryController {

    private DirectoryService directoryService;

    public DirectoryController() {
        this.directoryService = new DirectoryService();
    }

    public void createDirectory() {
       directoryService.createIfNotExist();
    }
}