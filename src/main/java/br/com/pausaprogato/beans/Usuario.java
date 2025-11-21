package br.com.pausaprogato.beans;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String departamento;
    private String cargo;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String departamento, String cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.departamento = departamento;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Usuario:" +
                "\nid=" + id +
                "\nnome='" + nome + '\'' +
                "\nemail='" + email + '\'' +
                "\ndepartamento='" + departamento + '\'' +
                "\ncargo='" + cargo + '\'' +
                '}';
    }
}
