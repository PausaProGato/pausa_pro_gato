package br.com.pausaprogato.main;

import br.com.pausaprogato.beans.*;
import br.com.pausaprogato.dao.*;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class TesteInserir {

    // Dialog input
    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }

    // Date input
    static LocalDate lerData(String mensagem){
        return LocalDate.parse(texto(mensagem));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = null;
        QualidadeSonoDAO qualidadeSonoDAO = null;
        PausasDAO pausasDAO = null;
        ObservacoesDAO observacoesDAO = null;
        NivelEstresseDAO nivelEstresseDAO = null;
        HumorDAO humorDAO = null;
        ExerciciosFeitosDAO exerciciosFeitosDAO = null;

        try {
            // Instancia objetos
            Usuario objUsuario = new Usuario();
            QualidadeSono objQualidadeSono = new QualidadeSono();
            Pausas objPausas = new Pausas();
            Observacoes objObservacoes = new Observacoes();
            NivelEstresse objNivelEstresse = new NivelEstresse();
            Humor objHumor = new Humor();
            ExerciciosFeitos objExerciciosFeitos = new ExerciciosFeitos();

            // Cadastro usuário e obtém o id gerado
            objUsuario.setNome(texto("Digite o nome"));
            objUsuario.setEmail(texto("Digite o email"));
            objUsuario.setDepartamento(texto("Digite o departamento"));
            objUsuario.setCargo(texto("Digite o cargo"));

            usuarioDAO = new UsuarioDAO();
            System.out.println(usuarioDAO.inserir(objUsuario));
            int usuarioIdGerado = objUsuario.getId(); // Pega o id do usuário cadastrado

            // Setar usuario_id em todas as entidades relacionadas
            objQualidadeSono.setUsuario_id(usuarioIdGerado);
            objPausas.setUsuario_id(usuarioIdGerado);
            objObservacoes.setUsuario_id(usuarioIdGerado);
            objNivelEstresse.setUsuario_id(usuarioIdGerado);
            objHumor.setUsuario_id(usuarioIdGerado);
            objExerciciosFeitos.setUsuario_id(usuarioIdGerado);

            // Cadastro das entidades relacionadas
            // Qualidade Sono
            objQualidadeSono.setQualidade(texto("Digite a qualidade do sono"));
            objQualidadeSono.setHoras_duracao(texto("Digite a duração do sono"));
            objQualidadeSono.setObservacoes(texto("Digite suas observações"));
            objQualidadeSono.setData(lerData("Digite a data (aaaa-MM-dd)"));

            qualidadeSonoDAO = new QualidadeSonoDAO();
            System.out.println(qualidadeSonoDAO.inserir(objQualidadeSono));

            // Pausas
            objPausas.setQuantidade_pausas(texto("Digite a quantidade de pausas"));
            objPausas.setDuracao_media(texto("Digite a duração média dessas pausas"));
            objPausas.setData(lerData("Digite a data (aaaa-MM-dd)"));

            pausasDAO = new PausasDAO();
            System.out.println(pausasDAO.inserir(objPausas));

            // Observações
            objObservacoes.setTexto(texto("Digite sua observação"));
            objObservacoes.setData(lerData("Digite a data (aaaa-MM-dd)"));

            observacoesDAO = new ObservacoesDAO();
            System.out.println(observacoesDAO.inserir(objObservacoes));

            // Nível Estresse
            objNivelEstresse.setNivel_estresse(texto("Digite o nível de estresse"));
            objNivelEstresse.setDescricao_estresse(texto("Digite uma descrição desse estresse"));
            objNivelEstresse.setData(lerData("Digite a data (aaaa-MM-dd)"));

            nivelEstresseDAO = new NivelEstresseDAO();
            System.out.println(nivelEstresseDAO.inserir(objNivelEstresse));

            // Humor
            objHumor.setNivel_humor(texto("Digite o nível do seu humor"));
            objHumor.setDescricao_humor(texto("Digite uma descrição sobre seu humor"));
            objHumor.setData(lerData("Digite a data (aaaa-MM-dd)"));

            humorDAO = new HumorDAO();
            System.out.println(humorDAO.inserir(objHumor));

            // Exercícios Feitos
            objExerciciosFeitos.setTipos(texto("Digite os tipos de exercícios feitos"));
            objExerciciosFeitos.setQuantidade_exercicio(texto("Digite a quantidade que cada exercício foi feito"));
            objExerciciosFeitos.setData(lerData("Digite a data (aaaa-MM-dd)"));

            exerciciosFeitosDAO = new ExerciciosFeitosDAO();
            System.out.println(exerciciosFeitosDAO.inserir(objExerciciosFeitos));

        } finally {
            // Fechar conexões
            if (usuarioDAO != null) { try { usuarioDAO.fecharConexao(); } catch (SQLException e) {} }
            if (qualidadeSonoDAO != null) { try { qualidadeSonoDAO.fecharConexao(); } catch (SQLException e) {} }
            if (pausasDAO != null) { try { pausasDAO.fecharConexao(); } catch (SQLException e) {} }
            if (observacoesDAO != null) { try { observacoesDAO.fecharConexao(); } catch (SQLException e) {} }
            if (nivelEstresseDAO != null) { try { nivelEstresseDAO.fecharConexao(); } catch (SQLException e) {} }
            if (humorDAO != null) { try { humorDAO.fecharConexao(); } catch (SQLException e) {} }
            if (exerciciosFeitosDAO != null) { try { exerciciosFeitosDAO.fecharConexao(); } catch (SQLException e) {} }
        }
    }
}
