package br.com.pausaprogato.dao;

import br.com.pausaprogato.beans.ExerciciosFeitos;
import br.com.pausaprogato.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciciosFeitosDAO {

    public Connection minhaConexao;

    public ExerciciosFeitosDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public void fecharConexao() throws SQLException {
        if(minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }

    // Create
    public String inserir(ExerciciosFeitos exerciciosFeitos) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO EXERCICIOS_FEITOS_PPGATO (usuario_id, quantidade_exercicio, tipos, data) VALUES (?, ?, ?, ?)",
                new String[] {"id"}
        );
        stmt.setInt(1, exerciciosFeitos.getUsuario_id());
        stmt.setString(2, exerciciosFeitos.getQuantidade_exercicio());
        stmt.setString(3, exerciciosFeitos.getTipos());
        stmt.setDate(4, java.sql.Date.valueOf(exerciciosFeitos.getData()));

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            exerciciosFeitos.setId(rs.getInt(1));
        }

        stmt.close();
        return "ExerciciosFeitos cadastrados com sucesso! ID gerado: " + exerciciosFeitos.getId();
    }

    // Read
    public List<ExerciciosFeitos> selecionar() throws SQLException {
        List<ExerciciosFeitos> listaExerciciosFeitos = new ArrayList<ExerciciosFeitos>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM EXERCICIOS_FEITOS_PPGATO"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            ExerciciosFeitos objExerciciosFeitos = new ExerciciosFeitos();
            objExerciciosFeitos.setId(rs.getInt("id"));
            objExerciciosFeitos.setUsuario_id(rs.getInt("usuario_id"));
            objExerciciosFeitos.setQuantidade_exercicio(rs.getString("quantidade_exercicio"));
            objExerciciosFeitos.setTipos(rs.getString("tipos"));
            objExerciciosFeitos.setData(rs.getDate("data").toLocalDate());

            listaExerciciosFeitos.add(objExerciciosFeitos);
        }
        stmt.close();
        return listaExerciciosFeitos;
    }

    // Update
    public String atualizar(ExerciciosFeitos exerciciosFeitos) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE EXERCICIOS_FEITOS_PPGATO SET usuario_id = ?, quantidade_exercicio = ?, tipos = ?, data = ? WHERE id = ?"
        );
        stmt.setInt(1, exerciciosFeitos.getUsuario_id());
        stmt.setString(2, exerciciosFeitos.getQuantidade_exercicio());
        stmt.setString(3, exerciciosFeitos.getTipos());
        stmt.setDate(4, java.sql.Date.valueOf(exerciciosFeitos.getData()));
        stmt.setInt(5, exerciciosFeitos.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "ExerciciosFeitos atualizados com sucesso";
        } else {
            return "Nenhuma informação de ExerciciosFeitos encontrada com o ID informado.";
        }
    }

    // Delete
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM EXERCICIOS_FEITOS_PPGATO WHERE id = ?"
        );

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "ExerciciosFeitos deletado com sucesso!";
        } else {
            return "Nenhuma informação de ExerciciosFeitos encontrada com esse ID.";
        }
    }
}
