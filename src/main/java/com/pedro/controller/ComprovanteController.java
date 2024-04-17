package com.pedro.controller;

import com.pedro.service.ComprovanteService;

import java.io.File;

public class ComprovanteController {


    private ComprovanteService comprovanteService;
    private FileController fileController = new FileController();


    public ComprovanteController(FileController fileController) {
        this.fileController = fileController;
    }

    public ComprovanteController(ComprovanteService comprovanteService) {
        this.comprovanteService = comprovanteService;
    }

    /*public String getText(File file) {

    }*/



}
