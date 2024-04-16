package com.pedro.controller;

import com.pedro.service.ComprovanteService;

import java.io.File;

public class ComprovanteController {


    private ComprovanteService comprovanteService;


    public ComprovanteController(ComprovanteService comprovanteService) {
        this.comprovanteService = comprovanteService;
    }

    public String getText(File file) {
        return comprovanteService.extractTextFromPDF(file);
    }

    public int getTypeUsingText(File file) {
        String text = getText(file);
        return comprovanteService.identifyType(text);
    }
}
