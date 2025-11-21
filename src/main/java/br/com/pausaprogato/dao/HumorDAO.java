package br.com.pausaprogato.dao;

import br.com.pausaprogato.beans.Humor;
import br.com.pausaprogato.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HumorDAO {

    public Connection minhaConexao;

    public HumorDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public void fecharConexao() throws SQLException {
        if(minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }

    // Create
    public String inserir(Humor humor) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO HUMOR_PPGATO (usuario_id, nivel_humor, descricao_humor, data) VALUES (?, ?, ?, ?)",
                new String[] {"id"}
        );
        stmt.setInt(1, humor.getUsuario_id());
        stmt.setString(2, humor.getNivel_humor());
        stmt.setString(3, humor.getDescricao_humor());
        stmt.setDate(4, java.sql.Date.valueOf(humor.getData()));

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            humor.setId(rs.getInt(1));
        }

        stmt.close();
        return "Humor cadastrado com sucesso! ID gerado: " + humor.getId();
    }

    // Read
    public List<Humor> selecionar() throws SQLException {
        List<Humor> listaHumor = new ArrayList<Humor>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM HUMOR_PPGATO"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Humor objHumor = new Humor();
            objHumor.setId(rs.getInt("id"));
            objHumor.setUsuario_id(rs.getInt("usuario_id"));
            objHumor.setNivel_humor(rs.getString("nivel_humor"));
            objHumor.setDescricao_humor(rs.getString("descricao_humor"));
            objHumor.setData(rs.getDate("data").toLocalDate());

            listaHumor.add(objHumor);
        }
        stmt.close();
        return listaHumor;
    }

    // Update
    public String atualizar(Humor humor) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE HUMOR_PPGATO SET usuario_id = ?, nivel_humor = ?, descricao_humor = ?, data = ? WHERE id = ?"
        );
        stmt.setInt(1, humor.getUsuario_id());
        stmt.setString(2, humor.getNivel_humor());
        stmt.setString(3, humor.getDescricao_humor());
        stmt.setDate(4, java.sql.Date.valueOf(humor.getData()));
        stmt.setInt(5, humor.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Humor atualizado com sucesso";
        } else {
            return "Nenhum humor encontrado com o ID informado.";
        }
    }

    // Delete
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM HUMOR_PPGATO WHERE id = ?"
        );

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Humor deletado com sucesso!";
        } else {
            return "Nenhum humor encontrado com esse ID.";
        }
    }
}
