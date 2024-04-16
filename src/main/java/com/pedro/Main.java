package com.pedro;


import com.pedro.controller.DirectoryController;


public class Main {
    private static DirectoryController directoryController;
    public static void main(String[] args) {
        directoryController = new DirectoryController();

        directoryController.createDirectory();



    }
}