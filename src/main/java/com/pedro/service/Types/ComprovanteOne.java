package com.pedro.service.Types;


import com.pedro.model.Comprovante;

import java.util.List;

import static com.pedro.model.Types.ProofTypeOne.*;

public class ComprovanteOne extends Comprovante {
    private List<String> lines;

    public ComprovanteOne(List<String> lines) {
        this.lines = lines;
    }

    public Comprovante collectData() {
        String nomeDestinatarioAlt = "";
        for (String line : this.lines) {

            String[] fields = line.split(": ");
            String prefixo = fields[0] + ":";

            for (String chave : chavesDatas) {
                if (prefixo.equals(chave)) {
                    setDataPagamento(fields[1].replace("/", "."));
                    break;
                }
            }
            for (String chave : chavesPagamentos) {
                for (String chaveAlt : chavesPagamentosAlt) {
                    if (prefixo.equals(chave)) {
                        setDestinatario(fields[1]);
                        break;
                    }
                    if (prefixo.equals(chaveAlt)) {
                        nomeDestinatarioAlt = fields[1];
                    }
                }
                if ("".equals(getDestinatario())) {
                    setDestinatario(nomeDestinatarioAlt);
                }
            }
            for (String chave : chavesValores) {
                if (prefixo.equals(chave)) {
                    setValor(fields[1]);
                    break;
                }
            }
        }
        return new Comprovante(getDestinatario(), getValor(), getDataPagamento());
    }
}
