package br.com.pausaprogato.beans;

import java.time.LocalDate;

public class NivelEstresse extends RegistroDiario{

    private String nivel_estresse;
    private String descricao_estresse;

    public NivelEstresse() {
    }

    public NivelEstresse(int id, int usuario_id, LocalDate data, String nivel_estresse, String descricao_estresse) {
        super(id, usuario_id, data);
        this.nivel_estresse = nivel_estresse;
        this.descricao_estresse = descricao_estresse;
    }

    public String getNivel_estresse() {
        return nivel_estresse;
    }

    public void setNivel_estresse(String nivel_estresse) {
        this.nivel_estresse = nivel_estresse;
    }

    public String getDescricao_estresse() {
        return descricao_estresse;
    }

    public void setDescricao_estresse(String descricao_estresse) {
        this.descricao_estresse = descricao_estresse;
    }

    @Override
    public String toString() {
        return "NivelEstresse:" +
                "\nnivel_estresse='" + nivel_estresse + '\'' +
                "\ndescricao_estresse='" + descricao_estresse + '\'' +
                '}';
    }

    @Override
    public String identificador() {
        return "Retorno da classe NivelEstresse";
    }
}
