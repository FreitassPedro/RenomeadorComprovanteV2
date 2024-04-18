package com.pedro.service.Types;

import com.pedro.model.Comprovante;

import java.util.List;

import static com.pedro.model.Types.ProofTypeTwo.*;

//Comprovantes Boleto
public class ComprovanteTwo extends Comprovante {
    private List<String> lines;

    private String numeroDocumento = "";
    private String nomeDestinatarioAlt = "";

    public ComprovanteTwo(List<String> lines) {
        this.lines = lines;
        collectData();
    }

    public Comprovante collectData() {
        for (String line : lines) {

            if (findDestinatario(line)) continue;
            if (findDataPagamento(line)) continue;
            if (findValor(line)) continue;


            if (!numeroDocumento.isEmpty()) {
                setDestinatario("DARF " + numeroDocumento);
            }
        }
        return new Comprovante(getDestinatario(), getValor(), getDataPagamento());
    }

    private boolean findDataPagamento(String line) {
        if (getDataPagamento() != null) return true;

        for (String chave : chavesDatas) {
            if (line.contains(chave)) {
                String dataPagamento = line.substring(0, line.indexOf(chave));
                setDataPagamento(dataPagamento.replace("/", "."));
                return true;
            }
        }
        return false;
    }

    private boolean findDestinatario(String line) {
        String nomeDestinatario = "";
        for (String chave : chavesNomes) {
            for (String chaveAlt : chavesNomesAlt) {
                if (line.contains(chave)) {
                    setDestinatario(line.substring(0, line.indexOf(chave)));
                    return true;
                }
                if (line.contains(chaveAlt)) {
                    nomeDestinatarioAlt = line.substring(0, line.indexOf(chaveAlt));
                    return true;
                }
            }

            if (line.contains("Número do Documento:")) {
                numeroDocumento = line.substring(0, line.indexOf("Número do Documento:"));
            }
        }
        if (nomeDestinatario.isEmpty()) {
            setDestinatario(nomeDestinatarioAlt);
        }
        return false;
    }

    private boolean findValor(String line) {
        if (getValor() != null) return true;

        for (String chave : chavesPagamentos) {
            if (line.contains(chave)) {
                int index = line.indexOf(chave);
                setValor(line.substring(0, index).trim());
                return true;
            }
        }
        return false;
    }


}

