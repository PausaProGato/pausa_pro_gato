package br.com.pausaprogato.beans;

import java.time.LocalDate;

public class Observacoes extends RegistroDiario{

    private String texto;

    public Observacoes() {
    }

    public Observacoes(int id, int usuario_id, LocalDate data, String texto) {
        super(id, usuario_id, data);
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Observacoes:" +
                "\ntexto='" + texto + '\'' +
                '}';
    }

    @Override
    public String identificador() {
        return "Retorno da classe Observacoes";
    }
}
