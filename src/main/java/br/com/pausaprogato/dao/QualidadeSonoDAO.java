package br.com.pausaprogato.dao;

import br.com.pausaprogato.beans.QualidadeSono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QualidadeSonoDAO {

    private Connection minhaConexao;

    // Construtor: recebe a conexão já aberta pelo BO
    public QualidadeSonoDAO(Connection conn) {
        this.minhaConexao = conn;
    }

    public String inserir(QualidadeSono qualidadeSono) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO QUALIDADE_SONO_PPGATO (usuario_id, qualidade, horas_duracao, observacoes, data) VALUES (?, ?, ?, ?, ?)",
                new String[] {"id"}
        );
        stmt.setInt(1, qualidadeSono.getUsuario_id());
        stmt.setString(2, qualidadeSono.getQualidade());
        stmt.setString(3, qualidadeSono.getHoras_duracao());
        stmt.setString(4, qualidadeSono.getObservacoes());
        stmt.setDate(5, java.sql.Date.valueOf(qualidadeSono.getData()));

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            qualidadeSono.setId(rs.getInt(1));
        }

        stmt.close();
        return "Informação de QualidadeSono cadastrada com sucesso! ID gerado: " + qualidadeSono.getId();
    }

    // Read
    public List<QualidadeSono> selecionar() throws SQLException {
        List<QualidadeSono> listaQualidadeSono = new ArrayList<>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM QUALIDADE_SONO_PPGATO"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            QualidadeSono objQualidadeSono = new QualidadeSono();
            objQualidadeSono.setId(rs.getInt("id"));
            objQualidadeSono.setUsuario_id(rs.getInt("usuario_id"));
            objQualidadeSono.setQualidade(rs.getString("qualidade"));
            objQualidadeSono.setHoras_duracao(rs.getString("horas_duracao"));
            objQualidadeSono.setObservacoes(rs.getString("observacoes"));
            objQualidadeSono.setData(rs.getDate("data").toLocalDate());

            listaQualidadeSono.add(objQualidadeSono);
        }
        stmt.close();
        return listaQualidadeSono;
    }

    // Update
    public String atualizar(QualidadeSono qualidadeSono) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE QUALIDADE_SONO_PPGATO SET usuario_id = ?, qualidade = ?, horas_duracao = ?, observacoes = ?, data = ? WHERE id = ?"
        );
        stmt.setInt(1, qualidadeSono.getUsuario_id());
        stmt.setString(2, qualidadeSono.getQualidade());
        stmt.setString(3, qualidadeSono.getHoras_duracao());
        stmt.setString(4, qualidadeSono.getObservacoes());
        stmt.setDate(5, java.sql.Date.valueOf(qualidadeSono.getData()));
        stmt.setInt(6, qualidadeSono.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Informações de QualidadeSono atualizadas com sucesso";
        } else {
            return "Nenhuma informação de QualidadeSono encontrada com o ID informado.";
        }
    }

    // Delete
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM QUALIDADE_SONO_PPGATO WHERE id = ?"
        );

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Informações de QualidadeSono deletadas com sucesso!";
        } else {
            return "Nenhuma informação de QualidadeSono encontrada com esse ID.";
        }
    }
}
