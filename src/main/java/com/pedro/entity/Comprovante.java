package com.pedro.entity;

public class Comprovante {
    private String destinatario;
    private String valor;
    private String dataPagamento;

    public Comprovante(String destinatario, String valor, String dataPagamento) {
        this.destinatario = destinatario;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
