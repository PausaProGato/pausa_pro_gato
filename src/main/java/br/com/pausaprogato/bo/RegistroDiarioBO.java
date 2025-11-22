package br.com.pausaprogato.bo;

import br.com.pausaprogato.beans.*;
import br.com.pausaprogato.dao.*;
import br.com.pausaprogato.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class RegistroDiarioBO {

    // Listar todos registros agregados
    public List<Map<String, Object>> selecionarTudoBO() throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            QualidadeSonoDAO qualidadeSonoDAO = new QualidadeSonoDAO(conn);
            PausasDAO pausasDAO = new PausasDAO(conn);
            ObservacoesDAO observacoesDAO = new ObservacoesDAO(conn);
            NivelEstresseDAO nivelEstresseDAO = new NivelEstresseDAO(conn);
            HumorDAO humorDAO = new HumorDAO(conn);
            ExerciciosFeitosDAO exerciciosFeitosDAO = new ExerciciosFeitosDAO(conn);

            List<Usuario> usuarios = usuarioDAO.selecionar();
            List<QualidadeSono> sonoList = qualidadeSonoDAO.selecionar();
            List<Pausas> pausasList = pausasDAO.selecionar();
            List<Observacoes> obsList = observacoesDAO.selecionar();
            List<NivelEstresse> stressList = nivelEstresseDAO.selecionar();
            List<Humor> humorList = humorDAO.selecionar();
            List<ExerciciosFeitos> exercList = exerciciosFeitosDAO.selecionar();

            List<Map<String, Object>> resultado = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                Map<String, Object> registro = new LinkedHashMap<>();
                registro.put("id", usuario.getId());
                registro.put("nome", usuario.getNome());
                registro.put("email", usuario.getEmail());
                registro.put("departamento", usuario.getDepartamento());
                registro.put("cargo", usuario.getCargo());

                registro.put("qualidadeSono", sonoList.stream()
                        .filter(s -> s.getUsuario_id() == usuario.getId()).toList());
                registro.put("pausas", pausasList.stream()
                        .filter(p -> p.getUsuario_id() == usuario.getId()).toList());
                registro.put("observacoes", obsList.stream()
                        .filter(o -> o.getUsuario_id() == usuario.getId()).toList());
                registro.put("nivelEstresse", stressList.stream()
                        .filter(n -> n.getUsuario_id() == usuario.getId()).toList());
                registro.put("humor", humorList.stream()
                        .filter(h -> h.getUsuario_id() == usuario.getId()).toList());
                registro.put("exerciciosFeitos", exercList.stream()
                        .filter(e -> e.getUsuario_id() == usuario.getId()).toList());

                resultado.add(registro);
            }
            return resultado;
        } finally {
            if (conn != null && !conn.isClosed()) conn.close();
        }
    }

    // Buscar dados agregados por id
    public Map<String, Object> buscarPorUsuarioIdBO(int usuarioId) throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> todos = selecionarTudoBO();
        for (Map<String, Object> registro : todos) {
            if (((Integer) registro.get("id")) == usuarioId) return registro;
        }
        return null;
    }

    // Métodos individuais de listagem
    public List<Usuario> selecionarUsuarios() throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new UsuarioDAO(conn).selecionar(); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }

    public List<QualidadeSono> selecionarQualidadeSono() throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new QualidadeSonoDAO(conn).selecionar(); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public List<Pausas> selecionarPausas() throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new PausasDAO(conn).selecionar(); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public List<Observacoes> selecionarObservacoes() throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new ObservacoesDAO(conn).selecionar(); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public List<NivelEstresse> selecionarNivelEstresse() throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new NivelEstresseDAO(conn).selecionar(); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public List<Humor> selecionarHumores() throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new HumorDAO(conn).selecionar(); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public List<ExerciciosFeitos> selecionarExerciciosFeitos() throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new ExerciciosFeitosDAO(conn).selecionar(); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }

    // Inserções isoladas
    public String inserirUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new UsuarioDAO(conn).inserir(usuario); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String inserirQualidadeSono(QualidadeSono qualidadeSono) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new QualidadeSonoDAO(conn).inserir(qualidadeSono); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String inserirPausas(Pausas pausas) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new PausasDAO(conn).inserir(pausas); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String inserirObservacoes(Observacoes obs) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new ObservacoesDAO(conn).inserir(obs); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String inserirNivelEstresse(NivelEstresse n) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new NivelEstresseDAO(conn).inserir(n); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String inserirHumor(Humor h) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new HumorDAO(conn).inserir(h); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String inserirExerciciosFeitos(ExerciciosFeitos e) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new ExerciciosFeitosDAO(conn).inserir(e); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }

    // Atualizações isoladas
    public String atualizarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new UsuarioDAO(conn).atualizar(usuario); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String atualizarQualidadeSono(QualidadeSono qualidadeSono) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new QualidadeSonoDAO(conn).atualizar(qualidadeSono); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String atualizarPausas(Pausas pausas) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new PausasDAO(conn).atualizar(pausas); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String atualizarObservacoes(Observacoes obs) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new ObservacoesDAO(conn).atualizar(obs); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String atualizarNivelEstresse(NivelEstresse n) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new NivelEstresseDAO(conn).atualizar(n); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String atualizarHumor(Humor h) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new HumorDAO(conn).atualizar(h); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }
    public String atualizarExerciciosFeitos(ExerciciosFeitos e) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new ExerciciosFeitosDAO(conn).atualizar(e); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }

    // métodos para apagar tudo do usuário
    public String deletarUsuario(int id) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try { return new UsuarioDAO(conn).deletar(id); }
        finally { if (conn != null && !conn.isClosed()) conn.close(); }
    }

    public String deletarQualidadeSonoPorUsuario(int usuarioId) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM QUALIDADE_SONO_PPGATO WHERE usuario_id = ?");
            stmt.setInt(1, usuarioId);
            int linhas = stmt.executeUpdate();
            stmt.close();
            return linhas > 0 ? "Registros de QualidadeSono apagados!" : "Nenhum registro encontrado.";
        } finally {
            if (conn != null && !conn.isClosed()) conn.close();
        }
    }
    public String deletarPausasPorUsuario(int usuarioId) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM PAUSAS_PPGATO WHERE usuario_id = ?");
            stmt.setInt(1, usuarioId);
            int linhas = stmt.executeUpdate();
            stmt.close();
            return linhas > 0 ? "Registros de Pausas apagados!" : "Nenhum registro encontrado.";
        } finally {
            if (conn != null && !conn.isClosed()) conn.close();
        }
    }
    public String deletarObservacoesPorUsuario(int usuarioId) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM OBSERVACOES_PPGATO WHERE usuario_id = ?");
            stmt.setInt(1, usuarioId);
            int linhas = stmt.executeUpdate();
            stmt.close();
            return linhas > 0 ? "Registros de Observacoes apagados!" : "Nenhum registro encontrado.";
        } finally {
            if (conn != null && !conn.isClosed()) conn.close();
        }
    }
    public String deletarNivelEstressePorUsuario(int usuarioId) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM NIVEL_ESTRESSE_PPGATO WHERE usuario_id = ?");
            stmt.setInt(1, usuarioId);
            int linhas = stmt.executeUpdate();
            stmt.close();
            return linhas > 0 ? "Registros de NivelEstresse apagados!" : "Nenhum registro encontrado.";
        } finally {
            if (conn != null && !conn.isClosed()) conn.close();
        }
    }
    public String deletarHumorPorUsuario(int usuarioId) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM HUMOR_PPGATO WHERE usuario_id = ?");
            stmt.setInt(1, usuarioId);
            int linhas = stmt.executeUpdate();
            stmt.close();
            return linhas > 0 ? "Registros de Humor apagados!" : "Nenhum registro encontrado.";
        } finally {
            if (conn != null && !conn.isClosed()) conn.close();
        }
    }
    public String deletarExerciciosFeitosPorUsuario(int usuarioId) throws SQLException, ClassNotFoundException {
        Connection conn = new ConexaoFactory().conexao();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM EXERCICIOS_FEITOS_PPGATO WHERE usuario_id = ?");
            stmt.setInt(1, usuarioId);
            int linhas = stmt.executeUpdate();
            stmt.close();
            return linhas > 0 ? "Registros de Exercícios apagados!" : "Nenhum registro encontrado.";
        } finally {
            if (conn != null && !conn.isClosed()) conn.close();
        }
    }

    // Deleta tudo agregado, para o endpoint DELETE completo
    public void deletarRegistroCompleto(int usuarioId) throws SQLException, ClassNotFoundException {
        deletarQualidadeSonoPorUsuario(usuarioId);
        deletarPausasPorUsuario(usuarioId);
        deletarObservacoesPorUsuario(usuarioId);
        deletarNivelEstressePorUsuario(usuarioId);
        deletarHumorPorUsuario(usuarioId);
        deletarExerciciosFeitosPorUsuario(usuarioId);
        deletarUsuario(usuarioId);
    }
}
