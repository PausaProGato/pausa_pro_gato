package br.com.pausaprogato.beans;

import java.time.LocalDate;

public class Pausas extends RegistroDiario{

    private String quantidade_pausas;
    private String duracao_media;

    public Pausas() {
    }

    public Pausas(int id, int usuario_id, LocalDate data, String quantidade_pausas, String duracao_media) {
        super(id, usuario_id, data);
        this.quantidade_pausas = quantidade_pausas;
        this.duracao_media = duracao_media;
    }

    public String getQuantidade_pausas() {
        return quantidade_pausas;
    }

    public void setQuantidade_pausas(String quantidade_pausas) {
        this.quantidade_pausas = quantidade_pausas;
    }

    public String getDuracao_media() {
        return duracao_media;
    }

    public void setDuracao_media(String duracao_media) {
        this.duracao_media = duracao_media;
    }

    @Override
    public String toString() {
        return "Pausas:" +
                "\nquantidade_pausas='" + quantidade_pausas + '\'' +
                "\nduracao_media='" + duracao_media + '\'';
    }

    @Override
    public String identificador() {
        return "Retorno da classe Pausas";
    }
}
