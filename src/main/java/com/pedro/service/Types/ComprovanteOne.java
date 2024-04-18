package com.pedro.service.Types;


import com.pedro.model.Comprovante;

import java.util.List;

import static com.pedro.model.Types.ProofTypeOne.*;

public class ComprovanteOne extends Comprovante {
    private List<String> lines;
    private String nomeDestinatarioAlt = "";

    public ComprovanteOne(List<String> lines) {
        this.lines = lines;
    }

    public Comprovante collectData() {
        String nomeDestinatarioAlt = "";
        for (String line : lines) {


            String[] lineSplited = line.split(": ");
            String prefixo = lineSplited[0] + ":";

            if (findDestinatario(lineSplited[1], prefixo)) continue;
            if (findDataPagamento(lineSplited[1], prefixo)) continue;
            if (findValor(lineSplited[1], prefixo)) continue;

        }
        return new Comprovante(getDestinatario(), getValor(), getDataPagamento());
    }

    private boolean findValor(String pathWithValor, String prefixo) {
        if (getValor() != null) return true;

        for (String chave : chavesValores) {
            if (prefixo.equals(chave)) {
                setValor(pathWithValor);
                return true;
            }
        }
        return false;
    }

    private boolean findDataPagamento(String partWithData, String prefixo) {
        if (getDataPagamento() != null) return true;
        for (String chave : chavesDatas) {
            if (prefixo.equals(chave)) {
                setDataPagamento(partWithData.replace("/", "."));
                return true;
            }
        }
        return false;
    }


    private boolean findDestinatario(String partWithDestinatario, String prefixo) {
        for (String chave : chavesPagamentos) {
            for (String chaveAlt : chavesPagamentosAlt) {
                if (prefixo.equals(chave)) {
                    setDestinatario(partWithDestinatario);
                    return true;
                }
                if (prefixo.equals(chaveAlt)) {
                    nomeDestinatarioAlt = partWithDestinatario;
                    return true;
                }
            }
            if ("".equals(getDestinatario())) {
                setDestinatario(nomeDestinatarioAlt);
                return true;
            }
        }
        return false;
    }
}
