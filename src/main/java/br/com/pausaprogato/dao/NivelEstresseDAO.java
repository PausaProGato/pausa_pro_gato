package br.com.pausaprogato.dao;

import br.com.pausaprogato.beans.NivelEstresse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NivelEstresseDAO {

    private Connection minhaConexao;

    // Construtor: recebe a conexão já aberta pelo BO
    public NivelEstresseDAO(Connection conn) {
        this.minhaConexao = conn;
    }

    // Create
    public String inserir(NivelEstresse nivelEstresse) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO NIVEL_ESTRESSE_PPGATO (usuario_id, nivel_estresse, descricao_estresse, data) VALUES (?, ?, ?, ?)",
                new String[] {"id"}
        );
        stmt.setInt(1, nivelEstresse.getUsuario_id());
        stmt.setString(2, nivelEstresse.getNivel_estresse());
        stmt.setString(3, nivelEstresse.getDescricao_estresse());
        stmt.setDate(4, java.sql.Date.valueOf(nivelEstresse.getData()));

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            nivelEstresse.setId(rs.getInt(1));
        }

        stmt.close();
        return "Informações de NivelEstresse cadastradas com sucesso! ID gerado: " + nivelEstresse.getId();
    }

    // Read
    public List<NivelEstresse> selecionar() throws SQLException {
        List<NivelEstresse> listaNivelEstresse = new ArrayList<>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM NIVEL_ESTRESSE_PPGATO"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            NivelEstresse objNivelEstresse = new NivelEstresse();
            objNivelEstresse.setId(rs.getInt("id"));
            objNivelEstresse.setUsuario_id(rs.getInt("usuario_id"));
            objNivelEstresse.setNivel_estresse(rs.getString("nivel_estresse"));
            objNivelEstresse.setDescricao_estresse(rs.getString("descricao_estresse"));
            objNivelEstresse.setData(rs.getDate("data").toLocalDate());

            listaNivelEstresse.add(objNivelEstresse);
        }
        stmt.close();
        return listaNivelEstresse;
    }

    // Update
    public String atualizar(NivelEstresse nivelEstresse) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE NIVEL_ESTRESSE_PPGATO SET usuario_id = ?, nivel_estresse = ?, descricao_estresse = ?, data = ? WHERE id = ?"
        );
        stmt.setInt(1, nivelEstresse.getUsuario_id());
        stmt.setString(2, nivelEstresse.getNivel_estresse());
        stmt.setString(3, nivelEstresse.getDescricao_estresse());
        stmt.setDate(4, java.sql.Date.valueOf(nivelEstresse.getData()));
        stmt.setInt(5, nivelEstresse.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Informações de NivelEstresse atualizadas com sucesso";
        } else {
            return "Nenhuma informação de NivelEstresse encontrada com o ID informado.";
        }
    }

    // Delete
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM NIVEL_ESTRESSE_PPGATO WHERE id = ?"
        );

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Informações de NivelEstresse deletadas com sucesso!";
        } else {
            return "Nenhuma informação de NivelEstresse encontrada com esse ID.";
        }
    }
}
