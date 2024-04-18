package com.pedro.service;

import com.pedro.model.Comprovante;
import com.pedro.service.Types.ComprovanteOne;
import com.pedro.service.Types.ComprovanteThree;
import com.pedro.service.Types.ComprovanteTwo;

import java.util.ArrayList;
import java.util.List;

public class ComprovanteService {
    private String text;
    private int type;

    public List<String> listOfLines = new ArrayList<>();

    public ComprovanteService(String text, int type) {
        this.text = text;
        this.type = type;
    }

    public void splitLines() {
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                listOfLines.add(line);
            }
        }
    }

    public Comprovante startCollectDatas() {
        switch (type) {
            case 1:
                Comprovante c1 = new ComprovanteOne(listOfLines);
                return c1;

            case 2:
                Comprovante c2 = new ComprovanteTwo(listOfLines);
                return c2;
            case 3:
                Comprovante c3 = new ComprovanteThree(listOfLines);
                return c3;
        }
        return null;
    }
}

