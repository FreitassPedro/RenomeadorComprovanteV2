package com.pedro.service;

import com.pedro.controller.DirectoryController;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class ComprovanteService {
    private DirectoryController directoryController;

    public String extractTextFromPDF(File file) {
        try {
            PDDocument pdfLoad = Loader.loadPDF(file);
            PDFTextStripper textStripper = new PDFTextStripper();

            return textStripper.getText(pdfLoad);
        } catch (IOException e) {
            System.err.println("Error collecting text");
        }
        return null;
    }


    public int identifyType(String text) {
        if (text.startsWith("Ass")) return 1;
        if (text.startsWith("Comprovante de Pagamento")) return 2;
        return 3;
    }
}

