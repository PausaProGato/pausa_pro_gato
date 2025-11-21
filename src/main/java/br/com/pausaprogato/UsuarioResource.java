package br.com.pausaprogato;

import br.com.pausaprogato.beans.Usuario;
import br.com.pausaprogato.bo.RegistroDiarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    // GET - Listar todos os usuários usando o BO
    @GET
    public Response listarUsuarios() throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        List<Usuario> lista = bo.selecionarUsuarios();

        Response.ResponseBuilder response = Response.ok(lista);
        response.header("Access-Control-Allow-Origin", "*");
        return response.build();
    }

    // GET - Buscar usuário por ID usando o BO
    @GET
    @Path("/{id}")
    public Response buscarUsuarioPorId(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        List<Usuario> lista = bo.selecionarUsuarios();

        Usuario usuario = lista.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Usuário não encontrado\"}")
                    .build();
        }

        Response.ResponseBuilder response = Response.ok(usuario);
        response.header("Access-Control-Allow-Origin", "*");
        return response.build();
    }

    // POST - Criar usuário usando o BO
    @POST
    public Response inserirUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.inserirUsuario(usuario);

        Response.ResponseBuilder response = Response.status(Response.Status.CREATED)
                .entity("{\"message\": \"" + msg + "\"}");
        response.header("Access-Control-Allow-Origin", "*");
        return response.build();
    }

    // PUT - Atualizar usuário usando o BO
    @PUT
    @Path("/{id}")
    public Response atualizarUsuario(@PathParam("id") int id, Usuario usuario) throws ClassNotFoundException, SQLException {
        usuario.setId(id);
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.atualizarUsuario(usuario);

        Response.ResponseBuilder response = Response.ok("{\"message\": \"" + msg + "\"}");
        response.header("Access-Control-Allow-Origin", "*");
        return response.build();
    }

    // DELETE - Remover usuário usando o BO
    @DELETE
    @Path("/{id}")
    public Response deletarUsuario(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.deletarUsuario(id);

        Response.ResponseBuilder response = Response.ok("{\"message\": \"" + msg + "\"}");
        response.header("Access-Control-Allow-Origin", "*");
        return response.build();
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
