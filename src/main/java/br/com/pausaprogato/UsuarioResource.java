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

    // GET - Listar todos os usuários (apenas dados puros)
    @GET
    public Response listarUsuarios() throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        List<Usuario> lista = bo.selecionarUsuarios();
        return Response.ok(lista)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    // GET - Buscar usuário por ID (apenas dados puros)
    @GET
    @Path("/{id}")
    public Response buscarUsuarioPorId(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        Usuario usuario = bo.selecionarUsuarios()
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"Usuário não encontrado\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
        return Response.ok(usuario)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    // POST - Criar usuário
    @POST
    public Response inserirUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.inserirUsuario(usuario);

        return Response.status(Response.Status.CREATED)
                .entity("{\"message\": \"" + msg + "\"}")
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    // PUT - Atualizar usuário
    @PUT
    @Path("/{id}")
    public Response atualizarUsuario(@PathParam("id") int id, Usuario usuario) throws ClassNotFoundException, SQLException {
        usuario.setId(id);
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.atualizarUsuario(usuario);

        return Response.ok("{\"message\": \"" + msg + "\"}")
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    // DELETE - Remover usuário
    @DELETE
    @Path("/{id}")
    public Response deletarUsuario(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.deletarUsuario(id);

        return Response.ok("{\"message\": \"" + msg + "\"}")
                .header("Access-Control-Allow-Origin", "*")
                .build();
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
