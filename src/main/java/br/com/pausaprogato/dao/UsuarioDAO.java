package br.com.pausaprogato.dao;

import br.com.pausaprogato.beans.Usuario;
import br.com.pausaprogato.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Connection minhaConexao;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public void fecharConexao() throws SQLException {
        if(minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }

    //Create
    public String inserir(Usuario usuario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO USUARIO_PPGATO (nome, email, departamento, cargo) VALUES (?, ?, ?, ?)",
                new String[] {"id"}
        );
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getDepartamento());
        stmt.setString(4, usuario.getCargo());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            usuario.setId(rs.getInt(1));
        }

        stmt.close();
        return "Usuário cadastrado com sucesso! ID gerado: " + usuario.getId();
    }

    //Read
    public List<Usuario> selecionar() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM USUARIO_PPGATO"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Usuario objUsuario = new Usuario();
            objUsuario.setId(rs.getInt("id"));
            objUsuario.setNome(rs.getString("nome"));
            objUsuario.setEmail(rs.getString("email"));
            objUsuario.setDepartamento(rs.getString("departamento"));
            objUsuario.setCargo(rs.getString("cargo"));

            listaUsuarios.add(objUsuario);
        }
        return listaUsuarios;
    }

    //Update
    public String atualizar(Usuario usuario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE USUARIO_PPGATO SET nome = ?, email = ?, departamento = ?, cargo = ? WHERE id = ?"
        );
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getDepartamento());
        stmt.setString(4, usuario.getCargo());
        stmt.setInt(5, usuario.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Usuario atualizado com sucesso";
        } else {
            return "Nenhum usuario encontrado com o ID informado.";
        }
    }

    //Delete
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM USUARIO_PPGATO WHERE id = ?"
        );

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Usuario deletado com sucesso!";
        } else {
            return "Nenhum usuario encontrado com esse ID.";
        }
    }
}
