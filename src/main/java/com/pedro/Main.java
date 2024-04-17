package com.pedro;

import com.pedro.config.ConfigurationProgram;
import com.pedro.controller.DirectoryController;
import com.pedro.controller.FileController;
/*

    @author: Pedro freitas

*/


public class Main {

    private ConfigurationProgram configurationProgram;
    public static void main(String[] args) {
        ConfigurationProgram config = new ConfigurationProgram();
        config.setDirectoryController(new DirectoryController());
        config.setFileController(new FileController());
        config.startProgram();

    }
}