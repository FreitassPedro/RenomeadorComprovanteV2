package com.pedro.service.Types;


import com.pedro.model.Comprovante;

import java.util.List;

import static com.pedro.model.Types.ProofTypeOne.*;

public class ComprovanteThree extends Comprovante {
    private List<String> lines;

    public Comprovante collectData() {
        String nomePagadorPara = "Nome do pagador:";
        String nomeDestinatarioDe = "Nome do destinatário:";
        String dataPagamento = "";

        boolean ehPagamento = false;
            for (String line : this.lines) {
                if (line.contains("Comprovante de Pagamento PIX")) {
                    ehPagamento = true;
                }

                //SE FOR PAGAMENTO, SALVAR DESTINATARIO
                //SE FOR RECEBIMENTO, SALVAR PAGADOR
                if (line.contains(nomePagadorPara) && !ehPagamento) {
                    String[] fields = line.split(":");
                    setDestinatario(fields[1].trim());
                    continue;
                } else if (line.contains(nomeDestinatarioDe) && ehPagamento) {
                    String[] fields = line.split(":");
                    setDestinatario(fields[1]);
                }

                for (String chave : chavesDatas) {
                    if (line.contains(chave)) {
                        String[] dataPagamentoSplit = line.split(": ");
                        dataPagamentoSplit = dataPagamentoSplit[1].split("-");
                        dataPagamento = dataPagamentoSplit[0];
                        setDataPagamento(dataPagamento.replace("/", "."));
                    }
                }
                for (String chave : chavesPagamentos) {
                    if (line.contains(chave)) {
                        String[] valores = line.split(": ");
                        setValor(valores[1]);
                        break;
                    }
                }

        }
        return new Comprovante(getDestinatario(), getValor(), getDataPagamento());
    }
}
