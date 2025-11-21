package br.com.pausaprogato.beans;

import java.time.LocalDate;

public class ExerciciosFeitos extends RegistroDiario{

    private String quantidade_exercicio;
    private String tipos;

    public ExerciciosFeitos() {
    }

    public ExerciciosFeitos(int id, int usuario_id, LocalDate data, String quantidade_exercicio, String tipos) {
        super(id, usuario_id, data);
        this.quantidade_exercicio = quantidade_exercicio;
        this.tipos = tipos;
    }

    public String getQuantidade_exercicio() {
        return quantidade_exercicio;
    }

    public void setQuantidade_exercicio(String quantidade_exercicio) {
        this.quantidade_exercicio = quantidade_exercicio;
    }

    public String getTipos() {
        return tipos;
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

    @Override
    public String toString() {
        return "ExerciciosFeitos:" +
                "\nquantidade_exercicio='" + quantidade_exercicio + '\'' +
                "\ntipos='" + tipos + '\'' +
                '}';
    }

    @Override
    public String identificador() {
        return "Retorno da classe ExerciciosFeitos";
    }
}
