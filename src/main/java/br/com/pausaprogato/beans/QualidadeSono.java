package br.com.pausaprogato.beans;

import java.time.LocalDate;

public class QualidadeSono extends RegistroDiario{

    private String qualidade;
    private String horas_duracao;
    private String observacoes;

    public QualidadeSono() {
    }

    public QualidadeSono(int id, int usuario_id, LocalDate data, String qualidade, String horas_duracao, String observacoes) {
        super(id, usuario_id, data);
        this.qualidade = qualidade;
        this.horas_duracao = horas_duracao;
        this.observacoes = observacoes;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public String getHoras_duracao() {
        return horas_duracao;
    }

    public void setHoras_duracao(String horas_duracao) {
        this.horas_duracao = horas_duracao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "QualidadeSono:" +
                "\nqualidade='" + qualidade + '\'' +
                "\nhoras_duracao='" + horas_duracao + '\'' +
                "\nobservacoes='" + observacoes + '\'';
    }

    @Override
    public String identificador() {
        return "Retorno da classe QualidadeSono";
    }
}
