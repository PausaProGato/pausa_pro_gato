package br.com.pausaprogato.bo;

import br.com.pausaprogato.beans.*;
import br.com.pausaprogato.dao.*;

import java.sql.SQLException;
import java.util.*;

public class RegistroDiarioBO {

    // Selecionar todos dados agregados por usuário
    public List<Map<String, Object>> selecionarTudoBO() throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        QualidadeSonoDAO qualidadeSonoDAO = new QualidadeSonoDAO();
        PausasDAO pausasDAO = new PausasDAO();
        ObservacoesDAO observacoesDAO = new ObservacoesDAO();
        NivelEstresseDAO nivelEstresseDAO = new NivelEstresseDAO();
        HumorDAO humorDAO = new HumorDAO();
        ExerciciosFeitosDAO exerciciosFeitosDAO = new ExerciciosFeitosDAO();

        try {
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
                        .filter(s -> s.getUsuario_id() == usuario.getId())
                        .toList());
                registro.put("pausas", pausasList.stream()
                        .filter(p -> p.getUsuario_id() == usuario.getId())
                        .toList());
                registro.put("observacoes", obsList.stream()
                        .filter(o -> o.getUsuario_id() == usuario.getId())
                        .toList());
                registro.put("nivelEstresse", stressList.stream()
                        .filter(n -> n.getUsuario_id() == usuario.getId())
                        .toList());
                registro.put("humor", humorList.stream()
                        .filter(h -> h.getUsuario_id() == usuario.getId())
                        .toList());
                registro.put("exerciciosFeitos", exercList.stream()
                        .filter(e -> e.getUsuario_id() == usuario.getId())
                        .toList());

