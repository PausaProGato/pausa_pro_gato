package br.com.pausaprogato;

import br.com.pausaprogato.beans.*;
import br.com.pausaprogato.bo.RegistroDiarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("/registros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistroDiarioResource {

    @GET
    public Response listarTodos() throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        List<Map<String, Object>> lista = bo.selecionarTudoBO();
        return Response.ok(lista)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        Map<String, Object> registro = bo.buscarPorUsuarioIdBO(id);

        if (registro == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Usuário não encontrado\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
        return Response.ok(registro)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @POST
    public Response inserirRegistro(Map<String, Object> dados) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        try {
            Object usuarioObj = dados.get("usuario");
            if (!(usuarioObj instanceof Map)) throw new IllegalArgumentException("Usuário inválido");
            Map<String, Object> usuarioMap = (Map<String, Object>) usuarioObj;
            Usuario usuario = new Usuario();
            usuario.setNome((String) usuarioMap.get("nome"));
            usuario.setEmail((String) usuarioMap.get("email"));
            usuario.setDepartamento((String) usuarioMap.get("departamento"));
            usuario.setCargo((String) usuarioMap.get("cargo"));
            bo.inserirUsuario(usuario);
            int usuarioId = usuario.getId();

            Object sonoObj = dados.get("qualidadeSono");
            if (sonoObj instanceof Map) {
                Map<String, Object> sonoMap = (Map<String, Object>) sonoObj;
                QualidadeSono qualidadeSono = new QualidadeSono();
                qualidadeSono.setUsuario_id(usuarioId);
                qualidadeSono.setQualidade((String) sonoMap.get("qualidade"));
                qualidadeSono.setHoras_duracao((String) sonoMap.get("horas_duracao"));
                qualidadeSono.setObservacoes((String) sonoMap.get("observacoes"));
                qualidadeSono.setData(LocalDate.parse((String) sonoMap.get("data")));
                bo.inserirQualidadeSono(qualidadeSono);
            }

            Object pausasObj = dados.get("pausas");
            if (pausasObj instanceof Map) {
                Map<String, Object> pausasMap = (Map<String, Object>) pausasObj;
                Pausas pausas = new Pausas();
                pausas.setUsuario_id(usuarioId);
                pausas.setQuantidade_pausas((String) pausasMap.get("quantidade_pausas"));
                pausas.setDuracao_media((String) pausasMap.get("duracao_media"));
                pausas.setData(LocalDate.parse((String) pausasMap.get("data")));
                bo.inserirPausas(pausas);
            }

            Object observacaoObj = dados.get("observacoes");
            if (observacaoObj instanceof Map) {
                Map<String, Object> observacaoMap = (Map<String, Object>) observacaoObj;
                Observacoes observacoes = new Observacoes();
                observacoes.setUsuario_id(usuarioId);
                observacoes.setTexto((String) observacaoMap.get("texto"));
                observacoes.setData(LocalDate.parse((String) observacaoMap.get("data")));
                bo.inserirObservacoes(observacoes);
            }

            Object estresseObj = dados.get("nivelEstresse");
            if (estresseObj instanceof Map) {
                Map<String, Object> estresseMap = (Map<String, Object>) estresseObj;
                NivelEstresse nivelEstresse = new NivelEstresse();
                nivelEstresse.setUsuario_id(usuarioId);
                nivelEstresse.setNivel_estresse((String) estresseMap.get("nivel_estresse"));
                nivelEstresse.setDescricao_estresse((String) estresseMap.get("descricao_estresse"));
                nivelEstresse.setData(LocalDate.parse((String) estresseMap.get("data")));
                bo.inserirNivelEstresse(nivelEstresse);
            }

            Object humorObj = dados.get("humor");
            if (humorObj instanceof Map) {
                Map<String, Object> humorMap = (Map<String, Object>) humorObj;
                Humor humor = new Humor();
                humor.setUsuario_id(usuarioId);
                humor.setNivel_humor((String) humorMap.get("nivel_humor"));
                humor.setDescricao_humor((String) humorMap.get("descricao_humor"));
                humor.setData(LocalDate.parse((String) humorMap.get("data")));
                bo.inserirHumor(humor);
            }

            Object exerciciosObj = dados.get("exerciciosFeitos");
            if (exerciciosObj instanceof Map) {
                Map<String, Object> exerciciosMap = (Map<String, Object>) exerciciosObj;
                ExerciciosFeitos exerciciosFeitos = new ExerciciosFeitos();
                exerciciosFeitos.setUsuario_id(usuarioId);
                exerciciosFeitos.setTipos((String) exerciciosMap.get("tipos"));
                exerciciosFeitos.setQuantidade_exercicio((String) exerciciosMap.get("quantidade_exercicio"));
                exerciciosFeitos.setData(LocalDate.parse((String) exerciciosMap.get("data")));
                bo.inserirExerciciosFeitos(exerciciosFeitos);
            }

            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\": \"Registro completo criado com sucesso\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizarRegistro(@PathParam("id") int id, Map<String, Object> dados) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        try {
            // Usuário
            Object usuarioObj = dados.get("usuario");
            if (!(usuarioObj instanceof Map)) throw new IllegalArgumentException("Usuário inválido");
            Map<String, Object> usuarioMap = (Map<String, Object>) usuarioObj;
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome((String) usuarioMap.get("nome"));
            usuario.setEmail((String) usuarioMap.get("email"));
            usuario.setDepartamento((String) usuarioMap.get("departamento"));
            usuario.setCargo((String) usuarioMap.get("cargo"));
            bo.atualizarUsuario(usuario);

            // Qualidade Sono
            Object sonoObj = dados.get("qualidadeSono");
            if (sonoObj instanceof Map) {
                Map<String, Object> sonoMap = (Map<String, Object>) sonoObj;
                QualidadeSono qualidadeSono = new QualidadeSono();
                qualidadeSono.setUsuario_id(id);
                qualidadeSono.setQualidade((String) sonoMap.get("qualidade"));
                qualidadeSono.setHoras_duracao((String) sonoMap.get("horas_duracao"));
                qualidadeSono.setObservacoes((String) sonoMap.get("observacoes"));
                qualidadeSono.setData(LocalDate.parse((String) sonoMap.get("data")));

                List<QualidadeSono> sonoList = bo.selecionarQualidadeSono();
                Optional<QualidadeSono> sonoExistente = sonoList.stream()
                        .filter(s -> s.getUsuario_id() == id).findFirst();
                if (sonoExistente.isPresent()) {
                    qualidadeSono.setId(sonoExistente.get().getId());
                    bo.atualizarQualidadeSono(qualidadeSono);
                } else {
                    bo.inserirQualidadeSono(qualidadeSono);
                }
            }

            // Pausas
            Object pausasObj = dados.get("pausas");
            if (pausasObj instanceof Map) {
                Map<String, Object> pausasMap = (Map<String, Object>) pausasObj;
                Pausas pausas = new Pausas();
                pausas.setUsuario_id(id);
                pausas.setQuantidade_pausas((String) pausasMap.get("quantidade_pausas"));
                pausas.setDuracao_media((String) pausasMap.get("duracao_media"));
                pausas.setData(LocalDate.parse((String) pausasMap.get("data")));

                List<Pausas> pausasList = bo.selecionarPausas();
                Optional<Pausas> pausaExistente = pausasList.stream()
                        .filter(p -> p.getUsuario_id() == id).findFirst();
                if (pausaExistente.isPresent()) {
                    pausas.setId(pausaExistente.get().getId());
                    bo.atualizarPausas(pausas);
                } else {
                    bo.inserirPausas(pausas);
                }
            }

            // Observações
            Object observacaoObj = dados.get("observacoes");
            if (observacaoObj instanceof Map) {
                Map<String, Object> observacaoMap = (Map<String, Object>) observacaoObj;
                Observacoes observacoes = new Observacoes();
                observacoes.setUsuario_id(id);
                observacoes.setTexto((String) observacaoMap.get("texto"));
                observacoes.setData(LocalDate.parse((String) observacaoMap.get("data")));

                List<Observacoes> obsList = bo.selecionarObservacoes();
                Optional<Observacoes> observacaoExistente = obsList.stream()
                        .filter(o -> o.getUsuario_id() == id).findFirst();
                if (observacaoExistente.isPresent()) {
                    observacoes.setId(observacaoExistente.get().getId());
                    bo.atualizarObservacoes(observacoes);
                } else {
                    bo.inserirObservacoes(observacoes);
                }
            }

            // Nivel Estresse
            Object estresseObj = dados.get("nivelEstresse");
            if (estresseObj instanceof Map) {
                Map<String, Object> estresseMap = (Map<String, Object>) estresseObj;
                NivelEstresse nivelEstresse = new NivelEstresse();
                nivelEstresse.setUsuario_id(id);
                nivelEstresse.setNivel_estresse((String) estresseMap.get("nivel_estresse"));
                nivelEstresse.setDescricao_estresse((String) estresseMap.get("descricao_estresse"));
                nivelEstresse.setData(LocalDate.parse((String) estresseMap.get("data")));

                List<NivelEstresse> stressList = bo.selecionarNivelEstresse();
                Optional<NivelEstresse> estresseExistente = stressList.stream()
                        .filter(n -> n.getUsuario_id() == id).findFirst();
                if (estresseExistente.isPresent()) {
                    nivelEstresse.setId(estresseExistente.get().getId());
                    bo.atualizarNivelEstresse(nivelEstresse);
                } else {
                    bo.inserirNivelEstresse(nivelEstresse);
                }
            }

            // Humor
            Object humorObj = dados.get("humor");
            if (humorObj instanceof Map) {
                Map<String, Object> humorMap = (Map<String, Object>) humorObj;
                Humor humor = new Humor();
                humor.setUsuario_id(id);
                humor.setNivel_humor((String) humorMap.get("nivel_humor"));
                humor.setDescricao_humor((String) humorMap.get("descricao_humor"));
                humor.setData(LocalDate.parse((String) humorMap.get("data")));

                List<Humor> humorList = bo.selecionarHumores();
                Optional<Humor> humorExistente = humorList.stream()
                        .filter(h -> h.getUsuario_id() == id).findFirst();
                if (humorExistente.isPresent()) {
                    humor.setId(humorExistente.get().getId());
                    bo.atualizarHumor(humor);
                } else {
                    bo.inserirHumor(humor);
                }
            }

            // Exercícios Feitos
            Object exerciciosObj = dados.get("exerciciosFeitos");
            if (exerciciosObj instanceof Map) {
                Map<String, Object> exerciciosMap = (Map<String, Object>) exerciciosObj;
                ExerciciosFeitos exerciciosFeitos = new ExerciciosFeitos();
                exerciciosFeitos.setUsuario_id(id);
                exerciciosFeitos.setTipos((String) exerciciosMap.get("tipos"));
                exerciciosFeitos.setQuantidade_exercicio((String) exerciciosMap.get("quantidade_exercicio"));
                exerciciosFeitos.setData(LocalDate.parse((String) exerciciosMap.get("data")));

                List<ExerciciosFeitos> exercList = bo.selecionarExerciciosFeitos();
                Optional<ExerciciosFeitos> exercExistente = exercList.stream()
                        .filter(e -> e.getUsuario_id() == id).findFirst();
                if (exercExistente.isPresent()) {
                    exerciciosFeitos.setId(exercExistente.get().getId());
                    bo.atualizarExerciciosFeitos(exerciciosFeitos);
                } else {
                    bo.inserirExerciciosFeitos(exerciciosFeitos);
                }
            }

            return Response.ok("{\"message\": \"Registro completo atualizado com sucesso\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarRegistro(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        try {
            bo.deletarRegistroCompleto(id);
            return Response.ok("{\"message\": \"Registro completo deletado com sucesso\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    @OPTIONS
    @Path("{path:.*}")
    public Response options() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }
}
