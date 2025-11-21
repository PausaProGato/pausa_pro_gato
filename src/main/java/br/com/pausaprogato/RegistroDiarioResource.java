package br.com.pausaprogato;

import br.com.pausaprogato.beans.Usuario;
import br.com.pausaprogato.bo.RegistroDiarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Path("/registros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistroDiarioResource {

    // GET - Listar todos os registros agregados por usuário
    @GET
    public Response listarTodos() throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        List<Map<String, Object>> lista = bo.selecionarTudoBO();

        Response.ResponseBuilder response = Response.ok(lista);
        response.header("Access-Control-Allow-Origin", "*");
        return response.build();
    }

    // GET - Buscar por usuário id (todos os registros daquele usuário)
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();

        try {
            Map<String, Object> registro = bo.buscarPorUsuarioIdBO(id);
            if (registro == null) {
                throw new NotFoundException("Usuário não encontrado");
            }
            Response.ResponseBuilder response = Response.ok(registro);
            response.header("Access-Control-Allow-Origin", "*");
            return response.build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // POST - Criar novo usuário
    @POST
    public Response inserirUsuario(Map<String, Object> dados) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();

        try {
            Usuario usuario = new Usuario();
            usuario.setNome((String) dados.get("nome"));
            usuario.setEmail((String) dados.get("email"));
            usuario.setDepartamento((String) dados.get("departamento"));
            usuario.setCargo((String) dados.get("cargo"));
            bo.inserirUsuario(usuario);

            Response.ResponseBuilder response = Response.status(Response.Status.CREATED)
                    .entity("{\"message\": \"Usuário criado com sucesso\"}");
            response.header("Access-Control-Allow-Origin", "*");
            return response.build();

        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // PUT - Atualizar usuário
    @PUT
    @Path("/{id}")
    public Response atualizarUsuario(@PathParam("id") int id, Map<String, Object> dados) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();

        try {
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome((String) dados.get("nome"));
            usuario.setEmail((String) dados.get("email"));
            usuario.setDepartamento((String) dados.get("departamento"));
            usuario.setCargo((String) dados.get("cargo"));
            bo.atualizarUsuario(usuario);

            Response.ResponseBuilder response = Response.ok("{\"message\": \"Usuário atualizado com sucesso\"}");
            response.header("Access-Control-Allow-Origin", "*");
            return response.build();

        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // DELETE - Remover usuário
    @DELETE
    @Path("/{id}")
    public Response deletarUsuario(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();

        try {
            bo.deletarUsuario(id);

            Response.ResponseBuilder response = Response.ok("{\"message\": \"Usuário deletado com sucesso\"}");
            response.header("Access-Control-Allow-Origin", "*");
            return response.build();

        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // OPTIONS - CORS support
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

