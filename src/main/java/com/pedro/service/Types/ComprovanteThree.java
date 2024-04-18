package com.pedro.service.Types;

import com.pedro.model.Comprovante;

import java.util.List;

import static com.pedro.model.Types.ProofTypeThree.*;

//Comprovante PIXs
public class ComprovanteThree extends Comprovante {
    private List<String> lines;
    private boolean ehPagamento;

    public ComprovanteThree(List<String> lines) {
        this.lines = lines;
        collectData();
    }

    public Comprovante collectData() {
        for (String line : this.lines) {
            if (findDestinatario(line)) continue;
            if (findDataPagamento(line)) continue;
            if (findValor(line)) continue;
        }

        return new Comprovante(getDestinatario(), getValor(), getDataPagamento());
    }

    private boolean findDestinatario(String line) {
        if (getDestinatario() != null) return true;

        String nomePagadorPara = "Nome do pagador:";
        String nomeDestinatarioDe = "Nome do destinatário:";

        if (line.contains("Comprovante de Pagamento PIX")) {
            ehPagamento = true;
        }

        //SE FOR PAGAMENTO, SALVAR DESTINATARIO
        //SE FOR RECEBIMENTO, SALVAR PAGADOR
        if (line.contains(nomePagadorPara) && !ehPagamento) {
            String[] fields = line.split(":");
            setDestinatario(fields[1].trim());
            return true;
        } else if (line.contains(nomeDestinatarioDe) && ehPagamento) {
            String[] fields = line.split(":");
            setDestinatario(fields[1]);
            return true;
        }
        return false;
    }

    private boolean findValor(String line) {
        if (getValor() != null) return true;

        for (String chave : chavesPagamentos) {
            if (line.contains(chave)) {
                String[] valores = line.split(": ");
                setValor(valores[1]);
                return true;
            }
        }
        return false;
    }

    private boolean findDataPagamento(String line) {
        if (getDataPagamento() != null) return true;

        for (String chave : chavesDatas) {
            if (line.contains(chave)) {
                String[] dataPagamentoSplit = line.split(": ");
                dataPagamentoSplit = dataPagamentoSplit[1].split("-");
                setDataPagamento(dataPagamentoSplit[0].replace("/", "."));

                return true;
            }
        }
        return false;
    }
}
