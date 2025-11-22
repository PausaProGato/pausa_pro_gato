package br.com.pausaprogato.dao;

import br.com.pausaprogato.beans.Observacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObservacoesDAO {

    private Connection minhaConexao;

    // Construtor: recebe a conexão já aberta pelo BO
    public ObservacoesDAO(Connection conn) {
        this.minhaConexao = conn;
    }

    //Create
    public String inserir(Observacoes observacoes) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO OBSERVACOES_PPGATO (usuario_id, texto, data) VALUES (?, ?, ?)",
                new String[] {"id"}
        );
        stmt.setInt(1, observacoes.getUsuario_id());
        stmt.setString(2, observacoes.getTexto());
        stmt.setDate(3, java.sql.Date.valueOf(observacoes.getData()));

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            observacoes.setId(rs.getInt(1));
        }

        stmt.close();
        return "Observações cadastradas com sucesso! ID gerado: " + observacoes.getId();
    }

    //Read
    public List<Observacoes> selecionar() throws SQLException {
        List<Observacoes> listaObservacoes = new ArrayList<>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM OBSERVACOES_PPGATO"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Observacoes objObservacoes = new Observacoes();
            objObservacoes.setId(rs.getInt("id"));
            objObservacoes.setUsuario_id(rs.getInt("usuario_id"));
            objObservacoes.setTexto(rs.getString("texto"));
            objObservacoes.setData(rs.getDate("data").toLocalDate());

            listaObservacoes.add(objObservacoes);
        }
        stmt.close();
        return listaObservacoes;
    }

    //Update
    public String atualizar(Observacoes observacoes) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE OBSERVACOES_PPGATO SET usuario_id = ?, texto = ?, data = ? WHERE id = ?"
        );
        stmt.setInt(1, observacoes.getUsuario_id());
        stmt.setString(2, observacoes.getTexto());
        stmt.setDate(3, java.sql.Date.valueOf(observacoes.getData()));
        stmt.setInt(4, observacoes.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Observações atualizadas com sucesso";
        } else {
            return "Nenhuma observação encontrada com o ID informado.";
        }
    }

    //Delete
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM OBSERVACOES_PPGATO WHERE id = ?"
        );

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Observações deletadas com sucesso!";
        } else {
            return "Nenhuma observação encontrada com esse ID.";
        }
    }
}
