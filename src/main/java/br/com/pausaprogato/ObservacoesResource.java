package br.com.pausaprogato;

import br.com.pausaprogato.beans.Observacoes;
import br.com.pausaprogato.bo.RegistroDiarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;

@Path("/observacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ObservacoesResource {

    @POST
    public Response inserir(Observacoes obj) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.inserirObservacoes(obj);
        return Response.status(Response.Status.CREATED)
                .entity("{\"message\": \"" + msg + "\"}")
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Observacoes obj) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        obj.setId(id);
        String msg = bo.atualizarObservacoes(obj);
        return Response.ok("{\"message\": \"" + msg + "\"}").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        String msg = bo.deletarObservacoes(id);
        return Response.ok("{\"message\": \"" + msg + "\"}").build();
    }

    @GET
    public Response listarTodos() throws ClassNotFoundException, SQLException {
        RegistroDiarioBO bo = new RegistroDiarioBO();
        return Response.ok(bo.selecionarObservacoes()).build();
    }
}

