package br.com.pausaprogato.beans;

import java.sql.Date;
import java.time.LocalDate;

public abstract class RegistroDiario {

    private int id;
    private int usuario_id;
    private LocalDate data;

    public RegistroDiario() {
    }

    public RegistroDiario(int id, int usuario_id, LocalDate data) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RegistroDiario:" +
                "\nid=" + id +
                "\nusuario_id=" + usuario_id +
                "\ndata=" + data;
    }

    public abstract String identificador();
}
