package br.com.pausaprogato.dao;

import br.com.pausaprogato.beans.Pausas;
import br.com.pausaprogato.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PausasDAO {

    public Connection minhaConexao;

    public PausasDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public void fecharConexao() throws SQLException {
        if(minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }

    //Create
    public String inserir(Pausas pausas) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO PAUSAS_PPGATO (usuario_id, quantidade_pausas, duracao_media, data) VALUES (?, ?, ?, ?)",
                new String[] {"id"}
        );
        stmt.setInt(1, pausas.getUsuario_id());
        stmt.setString(2, pausas.getQuantidade_pausas());
        stmt.setString(3, pausas.getDuracao_media());
        stmt.setDate(4, java.sql.Date.valueOf(pausas.getData()));

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            pausas.setId(rs.getInt(1));
        }

        stmt.close();
        return "Pausas cadastradas com sucesso! ID gerado: " + pausas.getId();
    }

    //Read
    public List<Pausas> selecionar() throws SQLException {
        List<Pausas> listaPausas = new ArrayList<>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM PAUSAS_PPGATO"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Pausas objPausas = new Pausas();
            objPausas.setId(rs.getInt("id"));
            objPausas.setUsuario_id(rs.getInt("usuario_id"));
            objPausas.setQuantidade_pausas(rs.getString("quantidade_pausas"));
            objPausas.setDuracao_media(rs.getString("duracao_media"));
            objPausas.setData(rs.getDate("data").toLocalDate());

            listaPausas.add(objPausas);
        }
        stmt.close();
        return listaPausas;
    }

    //Update
    public String atualizar(Pausas pausas) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE PAUSAS_PPGATO SET usuario_id = ?, quantidade_pausas = ?, duracao_media = ?, data = ? WHERE id = ?"
        );
        stmt.setInt(1, pausas.getUsuario_id());
        stmt.setString(2, pausas.getQuantidade_pausas());
        stmt.setString(3, pausas.getDuracao_media());
        stmt.setDate(4, java.sql.Date.valueOf(pausas.getData()));
        stmt.setInt(5, pausas.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Pausas atualizadas com sucesso";
        } else {
            return "Nenhuma pausa encontrada com o ID informado.";
        }
    }

    //Delete
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM PAUSAS_PPGATO WHERE id = ?"
        );

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Pausas deletadas com sucesso!";
        } else {
            return "Nenhuma pausa encontrada com esse ID.";
        }
    }
}
