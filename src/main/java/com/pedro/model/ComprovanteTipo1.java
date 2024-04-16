package com.pedro.model;

import java.util.ArrayList;
import java.util.List;

public class ComprovanteTipo1 extends Comprovante{
    public ComprovanteTipo1(String destinatario, String valor, String dataPagamento) {
        super(destinatario, valor, dataPagamento);
    }

    @Override
    public void splitLines(String lines) {

    }

    List<String> lines = new ArrayList<>();

}
