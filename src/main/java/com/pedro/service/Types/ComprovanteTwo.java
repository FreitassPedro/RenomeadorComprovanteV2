package com.pedro.service.Types;


import com.pedro.model.Comprovante;

import java.util.List;

import static com.pedro.model.Types.ProofTypeOne.*;
//TODO Documentos
public class ComprovanteTwo extends Comprovante {
    /*
    private List<String> lines;

    public void collectData() {
        int quantityDatas = 0;
        String nomeDestinatarioAlt = "";
        while (quantityDatas < 3) {
            for (String line : lines) {
                for (String chave : chavesPagamentos) {
                    if (line.contains(chave)) {
                        int index = line.indexOf(chave);
                        valor = line.substring(0, index).trim();
                        break;
                    }
                }
                for (String chave : chavesDatas) {
                    if (line.contains(chave)) {
                        dataPagamento = line.substring(0, line.indexOf(chave));
                        dataPagamento = dataPagamento.replace("/", ".");
                        break;
                    }
                }

                for (String chave : chavesNomes) {
                    for (String chaveAlt : chavesNomesAlt) {
                        if (line.contains(chave)) {
                            nomeDestinatario = line.substring(0, line.indexOf(chave));
                            break;
                        }
                        if (line.contains(chaveAlt)) {
                            nomeDestinatarioAlt = line.substring(0, line.indexOf(chaveAlt));
                            break;
                        }
                    }
                    if (line.contains("Número do Documento:")) {
                        numeroDocumento = line.substring(0, line.indexOf("Número do Documento:"));
                    }
                }
                if (nomeDestinatario.equals("")) {
                    nomeDestinatario = nomeDestinatarioAlt;
                }
            }
            if (!numeroDocumento.equals("")) {
                return dataPagamento + " R$ " + valor + " - DARF " + numeroDocumento + ".pdf";
            }
        }


    }

     */
}