                resultado.add(registro);
            }

            return resultado;
        } finally {
            usuarioDAO.fecharConexao();
            qualidadeSonoDAO.fecharConexao();
            pausasDAO.fecharConexao();
            observacoesDAO.fecharConexao();
            nivelEstresseDAO.fecharConexao();
            humorDAO.fecharConexao();
            exerciciosFeitosDAO.fecharConexao();
        }
    }

    // Buscar por usuário ID
    public Map<String, Object> buscarPorUsuarioIdBO(int usuarioId) throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> todos = selecionarTudoBO();
        for (Map<String, Object> registro : todos) {
            if (((Integer) registro.get("id")) == usuarioId) {
                return registro;
            }
        }
        return null;
    }

    // Selecionar listas simples das entidades (para uso nos Resources REST)
    public List<QualidadeSono> selecionarQualidadeSono() throws SQLException, ClassNotFoundException {
        QualidadeSonoDAO dao = new QualidadeSonoDAO();
        try {
            return dao.selecionar();
        } finally {
            dao.fecharConexao();
        }
    }

    public List<Pausas> selecionarPausas() throws SQLException, ClassNotFoundException {
        PausasDAO dao = new PausasDAO();
        try {
            return dao.selecionar();
        } finally {
            dao.fecharConexao();
        }
    }

    public List<Observacoes> selecionarObservacoes() throws SQLException, ClassNotFoundException {
        ObservacoesDAO dao = new ObservacoesDAO();
        try {
            return dao.selecionar();
        } finally {
            dao.fecharConexao();
        }
    }

    public List<NivelEstresse> selecionarNivelEstresse() throws SQLException, ClassNotFoundException {
        NivelEstresseDAO dao = new NivelEstresseDAO();
        try {
            return dao.selecionar();
        } finally {
            dao.fecharConexao();
        }
    }

    public List<Humor> selecionarHumores() throws SQLException, ClassNotFoundException {
        HumorDAO dao = new HumorDAO();
        try {
            return dao.selecionar();
        } finally {
            dao.fecharConexao();
        }
    }

    public List<ExerciciosFeitos> selecionarExerciciosFeitos() throws SQLException, ClassNotFoundException {
        ExerciciosFeitosDAO dao = new ExerciciosFeitosDAO();
        try {
            return dao.selecionar();
        } finally {
            dao.fecharConexao();
        }
    }

    public List<Usuario> selecionarUsuarios() throws SQLException, ClassNotFoundException {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            return dao.selecionar();
        } finally {
            dao.fecharConexao();
        }
    }

    // Inserir usuário e/ou entidade
    public String inserirUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            return usuarioDAO.inserir(usuario);
        } finally {
            usuarioDAO.fecharConexao();
        }
    }

    public String inserirQualidadeSono(QualidadeSono qualidadeSono) throws SQLException, ClassNotFoundException {
        QualidadeSonoDAO dao = new QualidadeSonoDAO();
        try {
            return dao.inserir(qualidadeSono);
        } finally {
            dao.fecharConexao();
        }
    }

    public String inserirPausas(Pausas pausas) throws SQLException, ClassNotFoundException {
        PausasDAO dao = new PausasDAO();
        try {
            return dao.inserir(pausas);
        } finally {
            dao.fecharConexao();
        }
    }

    public String inserirObservacoes(Observacoes obs) throws SQLException, ClassNotFoundException {
        ObservacoesDAO dao = new ObservacoesDAO();
        try {
            return dao.inserir(obs);
        } finally {
            dao.fecharConexao();
        }
    }

    public String inserirNivelEstresse(NivelEstresse n) throws SQLException, ClassNotFoundException {
        NivelEstresseDAO dao = new NivelEstresseDAO();
        try {
            return dao.inserir(n);
        } finally {
            dao.fecharConexao();
        }
    }

    public String inserirHumor(Humor h) throws SQLException, ClassNotFoundException {
        HumorDAO dao = new HumorDAO();
        try {
            return dao.inserir(h);
        } finally {
            dao.fecharConexao();
        }
    }

    public String inserirExerciciosFeitos(ExerciciosFeitos e) throws SQLException, ClassNotFoundException {
        ExerciciosFeitosDAO dao = new ExerciciosFeitosDAO();
        try {
            return dao.inserir(e);
        } finally {
            dao.fecharConexao();
        }
    }

    // Atualizar entidades
    public String atualizarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            return usuarioDAO.atualizar(usuario);
        } finally {
            usuarioDAO.fecharConexao();
        }
    }

    public String atualizarQualidadeSono(QualidadeSono qualidadeSono) throws SQLException, ClassNotFoundException {
        QualidadeSonoDAO dao = new QualidadeSonoDAO();
        try {
            return dao.atualizar(qualidadeSono);
        } finally {
            dao.fecharConexao();
        }
    }

    public String atualizarPausas(Pausas pausas) throws SQLException, ClassNotFoundException {
        PausasDAO dao = new PausasDAO();
        try {
            return dao.atualizar(pausas);
        } finally {
            dao.fecharConexao();
        }
    }

    public String atualizarObservacoes(Observacoes obs) throws SQLException, ClassNotFoundException {
        ObservacoesDAO dao = new ObservacoesDAO();
        try {
            return dao.atualizar(obs);
        } finally {
            dao.fecharConexao();
        }
    }

    public String atualizarNivelEstresse(NivelEstresse n) throws SQLException, ClassNotFoundException {
        NivelEstresseDAO dao = new NivelEstresseDAO();
        try {
            return dao.atualizar(n);
        } finally {
            dao.fecharConexao();
        }
    }

    public String atualizarHumor(Humor h) throws SQLException, ClassNotFoundException {
        HumorDAO dao = new HumorDAO();
        try {
            return dao.atualizar(h);
        } finally {
            dao.fecharConexao();
        }
    }

    public String atualizarExerciciosFeitos(ExerciciosFeitos e) throws SQLException, ClassNotFoundException {
        ExerciciosFeitosDAO dao = new ExerciciosFeitosDAO();
        try {
            return dao.atualizar(e);
        } finally {
            dao.fecharConexao();
        }
    }

    // Deletar entidades
    public String deletarUsuario(int id) throws SQLException, ClassNotFoundException {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            return dao.deletar(id);
        } finally {
            dao.fecharConexao();
        }
    }

    public String deletarQualidadeSono(int id) throws SQLException, ClassNotFoundException {
        QualidadeSonoDAO dao = new QualidadeSonoDAO();
        try {
            return dao.deletar(id);
        } finally {
            dao.fecharConexao();
        }
    }

    public String deletarPausas(int id) throws SQLException, ClassNotFoundException {
        PausasDAO dao = new PausasDAO();
        try {
            return dao.deletar(id);
        } finally {
            dao.fecharConexao();
        }
    }

    public String deletarObservacoes(int id) throws SQLException, ClassNotFoundException {
        ObservacoesDAO dao = new ObservacoesDAO();
        try {
            return dao.deletar(id);
        } finally {
            dao.fecharConexao();
        }
    }

    public String deletarNivelEstresse(int id) throws SQLException, ClassNotFoundException {
        NivelEstresseDAO dao = new NivelEstresseDAO();
        try {
            return dao.deletar(id);
        } finally {
            dao.fecharConexao();
        }
    }

    public String deletarHumor(int id) throws SQLException, ClassNotFoundException {
        HumorDAO dao = new HumorDAO();
        try {
            return dao.deletar(id);
        } finally {
            dao.fecharConexao();
        }
    }

    public String deletarExerciciosFeitos(int id) throws SQLException, ClassNotFoundException {
        ExerciciosFeitosDAO dao = new ExerciciosFeitosDAO();
        try {
            return dao.deletar(id);
        } finally {
            dao.fecharConexao();
        }
    }
}
