package br.com.pausaprogato.beans;

import java.time.LocalDate;

public class Humor extends RegistroDiario{

    private String nivel_humor;
    private String descricao_humor;

    public Humor() {
    }

    public Humor(int id, int usuario_id, LocalDate data, String nivel_humor, String descricao_humor) {
        super(id, usuario_id, data);
        this.nivel_humor = nivel_humor;
        this.descricao_humor = descricao_humor;
    }

    public String getNivel_humor() {
        return nivel_humor;
    }

    public void setNivel_humor(String nivel_humor) {
        this.nivel_humor = nivel_humor;
    }

    public String getDescricao_humor() {
        return descricao_humor;
    }

    public void setDescricao_humor(String descricao_humor) {
        this.descricao_humor = descricao_humor;
    }

    @Override
    public String toString() {
        return "Humor:" +
                "\nnivel_humor=" + nivel_humor +
                "\ndescricao_humor='" + descricao_humor + '\'' +
                '}';
    }

    @Override
    public String identificador() {
        return "Retorno da classe Humor";
    }
}
